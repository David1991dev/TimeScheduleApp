/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeschedule2.pkg0;

import java.awt.geom.Rectangle2D;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author horva
 */
public class TimeSchedule20 extends Application {
    
    Scene scene;
    public Stage stage1;
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        
        scene = new Scene(root,1000,500);
        scene.getStylesheets().add("/CSS/mycss.css");
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Time Schedule");
//        stage.setMaximized(true);
//        stage.setFullScreen(true);
        System.out.println(stage.getWidth());
    }
    

    
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
}
