package ve.org.bcv.excel;

import java.io.FileInputStream;
import java.util.List;

import javax.persistence.EntityManager;

import ve.org.bcv.dto.UserEncuestaDto;

public class ReadProveedorBasico implements WorkWithSheet, WriteBD {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WorkWithSheet readSheet = null;
	private String sheet;
	private int col;
	private String path;
	private List<String[]> rowFound;
	private int rowEnd;

	/**
	 * 
	 * Leemos la data de excel
	 * 
	 * @param sheet
	 * @param col
	 * @param path
	 */
	public ReadProveedorBasico(String sheet,int rowEnd, int col, String path) {
		super();
		/** Obtenemos todas las filas de la hoja en excel */
		readSheet = new ReadExcelProcess(sheet,rowEnd, col, path);

	}

	/*
	 * Metodo que devolvera los registros en una lista de arreglos
	 * 
	 * @see com.read.decorate.excel.interf.ReadSheet#readAllrow()
	 */
	public List<String[]> readAllrow() throws Exception {
		/** Obtenemos todas las filas de la hoja en excel */

		return readSheet.readAllrow();
	}

	 

	public WorkWithSheet getReadSheet() {
		return readSheet;
	}

	public void setReadSheet(WorkWithSheet readSheet) {
		this.readSheet = readSheet;
	}

	public String getSheet() {
		return sheet;
	}

	public void setSheet(String sheet) {
		this.sheet = sheet;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getRowEnd() {
		return rowEnd;
	}

	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}

	@Override
	public void createRecords(EntityManager manager) throws Exception {
		// TODO Auto-generated method stub
		
	}

 

	@Override
	public FileInputStream writeRow(List<UserEncuestaDto> lists) {
		// TODO Auto-generated method stub
		return null;
	}

}
