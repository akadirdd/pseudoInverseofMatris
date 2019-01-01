/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrisislem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kadir Han
 */
public class AnasayfaController implements Initializable {
    @FXML private RadioButton radioOto,radioMan;
    @FXML private Button buttonBaslat;
    @FXML private ToggleGroup group;
    @FXML private Label bilgi;
    int a=0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        group=new ToggleGroup();
        radioOto.setToggleGroup(group);
        radioMan.setToggleGroup(group);
        
        
    }    
    public void handleradio() throws Exception{
            if(radioOto.isSelected()){
                a=1;
                bilgi.setText("Random bir matris oluşturulur ve tersi alınır!");
            }else if(radioMan.isSelected()){
                a=2;
                bilgi.setText("Matris kullanıcı tarafından oluşturulur ve tersi alınır!");
            }
        
    }
    public void handleonClick(ActionEvent event) throws Exception{
        if(a==1){
            Parent layout=FXMLLoader.load(getClass().getResource("OtomatikIslemler.fxml"));  
            
            Scene gris=new Scene(layout);
            Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(gris);
            window.show();
        }else if(a==2){
            Parent layout1=FXMLLoader.load(getClass().getResource("ManualIslemler.fxml"));  
            Scene gris1=new Scene(layout1);
            Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(gris1);
            window.show();
        }
    }
    
}
