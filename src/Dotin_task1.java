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
    public static void main(String[] args) {
        String customer_number;
        BigDecimal amount;
        int duration;
        String deposit_type;
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
                    customer_number = String.valueOf(eElement
                            .getElementsByTagName("customerNumber")
                            .item(0)
                            .getTextContent());
                    deposit_type = String.valueOf(eElement
                            .getElementsByTagName("depositType")
                            .item(0)
                            .getTextContent());
                    amount = BigDecimal.valueOf(Long.parseLong(eElement
                            .getElementsByTagName("depositBalance")
                            .item(0)
                            .getTextContent()));
                    duration = Integer.valueOf(eElement
                            .getElementsByTagName("durationInDays")
                            .item(0)
                            .getTextContent());
                    System.out.println(customer_number + " " + amount + " " + duration);
                    DepositType depositType = (DepositType) Class.forName(deposit_type).newInstance();
                    depositType.setRate();
                    Deposit deposit = new Deposit(customer_number, amount, duration, depositType);
                    deposit.calculate_deposit_interest();
                    System.out.println(deposit.calculate_deposit_interest());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
