import java.util.Scanner;

class Methods {
static void add(int a, int b) {
System.out.println("Addition = " + (a + b));
}
static void subtract(int a, int b) {
System.out.println("Subtraction = " + (a - b));
}
static void multiply(int a, int b) {
System.out.println("Multiplication = " + (a * b));
}
static void divide(int a, int b) {
System.out.println("Division = " + (a / b));
}
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter a: ");
int a = sc.nextInt();
System.out.print("Enter b: ");
 int b = sc.nextInt();

 add(a, b);
 subtract(a, b);
 multiply(a, b);
 divide(a, b);
    }
} 