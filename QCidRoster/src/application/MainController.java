package application;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController implements Initializable {
	public String fileName=null;
	public String formname=null;
	public String newfileName=null;
	@FXML
	private Button btn1;
	@FXML
	private Button AddForm;
	@FXML
	private TextField TxtFld2;
	@FXML
	private ListView<String> TxtFld;
	@FXML
	private ListView<String> QCList;
	@FXML
	public ComboBox<String> combobox;
	ObservableList<String> list = FXCollections.observableArrayList("Certificate","NC Preex Notice","Summary Plan Description","DENTAL COORDINATION OF BENEFITS","Dental Continuity of Coverage","Policy","Facepage1","FL Notice For Residents of All States","Body May05 VDART","Schedule of Exhibits","Exhibit 1","Exhibit 2","Exhibit 3","Filing A Claim - DENTAL","FSVD Description of Covered Services","Dental Claim Review MS","HIPAA","Change Dependent Age","Dental Plans"); 
	public void Button2Action(ActionEvent event){
		String text = TxtFld2.getText();
		ObservableList<String> list2 = FXCollections.observableArrayList(text);
		combobox.setItems(list2);	
	}
	
	
	public void Button1Action(ActionEvent e){
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(
						new ExtensionFilter("Microsoft Excel 97-2003 Worksheet","*.xls"));
		File selectedFile = fc.showOpenDialog(null);
		String path=selectedFile.getPath();
		if(selectedFile != null){
			TxtFld.getItems().add(path);
			//fileName = path + selectedFile.getName();
			fileName = path;
			newfileName= fileName.replace("\\","\\\\");
			}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		combobox.setItems(list);
		
	}
	
	public void comboChanged(ActionEvent event){
		formname=combobox.getValue();
		System.out.println(formname);
		System.out.println(newfileName);
		if(newfileName!=null)
		{
			ExcelSheet obj = new ExcelSheet();
			ArrayList<String> Qc= new ArrayList<String>();
			Qc = obj.readExcelFile(newfileName,formname);
			ObservableList<String> outputQClist = FXCollections.observableArrayList(Qc);
			System.out.println(outputQClist);
			QCList.setItems(outputQClist);
		}
	}

	
}
