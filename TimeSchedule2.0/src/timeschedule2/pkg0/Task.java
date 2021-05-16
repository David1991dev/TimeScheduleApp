/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeschedule2.pkg0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ChoiceBox;

/**
 *
 * @author horva
 */
public class Task {
    
    private final SimpleStringProperty task;
    private final SimpleStringProperty startTime;
    private ChoiceBox<Integer> endTime;
    
    
    Task(String task, String startT, ChoiceBox<Integer> endTime){
        this.task = new SimpleStringProperty(task);
        this.startTime = new SimpleStringProperty(startT);
        this.endTime = endTime;
        
    }
    
    
    public String getTask() {
        return task.get();
    }

    public String getStartTime() {
        
        return startTime.get();
    }

    public ChoiceBox<Integer> getEndTime() {
        return endTime;
    }
    
    public void setTask(String tasK){
        task.set(tasK);
    } 
    
    public void setStartTime(String time){
        startTime.set(time);
    } 
    
    public void setEndTime(ChoiceBox<Integer> endTime){
        this.endTime = endTime;
    } 

}

