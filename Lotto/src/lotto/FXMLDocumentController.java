package lotto;


import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class FXMLDocumentController implements Initializable {
    private final int MAX = 90; //ha ide írjuk akkor bárhol elérjük ezen a fájlon belül
    private final int MIN = 1;
   
    
//<editor-fold defaultstate="collapsed" desc="Class variables">
    private int selectNumb1; //User number
    private int selectNumb2;
    private int selectNumb3;
    private int selectNumb4;
    private int selectNumb5;
    private int eredmenyInt;
    
    private int randomNumber1;
    private int randomNumber2;
    private int randomNumber3;
    private int randomNumber4;
    private int randomNumber5;
    
    private final String WN0 = "Sajnos most nem nyert";
    private final String WN1 = "1-es találatod volt";
    private final String WN2 = "2-es találatod volt";
    private final String WN3 = "3-as találatod volt";
    private final String WN4 = "4-es találatod volt";
    private final String WN5 = "5-ös találatod volt";
    
    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="@FXML items">
    @FXML
    ArrayList<Integer> str1 = new ArrayList<Integer>();
    ArrayList<Label> lab = new ArrayList<Label>();
    Set<Integer>selectedNumbers = new HashSet<>();
    ArrayList<Integer>userNumbers = new ArrayList<Integer>();
    ArrayList<Integer>randomNumbers = new ArrayList<Integer>();

    
    @FXML
    private Label genNumber1;
    @FXML
    private Label genNumber2;
    @FXML
    private Label genNumber3;
    @FXML
    private Label genNumber4;
    @FXML
    private Label genNumber5;
    @FXML
    private Label eredmeny;
    @FXML
    private Pane base;
    @FXML
    private Pane alertPane;
    @FXML
    private Label alertText;
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private TextField textField3;
    @FXML
    private TextField textField4;
    @FXML
    private TextField textField5;
    @FXML
    private HBox circleBox;
    
//</editor-fold>


    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        //Clear arrays
        str1.clear();
        selectedNumbers.clear();
        userNumbers.clear();
        randomNumbers.clear();
       
        
        alertPane.setVisible(false);
        calculate();
    }
        
    private void calculate(){
    
        //<editor-fold defaultstate="collapsed" desc=""Are user inputs numbers?"">
        try {
            //<editor-fold defaultstate="collapsed" desc=>
            selectNumb1 = Integer.parseInt(textField1.getText());
            selectNumb2 = Integer.parseInt(textField2.getText());
            selectNumb3 = Integer.parseInt(textField3.getText());
            selectNumb4 = Integer.parseInt(textField4.getText());
            selectNumb5 = Integer.parseInt(textField5.getText());
        } catch (Exception e) {
            failure("Nincs szám megadva");
            return;
        }
        //</editor-fold>

        // HashSet allows only 1 record fow each element
        //<editor-fold defaultstate="collapsed" desc="Are user inputs uniques?">
        selectedNumbers.add(selectNumb1);
        selectedNumbers.add(selectNumb2);
        selectedNumbers.add(selectNumb3);
        selectedNumbers.add(selectNumb4);
        selectedNumbers.add(selectNumb5);

        if (selectedNumbers.size() <5) {
             failure("Kevesebb különböző szám mint 5"); 
             return;
         }
        //</editor-fold>

            
        //<editor-fold defaultstate="collapsed" desc="Are user numbers between MIN and MAX?">
        userNumbers = new ArrayList<Integer>(selectedNumbers);       
        for (Integer number : userNumbers){
            if (number < MIN || number >MAX){
                failure("Rossz szám lett megadva");
                return;
            }
        }
        //</editor-fold>
              
        //<editor-fold defaultstate="collapsed" desc="Generate random numbers">
        randomNumber1 = getRandomNumber();
        randomNumber2 = getRandomNumber();
        randomNumber3 = getRandomNumber();
        randomNumber4 = getRandomNumber();
        randomNumber5 = getRandomNumber();

        circleBox.setVisible(true);
        
        randomNumbers.add(randomNumber1);
        randomNumbers.add(randomNumber2);
        randomNumbers.add(randomNumber3);
        randomNumbers.add(randomNumber4);
        randomNumbers.add(randomNumber5);
        
        Collections.sort(randomNumbers);
        
        genNumber1.setText(String.valueOf(randomNumbers.get(0)));
        genNumber2.setText(String.valueOf(randomNumbers.get(1)));
        genNumber3.setText(String.valueOf(randomNumbers.get(2)));
        genNumber4.setText(String.valueOf(randomNumbers.get(3)));
        genNumber5.setText(String.valueOf(randomNumbers.get(4)));
        //</editor-fold>
        
        checkNumbers();
        return;

    }
    
    
    @FXML
    private void onAlertButton (ActionEvent event) {
        base.setDisable(false);
        base.setOpacity(1);
        alertPane.setVisible(false);
        System.out.println("Rákattintottál az onAlertButton-ra");
        
    }
    
    private void failure(String text){
        //        We are gathering the numbers
            System.out.println("Elkaptalak");
            base.setOpacity(0.3);
            base.setDisable(true);
            alertPane.setVisible(true);
            alertText.setText(text);
            circleBox.setVisible(false);
    
    }
    
    private void checkNumbers(){
        randomNumbers.retainAll(userNumbers);
        System.out.println(randomNumbers);
        int result = 0;
        result = randomNumbers.size();
        if (result>0) {
            switch (result) {
            case 0 : eredmeny.setText(WN0);break;
            case 1 : eredmeny.setText(WN1);break;
            case 2 : eredmeny.setText(WN2);break;
            case 3 : eredmeny.setText(WN3);break;
            case 4 : eredmeny.setText(WN4);break;
            case 5 : eredmeny.setText(WN5);break;   
            }   
        }
        
    //        //<editor-fold defaultstate="collapsed" desc="Check the result by For cycle">
//        result =  0;
//        for (int i = 0; i < userNumbers.size(); i++) {
//            if (userNumbers.get(i) == Integer.parseInt(genNumber1.getText()) ||
//                    userNumbers.get(i) == Integer.parseInt(genNumber2.getText()) ||
//                    userNumbers.get(i) == Integer.parseInt(genNumber3.getText()) ||
//                    userNumbers.get(i) == Integer.parseInt(genNumber4.getText()) ||
//                    userNumbers.get(i) == Integer.parseInt(genNumber5.getText())) {
//            }result++;
//        }
//        //</editor-fold>
    }
    
    
    private int getRandomNumber(){   
        
        int random = (int) (Math.random() * MAX)+MIN;
        if (str1.contains(random)){
            random = getRandomNumber();
        }
        str1.add(random);
        return random;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}




