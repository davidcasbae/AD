package ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Ejercicio3 {

	public static void main(String[] args) {
		File f = new File("3_Ejercicio.csv");
		try {
			BufferedReader bfRd = new BufferedReader(new FileReader(f));
			String linea;
			while ((linea = bfRd.readLine())!= null){
				System.out.println(linea);
			}
			bfRd.close();
			PrintWriter pW = new PrintWriter(new FileWriter(f, true));
			pW.println("David;Castellano;Dam;2");
			pW.close();
		}catch(Exception e) {
			System.out.println("Error desconocido: " + e);
		}

	}

}
