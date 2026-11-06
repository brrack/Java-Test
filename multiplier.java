// give it the maximum addition value and it will print a triangle of #s with the number of #s decreasing as the sum of the two numbers increases
// really this just finds the highest number of #s that can be printed for each sum of two numbers from 0 to the maximum addition value
import java.util.Scanner;

public class multiplier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Maximum Addition Value: ");
        int number = scanner.nextInt();
        
        for (int i = 0, j = number; i <= number; i++, j--) {
            for (int k = 0; k < j*i; k++) {
                System.out.print("#");
            }
            System.out.println(i + ", " + j);
        }

        scanner.close();
    }
}
