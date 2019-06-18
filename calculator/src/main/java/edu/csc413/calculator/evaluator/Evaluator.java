package edu.csc413.calculator.evaluator;


import edu.csc413.calculator.operators.Operator;

import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {
    private Stack<Operand> operandStack;
    private Stack<Operator> operatorStack;
    private StringTokenizer tokenizer;
    private static final String DELIMITERS = "+-*^/()";

    public Evaluator() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    public int eval(String expression) {
        String token;

        // The 3rd argument is true to indicate that the delimiters should be used
        // as tokens, too. But, we'll need to remember to filter out spaces.
        this.tokenizer = new StringTokenizer(expression, DELIMITERS, true);

        // initialize operator stack - necessary with operator priority schema
        // the priority of any operator in the operator stack other than
        // the usual mathematical operators - "+-*/" - should be less than the priority
        // of the usual operators


        while (this.tokenizer.hasMoreTokens()) {
            // filter out spaces
            if (!(token = this.tokenizer.nextToken()).equals(" ")) {
                // check if token is an operand
                if (Operand.check(token)) {
                    operandStack.push(new Operand(token));
                } else if (!Operator.check(token)) {
                    System.out.println("*****invalid token******");
                    throw new RuntimeException("*****invalid token******");
                } else {
                    Operator newOp = Operator.getOperator(token);
                    if (operatorStack.isEmpty()) {
                        operatorStack.push(newOp);
                    } else if (newOp.equals(Operator.getOperator("("))) {
                        operatorStack.push(newOp);
                    } else if (newOp.equals(Operator.getOperator(")"))) {
                        operatorStack.push(newOp);
                        Operand op = new Operand(process());
                        operandStack.push(op);
                    } else if (operatorStack.peek().priority() <= newOp.priority()) {
                        operatorStack.push(newOp);
                    } else {
                        operandStack.push(new Operand(process()));
                    }
                }
            }
        }
        while (!operatorStack.isEmpty()) {
            operandStack.push(new Operand(process()));
        }

        return operandStack.pop().getValue();

        // Control gets here when we've picked up all of the tokens; you must add
        // code to complete the evaluation - consider how the code given here
        // will evaluate the expression 1+2*3
        // When we have no more tokens to scan, the operand stack will contain 1 2
        // and the operator stack will have + * with 2 and * on the top;
        // In order to complete the evaluation we must empty the stacks (except
        // the init operator on the operator stack); that is, we should keep
        // evaluating the operator stack until it only contains the init operator;
        // Suggestion: create a method that takes an operator as argument and
        // then executes the while loop.


    }

    public int process() {
        Operand op1;
        Operand op2;
        Operator op;
        int solution = 0;

        if (!operatorStack.isEmpty()) {
            if (operatorStack.peek().equals(Operator.getOperator(")"))) {
                operatorStack.pop();

                while (!operatorStack.peek().equals(Operator.getOperator("("))) {
                    operandStack.push(new Operand(process()));
                }

                operatorStack.pop();
                solution = operandStack.pop().getValue();
            } else {
                op1 = operandStack.pop();
                op2 = operandStack.pop();
                op = operatorStack.pop();

                Operand result = op.execute(op2,op1);
                solution = result.getValue();
            }
        }

        return solution;
    }

}