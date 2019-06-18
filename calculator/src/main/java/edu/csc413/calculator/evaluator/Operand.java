package edu.csc413.calculator.evaluator;

/**
 * Operand class used to represent an operand
 * in a valid mathematical expression.
 */
public class Operand {

    private int value;

    /**
     * construct operand from string token.
     */
    public Operand(String token) {
        if (check(token) == true) {
            value = Integer.parseInt(token);
        } else {
            System.out.println("invalid");
        }
    }

    /**
     * construct operand from integer
     */
    public Operand(int value) {
        this.value = value;
    }

    /**
     * return value of opernad
     */
    public int getValue() {
        return value;
    }

    /**
     * Check to see if given token is a valid
     * operand.
     */
    public static boolean check(String token) {
        try {
            int x = Integer.parseInt(token);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;

    }
}
