import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

        Scanner scanner = new Scanner(System.in);
        Menu(schedule, scanner);
    }

    static void Menu(Map<String, ArrayList<String>> schedule, Scanner scanner) {
        boolean running = true;

        while (running) {
            System.out.println("\nScheduler\n1: View Schedule\n2: New Class\n3: Save Schedule\n4: Load Schedule\n5: Quit\n");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    ViewSchedule(schedule);
                    break;
                case "2":
                    AddClass(schedule, scanner);
                    break;
                case "3":
                    SaveSchedule(schedule, scanner);
                    break;
                case "4":
                    LoadSchedule(schedule, scanner);
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("That is not a valid option.");
            }
        }
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

        if (maxTasks == 0) {
            System.out.println("No classes scheduled yet.");
        }
    }

    static void AddClass(Map<String, ArrayList<String>> schedule, Scanner scanner) {    // Add New Class
        System.out.print("What day(s) is your class in: ");
        String days = scanner.nextLine();
        if (days.equals("e")) {
            return;
        }
        String[] daysList = days.trim().split("\\s+");

        System.out.print("What is the name of the class: ");
        String className = scanner.nextLine();

        for (String day : daysList) {
            if (schedule.containsKey(day)) {
                schedule.get(day).add(className);
            } else {
                System.out.println(day + " is not a valid day. Use names like Monday or Tuesday.");
            }
        }
    }

    static void SaveSchedule(Map<String, ArrayList<String>> schedule, Scanner scanner) {
        System.out.print("Name this schedule: ");
        String scheduleName = scanner.nextLine().trim();

        if (!ValidScheduleName(scheduleName)) {
            System.out.println("Use letters, numbers, spaces, hyphens, or underscores in the name.");
            return;
        }

        ArrayList<String> lines = new ArrayList<>();

        for (String day : schedule.keySet()) {
            for (String className : schedule.get(day)) {
                lines.add(day + "|" + className);
            }
        }

        Path file = ScheduleFile(scheduleName);

        try {
            Files.createDirectories(file.getParent());
            Files.write(file, lines);
            System.out.println("Schedule saved as " + scheduleName + ".txt!");
        } catch (IOException e) {
            System.out.println("Could not save the schedule.");
        }
    }

    static void LoadSchedule(Map<String, ArrayList<String>> schedule, Scanner scanner) {
        System.out.print("Enter the schedule name to load: ");
        String scheduleName = scanner.nextLine().trim();

        if (!ValidScheduleName(scheduleName)) {
            System.out.println("Use letters, numbers, spaces, hyphens, or underscores in the name.");
            return;
        }

        Path file = ScheduleFile(scheduleName);

        if (!Files.exists(file)) {
            System.out.println("No saved schedule named " + scheduleName + " was found.");
            return;
        }

        try {
            List<String> lines = Files.readAllLines(file);

            for (ArrayList<String> classes : schedule.values()) {
                classes.clear();
            }

            for (String line : lines) {
                String[] parts = line.split("\\|", 2);

                if (parts.length == 2 && schedule.containsKey(parts[0])) {
                    schedule.get(parts[0]).add(parts[1]);
                }
            }

            System.out.println("Loaded " + scheduleName + ".txt!");
        } catch (IOException e) {
            System.out.println("Could not load the schedule.");
        }
    }

    static boolean ValidScheduleName(String scheduleName) {
        return scheduleName.matches("[a-zA-Z0-9 _-]+");
    }

    static Path ScheduleFile(String scheduleName) {
        return Path.of("Saved Schedules", scheduleName + ".txt");
    }
}
