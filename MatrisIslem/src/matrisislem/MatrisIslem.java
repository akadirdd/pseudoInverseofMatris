package matrisislem;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class MatrisIslem extends Application {

    public static void main(String[] args) {
        
        launch(args);
        
        
        
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        
        Stage window = primaryStage;
        Parent root=FXMLLoader.load(getClass().getResource("Anasayfa.fxml"));
        
        window.setTitle("Pseudo-İnverse");
        window.setScene(new Scene(root,1000,750));
        window.show();
        
    }
    
}
