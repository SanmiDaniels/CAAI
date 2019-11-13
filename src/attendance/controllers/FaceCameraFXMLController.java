/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.controllers;

import attendance.services.FaceRecognizer;
import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.OpenCVFrameGrabber;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author mofoluwaso
 */
public class FaceCameraFXMLController implements Initializable {

     
    @FXML
    private ImageView frame;
   
    private boolean cameraActive;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    } 
    
    @FXML
    public void startCamera(){
         
//        try {
//           new FaceRecognizer().recognizeFromCam();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(FaceCameraFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//        }
       
    }
    
    
    
   
    
}
