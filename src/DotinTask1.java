import java.math.BigDecimal;
import java.util.ArrayList;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
public class DotinTask1 {
    public static void main(String[] args) {
        String customerNumber;
        BigDecimal amount;
        Integer duration;
        String depositTypeValue;
        ArrayList<Deposit> depositArrayList = new ArrayList<>();
        String path = "src/xml.txt";
        ReadFile readFile = new ReadFile(path);
        try {
            NodeList nList = readFile.getnList();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    try{
                        setDepositParameter setDepositParameter = new setDepositParameter(eElement);
                        amount = setDepositParameter.getAmount();
                        duration = setDepositParameter.getDuration();
                        new validateAmount(amount);
                        new validateDuration(duration);
                        customerNumber = setDepositParameter.getCustomerNumber();
                        depositTypeValue = setDepositParameter.getDepositTypeValue();
                        System.out.println(customerNumber + " " + amount + " " + duration);
                        DepositType depositType = (DepositType) Class.forName(depositTypeValue).newInstance();
                        depositType.setRate();
                        Deposit deposit = new Deposit(customerNumber, amount, duration, depositType);
                        deposit.calculateDepositInterest();
                        depositArrayList.add(deposit);
                    } catch (ClassNotFoundException
                            | BusinessException
                            | NullPointerException e) {
                        System.out.println(e);
                    }
                }
            }
            new writeInFile(depositArrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
