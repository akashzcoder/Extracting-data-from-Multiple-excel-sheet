package RM_VariableExtraction;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController{
	public String fileName=null;
	public String fileName1=null;
	public String formname=null;
	public String newfileName=null;
	@FXML
	private Button btnOpenDirectoryChooser;
	@FXML
	private Button btn2;
	@FXML
	private Label labelSelectedDirectory;
	
	
	
	public void Button1Action(ActionEvent e){
		Label labelSelectedDirectory = new Label();
		Stage primaryStage = null;
		
        Button btnOpenDirectoryChooser = new Button();
        btnOpenDirectoryChooser.setText("Open DirectoryChooser");
        btnOpenDirectoryChooser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	String path=null;
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory = 
                        directoryChooser.showDialog(primaryStage);
                
                if(selectedDirectory == null){
                    labelSelectedDirectory.setText("No Directory selected");
                }else{
                	path=selectedDirectory.getAbsolutePath();
                    labelSelectedDirectory.setText(selectedDirectory.getAbsolutePath());
                }
            }
        });
		/*FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(
						new ExtensionFilter("Word 97-2003 Document","*.doc"));
		File selectedFile = fc.showOpenDialog(null);
		String path=selectedFile.getParent();
		if(selectedFile != null){
			//fileName = path + selectedFile.getName();
			fileName = path;
			fileName1=fileName.replace("\\","/");
			System.out.println(fileName1);
			newfileName= fileName.replace("\\","\\\\");
			}*/
	}
	public void Button2Action(ActionEvent e) throws IOException{
		VariableExtraction obj=new VariableExtraction();
		obj.variable(fileName1,newfileName);
	}
	
	
	
	
	

	
}
