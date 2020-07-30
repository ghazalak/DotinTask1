import java.math.BigDecimal;
import java.math.RoundingMode;

public class Deposit implements Comparable<Deposit> {
    private String customerNumber;
    private BigDecimal amount;
    private int duration;
    private DepositType depositType;


    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public DepositType getDepositType() {
        return depositType;
    }

    public void setDepositType(DepositType depositType) {
        this.depositType = depositType;
    }

    public Deposit(String customerNumber, BigDecimal amount, int duration, DepositType depositType) {
        this.customerNumber = customerNumber;
        this.amount = amount;
        this.duration = duration;
        this.depositType = depositType;
    }

    BigDecimal calculateDepositInterest() {
        BigDecimal rate = new BigDecimal(depositType.getRate());
        BigDecimal duration = new BigDecimal(getDuration());
        return amount.multiply(duration).multiply(rate).divide(new BigDecimal(36500), RoundingMode.HALF_UP);
    }

    @Override
    public int compareTo(Deposit o) {
        return this.calculateDepositInterest().compareTo(o.calculateDepositInterest());
    }
}
