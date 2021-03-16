package validator.expression.stack;

public class ExpressionOverflowException extends Throwable{
   private final String message;

   public ExpressionOverflowException(String message){
       super(message);
       this.message = message;
   }

    @Override
    public String getMessage() {
        return this.message;
    }
}
