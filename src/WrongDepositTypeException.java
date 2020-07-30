public class WrongDepositTypeException extends ClassNotFoundException{
    public WrongDepositTypeException(String DepositTypeName){
        super(DepositTypeName);
    }
}
