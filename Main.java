import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentLinkedList records = new StudentLinkedList();
    private static final StudentHashMap index = new StudentHashMap();
    private static final StudentQueue admissionQueue = new StudentQueue();
    private static final StudentStack recentlyRemoved = new StudentStack();

    public static void main(String[] args) {
        loadSampleData();
        System.out.println("==============================================");
        System.out.println("   STUDENT RECORD MANAGEMENT SYSTEM");
        System.out.println("   Data Structures & Algorithms Project");
        System.out.println("==============================================");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addStudent();
                case "2" -> displayStudents();
                case "3" -> searchStudent();
                case "4" -> sortStudents();
                case "5" -> deleteStudent();
                case "6" -> processQueue();
                case "7" -> showRecentlyRemoved();
                case "8" -> demonstrateHashMap();
                case "0" -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
        System.out.println("Program closed.");
    }

    private static void printMenu() {
        System.out.println("\n1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Sort Students");
        System.out.println("5. Delete Student");
        System.out.println("6. Process Admission Queue");
        System.out.println("7. Show Recently Removed (Stack)");
        System.out.println("8. HashMap Lookup");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private static void addStudent() {
        try {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (index.get(id) != null) {
                System.out.println("ID already exists.");
                return;
            }

            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter department: ");
            String dept = scanner.nextLine();
            System.out.print("Enter CGPA: ");
            double cgpa = Double.parseDouble(scanner.nextLine());

            Student student = new Student(id, name, dept, cgpa);
            records.add(student);
            index.put(student);
            admissionQueue.enqueue(student);
            System.out.println("Student added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input.");
        }
    }

    private static void displayStudents() {
        System.out.println("\n--- STUDENT RECORDS ---");
        records.display();
        System.out.println("Total records: " + records.size());
    }

    private static void searchStudent() {
        try {
            System.out.print("Enter ID to search: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("1. Linear Search");
            System.out.println("2. Binary Search (ID sorted)");
            System.out.print("Choice: ");
            int method = Integer.parseInt(scanner.nextLine());

            Student[] data = records.toArray();
            if (method == 2) SortAlgorithms.insertionSortById(data);

            Student result = method == 2
                    ? SearchAlgorithms.binarySearch(data, id)
                    : SearchAlgorithms.linearSearch(data, id);

            System.out.println(result == null ? "Student not found." : "Found: " + result);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    private static void sortStudents() {
        Student[] data = records.toArray();
        System.out.println("1. Bubble Sort by CGPA (descending)");
        System.out.println("2. Insertion Sort by ID");
        System.out.println("3. Merge Sort by Name");
        System.out.print("Choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1) SortAlgorithms.bubbleSortByCgpa(data);
            else if (choice == 2) SortAlgorithms.insertionSortById(data);
            else if (choice == 3) SortAlgorithms.mergeSortByName(data);
            else {
                System.out.println("Invalid choice.");
                return;
            }
            for (Student s : data) System.out.println(s);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    private static void deleteStudent() {
        try {
            System.out.print("Enter ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());
            Student student = index.remove(id);
            if (student != null && records.removeById(id)) {
                recentlyRemoved.push(student);
                System.out.println("Deleted: " + student);
            } else {
                System.out.println("Student not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
        }
    }

    private static void processQueue() {
        Student student = admissionQueue.dequeue();
        System.out.println(student == null
                ? "Admission queue is empty."
                : "Processed from queue: " + student);
    }

    private static void showRecentlyRemoved() {
        Student student = recentlyRemoved.peek();
        System.out.println(student == null
                ? "No recently removed record."
                : "Top of stack: " + student);
    }

    private static void demonstrateHashMap() {
        try {
            System.out.print("Enter ID for O(1) average-case HashMap lookup: ");
            int id = Integer.parseInt(scanner.nextLine());
            Student student = index.get(id);
            System.out.println(student == null ? "Student not found." : "Found: " + student);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
        }
    }

    private static void loadSampleData() {
        addSample(new Student(104, "Rahul", "CSE", 8.70));
        addSample(new Student(101, "Ananya", "AIML", 9.10));
        addSample(new Student(103, "Kiran", "DS", 8.40));
        addSample(new Student(102, "Priya", "CSE", 9.30));
    }

    private static void addSample(Student s) {
        records.add(s);
        index.put(s);
        admissionQueue.enqueue(s);
    }
}
