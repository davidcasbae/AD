package ficheros;

import java.io.File;

public class Ejercicio1 {

	public static void main(String[] args) {
		File fichero = new File(System.getProperty("user.dir"));
		File[] hijos = fichero.listFiles();
		System.out.println("Nombre del directorio actual: " + fichero.getName() + " tiene " + hijos.length
				+ " ficheros y directorios contenidos \n\t contenido:");
		for (File f : hijos) {
			System.out.println("\t\t" + f.getName() + " ----->\t" + (f.isFile() ? "F" : "D"));
		}
	}

}
