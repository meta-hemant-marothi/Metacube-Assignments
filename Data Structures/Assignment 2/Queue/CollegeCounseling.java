import java.util.*;

public class CollegeCounseling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Program> programs = new HashMap<>();
        programs.put("Engineering", new Program("Engineering", 2));
        programs.put("Medicine", new Program("Medicine", 1));
        programs.put("Arts", new Program("Arts", 2));
        programs.put("Commerce", new Program("Commerce", 2));
        programs.put("Law", new Program("Law", 1));
        CircularQueue<Student> queue = new CircularQueue<>();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- College Counseling System ---");
            System.out.println("1. Add Student to Queue");
            System.out.println("2. Process Next Student");
            System.out.println("3. Display Queue");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = validateIntegerInput(scanner);

            switch (choice) {
                case 1:
                    System.out.print("Enter Student Name: ");
                    scanner.nextLine(); // Consume leftover newline
                    String name = scanner.nextLine().trim();

                    if (name.isEmpty()) {
                        System.out.println("Invalid input. Student name cannot be empty.");
                        break;
                    }

                    System.out.println("Enter 5 Program Preferences (comma-separated): " + programs.keySet() + ":-");
                    String preferencesInput = scanner.nextLine().trim();
                    String[] preferences = preferencesInput.split(",");

                    if (preferences.length != 5) {
                        System.out.println("Invalid input. Please enter exactly 5 preferences.");
                        break;
                    }

                    queue.enqueue(new Student(name, Arrays.asList(preferences)));
                    System.out.println(name + " has been added to the queue.");
                    break;

                case 2:
                    if (queue.isEmpty()) {
                        System.out.println("No students in the queue.");
                    } else {
                        Student student = queue.dequeue();
                        allocateProgram(student, programs);
                    }
                    break;

                case 3:
                    displayQueue(queue);
                    break;

                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option (1-4).");
            }
        }

        scanner.close();
    }

    private static void allocateProgram(Student student, Map<String, Program> programs) {
        for (String preference : student.getPreferences()) {
            if (programs.containsKey(preference.trim()) && programs.get(preference.trim()).getCapacity() > 0) {
                programs.get(preference.trim()).setCapacity(programs.get(preference.trim()).getCapacity() - 1);
                System.out.println(student.getName() + " has been allocated to " + preference.trim());
                return;
            }
        }
        System.out.println(student.getName() + " could not be allocated to any program.");
    }

    private static void displayQueue(CircularQueue<Student> queue) {
        if (queue.isEmpty()) {
            System.out.println("The queue is empty.");
            return;
        }
    
        System.out.println("Students in the queue:");
        int size = queue.size();
        int index = queue.getFront();
    
        for (int i = 0; i < size; i++) {
            Student student = queue.getElementAt(index);
            System.out.println(student.getName() + " (Preferences: " + student.getPreferences() + ")");
            index = (index + 1) % queue.getCapacity();
        }
    }

    // Input validation for integer choices
    private static int validateIntegerInput(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();
            }
        }
    }
}