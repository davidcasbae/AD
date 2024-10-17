package csv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utilidades.Utilidades;

public class ejercicio09 {

	private static Workbook wb;
	private final static String DOCTRABAJO_IN = "vehiculosElectricos.xlsx";

	public static void main(String[] args) {

		try {
			// Abro un documento excel con extensión xslx
			wb = new XSSFWorkbook(new FileInputStream(new File(Utilidades.RUTA + DOCTRABAJO_IN)));
			// tomo la primera página
			buscarMarca("IBERDROLA");
			buscarCiudad("ZAMORA");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void buscarMarca(String marca) {
		Sheet hoja = wb.getSheetAt(0);
		int numFila = 1;
		Row fila = hoja.getRow(numFila);

		// Muestro las localizaciones de los puntos de carga de la marca IBERDROLA
		System.out.println("PUNTOS DE RECARGA DE " + marca + " EN CASTILLA Y LEÓN");
		while (fila != null) {
			Cell celdaBusqueda = fila.getCell(2);
			if (celdaBusqueda != null) {
				if (celdaBusqueda.getStringCellValue().contains(marca)) {
					System.out.println("----> " + fila.getCell(0).getStringCellValue() + "\t - "
							+ fila.getCell(1).getStringCellValue());
				}
			}
			fila = hoja.getRow(numFila++);
		}
	}

	private static void buscarCiudad(String ciudad) {
		Sheet hoja = wb.getSheetAt(0);
		Sheet newHoja = wb.createSheet(ciudad);
		int numFila = 1;
		Row fila = hoja.getRow(numFila);

		// Muestro las localizaciones de los puntos de carga de la marca IBERDROLA
		System.out.println("\n\n\nPUNTOS DE RECARGA EN " + ciudad);
		while (fila != null) {
			Cell celdaBusqueda = fila.getCell(1);
			if (celdaBusqueda != null) {
				if (celdaBusqueda.getStringCellValue().contains("("+ciudad+")")) {
					System.out.println("----> " + fila.getCell(0).getStringCellValue() + "\t - "
							+ fila.getCell(1).getStringCellValue());
					Row newFila = newHoja.createRow(numFila++);
					newFila.createCell(0).setCellValue((String)fila.getCell(0).getStringCellValue());
					newFila.createCell(1).setCellValue((String)fila.getCell(0).getStringCellValue());
				}
			}
			numFila++;
			fila = hoja.getRow(numFila);
		}
	}

}
