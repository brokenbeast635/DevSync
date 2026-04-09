# DevSync
Project Management Portal with Role-Based Dashboards, Chatbot, and Real-time Messaging
# DevSync — Project Management Portal

A Java Swing desktop application for managing software development teams.  
Supports **17 role-based dashboards**, real-time encrypted chat, an AI chatbot, and full SQL Server integration.

---

## Features

- Login & Signup with role-based access (17 roles)
- 17 fully implemented role dashboards (Admin, Client, PM, all engineering roles)
- Real-time team chat via encrypted TCP socket server
- AI chatbot powered by OpenAI (ChatGPT)
- SQL Server database for all persistent data
- Password management from every dashboard
- Messaging system between users
- Task creation, assignment, and tracking
- Project management and progress tracking

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17+ |
| GUI | Java Swing |
| Database | Microsoft SQL Server |
| JDBC Driver | mssql-jdbc 13.2.1 |
| HTTP Client | OkHttp 4.9.3 |
| JSON | Gson 2.10.1 |
| Chat Server | Python 3 (socket + pyodbc) |
| IDE | IntelliJ IDEA |

---

## Setup

### 1. Clone the repo
```bash
git clone https://github.com/<your-username>/DevSync.git
cd DevSync
```

### 2. Set up the database
1. Open SQL Server Management Studio (SSMS)
2. Create a database called `DevSync`
3. Run `database/schema.sql`

### 3. Configure DB connection
Edit `src/Database/DBconn.java` — find this line and update with your server name:
```java
"SERVER=localhost,1433;databaseName=DevSync;..."
```

### 4. Add libraries to IntelliJ
Go to **File → Project Structure → Libraries → +** and add every `.jar` from `src/Libraries/`.

### 5. Set your OpenAI API key
The chatbot reads from an environment variable:
```bash
# Windows
set OPENAI_API_KEY=sk-your-key-here

# Mac/Linux
export OPENAI_API_KEY=sk-your-key-here
```
Or edit `src/GUI/components/BaseDashboard.java` and replace the placeholder string.

### 6. Start the Python chat server (optional)
```bash
pip install pyodbc
# Edit pythonserver/server.py — update SERVER= to your machine name
python pythonserver/server.py
```

### 7. Run the app
Run `src/Main.java` from IntelliJ IDEA.

---

## Project Structure

```
DevSync/
├── src/
│   ├── Main.java
│   ├── Chatbot/          # AI Chatbot (OpenAI)
│   ├── core/             # Portal, Activity Logger, Communication
│   ├── Database/         # DBconn (all DB logic), UserDAO
│   ├── GUI/
│   │   ├── components/   # LoginFrame, SignupFrame, BaseDashboard
│   │   ├── Dashboards/   # 17 role dashboards
│   │   └── themes/       # ModernTheme colors & fonts
│   ├── Networking/       # TCP chat client
│   ├── project/          # Project, Task, Message models
│   └── user/             # 17 user role classes
├── pythonserver/
│   └── server.py         # Encrypted Python chat server
├── database/
│   └── schema.sql        # Full SQL Server schema + seed data
└── README.md
```

---

## Default Login

After running `schema.sql`, a default admin is seeded:

| Field | Value |
|---|---|
| Username | `admin` |
| Password | `admin123` |
| Role ID | `1` |

> Change this immediately after first login.

---

## Role ID Reference

| ID | Role |
|---|---|
| 1 | Admin |
| 2 | UI/UX Designer |
| 3 | Backend Engineer |
| 4 | Frontend Engineer |
| 5 | Client |
| 6 | Coder |
| 7 | Fullstack Engineer |
| 8 | QA Engineer |
| 9 | Data Engineer |
| 10 | Data Scientist |
| 11 | ML Engineer |
| 12 | Mobile Developer |
| 13 | Automation Test Engineer |
| 14 | Project Manager |
| 15 | Site Reliability Engineer |
| 16 | Software Architect |
| 17 | Product Manager |

---

## License

Semester project — free to use for educational purposes.
