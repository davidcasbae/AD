package ficheros;

import java.io.File;
import java.util.Scanner;

public class Ejercicio2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String nombreDir;
		System.out.println("Como se llama el directorio");
		nombreDir = sc.next();
		File fichero = new File(nombreDir);
		try {
			if (fichero.exists()) {
				if (fichero.isDirectory()) {
					System.out.println("Se trata de un directorio");
					File[] hijos = fichero.listFiles();
					System.out.println("Nombre del directorio actual: " + fichero.getName() + " tiene " 
					+ hijos.length + " ficheros y directorios contenidos \n\t contenido:");
					for (File f : hijos) {
						System.out.println("\t\t" + f.getName() + " ----->\t" + (f.isFile() ? "F" : "D"));
					}
				} else {
					System.out.print(fichero.length() + "bytes");
					if(fichero.canRead()){
						System.out.print(" r");
					}else {
						System.out.print(" -");
					}
					if(fichero.canWrite()){
						System.out.print("w");
					}else {
						System.out.print("-");
					}
					if(fichero.canExecute()){
						System.out.print("x");
					}else {
						System.out.print("-");
					}
				}
			} else {
				System.out.println("Este directorio no existe");
			}

		} catch (Exception e) {
			System.out.print("Error desconocido");
		}

	}

}
