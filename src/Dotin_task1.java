import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
public class Dotin_task1 {
    public static void main(String[] args) {
        String customerNumber;
        BigDecimal amount;
        int duration;
        String depositTypeValue;
        ArrayList<Deposit> ArrList = new ArrayList<>();
        Comparator comparator = Collections.reverseOrder();
        try {
            File inputFile = new File("src/xml.txt");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("deposit");
            System.out.println("----------------------------");
            FileWriter fileWriter = new FileWriter("src/final.txt");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    try{
                        BusinessException.validateAmount(BigDecimal.valueOf(Long.parseLong(eElement
                                .getElementsByTagName("depositBalance")
                                .item(0)
                                .getTextContent())));
                        BusinessException.validateDuration(Integer.valueOf(eElement
                                .getElementsByTagName("durationInDays")
                                .item(0)
                                .getTextContent()));

                        customerNumber = String.valueOf(eElement
                                    .getElementsByTagName("customerNumber")
                                    .item(0)
                                    .getTextContent());
                        depositTypeValue = String.valueOf(eElement
                                .getElementsByTagName("depositType")
                                .item(0)
                                .getTextContent());
                        amount = BigDecimal.valueOf(Long.parseLong(eElement
                                .getElementsByTagName("depositBalance")
                                .item(0)
                                .getTextContent()));
                        duration = Integer.parseInt(eElement
                                .getElementsByTagName("durationInDays")
                                .item(0)
                                .getTextContent());
                        System.out.println(customerNumber + " " + amount + " " + duration);
                        DepositType depositType = (DepositType) Class.forName(depositTypeValue).newInstance();
                        depositType.setRate();
                        Deposit deposit = new Deposit(customerNumber, amount, duration, depositType);
                        deposit.calculateDepositInterest();
                        ArrList.add(deposit);
                    } catch (ClassNotFoundException
                            | NumberFormatException
                            | BusinessException
                            | NullPointerException e) {
                        System.out.println(e);
                    }
                }
            }
            ArrList.sort(comparator);
            for(Deposit str: ArrList) {
                fileWriter.write(str.getCustomerNumber() + "#"
                        + str.calculateDepositInterest()
                        + System.lineSeparator());
            }
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
