import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            displayMenu();
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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
                case 14 -> {
                    System.out.println("Exiting... ");
                    exit = true;
                }
                default -> System.out.println("Invalid choice. Try again!");
            }
        }
    }

    static void displayMenu() {
        System.out.println("\nStudent Management System ");
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
        System.out.println("14. Exit");
    }

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
        System.out.println(" Student not found.");
    }

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

    static void showHighestGrade() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        Student topStudent = Collections.max(students, Comparator.comparingDouble(Student::getGrade));
        System.out.println("Highest Grade:");
        topStudent.displayInfo();
    }

    static void showLowestGrade() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        Student lowStudent = Collections.min(students, Comparator.comparingDouble(Student::getGrade));
        System.out.println(" Lowest Grade:");
        lowStudent.displayInfo();
    }

    static void countTotalStudents() {
        System.out.println(" Total Students: " + students.size());
    }

    static void sortByGrade() {
        students.sort(Comparator.comparingDouble(Student::getGrade).reversed());
        System.out.println("Students sorted by grade (descending).");
        viewStudents();
    }

    static void sortByName() {
        students.sort(Comparator.comparing(Student::getName));
        System.out.println("Students sorted by name (A-Z).");
        viewStudents();
    }

    static void clearAllStudents() {
        students.clear();
        System.out.println("All student records cleared.");
    }
}
