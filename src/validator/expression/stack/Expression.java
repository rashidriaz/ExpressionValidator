package validator.expression.stack;

public class Expression {
    private final MyStack stack;

    public Expression() {
        this.stack = new MyStack();
    }

    /*
    This method calls the to string method of the stack class and return the string of all the elements in teh stack
     */
    public String getExpression() {
        return stack.toString();
    }

    /*
    This method takes a string as an argument, validates its length, and then push every character of the string
     into the stack one by one
     */
    public void setExpression(String expression) throws ExpressionOverflowException {
        expression = expression.toUpperCase();
        if (expression.length() > 25) {
            throw new ExpressionOverflowException("Expression length exceeds the maximum allowed length of 25 characters");
        }//END OF IF
        for (int i = 0; i < expression.length(); i++) {
            stack.push(expression.charAt(i));
        }//END OF FOR LOOP
    }

    /*
    This method checks every element of the stack and validates it one by one. if any invalid
     character would be found in the stack, this method would return false
     */
    public boolean validateExpression() {
        MyStack temporaryStack = new MyStack();
        MyStack validationStack = new MyStack();
        int numberOfOperators = 0;
        int numberOfBrackets = 0;
        while (stack.hasNext()) {
            char characterFromStack;
            char characterFromValidationStack;
            characterFromStack = stack.pop();
            temporaryStack.push(characterFromStack);
            if (isNotABracket(characterFromStack) && !isAnOperator(characterFromStack)
                    && !isAVariable(characterFromStack) && !isANumber(characterFromStack)) {
                reInsertStackValues(temporaryStack);
                return false;
            }//END OF IF
            if (isAnOperator(characterFromStack)) {
                numberOfOperators++;
            }//END OF IF
            if (isAClosingBracket(characterFromStack)) {
                numberOfBrackets++;
                validationStack.push(characterFromStack);
            } else if (isAnOpeningBracket(characterFromStack)) {
                numberOfBrackets++;
                if (validationStack.isEmpty()) {
                    reInsertStackValues(temporaryStack);
                    return false;
                }//END OF IF
                characterFromValidationStack = validationStack.pop();
                if (!isACompletePair(characterFromStack, characterFromValidationStack)) {
                    reInsertStackValues(temporaryStack);
                    return false;
                }//END OF IF
            }// END OF IF /ELSE
        }//end of WHILE LOOP
        reInsertStackValues(temporaryStack);
        if (numberOfBrackets == 0 && numberOfOperators == 0) {
            return false;
        }
        return !validationStack.hasNext();
    }

    /*
    This method checks whether the given character is an opening bracket [, {, ( or not
     */
    private boolean isAnOpeningBracket(char character) {
        if (isNotABracket(character)) {
            return false;
        }
        return character == 40 || character == 91 || character == 123;
    }

    /*
    This method checks whether the given character is an opening bracket ], }, ) or not
     */
    public boolean isAClosingBracket(char character) {
        if (isNotABracket(character)) {
            return false;
        }
        return character == 41 || character == 93 || character == 125;
    }

    private boolean isACompletePair(char openingBracket, char closingBracket) {
        return (openingBracket == 40 && closingBracket == 41) ||
                (openingBracket == 91 && closingBracket == 93) ||
                (openingBracket == 123 && closingBracket == 125);
    }

    /*
    This method checks whether the given character is a bracket or not
     */
    private boolean isNotABracket(char character) {
        return character != 40 && character != 41 && character != 91
                && character != 93 && character != 123 && character != 125;
    }

    /*
    This method checks whether the given character is a valid number or not
     */
    private boolean isANumber(char character) {
        return character >= 48 && character <= 57;
    }

    /*
This method checks whether the given character is a valid operator or not
 */
    private boolean isAnOperator(char character) {
        return character == 42 || character == 43 || character == 45 || character == 47 || character == 94;
    }

    /*
    This method checks whether the given character is a valid variable name or not
 */
    private boolean isAVariable(char character) {
        return character >= 65 && character <= 90;
    }

    /*
    The functionality of this method is to re Insert all the characters back into
     the main stack which were popped out during the execution
     */
    private void reInsertStackValues(MyStack temporaryStack) {
        while (temporaryStack.hasNext()) {
            stack.push(temporaryStack.pop());
        }
    }

    /*
    This method checks whether the stack is empty or not
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /*
    The functionality of this method is to reset the stack
     */
    public void resetExpression() {
        stack.reset();
        System.out.println("\nExpression reset successful!!\n ");
    }
}
