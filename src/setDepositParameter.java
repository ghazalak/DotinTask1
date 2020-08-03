import org.w3c.dom.Element;

import java.math.BigDecimal;

public class setDepositParameter {
    private String customerNumber;
    private BigDecimal amount;
    private Integer duration;
    private String depositTypeValue;

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDepositTypeValue() {
        return depositTypeValue;
    }

    public void setDepositTypeValue(String depositTypeValue) {
        this.depositTypeValue = depositTypeValue;
    }

    public setDepositParameter(Element eElement){
        try {
            this.amount = BigDecimal.valueOf(Long.parseLong(eElement
                    .getElementsByTagName("depositBalance")
                    .item(0)
                    .getTextContent()));
            this.duration = Integer.parseInt(eElement
                    .getElementsByTagName("durationInDays")
                    .item(0)
                    .getTextContent());
            this.customerNumber = String.valueOf(eElement
                    .getElementsByTagName("customerNumber")
                    .item(0)
                    .getTextContent());
            this.depositTypeValue = String.valueOf(eElement
                    .getElementsByTagName("depositType")
                    .item(0)
                    .getTextContent());
        } catch ( NumberFormatException e) {
            System.out.println(e);
        }

    }
}
