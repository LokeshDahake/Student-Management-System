import java.io.*;
import java.util.*;

public class StudentManagementSystem {
    private static final String FILE_NAME = "students.dat";
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        loadStudents(); // Load existing student data from file
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Clear invalid input
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    searchStudent(scanner);
                    break;
                case 4:
                    updateStudent(scanner);
                    break;
                case 5:
                    deleteStudent(scanner);
                    break;
                case 6:
                    saveStudents();
                    System.out.println("Exiting... Data saved!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter Roll No: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Roll No must be a number.");
            scanner.next();
            return;
        }
        int rollNo = scanner.nextInt();
        scanner.nextLine();

        if (findStudent(rollNo).isPresent()) {
            System.out.println("Student with Roll No " + rollNo + " already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Age must be a number.");
            scanner.next();
            return;
        }
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Course: ");
        String course = scanner.nextLine();

        students.add(new Student(rollNo, name, age, course));
        System.out.println("Student added successfully!");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\nStudent List:");
        students.forEach(System.out::println);
    }

    private static void searchStudent(Scanner scanner) {
        System.out.print("Enter Roll No to search: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Roll No must be a number.");
            scanner.next();
            return;
        }
        int rollNo = scanner.nextInt();

        findStudent(rollNo).ifPresentOrElse(
            student -> System.out.println("Student Found: " + student),
            () -> System.out.println("Student not found.")
        );
    }

    private static void updateStudent(Scanner scanner) {
        System.out.print("Enter Roll No to update: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Roll No must be a number.");
            scanner.next();
            return;
        }
        int rollNo = scanner.nextInt();
        scanner.nextLine();

        Optional<Student> studentOpt = findStudent(rollNo);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            System.out.print("Enter New Name: ");
            student.setName(scanner.nextLine());
            System.out.print("Enter New Age: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Age must be a number.");
                scanner.next();
                return;
            }
            student.setAge(scanner.nextInt());
            scanner.nextLine();
            System.out.print("Enter New Course: ");
            student.setCourse(scanner.nextLine());
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.print("Enter Roll No to delete: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Roll No must be a number.");
            scanner.next();
            return;
        }
        int rollNo = scanner.nextInt();

        if (students.removeIf(student -> student.getRollNo() == rollNo)) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void saveStudents() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    private static void loadStudents() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            students = new ArrayList<>();
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            students = (List<Student>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading student data. Starting fresh.");
            students = new ArrayList<>();
        }
    }

    private static Optional<Student> findStudent(int rollNo) {
        return students.stream().filter(student -> student.getRollNo() == rollNo).findFirst();
    }
}
