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
    int startTime;
    int endTime;


    

    public Task(String task, int startTime, int endTime) {
        this.task = task;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    
    public String getTask() {
        return task;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime (int endTime) {
        this.endTime = endTime;
    }
  
}