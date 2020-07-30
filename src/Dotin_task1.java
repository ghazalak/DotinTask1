//package com.tutorialspoint.xml;
import java.math.BigDecimal;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
public class Dotin_task1 {
    static void validate(BigDecimal Amount, Integer Duration) throws NegativeValueException {
        if(Amount.compareTo(BigDecimal.ZERO) < 0)
            throw new NegativeValueException("Negative Amount");
        if(Duration < 0)
            throw new NegativeValueException("Negative Duration");
    }
    public static void main(String[] args) {
        String customerNumber;
        BigDecimal amount;
        int duration;
        String depositTypeValue;
        System.out.println("DOTIN TASK1!!!");
        try {
            File inputFile = new File("src/xml.txt");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("deposit");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    try {
                        try{
                            validate(BigDecimal.valueOf(Long.parseLong(eElement
                                            .getElementsByTagName("depositBalance")
                                            .item(0)
                                            .getTextContent()))
                                , Integer.valueOf(eElement
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
                            try {
                                DepositType depositType = (DepositType) Class.forName(depositTypeValue).newInstance();
                                depositType.setRate();
                                Deposit deposit = new Deposit(customerNumber, amount, duration, depositType);
                                deposit.calculate_deposit_interest();
                            } catch (ClassNotFoundException e) {
                                throw new WrongDepositTypeException("Wrong Deposit Type");
                            }
                        } catch (NumberFormatException NumericExceptio) {
                            throw new NumericException("Duration should be numeric");
                        }
                    }catch(Exception m){System.out.println("Exception occured: " + m);}
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
