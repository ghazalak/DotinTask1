import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class writeInFile {
    private ArrayList<Deposit> depositArrayList;
    private Comparator comparator = Collections.reverseOrder();
    public writeInFile(ArrayList<Deposit> depositArrayList){
        try {
            FileWriter fileWriter = new FileWriter("src/final.txt");
            depositArrayList.sort(comparator);
            for(Deposit str: depositArrayList) {
                fileWriter.write(str.getCustomerNumber() + "#"
                        + str.getDepositInterest()
                        + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
