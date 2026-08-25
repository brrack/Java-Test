import java.util.Scanner;

public class scheduler {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Create Scanner

        System.out.println("\nScheduler\n1: View Schedule\n2: New Class\n");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.print("You want to view your schedule");
            case "2":
                System.out.print("You want to add a new class");
        }

        scanner.close();
    }
}