import java.util.Scanner;

class CountDigitsSolution {
    public static void main(String[] args) {
        System.out.println("Running: " + CountDigitsSolution.class.getName());
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number whose digits are to be counted");
        int num = scanner.nextInt();
        scanner.close();

        // count_digits(num);
        count_digits_optimally(num);
    }

    static void count_digits(int num) {
        // brute force
        int n = num;
        int count = 0;

        while (n != 0) {
            n = n / 10;
            count++;
        }

        System.out.println("Number of digits is: " + count);
    }

    static void count_digits_optimally(int num) {
        // optimal
        int count = (int) Math.ceil((double) Math.log10(num));
        System.out.println("Number of digits is: " + count);
    }
}
