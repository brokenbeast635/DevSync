-- ============================================================
--  DevSync Database Schema
--  Database: DevSync (MS SQL Server)
--  Run this script in SSMS after creating the database.
-- ============================================================

USE DevSync;
GO

-- ────────────────────────────────────────────────────────────
-- CORE USER TABLES
-- ────────────────────────────────────────────────────────────

CREATE TABLE USERS (
    USER_ID     VARCHAR(20)  PRIMARY KEY,
    USERNAME    VARCHAR(50)  NOT NULL UNIQUE,
    PASSWORD    VARCHAR(100) NOT NULL,
    EMAIL       VARCHAR(100) NOT NULL UNIQUE,
    ROLE        VARCHAR(50)  NOT NULL,
    CREATED_AT  DATETIME     DEFAULT GETDATE()
);

-- Legacy login table (used in some queries)
CREATE TABLE LOGIN (
    UserId      VARCHAR(20)  PRIMARY KEY,
    Username    VARCHAR(50)  NOT NULL,
    Password    VARCHAR(100) NOT NULL,
    Email       VARCHAR(100),
    Role        VARCHAR(50)
);

CREATE TABLE ADMIN (
    ID          VARCHAR(20)  PRIMARY KEY,
    USERNAME    VARCHAR(50)  NOT NULL,
    PASSWORD    VARCHAR(100) NOT NULL,
    EMAIL       VARCHAR(100)
);

-- ────────────────────────────────────────────────────────────
-- PROJECTS & TASKS
-- ────────────────────────────────────────────────────────────

CREATE TABLE PROJECTS (
    PROJECT_ID          VARCHAR(20)  PRIMARY KEY,
    PROJECT_NAME        VARCHAR(100) NOT NULL,
    DESCRIPTION         TEXT,
    STATUS              VARCHAR(30)  DEFAULT 'Active',
    MANAGER             VARCHAR(50),
    project_client      VARCHAR(50),
    project_budget      DECIMAL(18,2),
    project_description TEXT,
    project_status      VARCHAR(30)  DEFAULT 'Active',
    project_manager_id  VARCHAR(20),
    CREATED_AT          DATETIME     DEFAULT GETDATE()
);

CREATE TABLE PROJECT_TEAM (
    ID          INT          IDENTITY(1,1) PRIMARY KEY,
    PROJECT_ID  VARCHAR(20)  NOT NULL,
    USER_ID     VARCHAR(20)  NOT NULL,
    ROLE        VARCHAR(50),
    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECTS(PROJECT_ID),
    FOREIGN KEY (USER_ID)    REFERENCES USERS(USER_ID)
);

CREATE TABLE TASKS (
    TASK_ID         INT          IDENTITY(1,1) PRIMARY KEY,
    PROJECT_ID      VARCHAR(20)  NOT NULL,
    TASK_NAME       VARCHAR(100),
    TASK_DESCRIPTION TEXT,
    STATUS          VARCHAR(30)  DEFAULT 'Pending',
    ASSIGNED_TO     VARCHAR(50),
    CREATED_AT      DATETIME     DEFAULT GETDATE(),
    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECTS(PROJECT_ID)
);

-- ────────────────────────────────────────────────────────────
-- MESSAGING & COMMUNICATION
-- ────────────────────────────────────────────────────────────

CREATE TABLE MESSAGES (
    MESSAGE_ID  INT          IDENTITY(1,1) PRIMARY KEY,
    SENDER      VARCHAR(50)  NOT NULL,
    RECEIVER    VARCHAR(50)  NOT NULL,
    CONTENT     TEXT,
    SENT_AT     DATETIME     DEFAULT GETDATE()
);

-- Used by Java DBconn for chat
CREATE TABLE ChatMessages (
    Id          INT          IDENTITY(1,1) PRIMARY KEY,
    Sender      VARCHAR(50)  NOT NULL,
    Receiver    VARCHAR(50)  NOT NULL,
    Message     TEXT,
    SentAt      DATETIME2    DEFAULT SYSUTCDATETIME()
);

CREATE TABLE ANNOUNCEMENTS (
    ID          INT          IDENTITY(1,1) PRIMARY KEY,
    TITLE       VARCHAR(200),
    CONTENT     TEXT,
    POSTED_BY   VARCHAR(50),
    POSTED_AT   DATETIME     DEFAULT GETDATE()
);

-- ────────────────────────────────────────────────────────────
-- LOGGING
-- ────────────────────────────────────────────────────────────

CREATE TABLE SYSTEM_LOGS (
    LOG_ID      INT          IDENTITY(1,1) PRIMARY KEY,
    SYSTEM_LOG  TEXT,
    LOG_MESSAGE TEXT,
    CREATED_AT  DATETIME     DEFAULT GETDATE()
);

CREATE TABLE ERROR_LOGS (
    LOG_ID      INT          IDENTITY(1,1) PRIMARY KEY,
    ERROR_LOG   TEXT,
    CREATED_AT  DATETIME     DEFAULT GETDATE()
);

CREATE TABLE ACTIVITY_LOGS (
    LOG_ID       INT          IDENTITY(1,1) PRIMARY KEY,
    ACTIVITY_LOG TEXT,
    CREATED_AT   DATETIME     DEFAULT GETDATE()
);

CREATE TABLE REPORTS (
    REPORT_ID   INT          IDENTITY(1,1) PRIMARY KEY,
    REPORT_TYPE VARCHAR(50),
    CONTENT     TEXT,
    CREATED_BY  VARCHAR(50),
    CREATED_AT  DATETIME     DEFAULT GETDATE()
);

-- ────────────────────────────────────────────────────────────
-- ROLE-SPECIFIC TABLES
-- ────────────────────────────────────────────────────────────

-- Backend Engineer
CREATE TABLE BACKEND_TASKS (
    TASK_ID          INT          IDENTITY(1,1) PRIMARY KEY,
    BE_USERNAME      VARCHAR(50)  NOT NULL,
    TASK_DESCRIPTION TEXT,
    TASK_TYPE        VARCHAR(50),
    CREATED_AT       DATETIME     DEFAULT GETDATE()
);

CREATE TABLE BACKEND_TECHNOLOGIES (
    TECH_ID         INT          IDENTITY(1,1) PRIMARY KEY,
    BE_USERNAME     VARCHAR(50)  NOT NULL,
    TECHNOLOGY_NAME VARCHAR(100),
    ADDED_AT        DATETIME     DEFAULT GETDATE()
);

-- Frontend Engineer
CREATE TABLE FRONTEND_TASKS (
    TASK_ID          INT          IDENTITY(1,1) PRIMARY KEY,
    FE_USERNAME      VARCHAR(50)  NOT NULL,
    TASK_DESCRIPTION TEXT,
    TASK_TYPE        VARCHAR(50),
    CREATED_AT       DATETIME     DEFAULT GETDATE()
);

CREATE TABLE FRONTEND_FRAMEWORKS (
    FRAMEWORK_ID    INT          IDENTITY(1,1) PRIMARY KEY,
    FE_USERNAME     VARCHAR(50)  NOT NULL,
    FRAMEWORK_NAME  VARCHAR(100),
    ADDED_AT        DATETIME     DEFAULT GETDATE()
);

-- Fullstack Engineer
CREATE TABLE FULLSTACK_TASKS (
    TASK_ID          INT          IDENTITY(1,1) PRIMARY KEY,
    FSE_USERNAME     VARCHAR(50),
    FSE_ID           VARCHAR(20),
    TASK_DESCRIPTION TEXT,
    TASK_TYPE        VARCHAR(50),
    CREATED_AT       DATETIME     DEFAULT GETDATE()
);

CREATE TABLE FULLSTACK_TECHNOLOGIES (
    TECH_ID         INT          IDENTITY(1,1) PRIMARY KEY,
    FSE_USERNAME    VARCHAR(50),
    TECH_NAME       VARCHAR(100),
    ADDED_AT        DATETIME     DEFAULT GETDATE()
);

-- Mobile Developer
CREATE TABLE MOBILE_TASKS (
    TASK_ID             INT          IDENTITY(1,1) PRIMARY KEY,
    DEVELOPER_USERNAME  VARCHAR(50),
    TASK_DESCRIPTION    TEXT,
    TASK_STATUS         VARCHAR(30)  DEFAULT 'Pending',
    CREATED_AT          DATETIME     DEFAULT GETDATE()
);

CREATE TABLE MOBILE_PLATFORMS (
    PLATFORM_ID         INT          IDENTITY(1,1) PRIMARY KEY,
    DEVELOPER_USERNAME  VARCHAR(50),
    PLATFORM_NAME       VARCHAR(100),
    CREATED_AT          DATETIME     DEFAULT GETDATE()
);

CREATE TABLE MOBILE_FEATURES (
    FEATURE_ID          INT          IDENTITY(1,1) PRIMARY KEY,
    DEVELOPER_USERNAME  VARCHAR(50),
    FEATURE_NAME        VARCHAR(100),
    CREATED_AT          DATETIME     DEFAULT GETDATE()
);

-- QA Engineer
CREATE TABLE QA_TASKS (
    TASK_ID         INT          IDENTITY(1,1) PRIMARY KEY,
    QA_ENGINEER_ID  VARCHAR(50),
    PROJECT_ID      VARCHAR(20),
    TASK_DESCRIPTION TEXT,
    TASK_STATUS     VARCHAR(30)  DEFAULT 'Pending',
    CREATED_AT      DATETIME     DEFAULT GETDATE()
);

CREATE TABLE TEST_RESULTS (
    RESULT_ID       INT          IDENTITY(1,1) PRIMARY KEY,
    QA_ENGINEER_ID  VARCHAR(50),
    PROJECT_ID      VARCHAR(20),
    RESULT_DETAILS  TEXT,
    CREATED_AT      DATETIME     DEFAULT GETDATE()
);

CREATE TABLE TEST_SCRIPTS (
    SCRIPT_ID       INT          IDENTITY(1,1) PRIMARY KEY,
    QA_ENGINEER_ID  VARCHAR(50),
    SCRIPT_DETAILS  TEXT,
    CREATED_AT      DATETIME     DEFAULT GETDATE()
);

-- Automation Test Engineer
CREATE TABLE ATE_TASKS (
    TASK_ID         INT          IDENTITY(1,1) PRIMARY KEY,
    ATE_ID          VARCHAR(50),
    ENGINEER_ID     VARCHAR(50),
    TEST_RESULTS    TEXT,
    SCRIPT_DETAILS  TEXT,
    TASK_STATUS     VARCHAR(30)  DEFAULT 'Pending',
    CREATED_AT      DATETIME     DEFAULT GETDATE()
);

CREATE TABLE ATE_TOOLS (
    TOOL_ID         INT          IDENTITY(1,1) PRIMARY KEY,
    ATE_ID          VARCHAR(50),
    TOOL_NAME       VARCHAR(100),
    CREATED_AT      DATETIME     DEFAULT GETDATE()
);

CREATE TABLE AUTOMATION_TOOLS (
    TOOL_ID             INT          IDENTITY(1,1) PRIMARY KEY,
    ENGINEER_ID         VARCHAR(50),
    TOOL_NAME           VARCHAR(100),
    CREATED_AT          DATETIME     DEFAULT GETDATE()
);

-- UI/UX Designer
CREATE TABLE UIUX_TASKS (
    TASK_ID          INT          IDENTITY(1,1) PRIMARY KEY,
    DESIGNER_ID      VARCHAR(50),
    UserID           VARCHAR(50),
    TaskDescription  TEXT,
    TASK_STATUS      VARCHAR(30)  DEFAULT 'Pending',
    CREATED_AT       DATETIME     DEFAULT GETDATE()
);

CREATE TABLE UIUX_WIREFRAMES (
    WIREFRAME_ID    INT          IDENTITY(1,1) PRIMARY KEY,
    DESIGNER_ID     VARCHAR(50),
    DETAILS         TEXT,
    CREATED_AT      DATETIME     DEFAULT GETDATE()
);

CREATE TABLE UIUX_TOOLS (
    TOOL_ID         INT          IDENTITY(1,1) PRIMARY KEY,
    DESIGNER_ID     VARCHAR(50),
    UserID          VARCHAR(50),
    TOOL_NAME       VARCHAR(100),
    ToolName        VARCHAR(100),
    CREATED_AT      DATETIME     DEFAULT GETDATE()
);

-- Data Scientist / ML Engineer
CREATE TABLE DATA_SCIENCE_TASKS (
    TASK_ID         INT          IDENTITY(1,1) PRIMARY KEY,
    DS_ID           VARCHAR(50),
    TASK_DESCRIPTION TEXT,
    TASK_STATUS     VARCHAR(30)  DEFAULT 'Pending',
    CREATED_AT      DATETIME     DEFAULT GETDATE()
);

CREATE TABLE DATA_ANALYSIS_REPORTS (
    REPORT_ID       INT          IDENTITY(1,1) PRIMARY KEY,
    DS_ID           VARCHAR(50),
    REPORT_TYPE     VARCHAR(50),
    REPORT_DETAILS  TEXT,
    CREATED_AT      DATETIME     DEFAULT GETDATE()
);

CREATE TABLE ML_ALGORITHMS (
    ALGO_ID         INT          IDENTITY(1,1) PRIMARY KEY,
    DS_ID           VARCHAR(50),
    ALGORITHM_NAME  VARCHAR(100),
    CREATED_AT      DATETIME     DEFAULT GETDATE()
);

CREATE TABLE MLE_TASKS (
    TASK_ID         INT          IDENTITY(1,1) PRIMARY KEY,
    MLE_ID          VARCHAR(50),
    TASK_DESCRIPTION TEXT,
    TASK_STATUS     VARCHAR(30)  DEFAULT 'Pending',
    CREATED_AT      DATETIME     DEFAULT GETDATE()
);

CREATE TABLE MLE_FRAMEWORKS (
    FRAMEWORK_ID    INT          IDENTITY(1,1) PRIMARY KEY,
    MLE_ID          VARCHAR(50),
    FRAMEWORK_NAME  VARCHAR(100),
    CREATED_AT      DATETIME     DEFAULT GETDATE()
);

-- SRE (Site Reliability Engineer)
CREATE TABLE SRE_TOOLS (
    TOOL_ID             INT          IDENTITY(1,1) PRIMARY KEY,
    ENGINEER_USERNAME   VARCHAR(50),
    TOOL_NAME           VARCHAR(100),
    CREATED_AT          DATETIME     DEFAULT GETDATE()
);

-- Software Architect
CREATE TABLE SOFTWARE_ARCHITECT_SPECIALIZATIONS (
    ID          INT          IDENTITY(1,1) PRIMARY KEY,
    USERNAME    VARCHAR(50),
    SPECIALIZATION VARCHAR(100),
    CREATED_AT  DATETIME     DEFAULT GETDATE()
);

CREATE TABLE ARCHITECTURE_TASKS (
    TASK_ID         INT          IDENTITY(1,1) PRIMARY KEY,
    USERNAME        VARCHAR(50),
    TASK_DESCRIPTION TEXT,
    TASK_STATUS     VARCHAR(30)  DEFAULT 'Pending',
    CREATED_AT      DATETIME     DEFAULT GETDATE()
);

-- Coder
CREATE TABLE coder_skill (
    ID          INT          IDENTITY(1,1) PRIMARY KEY,
    USERID      VARCHAR(50),
    coder_id    VARCHAR(50),
    coder_skill VARCHAR(100),
    CREATED_AT  DATETIME     DEFAULT GETDATE()
);

-- ────────────────────────────────────────────────────────────
-- SEED: Default Admin Account
-- ────────────────────────────────────────────────────────────

INSERT INTO USERS (USER_ID, USERNAME, PASSWORD, EMAIL, ROLE)
VALUES ('admin001', 'admin', 'admin123', 'admin@devsync.com', 'Admin');

INSERT INTO ADMIN (ID, USERNAME, PASSWORD, EMAIL)
VALUES ('admin001', 'admin', 'admin123', 'admin@devsync.com');

GO
