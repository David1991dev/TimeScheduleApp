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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
import javafx.util.converter.IntegerStringConverter;
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
    
//</editor-fold>
    
    ObservableList<Task> data = FXCollections.observableArrayList();
    
    
    ArrayList<ChoiceBox> TimeArrayList = new ArrayList<ChoiceBox>();
    ArrayList<ChoiceBox> minutesArrayList = new ArrayList<ChoiceBox>();
    
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
          
    data.add(new Task("Edzés", 16, 18));
    data.add(new Task("Munka", 8, 15));
          

    TableColumn taskCol = new TableColumn("Feladat");
    TableColumn startTimeCol = new TableColumn("Kezdési idő");
    TableColumn endTimeCol = new TableColumn("Befejezési idő");
    
    taskCol.setMinWidth(150);

    
    table.getColumns().addAll(taskCol,startTimeCol,endTimeCol);
    

    taskCol.setCellFactory(TextFieldTableCell.forTableColumn());
    startTimeCol.setCellFactory(TextFieldTableCell.<Task, Integer>forTableColumn(new IntegerStringConverter()));
    endTimeCol.setCellFactory(TextFieldTableCell.<Task, Integer>forTableColumn(new IntegerStringConverter()));
    
    taskCol.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
    startTimeCol.setCellValueFactory(new PropertyValueFactory<Task, Integer>("startTime"));
    endTimeCol.setCellValueFactory(new PropertyValueFactory<Task, Integer>("endTime"));
        

    

        taskCol.setOnEditCommit(
        new EventHandler<TableColumn.CellEditEvent<Task, String>>() {
        @Override
        public void handle(TableColumn.CellEditEvent<Task, String> tableColumnEvent) {
            String newValue = tableColumnEvent.getNewValue();
            int actualTableCellPosition = tableColumnEvent.getTablePosition().getRow();
            Task actTask = tableColumnEvent.getTableView().getItems().get(actualTableCellPosition);
            
            actTask.setTask(tableColumnEvent.getNewValue());
           
        }
    });
        
        startTimeCol.setOnEditCommit(
        new EventHandler<TableColumn.CellEditEvent<Task, Integer>>() {
        @Override
        public void handle(TableColumn.CellEditEvent<Task, Integer> tableColumnEvent) {
            int newValue = tableColumnEvent.getNewValue();
            int tableCellPosition = tableColumnEvent.getTablePosition().getRow();
            Task actualTask = tableColumnEvent.getTableView().getItems().get(tableCellPosition);
            
            actualTask.setStartTime(newValue);
           
        }
    });

        endTimeCol.setOnEditCommit(
        new EventHandler<TableColumn.CellEditEvent<Task, Integer>>() {
            @Override           
            public void handle(TableColumn.CellEditEvent<Task, Integer> tableColumnEvent) {
              int oldValue = tableColumnEvent.getOldValue();           
              int actualTableCellPosition = tableColumnEvent.getTablePosition().getRow();

              Task actualTask = tableColumnEvent.getTableView().getItems().get(actualTableCellPosition);
              actualTask.setEndTime(tableColumnEvent.getNewValue());
            }
        });
        


    
    table.setEditable(true);
    table.setItems(data);   

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
    private void setArcVisual(Arc arc, int startH,int endH) {
        try{
            if (startH > 15){
                arc.setCenterX(400.0f);
            }else{
                arc.setCenterX(150.0f);
            }
        
        arc.setCenterY(220.0f);
        arc.setRadiusX(100.0f);
        arc.setRadiusY(arc.getRadiusX());
        arc.setType(ArcType.ROUND);
        arc.setFill(Color.hsb(colour, 1,brightness));
        arc.setStroke(BLACK);
        arc.setStrokeWidth(2);
        arc.setOpacity(0.5);
        setArcAngle(arc,startH,endH);
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
    
    private void setArcAngle(Arc arc, int startH, int endH){
        arc.setStartAngle(setAngle(endH));
        arc.setLength(getLength(startH, endH));
    }

    private double setAngle(int hour){
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
            
//            if (minute == 30){
//                if (hour>=3 && hour<=9 || hour>=15 && hour<=21){
//                    angle += 15.0f;
//                }else{
//                    angle -=15f;
//                }
//            }
        return angle;  
    }
    
    private double getLength(int startH,int endH){
        duration = 0.0f;
        int diffHour = endH- startH;
//        int diffMinute = minuteOut - minuteIn;
//        if (minuteIn != 30 && minuteOut != 30){
//            if (diffMinute > 0){
//                duration += 15.0f; 
//            }else if(diffMinute <0){
//                duration -=15.0f;
//            }   
//        }
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
//       if(choiceBoxEnd.getValue() != null && choiceBoxEndMinute != null && choiceBoxStart != null && choiceBoxStartMinute != null){
       int startTime = (int) choiceBoxStart.getValue();
       int endTime = (int) choiceBoxEnd.getValue();
       Task task = new Task(inputTask.getText(),startTime,endTime);
       data.add(task);
       updateClocks();
       }
   
   
    private void updateClocks() {
        anpa2.getChildren().clear();
        setImage();
        ArrayList<Arc> arcList = new ArrayList<>();
        int index = 0;
        arcList = arcs.createArc();

        for (Task actualTask : data) {
                        
            int startH = actualTask.getStartTime();
            int endH = actualTask.getEndTime();
            setArcVisual(arcList.get(index),startH,endH);
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
