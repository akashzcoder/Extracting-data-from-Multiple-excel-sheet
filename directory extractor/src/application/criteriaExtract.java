package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class criteriaExtract
{
	
private static String fileName_main;

     
        static HSSFWorkbook myWorkBook_m = new HSSFWorkbook();
        static HSSFSheet mySheet_m = myWorkBook_m.createSheet("Criterias and Rules");
        //static HSSFSheet mySheet_m2 = myWorkBook_m.createSheet("Removing redundant Variables");
        static HSSFRow myRow_m = null;
        static HSSFCell myCell_m = null;
        static HSSFWorkbook myWorkBook_m2 = new HSSFWorkbook();
        static HSSFSheet mySheet_m2 = myWorkBook_m2.createSheet();
        static HSSFRow myRow_m2 = null;
        static HSSFCell myCell_m2 = null;
        static HSSFWorkbook myWorkBook_m3 = new HSSFWorkbook();
        static HSSFSheet mySheet_m3 = myWorkBook_m3.createSheet();
        static HSSFRow myRow_m3 = null;
        static HSSFCell myCell_m3 = null;
        static String arr[][]=new String[200][200];
        static int i=0;
        static int k=0;
        static int z=0;
        static int x=0;
        static String fileName_main2 = null;
        static String fileName_main3 = null;
        static HSSFCell myCell2_m2 = null;
        static HSSFCell myCell2_m3 = null;
        static HSSFCell myCell2_m = null;
public  boolean rules(String fileName2, String newfileName) throws IOException{
	if(newfileName == null)
		return false;
	fileName_main2 = newfileName + "\\\\ContentLevelCriterias.xls";
	fileName_main3 = newfileName + "\\\\VariableRules.xls";
	boolean tf3=false;
	System.out.println(fileName_main);
	File folder = new File(fileName2);
	File[] listOfFiles = folder.listFiles();
	String s="";

for (File file : listOfFiles) {
   if (file.isFile()) {
   
arr[i][0]=file.getName();
       s=newfileName+'/'+arr[i][0];
       String fileName = s;
       tf3 = readMyDocument(fileName);
   }
}
return tf3;
}
public static boolean readMyDocument(String fileName){
       //POIFSFileSystem fs = null;
	Boolean tf2=false;
	POIFSFileSystem fs = null;
       try {
       
 
           fs = new POIFSFileSystem(new FileInputStream(fileName));
           HWPFDocument doc = new HWPFDocument(fs.getRoot());
           tf2 =readParagraphs(doc);
       } catch (Exception e) {
           e.printStackTrace();
       }
return tf2;   
}  
 
public static boolean readParagraphs(HWPFDocument doc) throws Exception{
    Boolean tf= false;   
	WordExtractor we = new WordExtractor(doc);
        ArrayList<String> contentItem=new ArrayList<String>();
        ArrayList<String> ContentRuleCriteria=new ArrayList<String>();
        ArrayList<String> VariableCriteria=new ArrayList<String>();
       /**Get the total number of paragraphs**/
       String[] paragraphs = we.getParagraphText();
       int paragraph_count=paragraphs.length;
       System.out.println("Total Paragraphs: "+paragraphs.length);
       System.out.println(paragraphs[2]);
       int l=0;
       char ch1;
       String str="";
       //str="";
       for (int i = 0; i < paragraphs.length; i++) {
       
 
           String ch=paragraphs[i].toString();
           System.out.println(ch);
           
           if(ch.contains("IF") && !ch.contains("<") && !ch.contains("ENDIF") && !ch.contains("***"))

           {

                   ContentRuleCriteria.add(ch);

                   System.out.println(ch);

           }
           if(ch.contains("IF") && ch.contains("<") && !ch.contains("ENDIF") && !ch.contains("***"))
           {

                   l=ch.length();
                   String subString="";
               for(int i1=1;i1<l-1;i1++)
               {
               if(ch.charAt(i1)=='<' )
               {
                   int x2=i1;
               while(ch.charAt(x2)!='>')
               {
               subString=subString+ch.charAt(x2);
               x2++;
               }
               VariableCriteria.add(subString);
               subString="";
               }               
               }
           }

       }
            /*for (int j=0; j <contentItem.size();j++){

            //String s="Scenario ";

 

            myRow_m = mySheet_m.createRow(k);

           myCell_m = myRow_m.createCell(0);

           myCell_m.setCellValue(arr[i][0]);

           myCell2_m = myRow_m.createCell(1);

           myCell2_m.setCellValue(contentItem.get(j));

           k++;

            }

            FileOutputStream out_m = new FileOutputStream(fileName_main);

            myWorkBook_m.write(out_m);

            out_m.close();*/

           

            for (int j=0; j <ContentRuleCriteria.size();j++){

            myRow_m2 = mySheet_m2.createRow(z);

           myCell_m2 = myRow_m2.createCell(0);

           myCell_m2.setCellValue(arr[i][0]);

           myCell2_m2 = myRow_m2.createCell(1);

           myCell2_m2.setCellValue(ContentRuleCriteria.get(j));

           z++;

            }
			FileOutputStream out_m2 = new FileOutputStream(fileName_main2);

            myWorkBook_m2.write(out_m2);

            out_m2.close();

           

            for (int j=0; j <VariableCriteria.size();j++){

                myRow_m3 = mySheet_m3.createRow(x);

               myCell_m3 = myRow_m3.createCell(0);

               myCell_m3.setCellValue(arr[i][0]);

               myCell2_m3 = myRow_m3.createCell(1);

               myCell2_m3.setCellValue(VariableCriteria.get(j));

               x++;

                }

                FileOutputStream out_m3 = new FileOutputStream(fileName_main3);

                myWorkBook_m3.write(out_m3);

                out_m3.close();

           
            //fileName_main=newfileName + "\\Result.xls";
            FileOutputStream out_m = new FileOutputStream(fileName_main);
            myWorkBook_m.write(out_m);
            out_m.close();
            return tf;
   }
}

