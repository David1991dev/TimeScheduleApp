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
    
    String task;
    ChoiceBox<Integer> startHour;
    ChoiceBox<Integer> startMinute;
    ChoiceBox<Integer> endHour;
    ChoiceBox<Integer> endMinute;
    

    
    Task(String task, ChoiceBox<Integer> startH, ChoiceBox<Integer> startM, ChoiceBox<Integer> endH, ChoiceBox<Integer> endM){
        this.task = task;
        this.startHour = startH;
        this.startMinute = startM;
        this.endHour = endH;
        this.endMinute = endM;
        
    }

    public ChoiceBox<Integer> getStartHour() {
        return startHour;
    }

    public void setStartHour(ChoiceBox<Integer> startHour) {
        this.startHour = startHour;
    }

    public ChoiceBox<Integer> getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(ChoiceBox<Integer> startMinute) {
        this.startMinute = startMinute;
    }

    public ChoiceBox<Integer> getEndHour() {
        return endHour;
    }

    public void setEndHour(ChoiceBox<Integer> endHour) {
        this.endHour = endHour;
    }

    public ChoiceBox<Integer> getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(ChoiceBox<Integer> endMinute) {
        this.endMinute = endMinute;
    }
    
    public void setTask(String task){
        this.task = task;
    }
    
    public String getTask(){
        return task;
    }
    
}