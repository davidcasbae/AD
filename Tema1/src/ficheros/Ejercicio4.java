package ficheros;

import java.io.*;

public class Ejercicio4 {
	public static void main(String[] args) {
		try {
			FileInputStream leer = new FileInputStream(new File("imagen.jpg"));
			FileOutputStream copiar = new FileOutputStream(new File("imagen.jpg"));
			/**
			 * int leido; 
			 * while((leido = leer.read())!=-1) { 
			 * 	copiar.write(leido); 
			 * }
			 **/
			copiar.write(leer.readAllBytes());
			leer.close();
			copiar.close();
		} catch (FileNotFoundException e) {
			System.out.println("No existe" + e);
		} catch (IOException eR) {
			System.out.println("Error lectura o escritura");
			eR.printStackTrace();
		}
	}
}
