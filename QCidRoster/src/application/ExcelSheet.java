package application;

import java.io.FileInputStream;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
 
 
public class ExcelSheet {
 
 

public ArrayList<String> readExcelFile(String fileName, String formname)
{
    /** --Define a Vector
        --Holds Vectors Of Cells
     */
	ArrayList<String> QC = new ArrayList<String>();
 
    try{
    /** Creating Input Stream**/
    //InputStream myInput= ReadExcelFile.class.getResourceAsStream( fileName );
    FileInputStream myInput = new FileInputStream(fileName);
 
    /** Create a POIFSFileSystem object**/
    POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
 
    /** Create a workbook using the File System**/
     HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
     String columnWanted = "Changes made in docs";
     int columnNo = 0;
     int columnNoQC = 0;
     /** Get the first sheet from workbook**/
   //HSSFSheet mySheet = myWorkBook.getSheetAt(0);
    for (int i = 0; i < myWorkBook.getNumberOfSheets(); i++) {
        HSSFSheet mySheet = myWorkBook.getSheetAt(i);  
        //.... code to print the sheet's values here
        Row firstRow = mySheet.getRow(0);
        
        for(org.apache.poi.ss.usermodel.Cell cell:firstRow){
            if (cell.getStringCellValue().equals(columnWanted)){
                columnNo = cell.getColumnIndex();
            }
            if (cell.getStringCellValue().equals("QC #")){
                columnNoQC = cell.getColumnIndex();
            }
            
        }
        System.out.println(columnNo);
        
        if(columnNo!=0){
    		int rowNum=mySheet.getLastRowNum();

 
    		
    		for(int i2=1;i2<rowNum;i2++)
    		{
    			HSSFRow row=mySheet.getRow(i2);
    			
    			    HSSFCell cell=row.getCell(columnNo);
    			    HSSFCell cell2=row.getCell(columnNoQC);
    			    if(cell.getCellType() != HSSFCell.CELL_TYPE_BLANK)
    			    {
    				String value=cellToString(cell);
    				String value2 = cellToString(cell2);
    				Boolean val = value.toLowerCase().contains(formname.toLowerCase());
    				if(val == true)
    				{
    					if(value2!= null)
    					{
    				QC.add(value2);	
    					}
    				
    			    }		
    			    }
        }
        columnNo=0;
        columnNoQC=0;
    /** We now need something to iterate through the cells.**/
      }}}catch (Exception e){e.printStackTrace(); }
	return QC;
    
}
 
public static String cellToString(HSSFCell cell)
{
	int type;
	Object result;
	type=cell.getCellType();
	switch(type)
	{
	case 0:
		result=cell.getNumericCellValue();
		break;
	case 1:
		result=cell.getStringCellValue();
		break;
	
	default:
		result=" ";
	//throw new RuntimeException("There are no support for this type of cell");
	
	}
	return result.toString();
}
}
