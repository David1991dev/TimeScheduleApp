/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeschedule2.pkg0;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import static javafx.scene.input.KeyCode.S;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLACK;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import static javax.swing.text.html.HTML.Tag.S;
import static jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType.S;

public class FXMLDocumentController extends TimeSchedule20 implements Initializable {
    
//<editor-fold defaultstate="collapsed" desc="ANNOTATION">
    @FXML
            Label label;
    @FXML
            AnchorPane anpa0;
    @FXML
            AnchorPane anpa1;
    @FXML
            AnchorPane anpa2;
    @FXML
            TableView table;
    @FXML
            ChoiceBox choiceBoxStart;
    @FXML
            ChoiceBox choiceBoxEnd;
    @FXML
            ChoiceBox choiceBoxEndMinute;
    @FXML
            ChoiceBox choiceBoxStartMinute;
    @FXML
            TextField inputTask;
    @FXML   
            Color gray;
    
    @FXML 
            TableColumn <Task, String> taskCol;
    @FXML 
            TableColumn <Task, String> startCol;
    @FXML 
            TableColumn<Task, ChoiceBox> endCol;
//</editor-fold>
    
    ObservableList<Task> data = FXCollections.observableArrayList();
    
    ArrayList<ChoiceBox> TimeArrayList = new ArrayList<ChoiceBox>();
    ArrayList<ChoiceBox> minutesArrayList = new ArrayList<ChoiceBox>();
    ChoiceBox<Integer> tesztBox = new ChoiceBox<>(); 

    ArcGenerator arcs = new ArcGenerator();
    
    float angle = 0.0f;
    float duration;
    double degree = 0.0;
    byte num;
    double brightness = 0.2;
    double colour = 100;
  
        
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        TimeSchedule20 time = new TimeSchedule20();
    }
    
    @FXML
    private void updateButtonAction(ActionEvent event) {
        updateClocks();
    }
    


    
// TABLE 
    
    public void setDateTabel(){
       table.setEditable(true);
       
        tesztBox.getItems().add(10);
        tesztBox.getItems().add(30);
        
        
//       TableColumn taskCol = new TableColumn("Feladat");
//       TableColumn time = new TableColumn("Idő");
//       TableColumn startCol = new TableColumn("Kezdés");
       TableColumn endCol2 = new TableColumn("Teszt");
       
       data.add(new Task("Edzés", "7:30", tesztBox));
       taskCol.setCellFactory(TextFieldTableCell.forTableColumn());
       startCol.setCellFactory(ChoiceBoxTableCell.forTableColumn());
       endCol.setCellFactory(ChoiceBoxTableCell.forTableColumn());
       

       
       taskCol.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
       startCol.setCellValueFactory(new PropertyValueFactory<Task, String>("startTime"));
       endCol.setCellValueFactory(new PropertyValueFactory<Task, ChoiceBox>("endTime"));

       
       taskCol.setMinWidth(100);

       table.setItems(data);
       table.getColumns().addAll(endCol2);
//       table.getColumns().addAll(taskCol, startCol, endCol);
//       time.getColumns().addAll(startCol, endCol);
       
        System.out.println("Teszt módosítás");
    
    }
    
    private void choiceBoxMethod(){  
        TimeArrayList.forEach((t) -> {
            t.getItems().addAll(null,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,5);
        });
        
        minutesArrayList.forEach((t) -> {
            t.getItems().addAll(null,0,30);
        });
    }

// ARC    
    private void setArcVisual(Arc arc, int startH,int startM, int endH, int endM, double position) {
        try{
        arc.setCenterX(position);
        arc.setCenterY(220.0f);
        arc.setRadiusX(100.0f);
        arc.setRadiusY(arc.getRadiusX());
        arc.setType(ArcType.ROUND);
        arc.setFill(Color.hsb(colour, 1,brightness));
        arc.setStroke(BLACK);
        arc.setStrokeWidth(2);
        arc.setOpacity(0.5);
        setArcAngle(arc,startH,startM,endH,endM);
        anpa2.getChildren().add(arc);
        if (brightness<0.9){
            brightness+=0.05;
        }else{
            brightness = 0;
        }
        if (colour<900){
            colour+=50;
        }else{
            colour=100;
        }
        }
        catch(Exception e){
            System.out.println(e);
        }
    } 
    
    private void setArcAngle(Arc modArc, int startH, int startM, int endH, int endM){
        modArc.setStartAngle(setAngle(endH, endM));
        modArc.setLength(getLength(startH, startM, endH, endM));
    }

    private double setAngle(int hour, int minute ){
            switch (hour) {
            case 6 : angle = -90.0f;break;
            case 7 : angle = -120.0f;break;
            case 8 : angle = -150.0f;break;
            case 9 : angle = -180.0f;break;
            case 10 : angle = 150.0f;break;
            case 11 : angle = 120.0f;break;
            case 12 : angle = 90.0f;break;
            case 13 : angle = 60.0f;break;
            case 14 : angle = 30.0f;break;
            case 15 : angle = 0.0f;break;
            case 16 : angle = -30.0f;break;
            case 17 : angle = -60.0f;break;
            case 18 : angle = -90.0f;break;
            case 19 : angle = -120.0f;break;
            case 20: angle = -150.0f;break;
            case 21 : angle = -180.0f;break;
            case 22 : angle = 150.0f;break;
            case 23 : angle = 120.0f;break;
            case 0 : angle = 90.0f;break;
            case 1 : angle = 60.0f;break;
            case 2 : angle = 30.0f;break;
            case 3 : angle = 0.0f;break;
            case 4 : angle = -30.0f;break;
            case 5 : angle = -60.0f;break;
            }
            
            if (minute == 30){
                if (hour>=3 && hour<=9 || hour>=15 && hour<=21){
                    angle += 15.0f;
                }else{
                    angle -=15f;
                }
            }return angle;  
    }
    
    private double getLength(int hourIn, int minuteIn, int hourOut, int minuteOut){
        duration = 0.0f;
        int diffHour = hourOut- hourIn;
        int diffMinute = minuteOut - minuteIn;
        if (minuteIn != 30 && minuteOut != 30){
            if (diffMinute > 0){
                duration += 15.0f; 
            }else if(diffMinute <0){
                duration -=15.0f;
            }   
        }
        duration += diffHour*30.0f;
       return duration; 
    }
    
   private void setImage(){
       try {
       double y = 220.0;
       double x = 150.0;
       double x2 = 400.0;
       double sizeX = 230.0;
       double sizeY = 230.0;
       ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("clock.jpg")));
       ImageView imageView2 = new ImageView(new Image(getClass().getResourceAsStream("clock.jpg")));
       imageView.setFitHeight(sizeX);
       imageView.setFitWidth(sizeY);
       imageView.setLayoutX(x-(sizeX/2));
       imageView.setLayoutY(y-(sizeY/2));
       
       imageView2.setFitHeight(sizeX);
       imageView2.setFitWidth(sizeY);
       imageView2.setLayoutX(x2-(sizeX/2));
       imageView2.setLayoutY(y-(sizeY/2));
      
       anpa2.getChildren().addAll(imageView, imageView2);
       } catch (Exception e) {
           System.out.println(e);
       }



   }
    
   
//// Button
   
   @FXML
   private void addNewTaskButton (ActionEvent event){
       if(choiceBoxEnd.getValue() != null && choiceBoxEndMinute != null && choiceBoxStart != null && choiceBoxStartMinute != null){
       String startTime = choiceBoxStart.getValue() + ":" +choiceBoxStartMinute.getValue();
       String endTime = choiceBoxEnd.getValue() + ":" +choiceBoxEndMinute.getValue();
//       Task task = new Task(inputTask.getText(),startTime,endTime);
//       data.add(task);
       updateClocks();
       }
   }
   
    private void updateClocks() {
        anpa2.getChildren().clear();
        setImage();
        ArrayList<Arc> arcList = new ArrayList<>();
        int index = 0;
        arcList = arcs.createArc();

        for (Task actualTask : data) {
            List<String> arrListStartTime = new ArrayList<>(Arrays.asList(actualTask.getStartTime().split(":"))); 
//            List<String> arrListEndTime = new ArrayList<>(Arrays.asList(actualTask.getEndTime().getValue().split(":"))); 
            Integer startH = Integer.parseInt(arrListStartTime.get(0));
            Integer startM = Integer.parseInt(arrListStartTime.get(1)); 
//            Integer endH = Integer.parseInt(arrListEndTime.get(0));
//            Integer endM = Integer.parseInt(arrListEndTime.get(1));
//            System.out.println("startH: " + startH +"\n" +"start M: " +startM + "\n"+ "  endH: " + endH + "\n" +"\n endM" +endM);
//            setArcVisual(arcList.get(index),startH,startM,endH,endM,150.0f);
            index++;

        }
        
        
    }

   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        setImage();
        TimeArrayList.add(choiceBoxEnd);
        TimeArrayList.add(choiceBoxStart);
        minutesArrayList.add(choiceBoxStartMinute);
        minutesArrayList.add(choiceBoxEndMinute);
        choiceBoxMethod();
        setDateTabel();
   
    }    



    
}
