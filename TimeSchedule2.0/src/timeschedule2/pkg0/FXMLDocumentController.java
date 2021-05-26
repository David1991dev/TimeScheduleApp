/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeschedule2.pkg0;

import java.awt.Component;
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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
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
import javax.swing.JLabel;
import static javax.swing.text.html.HTML.Tag.S;
import static jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType.S;

public class FXMLDocumentController extends TimeSchedule20 implements Initializable {
    
//<editor-fold defaultstate="collapsed" desc="ANNOTATION">
    @FXML
            Label label;
    @FXML
            Label afternoonLabel;
    @FXML
            Label morningLabel;
    @FXML
            Label morningTimeLabel;
    @FXML
            Label afternoonTimeLabel;
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
            TextField startMinuteInput;
    @FXML
            TextField endMinuteInput;
    @FXML   
            Color gray;
    
//</editor-fold>
    
    ObservableList<Task> data = FXCollections.observableArrayList();
    
    
    ArrayList<ChoiceBox> TimeArrayList = new ArrayList<ChoiceBox>();
    
    ArcGenerator arcs = new ArcGenerator();
    
    float angle = 0.0f;
    float duration;
    double degree = 0.0;
    byte num;
    double brightness = 0.2;
    double colour = 100;
    double morningClockPosition = 150;
    double afternoonClockPosition = 500;
  
        
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
          
//    data.add(new Task("Edzés", 16, 18));
//    data.add(new Task("Munka", 8, 15));
    data.add(new Task("Munka", 8,20, 16,59));
          

    TableColumn taskCol = new TableColumn("Task");
    TableColumn startTimeCol = new TableColumn("Start time");
    TableColumn endTimeCol = new TableColumn("End time");
    TableColumn startTimeHourCol = new TableColumn("Hour");
    TableColumn startTimeMinuteCol = new TableColumn("Minute");
    TableColumn endTimeHourCol = new TableColumn("Hour");
    TableColumn endTimeMinuteCol = new TableColumn("Minute");
    TableColumn colorCol = new TableColumn("Colour");
    TableColumn deleteCol = new TableColumn("Delete");
    
    taskCol.setMinWidth(190);
    
    
    table.getColumns().addAll(taskCol,startTimeCol,endTimeCol,deleteCol);
    startTimeCol.getColumns().addAll(startTimeHourCol,startTimeMinuteCol);
    endTimeCol.getColumns().addAll(endTimeHourCol,endTimeMinuteCol);
    
    
    

    taskCol.setCellFactory(TextFieldTableCell.forTableColumn());
    startTimeHourCol.setCellFactory(TextFieldTableCell.<Task, Integer>forTableColumn(new IntegerStringConverter()));
    startTimeMinuteCol.setCellFactory(TextFieldTableCell.<Task, Integer>forTableColumn(new IntegerStringConverter()));
    endTimeHourCol.setCellFactory(TextFieldTableCell.<Task, Integer>forTableColumn(new IntegerStringConverter()));
    endTimeMinuteCol.setCellFactory(TextFieldTableCell.<Task, Integer>forTableColumn(new IntegerStringConverter()));
    
    taskCol.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
    startTimeHourCol.setCellValueFactory(new PropertyValueFactory<Task, Integer>("startTimeHour"));
    startTimeMinuteCol.setCellValueFactory(new PropertyValueFactory<Task, Integer>("startTimeMinute"));
    endTimeHourCol.setCellValueFactory(new PropertyValueFactory<Task, Integer>("endTimeHour"));
    endTimeMinuteCol.setCellValueFactory(new PropertyValueFactory<Task, Integer>("endTimeMinute"));
        

    
    //<editor-fold defaultstate="collapsed" desc="Event handlers">

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

    startTimeHourCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Task, Integer>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Task, Integer> tableColumnEvent) {
                    int newValue = tableColumnEvent.getNewValue();
                    int tableCellPosition = tableColumnEvent.getTablePosition().getRow();
                    Task actualTask = tableColumnEvent.getTableView().getItems().get(tableCellPosition);

                    actualTask.setStartTimeHour(newValue);

                }
            });

    startTimeMinuteCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Task, Integer>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Task, Integer>tableCellEvent) {
                    int newValue = tableCellEvent.getNewValue();
                    int tableCellposition = tableCellEvent.getTablePosition().getRow();
                    Task actualTask = tableCellEvent.getTableView().getItems().get(tableCellposition);

                    actualTask.setStartTimeMinute(newValue);


                }
            });

    endTimeHourCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Task, Integer>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Task, Integer> tableColumnEvent) {
                    int oldValue = tableColumnEvent.getOldValue();
                    int actualTableCellPosition = tableColumnEvent.getTablePosition().getRow();

                    Task actualTask = tableColumnEvent.getTableView().getItems().get(actualTableCellPosition);
                    actualTask.setEndTimeHour(tableColumnEvent.getNewValue());
                }
            });

    endTimeMinuteCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Task, Integer>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Task, Integer> tableColumnEvent) {
                    int newValue = tableColumnEvent.getNewValue();
                    int tableCellPosition = tableColumnEvent.getTablePosition().getRow();
                    Task actuTask = tableColumnEvent.getTableView().getItems().get(tableCellPosition);

                    actuTask.setEndTimeMinute(newValue);

                }
            }
    );

    //</editor-fold>
    
    
    //Delete col.
    
    //<editor-fold defaultstate="collapsed" desc="Delete button">
    Callback<TableColumn<Task,String>,TableCell<Task, String>> deleteCallback =
            new Callback<TableColumn<Task, String>, TableCell<Task, String>>()
            {
                @Override
                public TableCell call (final TableColumn<Task, String> param)
                {
                    final TableCell<Task, String> cell = new TableCell<Task, String>()
                    {
                        final Button btn = new Button("Törlés");

                        @Override
                        public void updateItem (String item, boolean empty)
                        {
                            super.updateItem(item, empty);
                            if (empty){
                                setGraphic(null);
                                setText(null);
                            }else{
                                btn.setOnAction((ActionEvent event) ->
                                {
                                    Task actualTask = getTableView().getItems().get(getIndex());
                                    data.remove(actualTask);
                                }
                                );
                                setGraphic(btn);
                                setText(null);
                            }

                        }
                    };
                    return cell;

                }
            };
    //</editor-fold>
    
    deleteCol.setCellFactory(deleteCallback);
    
 
    table.setEditable(true);
    table.setItems(data);   

    }
    
    private void choiceBoxMethod(){  
        TimeArrayList.forEach((t) -> {
            t.getItems().addAll(null,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,5);
        });
    }

// ARC    
    private void setArcVisual(Arc arc, int startH,int startM,int endH, int endM) {
        try{
            if (endH > 15 && startH >= 12){
                arc.setCenterX(afternoonClockPosition);
            }else{
                arc.setCenterX(morningClockPosition);
            }
        
        arc.setCenterY(180.0f);
        arc.setRadiusX(100.0f);
        arc.setRadiusY(arc.getRadiusX());
        arc.setType(ArcType.ROUND);
        arc.setFill(Color.hsb(colour, 1,brightness));
        arc.setStroke(BLACK);
        arc.setStrokeWidth(2);
        arc.setOpacity(0.5);
        setArcAngle(arc,startH,startM,endH, endM);
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
    
    private void setArcAngle(Arc arc, int startH,int startM, int endH, int endM){
        arc.setStartAngle(setAngle(endH,endM));
        arc.setLength(getLength(startH,startM,endH,endM));
    }

    private double setAngle(int hour, int minute){
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
//            System.out.println(angle);
            float angleMinute;
            angleMinute = (minute*0.5f); 
            
                if (hour>=3 && hour<=9 || hour>=15 && hour<=21){
                    angleMinute *= -1;
                }else{
                    angleMinute = angleMinute;
                }
            angle += angleMinute;
                
        return angle;  
    }
    
    
    
    private double getLength(int startH, int startM, int endH, int endM){
        duration = 0.0f;
        float diffHour = (endH - startH) * 30.0f;
        duration =diffHour + endM*0.5f - startM *0.5f;
        
       return duration; 
    }
    
   private void setImage(){
       try {
       double y = 180.0;
       double x = morningClockPosition;
       double x2 = afternoonClockPosition;
       double sizeX = 230.0;
       double sizeY = 230.0;
       ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("clock5.png")));
       ImageView imageView2 = new ImageView(new Image(getClass().getResourceAsStream("clock5.png")));
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
       int startTimeMinute = Integer.parseInt(startMinuteInput.getText());
       int endTimeMinute = Integer.parseInt(endMinuteInput.getText());
       Task task = new Task(inputTask.getText(),startTime,startTimeMinute,endTime,endTimeMinute);
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
                        
            int startH = actualTask.getStartTimeHour();
            int startM = actualTask.getStartTimeMinute();
            
            int endH = actualTask.getEndTimeHour();
            int endM = actualTask.getEndTimeMinute();
//            System.out.println(startH + " " + startM + " " + endH + " " + endM);
            
            setArcVisual(arcList.get(index),startH,startM,endH,endM);
            index++;            

        }
        
        
    }
    
    
    public void setLabelsPosition(){
        
        afternoonLabel.setLayoutX(morningClockPosition - (afternoonLabel.getPrefWidth()/2));
        morningTimeLabel.setLayoutX(morningClockPosition - (morningTimeLabel.getPrefWidth()/2));
        
        morningLabel.setLayoutX(afternoonClockPosition- (morningLabel.getPrefWidth()/2));        
        afternoonTimeLabel.setLayoutX(afternoonClockPosition - (afternoonLabel.getPrefWidth()/2));
    }

   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setLabelsPosition();
        setImage();
        TimeArrayList.add(choiceBoxEnd);
        TimeArrayList.add(choiceBoxStart);
        choiceBoxMethod();
        setDateTabel();

   
    }    



    
}
