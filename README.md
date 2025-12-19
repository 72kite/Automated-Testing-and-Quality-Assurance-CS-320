# Automated-Testing-and-Quality-Assurance-CS-320
A collection of software testing artifacts focusing on JUnit 5, Test-Driven Development (TDD), and quality assurance practices through a Contact Service implementation.




CS 320: Software Test Automation and Quality Assurance
📌 Project Overview
This repository contains artifacts from my coursework in Software Test Automation and QA. The projects showcased here demonstrate a rigorous approach to the Software Development Life Cycle (SDLC), focusing on Test-Driven Development (TDD), unit testing, and ensuring software reliability through automated verification.

🗂️ Included Artifacts
Contact Service (Project One): A robust Java-based contact management system featuring:

Contact.java & ContactService.java: Core logic and data validation.

ContactTest.java & ContactServiceTest.java: Comprehensive JUnit 5 test suites.

Testing Summary & Reflections (Project Two): A detailed report analyzing testing strategies, coverage metrics, and the importance of automated quality assurance.

🧠 Reflection
1. Ensuring Functionality and Security
To ensure that software is both functional and secure, I utilize Unit Testing and Defensive Programming. By using the JUnit 5 framework, I can verify that every individual component performs exactly as expected before it is integrated into the larger system. Functionality is confirmed when 100% of the requirements—such as character limits and unique IDs—are met by passing test cases. Security is addressed at the code level through strict input validation in the constructors; by rejecting null or improperly formatted data immediately, I prevent many common vulnerabilities like injection or memory corruption from entering the application.

2. Interpreting User Needs
I interpret user needs by breaking down high-level requirements into specific, measurable technical constraints. For the Contact Service, I translated the "User Need" for reliable data into a set of validation rules (e.g., "Phone number must be exactly 10 digits"). I then incorporate these needs into the program by writing tests first (Test-Driven Development). This ensures that the user’s requirements are the driving force behind the code’s architecture, rather than an afterthought.

3. Approaching Software Design
My approach to software design is centered on Modularity and the Single Responsibility Principle (SRP). I separate data objects (like the Contact class) from the business logic that manages them (like the ContactService). This makes the code more maintainable and significantly easier to test. I also design for Testability; by keeping methods small and focused, I can achieve high code coverage, which provides a high degree of confidence that the software will remain stable as new features are added.

🛠️ Tools and Technologies
Language: Java

Testing Framework: JUnit 5

Build Tool: Maven / Eclipse IDE

Methodology: Test-Driven Development (TDD)

🚀 How to Run the Tests
Clone this repository.

Import the project into your preferred IDE (Eclipse or IntelliJ).

Navigate to the src/test/java folder.

Right-click ContactServiceTest.java and select Run As > JUnit Test.
