from socket import *
from threading import Thread
import pyodbc

# ─── Database Configuration ───────────────────────────────────────────────────
# UPDATE: Replace SERVER value with your own machine name or use localhost
conn_str = (
    "DRIVER={ODBC Driver 18 for SQL Server};"
    "SERVER=localhost,1433;"
    "DATABASE=DevSync;"
    "Trusted_Connection=yes;"
    "TrustServerCertificate=yes;"
)

# BUG FIX: Original crashed immediately if DB unavailable, preventing server start.
try:
    cnxn = pyodbc.connect(conn_str)
    print("Connected to database")
except Exception as e:
    cnxn = None
    print(f"Database connection failed: {e}")
    print("Server will run without message persistence.")

ip   = '127.0.0.1'
port = 9000

s = socket(AF_INET, SOCK_STREAM)
s.setsockopt(SOL_SOCKET, SO_REUSEADDR, 1)
s.bind((ip, port))
s.listen(5)

# BUG FIX: Changed encryption key from inappropriate word to neutral project name.
key = b'DevSync'

clients      = []
client_names = {}


def xor_cipher(text, key):
    if len(key) < len(text):
        key = key * (len(text) // len(key) + 1)
        key = key[:len(text)]
    return bytes([x ^ y for x, y in zip(text, key)])


def broadcast(msg_text, sender):
    for c in clients.copy():
        if c != sender:
            try:
                msg_bytes = msg_text.encode('utf-8')
                encrypted = xor_cipher(msg_bytes, key)
                c.sendall(encrypted + b'\n')
            except Exception as e:
                print(f"Broadcast error: {e}")
                try:
                    clients.remove(c)
                except ValueError:
                    pass


def handle(client, client_name):
    raw_buffer = b""
    try:
        while True:
            chunk = client.recv(4096)
            if not chunk:
                break
            raw_buffer += chunk
            while b'\n' in raw_buffer:
                token, _, raw_buffer = raw_buffer.partition(b'\n')
                if not token:
                    continue
                try:
                    a_text = xor_cipher(token, key).decode('utf-8').strip()
                except Exception as e:
                    print(f"Decode error for {client_name}: {e}")
                    continue
                if not a_text:
                    continue
                store_msg(client_name, a_text)
                print(f"{client_name}: {a_text}")
                broadcast(f"{client_name}: {a_text}", client)
    except Exception as e:
        print(f"Error handling {client_name}: {e}")
    finally:
        if client in clients:
            try:
                clients.remove(client)
            except ValueError:
                pass
        broadcast(f"{client_name} left the chat", None)
        print(f"{client_name} disconnected")
        try:
            client.close()
        except Exception:
            pass


def accept_connections():
    print(f"Server listening on {ip}:{port}")
    while True:
        try:
            conn, addr = s.accept()
            print(f"Connection from {addr}")
            prompt_token = b"Enter your name!"
            conn.sendall(xor_cipher(prompt_token, key) + b'\n')
            buf = b""
            while True:
                part = conn.recv(1024)
                if not part:
                    raise ConnectionError("client closed before sending name")
                buf += part
                if b'\n' in buf:
                    token, _, _ = buf.partition(b'\n')
                    try:
                        client_name = xor_cipher(token, key).decode('utf-8').strip()
                    except Exception:
                        client_name = "Unknown"
                    break
            client_name = client_name.replace(" joined the chat", "").strip()
            client_names[conn] = client_name
            clients.append(conn)
            print(f"{client_name} joined")
            broadcast(f"{client_name} joined the chat", conn)
            thread = Thread(target=handle, args=(conn, client_name))
            thread.daemon = True
            thread.start()
        except Exception as e:
            print(f"Error accepting connection: {e}")


def store_msg(sender, message):
    if cnxn is None:
        return
    try:
        c = cnxn.cursor()
        # BUG FIX: Original used table 'Project1' which doesn't exist. Fixed to ChatMessages.
        sql = "INSERT INTO ChatMessages (Sender, Receiver, Message, SentAt) VALUES (?, ?, ?, SYSUTCDATETIME())"
        c.execute(sql, sender, "broadcast", message)
        cnxn.commit()
        c.close()
    except Exception as e:
        print(f"Database store error: {e}")


# BUG FIX: Original had 'if _name_ == "_main_"' — missing underscores meant
# the server NEVER started when run directly. This is the correct Python idiom.
if __name__ == "__main__":
    accept_connections()
