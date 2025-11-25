# S3.3-Team developers - Agenda application
![goatimg](https://images.unsplash.com/photo-1626115540488-c3776dc53b43?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D)
### 🗂️ Table of Contents

- 📋 [About the Project](#-about-the-project)
- 👥 [Project Context](#-project-context)
- 📌 [What Is a Task?](#-what-is-a-task)
  - 🎯 [Mandatory Fields](#-mandatory-fields)
  - 🔧 [Internal Fields](#-internal-fields)
  - ⚡ [Optional Fields](#-optional-fields)
- 🚀 [Key Functionalities](#-key-functionalities)
  - 💼 [Task Management](#-task-management)
  - 💾 [Data Persistence](#-data-persistence)
- 🏗️ [Architecture & Design Patterns](#️-architecture--design-patterns)
  - 🎨 [Layered Structure](#-layered-structure)
  - 🔄 [Design Patterns Implemented](#-design-patterns-implemented)
- 🧪 [Testing Strategy](#-testing-strategy)
- 🛠️ [Technologies Used](#️-technologies-used)
- ⚡ [How to Run](#-how-to-run)
- 💭 [Project Philosophy](#-project-philosophy)

---

## 📋 About the Project

This project 

This project is a console-based productivity tool that helps users organize and manage their tasks efficiently.

Unlike a traditional calendar focused on specific dates, this system centers around the tasks themselves, their lifecycle, and their priority.

The application is built using a Layered Architecture, enforcing separation of concerns, SOLID principles, and a unitary testing strategy to tests all the service operations.

## 👥 Project Context

**🏆 IT Academy Java Bootcamp - Collaborative GitHub Project**

This application was developed as part of the **IT Academy Java Bootcamp**, serving as a comprehensive collaborative project that demonstrates:

- 🔄 **GitHub-based collaboration** using feature branches, pull requests, and code reviews
- 👨‍💻 **Team development workflows** following industry best practices
- 📝 **Version control mastery** with proper commit conventions and branch management
- 🤝 **Collaborative problem-solving** in a simulated professional environment
- ✅ **Quality assurance** through peer reviews and collective code ownership

The project showcases the practical application of Java development skills in a real-world collaborative setting.

---

## 📌 What Is a Task?

A **Task** is the central entity of the application. Each task represents a user-defined activity with a lifecycle influenced by dates, priority, and optional metadata.

### 🎯 Mandatory Fields

- **Text Identifier (Description/Title)**  
  A required, unique text string used to distinguish the task.
- **Priority Level**  
  Tasks always begin with:
  - `MEDIUM` priority by default  
    Users may manually change the priority to:
  - `LOW`
  - `HIGH`
- **Status (Lifecycle)**: Tracks the progress of the task:
  - `⏳ IN PROGRESS`: The default state upon creation.
  - `✅ COMPLETED`: The state when the user marks the task as done.

### 🔧 Internal Fields
- **Task ID**  
  A unique identifier assigned automatically by the SQL database.
- **Creation Date**: Automatically assigned by the system when the task is created.

### ⚡ Optional Fields
- **Expiration Date**: Provided by the user. Indicates when the task should be done. Validations ensure this date cannot be in the past during creation.

---

## 🚀 Key Functionalities

The current version supports a complete lifecycle for task management via an interactive Command Line Interface (CLI):

### 💼 Task Management

- **Create Task**: Users can create tasks with a title and an optional expiration date. The system validates inputs to prevent empty titles or past dates.
- **List Tasks**:
  - **List All**: Displays every task in the repository.
  - **List Pending**: Filters and shows only active tasks (`⏳ In Progress`).
  - **List Completed**: Filters and shows only finished tasks (`✅ Completed`).
- **Update Status**: Tasks can be marked as completed, updating their visual status in the list.
- **Delete Task**: Secure deletion flow that requires:
  - Listing tasks to identify the ID.
  - Explicit user confirmation (S/N) before removal from the database.

### 💾 Data Persistence

- **MySQL Database**: All data is persisted reliably.
- **Docker Containerization**: The database runs in a Docker container, ensuring a consistent environment.
- **DAO Pattern**: The application uses the Data Access Object pattern to handle SQL operations transparently.

---

## 🏗️ Architecture & Design Patterns

This project implements strict Clean Code and SOLID principles.

### 🎨 Layered Structure

- **Presentation Layer (cli)**: Handles user input/output. It communicates with the Service layer via DTOs (Data Transfer Objects).
- **Service Layer (service)**: Contains all business logic and validations. It orchestrates data flow between the Controller and the Repository.
- **Persistence Layer (repository)**: Defines the contract (Interface/Adapter) for data access.
- **Infrastructure Layer (dao)**: Handles the low-level JDBC connections and SQL queries.

### 🔄 Design Patterns Implemented

- **Singleton**: Used for the `MySQLDatabaseConnection` to ensure a single, shared database connection pool.
- **Builder**: Used in the Task entity to ensure immutability and readability during object construction.
- **Repository/DAO**: Decouples business logic from the specific database implementation.
- **DTO (Data Transfer Object)**: Separates the internal database representation (`TaskDTO`) from the data presented to the user (`TaskOutputDTO`).

## 🧪 Testing Strategy

Reliability is a core pillar of this project. We follow a Unit Testing strategy using JUnit 5 and AssertJ.

- **Isolation**: Tests are strictly unitary. We do not depend on the real database for logic testing.
- **Test Doubles (Fakes)**: We utilize `FakeTaskRepository` and `TaskFactory` (using the real Builder) to simulate database behavior in memory.
- **Coverage**:
  - ✅ Happy Path (Creation, Listing, Deletion)
  - ✅ Edge Cases (Null inputs, invalid dates, non-existent IDs)
  - ✅ Mappings (Verifying Entity <-> DTO conversion)

---

## 🛠️ Technologies Used

- ☕ **Java 8**
- 🗄️ **MySQL**
- 🔌 **JDBC**
- 📦 **Maven**
- 🐳 **Docker** (for MySQL containerization)

---

## ⚡ How to Run

### 1. Start the Database

Ensure Docker is running and execute:

```bash
docker compose up -d
```
### 2. Build the Project

You can build and execute the project directly with IDE functions or by console:
```bash
./mvnw clean install
```
and then **run the application**

```bash
java -jar target/agenda-app.jar
```

---

## 📌 Project Philosophy

This system is designed to treat tasks as dynamic entities that change importance over time, allowing users to focus on what matters **now**, rather than browsing through calendar pages.

---
