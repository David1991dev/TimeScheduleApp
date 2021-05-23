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


public class Task {
    
    String task;
    int startTimeHour;
    int startTimeMinute;
    int endTimeHour;
    int endTimeMinute;

    public Task(String task, int startTimeHour, int startTimeMinute, int endTimeHour, int endTimeMinute) {
        this.task = task;
        this.startTimeHour = startTimeHour;
        this.startTimeMinute = startTimeMinute;
        this.endTimeHour = endTimeHour;
        this.endTimeMinute = endTimeMinute;
    }


    

    public Task(String task, int startTime, int endTime) {
        this.task = task;
        this.startTimeHour = startTime;
        this.endTimeHour = endTime;
    }
        
    
    public String getTask() {
        return task;
    }

    public int getStartTimeHour() {
        return startTimeHour;
    }

    public int getEndTimeHour() {
        return endTimeHour;
    }

    public int getStartTimeMinute() {
        return startTimeMinute;
    }

    public int getEndTimeMinute() {
        return endTimeMinute;
    }
    
    public void setTask(String task) {
        this.task = task;
    }

    public void setStartTimeHour(int startTime) {
        this.startTimeHour = startTime;
    }

    public void setEndTimeHour (int endTime) {
        this.endTimeHour = endTime;
    }

    public void setStartTimeMinute(int startTimeMinute) {
        this.startTimeMinute = startTimeMinute;
    }

    public void setEndTimeMinute(int endTimeMinute) {
        this.endTimeMinute = endTimeMinute;
    }
    
    
  
}