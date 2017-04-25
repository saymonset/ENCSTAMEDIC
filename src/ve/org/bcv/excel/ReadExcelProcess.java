package ve.org.bcv.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import ve.org.bcv.dto.BenefMedicamentoDto;
import ve.org.bcv.dto.ParametroNominaDto;
import ve.org.bcv.dto.UserEncuestaDto;

public class ReadExcelProcess implements WorkWithSheet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sheet;
	private int col;
	private String path;
	private int rowEnd;
	private int rowStart;

	public ReadExcelProcess(String sheet, int rowEnd, int col, String path) {
		super();
		this.sheet = sheet;
		this.col = col;
		this.path = path;
		this.rowEnd = rowEnd;
	}

	public ReadExcelProcess(String path, int rowStart) {
		super();
		this.path = path;
		this.rowStart = rowStart;
	}

	public FileInputStream writeRow(List<UserEncuestaDto> lists) {
		FileInputStream file = null;
		try {
			// Get the excel file.
			file = new FileInputStream(new File(path));

			// Get workbook for XLS file.
			HSSFWorkbook yourworkbook = new HSSFWorkbook(file);

			// Get first sheet from the workbook.
			HSSFSheet sheet1 = yourworkbook.getSheetAt(0);

			String fechaStr = "";
			String cedulaTrab = "";
			String empleado = "";
			String beneficiario = "";
			String cedulaBenf = "";
			String codigoMedicamento = "";
			String medicamento = "";
			String cantSolicitada = "";

			//Row row =  null;

			for (UserEncuestaDto userEncuestaDto : lists) {
			
				for (BenefMedicamentoDto benefMedicamentoDto : userEncuestaDto.getBenefMedicamentos()) {
					for (ParametroNominaDto medicinas : benefMedicamentoDto.getParametroNominaDtos()) {
						int col = 0;
						//row = sheet1.getRow(rowStart++);
						  HSSFRow row = sheet1.getRow(rowStart);
						  if (row == null){
							  row = sheet1.createRow(rowStart);
						  }
						  rowStart++;
						  
						  
						  fechaStr = medicinas.getFeRegPagoStr();
							HSSFCell fechaStrCell = row.getCell(col);
							if (fechaStrCell == null) {
								fechaStrCell = row.createCell(col);
							}
							fechaStrCell.setCellType(HSSFCell.CELL_TYPE_STRING);
							fechaStrCell.setCellValue(fechaStr);
							col++;
						  
						  cedulaTrab = benefMedicamentoDto.getNuCedula() + "";
							HSSFCell cedulaTrabCell = row.getCell(col);
							if (cedulaTrabCell == null) {
								cedulaTrabCell = row.createCell(col);
							}
							cedulaTrabCell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cedulaTrabCell.setCellValue(cedulaTrab);
							col++;
						  
						empleado = userEncuestaDto.getNombre();
						HSSFCell empleadoCell = row.getCell(col);
						if (empleadoCell == null) {
							empleadoCell = row.createCell(col);
						}
						empleadoCell.setCellType(HSSFCell.CELL_TYPE_STRING);
						empleadoCell.setCellValue(empleado);
						col++;
						

						
						
						beneficiario = benefMedicamentoDto.getNombreFamiliar();
						HSSFCell beneficiarioCell = row.getCell(col);
						if (beneficiarioCell == null) {
							beneficiarioCell = row.createCell(col);
						}
						beneficiarioCell.setCellType(HSSFCell.CELL_TYPE_STRING);
						beneficiarioCell.setCellValue(beneficiario);
						col++;

						
						
						cedulaBenf = benefMedicamentoDto.getNuCedulaFamiliar() + "";
						HSSFCell cedulaBenfCell = row.getCell(col);
						if (cedulaBenfCell == null) {
							cedulaBenfCell = row.createCell(col);
						}
						cedulaBenfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cedulaBenfCell.setCellValue(cedulaBenf);
						col++;
						
						codigoMedicamento = medicinas.getCo_parametro_nom();
						HSSFCell codigoMedicamentoCell = row.getCell(col);
						if (codigoMedicamentoCell == null) {
							codigoMedicamentoCell = row.createCell(col);
						}
						codigoMedicamentoCell.setCellType(HSSFCell.CELL_TYPE_STRING);
						codigoMedicamentoCell.setCellValue(codigoMedicamento);
						col++;
						
						medicamento = medicinas.getTx_parametro_nom();
						HSSFCell medicamentoCell = row.getCell(col);
						if (medicamentoCell == null) {
							medicamentoCell = row.createCell(col);
						}
						medicamentoCell.setCellType(HSSFCell.CELL_TYPE_STRING);
						medicamentoCell.setCellValue(medicamento);
						col++;
						
						cantSolicitada = medicinas.getCantSolicitada() + "";
						HSSFCell cantSolicitadaCell = row.getCell(col);
						if (cantSolicitadaCell == null) {
							cantSolicitadaCell = row.createCell(col);
						}
						cantSolicitadaCell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cantSolicitadaCell.setCellValue(cantSolicitada);
						col++;
					}
				}

			}


			// Close the excel file.
			FileOutputStream fos = new FileOutputStream(path);
			yourworkbook.write(fos);
			file.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;

	}

	public List<String[]> readAllrow() throws Exception {
		FileInputStream file = null;
		String[] record = null;
		List<String[]> rowFound = new ArrayList<String[]>();
		int i = 0;
		try {
			file = new FileInputStream(new File(path));
			// Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			// Get first sheet from the workbook
			// HSSFSheet sheet = workbook.getSheet(getSheet());
			HSSFSheet sheet = workbook.getSheetAt(0);
			// Get iterator to all the rows in current sheet
			Iterator<Row> rowIterator = sheet.iterator();
			Cell cell = null;

			while (rowIterator.hasNext() && rowEnd > 0) {
				rowEnd--;
				Row row = (Row) rowIterator.next();

				int col = 0;
				System.out.println("continuamos ñleyenfd0=" + (++i));
				record = new String[getCol()];
				while (col < getCol()) {
					System.out.println("II continuamos leyenfd0=" + (++i));
					// Update the value of cell
					cell = row.getCell(col);
					if (cell != null) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						record[col] = cell.getStringCellValue();
						System.out.println("" + cell.getStringCellValue());
						col++;

					}
					System.out.println("IV continuamos leyenfd0=" + (++i));
				}
				System.out.println("V continuamos leyenfd0=" + (++i));
				rowFound.add(record);
			}
		} finally {
			System.out.println("VI continuamos leyenfd0=" + (++i));
			if (file != null) {
				file.close();
			}
		}
		System.out.println("VII continuamos leyenfd0=" + (++i));
		System.out.println("exit-ccccccccccccccc--");
		return rowFound;
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


}
