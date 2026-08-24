import java.util.Scanner;

public class numguesser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Create Scanner

        System.out.println("Welcome to the Number Guesser Game!");
        System.out.print("Easy: 1 - 10 | Medium: 1 - 100 | Hard: 1 - 1000\nChoose your difficulty level (easy, medium, hard): ");
        String difficulty = scanner.nextLine();

        int numberToGuess = 0;
        int userGuess = 0;
        int attempts = 0;

        switch (difficulty.toLowerCase()) {
            case "easy":
                numberToGuess = (int) (Math.random() * 10) + 1;
                break;
            case "medium":
                numberToGuess = (int) (Math.random() * 100) + 1;
                break;
            case "hard":
                numberToGuess = (int) (Math.random() * 1000) + 1;
                break;
            default:
                System.out.println("Invalid difficulty level. Defaulting to easy.");
                numberToGuess = (int) (Math.random() * 10) + 1;
        }

        while (userGuess != numberToGuess) {
            System.out.print("Enter your guess: ");
            userGuess = scanner.nextInt();
            attempts++;

            if (userGuess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else if (userGuess > numberToGuess) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Congratulations! You've guessed the number: " + numberToGuess);
                System.out.println("It took you " + attempts + " attempts.");
            }
        }

        scanner.close();
    }
}
