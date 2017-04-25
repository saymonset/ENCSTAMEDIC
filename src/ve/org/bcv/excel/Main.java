package ve.org.bcv.excel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import ve.org.bcv.util.Pdf;

public class Main {
	public static void main(String[] args) {
		
		
		InputStream is = null;
		is = Main.class.getResourceAsStream("FORMATO_EXCEL_ENCUESTAS_MEDICAS.xls");
		File result = null;
		try {
			 result = Pdf.inputStreamToFile(is,"xls");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (null != result){
			 String path = result.getAbsolutePath();
				/** Obtenemos todas las filas de la hoja en excel */
				//(sheet,rowEnd, col, path);
				 WorkWithSheet readSheet = new ReadExcelProcess("Hoja1",12, 9, path);
				 try {
					 
				//	 readSheet.writeRow();
					 
//			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

}
