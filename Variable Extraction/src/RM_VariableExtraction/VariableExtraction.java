package RM_VariableExtraction;

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

public class VariableExtraction
{
	
private static String fileName_main;

     
        static HSSFWorkbook myWorkBook_m = new HSSFWorkbook();
        static HSSFSheet mySheet_m = myWorkBook_m.createSheet("Variables");
        static HSSFSheet mySheet_m2 = myWorkBook_m.createSheet("Removing redundant Variables");
        static HSSFRow myRow_m = null;
        static HSSFCell myCell_m = null;
    static String arr[][]=new String[200][200];
    static int i=0;
    static int k=0;
        static HSSFCell myCell2_m = null;
public  void variable(String fileName2, String newfileName) throws IOException{
	fileName_main = newfileName + "\\\\Result.xls";
	System.out.println(fileName_main);
	File folder = new File(fileName2);
File[] listOfFiles = folder.listFiles();
String s="";

for (File file : listOfFiles) {
   if (file.isFile()) {
   
arr[i][0]=file.getName();
       s=newfileName+'/'+arr[i][0];
       String fileName = s;
       readMyDocument(fileName);
   }
}
}
public static void readMyDocument(String fileName){
       //POIFSFileSystem fs = null;
POIFSFileSystem fs = null;
       try {
       
 
           fs = new POIFSFileSystem(new FileInputStream(fileName));
           HWPFDocument doc = new HWPFDocument(fs.getRoot());
           readParagraphs(doc);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }  
 
public static void readParagraphs(HWPFDocument doc) throws Exception{
       WordExtractor we = new WordExtractor(doc);
        ArrayList<String> contentItem=new ArrayList<String>();
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
           
           if(ch.contains("["))
                      
           {
                      
           l=ch.length();
                      
           for(int i1=0;i1<l-1;i1++)
                          
           {
                          
           if(((ch.charAt(i1)=='(' && ch.charAt(i1+1)=='a')||(ch.charAt(i1)==' ' && ch.charAt(i1+1)=='a')||(ch.charAt(i1)=='<' && ch.charAt(i1+1)=='a'))&&((ch.charAt(i1+4)=='[')||(ch.charAt(i1+5)=='[')||(ch.charAt(i1+6)=='[')||(ch.charAt(i1+7)=='[')||(ch.charAt(i1+8)=='[')||(ch.charAt(i1+9)=='[')||(ch.charAt(i1+10)=='[')))
                          
           {int x=i1+1;
                          
           while(ch.charAt(x)!=']')
                          
           {
                          
           str=str+ch.charAt(x);
                          
           x++;
                          
           }
                          
           str=str+ch.charAt(x);
                              
           contentItem.add(str);
                                  
           str="";
                          
           }
                          
           }
                      
           }
           
           if(ch.contains(" lB") || ch.contains("(lB"))
               
           {
                      
           l=ch.length();
                      
            
                      
           System.out.println(l);
                      
           for(int i1=0;i1<l-1;i1++)
                      
           {
                      
           if(ch.charAt(i1)=='l' && ch.charAt(i1+1)=='B')
                      
           {int x=i1;
                      
           while(ch.charAt(x)!=' ' &&  ch.charAt(x)!='"'&& ch.charAt(x)!=')' && x!=l-1)
                      
           {
                      
           str=str+ch.charAt(x);
                      
           x++;
                      
           }
                          
           contentItem.add(str);
                              
           str="";
                      
           }

                      
           }}
                      
            
                      
           if(ch.contains("<c"))
                      
           {
                      
           l=ch.length();
                      
           for(int i1=0;i1<l-1;i1++)
                          
           {
                          
           if((ch.charAt(i1)=='<' && ch.charAt(i1+1)=='c')||(ch.charAt(i1)=='<' && ch.charAt(i1+1)=='d'))
                          
           {int x=i1+1;
                          
           while(ch.charAt(x)!='>')
                          
           {
                          
           str=str+ch.charAt(x);
                          
           x++;
                          
           }
                              
           contentItem.add(str);
                                  
           str="";
                          
           }
                          
           }
                      
           }
                      
           if(ch.contains("["))
                      
           {
                      
           l=ch.length();
                      
           for(int i1=0;i1<l-1;i1++)
                          
           {
                          
           if((ch.charAt(i1)==' ' && ch.charAt(i1+1)=='a')&&((ch.charAt(i1+4)=='[')||(ch.charAt(i1+5)=='[')||(ch.charAt(i1+6)=='[')||(ch.charAt(i1+7)=='[')))
                          
           {int x=i1+1;
                          
           while(ch.charAt(x)!=']')
                          
           {
                          
           str=str+ch.charAt(x);
                          
           x++;
                          
           }
                          
           str=str+ch.charAt(x);
                              
           contentItem.add(str);
                                  
           str="";
                          
           }
                          
           }
                      
           }  
           
 
      }
            for (int j=0; j <contentItem.size();j++){
            //String s="Scenario ";

            myRow_m = mySheet_m.createRow(k);
           myCell_m = myRow_m.createCell(0);
           myCell_m.setCellValue(arr[i][0]);
           myCell2_m = myRow_m.createCell(1);
           myCell2_m.setCellValue(contentItem.get(j));
           k++;
            }
           
            //fileName_main=newfileName + "\\Result.xls";
            FileOutputStream out_m = new FileOutputStream(fileName_main);
            myWorkBook_m.write(out_m);
            out_m.close();
   }
}

