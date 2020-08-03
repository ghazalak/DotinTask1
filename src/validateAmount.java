import java.math.BigDecimal;

public class validateAmount {
    public validateAmount(BigDecimal amount) throws NegativeAmountException {
        if(amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeAmountException("Negative amount: " + amount);
        }
    }
}
