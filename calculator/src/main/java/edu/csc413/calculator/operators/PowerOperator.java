package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class PowerOperator extends Operator{
    public PowerOperator() {
        priority = 3;
    }

    public Operand execute(Operand op1, Operand op2) {
         int power = op2.getValue();
         int value = op1.getValue();
         int result = 1;

         for (int i = 0; i < power; i++) {
             result *= value;
         }

         Operand op = new Operand(result);
         return op;
    }

    public int priority() {
        return priority;
    }
}
