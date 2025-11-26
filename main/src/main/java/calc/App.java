package calc;

import java.util.Scanner;

/**
 * Scientific Calculator Application
 *
 * This class implements a scientific calculator with arithmetic and power operations.
 * It provides a menu-driven interface for user interaction.

 */
public class App {

    // Base Calculator class
    static class Calculator {
        double currentVal = 0.0;

        @Override
        public String toString() {
            return "Calculator";
        }
    }

    // Arithmetic operations
    static class Arithmetic extends Calculator {
        double addition(double a, double b) {
            currentVal = a + b;
            return currentVal;
        }

        double subtraction(double a, double b) {
            currentVal = a - b;
            return currentVal;
        }

        double multiplication(double a, double b) {
            currentVal = a * b;
            return currentVal;
        }

        double division(double a, double b) {
            if (b == 0) {
                System.out.println("Cannot divide by zero!");
                currentVal = 0;
            } else {
                currentVal = a / b;
            }
            return currentVal;
        }
    }

    // Power operations
    static class Power extends Calculator {
        double exponent(double base, double power) {
            currentVal = Math.pow(base, power);
            return currentVal;
        }

        double root(double a) {
            if (a < 0) {
                throw new IllegalArgumentException("Input 'a' must be non-negative for square root.");
            }
            currentVal = Math.sqrt(a);
            return currentVal;
        }

        double cubeRoot(double a) {
            currentVal = Math.cbrt(a);
            return currentVal;
        }
    }

    // Utility functions
    static void resetCurrentVal(Calculator instance) {
        instance.currentVal = 0;
    }

    static void printMenu() {
        System.out.println("Welcome to Scientific Calculator!");
        System.out.println("Choose any one of the following operations to get started!:");
        System.out.println("1. Arithmetic");
        System.out.println("2. Power");
        System.out.println("3. Exit");
        System.out.println("4. Reset Current Value");
    }

    static void printArithmeticMenu() {
        System.out.println("A. Addition");
        System.out.println("B. Subtraction");
        System.out.println("C. Multiplication");
        System.out.println("D. Division");
    }

    static void printPowerMenu() {
        System.out.println("A. Exponent");
        System.out.println("B. Square Root");
        System.out.println("C. Cube Root");
    }

    static double[] powerInput(Scanner sc) {
        while (true) {
            try {
                System.out.print("Enter base: ");
                double base = sc.nextDouble();
                System.out.print("Enter power: ");
                double power = sc.nextDouble();
                return new double[]{base, power};
            } catch (Exception e) {
                System.out.println("Please enter the correct value!");
                sc.nextLine();
            }
        }
    }

    static double[] inputTwoOperands(Scanner sc) {
        while (true) {
            try {
                System.out.print("Enter first operand: ");
                double a = sc.nextDouble();
                System.out.print("Enter second operand: ");
                double b = sc.nextDouble();
                return new double[]{a, b};
            } catch (Exception e) {
                System.out.println("Please enter the correct value!");
                sc.nextLine();
            }
        }
    }

    static boolean continueCalculator(Scanner sc) {
        String choice = "wrong";
        while (!"yn".contains(choice)) {
            System.out.print("Do you wish to continue calculations? (y/n): ");
            choice = sc.next().toLowerCase();
        }
        return "y".equals(choice);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calculator = new Calculator();
        boolean calculation = true;

        while (calculation) {
            printMenu();
            System.out.println();

            int choice = -1;
            while (choice < 1 || choice > 4) {
                try {
                    System.out.print("Please choose from 1 to 4: ");
                    choice = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input!");
                    sc.nextLine();
                }
            }

            // Handle choices
            if (choice == 4) {
                resetCurrentVal(calculator);
                System.out.println("Current value has been reset to zero...\n");
            } else if (choice == 1) {
                Arithmetic arithmeticCalci = new Arithmetic();
                printArithmeticMenu();
                String operation = "wrong";
                while (!"ABCD".contains(operation)) {
                    System.out.print("Choose any of the above, (enter a,b,c, or d): ");
                    operation = sc.next().toUpperCase();
                }
                double[] operands = inputTwoOperands(sc);
                double a = operands[0], b = operands[1];
                if ("A".equals(operation)) {
                    calculator.currentVal = arithmeticCalci.addition(a, b);
                    System.out.println("Sum of " + a + " and " + b + " is " + calculator.currentVal);
                } else if ("B".equals(operation)) {
                    calculator.currentVal = arithmeticCalci.subtraction(a, b);
                    System.out.println("Difference between " + a + " and " + b + " is " + calculator.currentVal);
                } else if ("C".equals(operation)) {
                    calculator.currentVal = arithmeticCalci.multiplication(a, b);
                    System.out.println("Product of " + a + " and " + b + " is " + calculator.currentVal);
                } else if ("D".equals(operation)) {
                    calculator.currentVal = arithmeticCalci.division(a, b);
                    System.out.println(a + " divided by " + b + " is " + calculator.currentVal);
                }
            } else if (choice == 2) {
                Power powerCalci = new Power();
                printPowerMenu();
                String operation = "wrong";
                while (!"ABC".contains(operation)) {
                    System.out.print("Choose any of the above, (enter a,b, or c): ");
                    operation = sc.next().toUpperCase();
                }
                if ("A".equals(operation)) {
                    double[] bp = powerInput(sc);
                    double b = bp[0], p = bp[1];
                    calculator.currentVal = powerCalci.exponent(b, p);
                    System.out.println(b + " raised to " + p + " = " + calculator.currentVal);
                } else if ("B".equals(operation)) {
                    System.out.print("Enter number: ");
                    double x = sc.nextDouble();
                    calculator.currentVal = powerCalci.root(x);
                    System.out.println("Square root of " + x + " = " + calculator.currentVal);
                } else if ("C".equals(operation)) {
                    System.out.print("Enter number: ");
                    double x = sc.nextDouble();
                    calculator.currentVal = powerCalci.cubeRoot(x);
                    System.out.println("Cube root of " + x + " = " + calculator.currentVal);
                }
            } else if (choice == 3) {
                calculation = false;
                System.out.println("Thanks for using the Scientific Calculator!");
                System.out.println("Exiting program.....");
            }

            if (choice >= 1 && choice <= 2) {
                calculation = continueCalculator(sc);
                if (!calculation) {
                    System.out.println("Thanks for using the Scientific Calculator!");
                    System.out.println("Exiting the program.....");
                }
            }
        }
        sc.close();
    }
}
