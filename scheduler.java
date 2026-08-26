import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class scheduler {
    public static void main(String[] args) {
        Map<String, ArrayList<String>> schedule = new LinkedHashMap<>();

        schedule.put("Monday", new ArrayList<>());
        schedule.put("Tuesday", new ArrayList<>());
        schedule.put("Wednesday", new ArrayList<>());
        schedule.put("Thursday", new ArrayList<>());
        schedule.put("Friday", new ArrayList<>());
        schedule.put("Saturday", new ArrayList<>());
        schedule.put("Sunday", new ArrayList<>());

        Menu(schedule);
    }

    static void Menu(Map<String, ArrayList<String>> schedule) {
        Scanner scanner = new Scanner(System.in);   // Create Scanner

        System.out.println("\nScheduler\n1: View Schedule\n2: New Class\n");
        String choice = scanner.nextLine();

        switch (choice) {   // Check what user inputs
            case "1":
                ViewSchedule(schedule);
                break;
            case "2":
                AddClass(schedule, scanner);
                break;
        }

        Menu(schedule);  // Loop of Main
        scanner.close();
    }

    static void ViewSchedule(Map<String, ArrayList<String>> schedule) {    // View Schedule 
        int maxTasks = 0;
        
        // Find the largest number of tasks on any day
        for (ArrayList<String> tasks : schedule.values()) {
            if (tasks.size() > maxTasks) {
                maxTasks = tasks.size();
            }
        }

        // Print headers
        for (String day : schedule.keySet()) {
            System.out.printf("%-18s", day);
        }
        System.out.println();

        // Print task rows
        for (int row = 0; row < maxTasks; row++) {
            for (String day : schedule.keySet()) {
                ArrayList<String> tasks = schedule.get(day);

                if (row < tasks.size()) {
                    System.out.printf("%-18s", tasks.get(row));
                } else {
                    System.out.printf("%-18s", "");
                }
            }
            System.out.println();
            }
        }

    static void AddClass(Map<String, ArrayList<String>> schedule, Scanner scanner) {    // Add New Class
        System.out.print("What day(s) is your class in: ");
        String days = scanner.nextLine();
        switch (days) {
            case "e":
            Menu(schedule);
        }
        String[] day = days.split(" ");

        System.out.print("What is the name of the class: ");
        String className = scanner.nextLine();

        for (String d : day) {
            schedule.get(d).add(className);
        }
    }
}