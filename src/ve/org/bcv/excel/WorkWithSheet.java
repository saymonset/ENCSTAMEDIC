package ve.org.bcv.excel;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.List;

import ve.org.bcv.dto.UserEncuestaDto;

public interface WorkWithSheet extends Serializable{
	/**
	 * Nombre de la hoja de excel
	 * @return
	 */
	String getSheet();
	void setSheet(String sheet);
	/**
	 * cuantas columnas se leeran de la hoja de excel
	 * @return
	 */
	int getCol() ;
	void setCol(int col);
	
	/**
	 * cuantas rows se leeran de la hoja de excel
	 * @return
	 */
	int getRowEnd() ;
	void setRowEnd(int rowEnd);
	
	String getPath() ;
	void setPath(String path) ;
	
	 FileInputStream writeRow(List<UserEncuestaDto> lists) ;
	/**
	 * Metodo que leera las celdas de las hojas de excel
	 * Retornaremos en una lista , todos los registros encontrados en la hoja de excel.
	 * cada registro esta representado por un arreglo, cada posicion es un campo que se lee 
	 * @return
	 */
	List<String[]> readAllrow() throws Exception;
	
 
}