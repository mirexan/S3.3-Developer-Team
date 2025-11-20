# Task-Based Agenda Application

This project is a console-based productivity tool that helps users organize and manage their tasks.  
Unlike a traditional calendar focused on specific dates, this system centers around the **tasks themselves**, their lifecycle, and how their priority evolves over time.

---

## 📌 What Is a Task?

A **Task** is the central entity of the application. Each task represents a user-defined activity with a lifecycle influenced by dates, priority, and optional metadata.

### **Mandatory Fields**
- **Start Date**  
  Provided by the user. Indicates when the task becomes active.
- **Text Identifier (Description/Title)**  
  A required, unique text string used to distinguish the task.
- **Priority Level**  
  Tasks always begin with:
  - `MEDIUM` priority by default  
  Users may manually change the priority to:
  - `LOW`
  - `HIGH`

### **Internal Field**
- **Task ID**  
  A unique identifier assigned automatically by the SQL database.

### **Optional Fields**
- **Expiration Date**  
  Defines the last day the task remains active.  
  After this date:
  - The system may ask the user whether to discard the task, or  
  - It may auto-discard it (depending on logic defined later).

- **Category / Context**  
  A user-defined text field to classify tasks (e.g., *Work*, *Personal*, etc.).

---

## 🧠 How Tasks Behave (Proposal)

- The system uses the **current system clock** to evaluate which tasks are active.
- Users can create tasks on any date.
- Tasks **start with MEDIUM priority**.
- Users can manually reduce them to **LOW priority** or increase them to **HIGH**.
- Tasks **with an expiration date** automatically become **HIGH priority** as the expiration day approaches (e.g., the day before).
- Each new day acts as a **turn**, during which the system evaluates which tasks are active, expired, or urgent.

---

## 🧪 Current Development Scope

The current step of the project focuses on:

1. **Defining the Task entity**
2. **Implementing SQL persistence (MySQL + JDBC)**
3. **Creating `TaskService` (Singleton)**  
   - Responsible for communication with the SQL database  
   - Handles creation, retrieval, and update of tasks
4. **Implementing a console-based user menu**  

Future extensions include:
- Notes management
- Event management
- Full agenda behavior across domains

---

## 🛠️ Technologies Used

- **Java 8**
- **MySQL**
- **JDBC**
- **Maven**
- **Docker** (for MySQL containerization)

---

## 📌 Project Philosophy

This system is designed to treat tasks as dynamic entities that change importance over time, allowing users to focus on what matters **now**, rather than browsing through calendar pages.

---
