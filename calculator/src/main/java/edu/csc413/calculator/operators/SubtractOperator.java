package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class SubtractOperator extends Operator{
    public SubtractOperator() {
        priority = 1;
    }

    public Operand execute(Operand op1, Operand op2) {
        int temp = op1.getValue() - op2.getValue();
        Operand op = new Operand(temp);
        return op;
    }

    public int priority() {
        return priority;
    }
}
