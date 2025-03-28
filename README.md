# Student Management System 📚

## 📌 Overview
The **Student Management System** is a Java-based console application that allows users to manage student records. It supports adding, viewing, searching, updating, and deleting students. The data is stored persistently using Java serialization (`students.dat` file).

## 🚀 Features
- Add new students with roll number, name, age, and course.
- View all students in a list format.
- Search for a student by roll number.
- Update student details.
- Delete a student record.
- Save data using file serialization.

## 🛠️ Technologies Used
- Java (Core Java, OOP, File Handling)
- Serialization for data storage

## 🏗️ How to Run
1. **Clone the Repository**
   ```sh
   git clone https://github.com/YOUR_USERNAME/Student-Management-System.git
   cd Student-Management-System
2. **Compile the Java Files**
   ```sh
   javac Student.java StudentManagementSystem.java
3. **Run the Application**
   ```sh
   java StudentManagementSystem

## 📂 File Structure
📦 Student-Management-System
 ┣ 📜 Student.java                 # Student class (model)
 ┣ 📜 StudentManagementSystem.java # Main application logic
 ┣ 📜 students.dat                 # Serialized student data (ignored in .gitignore)
 ┣ 📜 README.md                    # Project documentation
 ┗ 📜 .gitignore                    # Excludes compiled files and student data

 ## 📝 To-Do (Future Enhancements)
1. Implement a GUI using Java Swing or JavaFX.
2. Replace serialization with a database (MySQL).
3. Add exception handling for robustness.

## 📄 License
This project is open-source and available under the MIT License
