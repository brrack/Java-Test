import java.util.Scanner;

public class calc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Create Scanner
        
        System.out.print("Two Number Calculator\nEnter Equation: ");
        String equation = scanner.nextLine();

        String[] parts = equation.split(" ");

        double num1 = Double.parseDouble(parts[0]);
        String op = parts[1];
        double num2 = Double.parseDouble(parts[2]);

        switch (op) {
            case "+":
                System.out.println("Sum: " + (num1 + num2));
                break;
            case "-":
                System.out.println("Difference: " + (num1 - num2));
                break;
            case "*":
                System.out.println("Product: " + (num1 * num2));
                break;
            case "/":
                System.out.println("Quotient: " + (num1 / num2));
                break;
            default:
                System.out.println("Invalid Operator");
        }

        scanner.close();
    }
}