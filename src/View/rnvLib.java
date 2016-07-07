/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import BusinessModel.Manager;
import Model.Person;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author tamqu
 */
public class rnvLib {
    public static void main(String[] args){
        String url = "C:/Users/tamqu/Desktop/d.rnv.xlsx";
        
        try
        {
            FileInputStream file = new FileInputStream(new File(url));
            ArrayList<Person> personas = new ArrayList<>();
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            DecimalFormat dformatDni = new DecimalFormat("00000000");
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            //Iterate through each rows one by one
            java.util.Iterator<Row> rowIterator = sheet.iterator();
            //Saltando cabecera
            rowIterator.next();
            
            while (rowIterator.hasNext()) 
            {
                Person persona = new Person();
                Row row = rowIterator.next();
                Cell c = row.getCell(0);
                if(c == null || c.getCellType() == Cell.CELL_TYPE_BLANK){
                    break;
                }
                //For each row, iterate through all the columns
                java.util.Iterator<Cell> cellIterator = row.cellIterator();
                int nrow = 0;
                String str;
                while (cellIterator.hasNext()) 
                {
                    Cell cell = cellIterator.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    str = cell.getStringCellValue();
                    
                    switch (nrow){
                        case 0: java.lang.System.out.print("Nombres: ");
                            persona.setName(str);
                            break;
                        case 1: java.lang.System.out.print("Apellidos: ");
                            persona.setLastname(str);
                            break;
                        case 2: java.lang.System.out.print("DNI: ");
                            //java.lang.System.out.print(str);
                            str = dformatDni.format(Integer.parseInt(str));
                            persona.setDni(str);
                            break;
                        case 3: java.lang.System.out.print("Ubigeo: ");
                            persona.setUbigeo(str);
                            break;
                        case 4: java.lang.System.out.print("Huella: ");
                            persona.setFingerprint("../rnv/"+str+".jpg");
                            break;
                        case 5: java.lang.System.out.print("Firma: ");
                            persona.setSignature("../rnv/"+str+".jpg");
                            break;
                        case 6: java.lang.System.out.print("Habilitado: ");
                            persona.setCitizen(!Boolean.parseBoolean(str));
                            break;                       
                    }

                    java.lang.System.out.print(str + " | ");
                    nrow++;
                }
                java.lang.System.out.println("");
                persona.setDisabled(false);
                personas.add(persona);
            }
            file.close();
            Manager.addPeople(personas);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
