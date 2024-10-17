package ficheros;

import java.io.*;

public class Ejercicio8 {

	public static void main(String[] args) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(new File("ejercicio08.csv")));
			String linea;
			while((linea = bf.readLine())!= null) {
				String sinComillas [] = linea.split(",");
				sinComillas = matadora(sinComillas);
				mostrar(sinComillas);
			}
			bf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private static void mostrar(String[] sinComillas) {
		for (int i = 0; i < sinComillas.length; i++) {
			System.out.print("\t"+sinComillas[i]);
		}
		System.out.println();
	}

	private static String [] matadora(String[] sinComillas) {
		for (int i = 0; i < sinComillas.length; i++) {
			sinComillas[i] = sinComillas[i].replaceAll("^\"", "").replaceAll("\"$", "");
		}
		return null;
	}
}
