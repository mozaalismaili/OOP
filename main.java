import java.util.*;

// Main class for the Student Management System
public class Main {
    static Scanner scanner = new Scanner(System.in); // Scanner for user input
    static ArrayList<Student> students = new ArrayList<>(); // List to store student objects

    public static void main(String[] args) {
        boolean exit = false;

        // Main loop to keep showing the menu until user exits
        while (!exit) {
            displayMenu(); // Show available options
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline

            // Perform the operation based on user choice
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> updateStudentGrade();
                case 4 -> deleteStudent();
                case 5 -> searchById();
                case 6 -> searchByName();
                case 7 -> calculateAverageGrade();
                case 8 -> showHighestGrade();
                case 9 -> showLowestGrade();
                case 10 -> countTotalStudents();
                case 11 -> sortByGrade();
                case 12 -> sortByName();
                case 13 -> clearAllStudents();
                case 14 -> showSecondHighestGrade();
                case 15 -> countGradesBetween80And90();
                case 16 -> exportToFile();
                case 17 -> {
                    System.out.println("Exiting... ");
                    exit = true;
                }
                default -> System.out.println("Invalid choice. Try again!");
            }
        }
    }

    // Function to show the menu
    static void displayMenu() {
        System.out.println("\n===== Student Management System =====");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student Grade");
        System.out.println("4. Delete Student");
        System.out.println("5. Search Student by ID");
        System.out.println("6. Search Student by Name");
        System.out.println("7. Calculate Average Grade");
        System.out.println("8. Show Highest Grade");
        System.out.println("9. Show Lowest Grade");
        System.out.println("10. Count Total Students");
        System.out.println("11. Sort Students by Grade");
        System.out.println("12. Sort Students by Name");
        System.out.println("13. Clear All Students");
        System.out.println("14. Show Second Highest Grade");
        System.out.println("15. Count Grades Between 80 and 90");
        System.out.println("16. Export Student Data to File");
        System.out.println("17. Exit");
    }

    // Add new student
    static void addStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter grade: ");
        double grade = scanner.nextDouble();
        students.add(new Student(name, id, grade));
        System.out.println("Student added successfully.");
    }

    // View all students
    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                s.displayInfo();
                System.out.println("------------");
            }
        }
    }

    // Update a student's grade by ID
    static void updateStudentGrade() {
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        for (Student s : students) {
            if (s.getId() == id) {
                System.out.print("Enter new grade: ");
                double newGrade = scanner.nextDouble();
                s.setGrade(newGrade);
                System.out.println("Grade updated.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Delete a student by ID
    static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getId() == id) {
                iterator.remove();
                System.out.println("Student deleted.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Search for student by ID
    static void searchById() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        for (Student s : students) {
            if (s.getId() == id) {
                s.displayInfo();
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Search for students by name (case insensitive)
    static void searchByName() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        boolean found = false;
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                s.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }

    // Calculate and display average grade
    static void calculateAverageGrade() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        double sum = 0;
        for (Student s : students) {
            sum += s.getGrade();
        }
        double average = sum / students.size();
        System.out.println("Average Grade: " + average);
    }

    // Show student with the highest grade
    static void showHighestGrade() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        Student topStudent = Collections.max(students, Comparator.comparingDouble(Student::getGrade));
        System.out.println("Highest Grade:");
        topStudent.displayInfo();
    }

    // Show student with the lowest grade
    static void showLowestGrade() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        Student lowStudent = Collections.min(students, Comparator.comparingDouble(Student::getGrade));
        System.out.println("Lowest Grade:");
        lowStudent.displayInfo();
    }

    // Count the total number of students
    static void countTotalStudents() {
        System.out.println("Total Students: " + students.size());
    }

    // Sort students by grade (descending)
    static void sortByGrade() {
        students.sort(Comparator.comparingDouble(Student::getGrade).reversed());
        System.out.println("Students sorted by grade (high to low):");
        viewStudents();
    }

    // Sort students by name (A to Z)
    static void sortByName() {
        students.sort(Comparator.comparing(Student::getName));
        System.out.println("Students sorted by name (A-Z):");
        viewStudents();
    }

    // Clear all student records
    static void clearAllStudents() {
        students.clear();
        System.out.println("All student records cleared.");
    }

    // Show second highest grade
    static void showSecondHighestGrade() {
        if (students.size() < 2) {
            System.out.println("Not enough students.");
            return;
        }
        Set<Double> uniqueGrades = new TreeSet<>(Comparator.reverseOrder());
        for (Student s : students) {
            uniqueGrades.add(s.getGrade());
        }

        Iterator<Double> it = uniqueGrades.iterator();
        it.next(); // Skip highest
        double second = it.next(); // Second highest

        for (Student s : students) {
            if (s.getGrade() == second) {
                System.out.println("Second Highest Grade:");
                s.displayInfo();
                return;
            }
        }
    }

    // Count students with grade between 80 and 90
    static void countGradesBetween80And90() {
        long count = students.stream()
                .filter(s -> s.getGrade() >= 80 && s.getGrade() <= 90)
                .count();
        System.out.println("Students with grades between 80 and 90: " + count);
    }

    // Export student data to file
    static void exportToFile() {
        try (PrintWriter writer = new PrintWriter("students.txt")) {
            for (Student s : students) {
                writer.println("Name: " + s.getName());
                writer.println("ID: " + s.getId());
                writer.println("Grade: " + s.getGrade());
                writer.println("------------------");
            }
            System.out.println("Data exported to students.txt");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
}

}
