import java.math.BigDecimal;

public class BusinessException extends Exception{
    public BusinessException(String S){
        super(S);
    }
    static void validateAmount(BigDecimal Amount) throws BusinessException {
        if(Amount.compareTo(BigDecimal.ZERO) < 0)
            throw new BusinessException("Negative amount: " + Amount);
    }
    static void validateDuration(Integer Duration) throws BusinessException {
        if(Duration < 0)
            throw new BusinessException("Negative duration: " + Duration);
    }

}
