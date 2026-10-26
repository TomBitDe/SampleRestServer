# Agent Instructions & Project Rules for Standard Java (NetBeans Setup)

This file defines the code quality standards, architecture, and tool constraints for this Standard Java project. The AI Agent MUST follow these rules for every task.

## 1. Environment & Tools
* **Language:** Standard Java (Java SE). Do NOT include Spring, Quarkus, or other heavy enterprise frameworks.
* **IDE:** Apache NetBeans. Respect the NetBeans project structure (`nbproject/` and `src/`).
* **Version Control:** Git or SVN. Keep modifications minimal, isolated, and focused on the requested feature. Do not delete or overwrite files outside the scope.

## 2. Core Coding Standards (Standard Java)
* **Code Style:** Standard Java naming conventions (CamelCase, PascalCase for classes, UPPER_CASE for constants).
* **UI/Console:** For GUI elements, use standard **Swing** (NetBeans GUI Builder compatible) or **JavaFX** as requested. Keep UI logic strictly separated from business logic.
* **No Magic Libraries:** Avoid external dependencies unless explicitly defined in the project configuration. Rely on the Java Standard Library (JDK) for collections, I/O, and networking.
* **Resource Management:** Always use try-with-resources for streams, database connections (`JDBC`), and file I/O to prevent memory leaks.

## 3. Architecture & Clean Code
* **Design Patterns:** Use standard Object-Oriented Programming (OOP) patterns (e.g., Singleton, Factory, DAO for database access) to keep the codebase maintainable.
* **Separation of Concerns:** Split the application into clear packages:
  * `ui` / `view` (User Interface / Forms)
  * `model` / `data` (Data objects / POJOs)
  * `service` / `logic` (Business rules and processing)
  * `dao` / `db` (Database or File access if applicable)
* **Data Objects:** Use classic POJOs (Plain Old Java Objects) with private fields, standard getters/setters, and explicit `toString()`, `equals()`, and `hashCode()` implementations where needed (Do NOT use Lombok).

## 4. Error Handling & Robustness
* **Defensive Programming:** Always check for `null` and invalid input at method boundaries. Throw clear `IllegalArgumentException` if parameters are invalid.
* **Exceptions:** Handle checked exceptions locally with clear `try-catch` blocks. Do not blindly swallow exceptions (`catch (Exception e) {}` is forbidden). Log errors to `System.err` or a basic Logger.
* **Thread Safety:** If the application uses background tasks or SwingWorkers, ensure UI components are only updated on the Event Dispatch Thread (EDT) via `SwingUtilities.invokeLater`.

## 5. Testing & Verification
* **Unit Testing:** Use standard **JUnit** (integrated into NetBeans).
* **Test Isolation:** Ensure tests do not rely on local absolute file paths. Use relative paths or classpath resources.
