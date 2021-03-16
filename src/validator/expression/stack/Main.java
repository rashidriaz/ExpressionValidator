package validator.expression.stack;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Expression myExpression = new Expression();
        char option;
        do {
            printMenu(myExpression.isEmpty());
            option = getOption(myExpression.isEmpty());
        } while (performTask(option, myExpression));

    }

    /*
    The functionality of this method is to perform the user's desired task based on the option provided as a parameter
     */
    private static boolean performTask(char option, Expression myExpression) {
        switch (option) {
            case '1' -> {
                addAnExpression(myExpression);
                return true;
            }//END OF CASE 1
            case '2' -> {
                validateTheExpression(myExpression);
                return true;
            }//END OF CASE 2
            case '3' -> {
                System.out.println("\n" + myExpression.getExpression() + "\n");
                return true;
            }// END OF CASE 3
            case '4' -> {
                myExpression.resetExpression();
                return true;
            }//END OF CASE 4
        }//END OF SWITCH
        return false;
    }

    /*
    The functionality of this method is to call the validation method of the
     expression class and based on the value it returns, prompts the user whether
      the provided expression is valid or not
     */
    private static void validateTheExpression(Expression myExpression) {
        if (myExpression.validateExpression()) {
            System.out.println("\nProvided Expression is valid!\n");
        } else {
            System.out.println("\nInvalid Expression provided. Please enter a valid Expression!");
        }//END OF IF/ELSE
    }

    /*
    The functionality of this method is to get an expression frm the user as a string,
     and pass it to the setExpression method of the Expression class.
     */
    private static void addAnExpression(Expression myExpression) {
        System.out.print("\n\nPlease enter your new expression(Max character limit: 25) here: \t");
        String expression = new Scanner(System.in).nextLine();
        try {
            myExpression.setExpression(expression);
        } catch (ExpressionOverflowException e) {
            System.out.println(e.getMessage());
            System.out.print("Would you like to re Enter the expression" +
                    " or go back to the main menu? (Y- Yes, N- NO):\t");
            char option = new Scanner(System.in).next().toLowerCase().charAt(0);
            if (option == 'y') {
                addAnExpression(myExpression);
            }//END OF IF
        }//END OF TRY/ CATCH
    }

    /*
    The functionality of this method is to validate the user's input and return the option selected by the user
     */
    private static char getOption(boolean expressionIsEmpty) {
        char option = new Scanner(System.in).next().charAt(0);
        if (expressionIsEmpty && option != '1') {
            return '.';
        }// END OF IF
        if (!expressionIsEmpty && option == '1') {
            return '.';
        }
        return option;
    }

    /*
    The functionality of this method is to print the menu card regarding all the
     available functionalities of the system
     */
    private static void printMenu(boolean expressionIsEmpty) {
        System.out.println("Welcome to my Intelligent Stack Validator.");
        System.out.println("Please select an option");
        if (expressionIsEmpty) {
            System.out.println("1. add an expression");
        } else {
            System.out.println("2. validate the expression");
            System.out.println("3. Print the expression");
            System.out.println("4. Reset the expression");
        }// END OF IF/ELSE
        System.out.println("Press any key to exit");
        System.out.print("\nEnter your choice:\t");
    }
}
