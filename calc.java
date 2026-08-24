import java.util.Scanner;

public class calc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   // Create Scanner

        System.out.print("Enter Operator: ");       // Get Operator
        String op = scanner.nextLine();
        
        if (op == "+") {
            System.out.println("You want to add");
        }
        else if (op.equals("-")) {
            System.out.println("You want to subtract");
        }
        else if (op == "*") {
            System.out.println("You want to multiply");
        }
        else if (op == "/") {
            System.out.println("You want to divide");
        }
    }
}