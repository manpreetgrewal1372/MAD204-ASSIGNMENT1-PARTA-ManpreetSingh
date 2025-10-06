import java.io.*;
import java.util.*;

/**
 * MAD204-01 | Assignment 1 - Part A
 * Author: Manpreet Singh
 * Student ID: A00198842
 * Date: 2025-10-06
 * Description: Main console-based People Management System
 */

public class PeopleManagementSystem {
    private static ArrayList<Person> people = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final String FILE_NAME = "people.txt";

    public static void main(String[] args) {
        loadFromFile();

        while (true) {
            System.out.println("\n=== People Management System ===");
            System.out.println("1. Add Person");
            System.out.println("2. List People");
            System.out.println("3. Search Person by ID");
            System.out.println("4. Search Person by Name");
            System.out.println("5. Remove Person");
            System.out.println("6. Celebrate Birthday");
            System.out.println("7. Show Performance Evaluation");
            System.out.println("8. Run Countdown (Recursion Demo)");
            System.out.println("9. Save & Exit");
            System.out.print("Enter choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> addPersonMenu();
                case 2 -> listPeople();
                case 3 -> searchById();
                case 4 -> searchByName();
                case 5 -> removeById();
                case 6 -> celebrateBirthdayMenu();
                case 7 -> showPerformanceMenu();
                case 8 -> runCountdown();
                case 9 -> {
                    saveToFile();
                    System.out.println("Data saved. Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addPersonMenu() {
        System.out.println("\nChoose Person Type:");
        System.out.println("1. Student");
        System.out.println("2. Professor");
        System.out.println("3. Teaching Assistant");
        System.out.print("Enter choice: ");
        int type = getIntInput();

        try {
            System.out.print("Enter ID: ");
            int id = getIntInput();

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Age: ");
            int age = getIntInput();
            if (age <= 0) throw new IllegalArgumentException("Age must be > 0");

            switch (type) {
                case 1 -> {
                    System.out.print("Enter Program: ");
                    String program = scanner.nextLine();
                    System.out.print("Enter Year: ");
                    int year = getIntInput();
                    System.out.print("Enter GPA: ");
                    double gpa = getDoubleInput();
                    if (gpa < 0 || gpa > 4.0) throw new IllegalArgumentException("GPA must be between 0 and 4.0");
                    people.add(new Student(id, name, age, program, year, gpa));
                }
                case 2 -> {
                    System.out.print("Enter Department: ");
                    String dept = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Courses Taught: ");
                    int courses = getIntInput();
                    people.add(new Professor(id, name, age, dept, title, courses));
                }
                case 3 -> {
                    System.out.print("Enter Program: ");
                    String program = scanner.nextLine();
                    System.out.print("Enter Year: ");
                    int year = getIntInput();
                    System.out.print("Enter GPA: ");
                    double gpa = getDoubleInput();
                    if (gpa < 0 || gpa > 4.0) throw new IllegalArgumentException("GPA must be between 0 and 4.0");
                    System.out.print("Enter Assigned Course: ");
                    String assignedCourse = scanner.nextLine();
                    people.add(new TeachingAssistant(id, name, age, program, year, gpa, assignedCourse));
                }
                default -> System.out.println("Invalid type selected.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listPeople() {
        if (people.isEmpty()) {
            System.out.println("No people in the system.");
            return;
        }
        for (Person p : people) System.out.println(p);
    }

    private static void searchById() {
        System.out.print("Enter ID: ");
        int id = getIntInput();
        Person result = search(id);
        if (result != null) System.out.println(result);
        else System.out.println("Person not found.");
    }

    private static void searchByName() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        Person result = search(name);
        if (result != null) System.out.println(result);
        else System.out.println("Person not found.");
    }

    private static void removeById() {
        System.out.print("Enter ID to remove: ");
        int id = getIntInput();
        Person result = search(id);
        if (result != null) {
            people.remove(result);
            System.out.println("Person removed.");
        } else {
            System.out.println("Person not found.");
        }
    }

    private static void celebrateBirthdayMenu() {
        System.out.print("Enter ID to celebrate birthday: ");
        int id = getIntInput();
        Person result = search(id);
        if (result != null) result.celebrateBirthday();
        else System.out.println("Person not found.");
    }

    private static void showPerformanceMenu() {
        for (Person p : people) {
            if (p instanceof Evaluable e) {
                System.out.println(p.getName() + " Performance: " + e.evaluatePerformance());
            }
        }
    }

    private static void runCountdown() {
        System.out.print("Enter number for countdown: ");
        int n = getIntInput();
        System.out.println("Countdown:");
        countdown(n);
    }

    private static void countdown(int n) {
        if (n <= 0) {
            System.out.println("Done!");
            return;
        }
        System.out.println(n);
        countdown(n - 1);
    }

    private static int factorial(int a) {
        if (a <= 1) return 1;
        return a * factorial(a - 1);
    }

    private static Person search(int id) {
        for (Person p : people) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    private static Person search(String name) {
        for (Person p : people) {
            if (p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }

    private static void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Person p : people) {
                writer.write(p.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private static void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Loaded: " + line);
                // For now, just loading as print. You can expand this to parse and reconstruct Person objects
            }
        } catch (IOException e) {
            System.out.println("No previous data found.");
        }
    }

    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Try again: ");
            }
        }
    }

    private static double getDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid decimal number. Try again: ");
            }
        }
    }
}
