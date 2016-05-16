package application;

import java.io.File;
import java.io.IOException;
import application.VariableExtraction;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @Akash Z
 */
public class directory extends Application {

	
	public String fileName=null;
	public String fileName1=null;
	public String formname=null;
	public String newfileName=null;
	public String path = null;
	public Boolean error = false;
    @Override
    public void start(final Stage primaryStage) {
        
        final Label labelSelectedDirectory = new Label();
        final Label header1 = new Label();
        header1.setText("CUSTOMIZED TOOL");
        header1.setFont(new Font("Verdana", 30));
        //Image image = new Image(getClass().getResourceAsStream("C:\\Users\\akash\\Desktop\\index.jpg"));
 
        Button btnOpenDirectoryChooser = new Button();
        btnOpenDirectoryChooser.setText("Open Folder");
        btnOpenDirectoryChooser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory = 
                        directoryChooser.showDialog(primaryStage);
                
                if(selectedDirectory == null){
                    labelSelectedDirectory.setText("No Directory selected");
                }else{
                    labelSelectedDirectory.setText(selectedDirectory.getAbsolutePath());
                    path=selectedDirectory.getAbsolutePath();
                    fileName = path;
        			fileName1=fileName.replace("\\","/");
        			System.out.println(fileName1);
        			newfileName= fileName.replace("\\","\\\\");
        			System.out.println(newfileName);
                }
            }
        });
       
        Button ExtractVariables = new Button();
        ExtractVariables.setText("Extract Variables");
        {
        	ExtractVariables.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					VariableExtraction obj=new VariableExtraction();
					try {
						error = obj.variable(fileName1,newfileName);
						if(error ==false){
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Error Message");
							alert.setHeaderText(null);
							alert.setContentText("Please check the input folder: All files must be in *.doc format or else go through the read me file once. ");

							alert.showAndWait();
						}else
						{
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Success!");
							alert.setHeaderText(null);
							alert.setContentText("Please find the output excell sheet - 'Variables' in the same folder.");

							alert.showAndWait();							
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
        	});
        }
        //Button for extraction of variables has ended
        //Start of Button for criteria extraction
        Button criteriaExtraction = new Button();
        criteriaExtraction.setText("Rules Extraction");
        {
        criteriaExtraction.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			
				// TODO Auto-generated method stub
				criteriaExtract obj =new criteriaExtract();
				try {
					Boolean error2 =obj.rules(fileName1,newfileName);
					if(error ==false){
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Error Message");
						alert.setHeaderText(null);
						alert.setContentText("Please check the input folder: All files must be in *.doc format or else go through the read me file once. ");

						alert.showAndWait();
					}else
					{
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Success!");
						alert.setHeaderText(null);
						alert.setContentText("Please find the output excell sheets: 'ContentLevelCriterias' and 'VariableRules' in the same folder.");

						alert.showAndWait();							
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        	
        	
        });
        }
        //HBox hbx=new HBox(10);

        VBox vBox = new VBox(15);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-border-style: solid;"+ "-fx-border-width: 1;"
 + "-fx-border-color: black");
        vBox.getChildren().addAll(
                header1, labelSelectedDirectory,
                btnOpenDirectoryChooser,ExtractVariables, criteriaExtraction);
        
        StackPane root = new StackPane();
        root.getChildren().add(vBox);
        
        
        Scene scene = new Scene(root, 400, 300, Color.BISQUE);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setTitle("Customized Tool");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}