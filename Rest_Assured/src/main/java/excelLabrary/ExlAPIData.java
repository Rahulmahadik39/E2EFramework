package excelLabrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExlAPIData {
	
	private List<String> userList=new ArrayList<>();
	private List<String> passwordList=new ArrayList<>();
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		ExlAPIData ex=new ExlAPIData();
		ex.setData();
		System.out.println(ex.userList.get(2));
	}
	
	public void setData() throws EncryptedDocumentException, IOException {
		   
		String fi="C:\\Users\\Rahul\\NewDoc\\Demo.xlsx";		
			FileInputStream file=new FileInputStream(fi);
			
			Workbook book= WorkbookFactory.create(file);
			
			Sheet  sheet=book.getSheetAt(0);
			
			Row row=sheet.getRow(0);
			int lastRN=sheet.getLastRowNum();
			int lastCN=row.getLastCellNum();
		    Cell cell=row.getCell(1);
		    
		    
		    
			
		    for(int i=1;i<=lastRN;i++) {
				      row=sheet.getRow(i);
				      
				for(int j=1;j<lastCN;j++) {
					  cell=row.getCell(j);
					  
					if(cell.getColumnIndex()==1) {						
						userList.add(cell.toString());
					}
					
					if(cell.getColumnIndex()==2) {
						passwordList.add(cell.toString());
					}
					
				}
			}
		    System.out.println("values selected ");
	}

	public List<String> getUserList() {
		
		return userList;
	}

	public List<String> getPasswordList() {
		return passwordList;
	}
}
