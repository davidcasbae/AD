package ficheros;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio5_optimizado_temperaturas {

	private final static int TAMANIO = 13;
	private static RandomAccessFile rFile;

	public static void main(String[] args) {
		try {
			rFile = new RandomAccessFile(new File("fichero.dat"), "rw");
			menu();
			rFile.close();
		} catch (Exception e) {
			System.out.println("Error desconocido: " + e);
		}

	}

	/**
	 * Escribe registros de meses y temperatueras en un fichero de acceso aleatorio
	 * o modifica el valor de una posicion determinada (los valores, posición y
	 * nuevas temperaturas, se pasa como parametros) o añade al final del fichero un
	 * registro de temperaturas que se pasa como parametro.
	 * 
	 * @param mes     mes a modificar o añadir (cadena con tres caracteres)
	 * 
	 * @param valores si vavio, copia registros de temperaturas en el ficher
	 * si tiene dos valores se toma el primero como temperatura minima el segundo como maxima y se añade al final,
	 * si tiene dos valores se toma el primero como temperatura minima el segundo como maxima y se añade al final
	 */
	public static void escribir(String mes, int... valores) {
		String[] meses = { "ENE", "FEB", "MAR", "ABR", "MAY", "JUN" };
		int[] tempMax = { 12, 18, 17, 20, 17, 28 };
		int[] tempMin = { 1, 6, 7, 4, 9, 15 };
		try {
			if (valores.length == 0) {
				for (int i = 0; i < meses.length; i++) {
					rFile.writeUTF(meses[i]);
					rFile.writeInt(tempMin[i]);
					rFile.writeInt(tempMax[i]);
				}
			} else if (valores.length == 2) {
				rFile.seek(rFile.length());
				rFile.writeUTF(mes);
				rFile.writeInt(valores[0]);
				rFile.writeInt(valores[1]);
			} else if (valores.length == 3) {
				// primer parametro como minima el segundo como maxima y el tercero como lugar
				int posicion = (valores[2] - 1) * TAMANIO;
				// comprobar posicion correcta (no > tamaño fichero, ni inferior a 0)
				if (posicion >= rFile.length() || posicion < 0) {
					System.out.println("La posicion no es correcta");
				} else {
					rFile.seek(posicion);
					rFile.writeUTF(mes);
					rFile.writeInt(valores[0]);
					rFile.writeInt(valores[1]);
				}
			}
		} catch (Exception e) {
			System.out.println("Error desconocido: " + e);
		}

	}

	/**
	 * Lee el contenido del fichero o el valor de una posición que se pasa como
	 * parámetro
	 * 
	 * @param valores si vacio, lee y muestra el contenido de todo el fichero si
	 *                tiene un unico valor, muestra el elemento contenido en ese
	 *                lugar.
	 */

	public static void leer(int... valores) {
		try {
			if (valores.length == 0) {
				rFile.seek(0);
				while (rFile.getFilePointer() != rFile.length()) {
					System.out.println(rFile.readUTF() + ":\t"+rFile.readInt()+"ºC/"+rFile.readInt()+"ºC");
				}
			} else if (valores.length == 1) {
				// calcular posicion
				int posicion = (valores[0] - 1) * TAMANIO;
				// comprobar posicion correcta (no > tamaño fichero, ni inferior a 0)
				if (posicion >= rFile.length() || posicion < 0) {
					System.out.println("La posicion no es correcta");
				} else {
					rFile.seek(posicion);
					System.out.println("Valor en la posicion " + valores[0] + ": "
							+ rFile.readUTF() + ":\t"+rFile.readInt()+"ºC/"+rFile.readInt()+"ºC");
				}
			}
		} catch (Exception e) {
			System.out.println("Error desconocido: " + e);
		}
	}

	public static void menu() {
		Scanner sc = new Scanner(System.in);
		boolean salir = false;
		int opcion;
		int lugar;

		while (!salir) {
			System.out.println("\n1. Leer fichero");
			System.out.println("2. consultar");
			System.out.println("3. añadir al final");
			System.out.println("4. modificar valor");
			System.out.println("5. salir");
			try {
				System.out.print("Elige una opción: ");
				opcion = sc.nextInt();

				switch (opcion) {
				case 1:
					System.out.println("Leyendo el contenido del fichero");
					leer();
					break;
				case 2:
					System.out.println("consultando la posicion del fichero" + "\nIndique la posición a consultar: ");
					leer(sc.nextInt());
					break;
				case 3:
					System.out.println("Añadiendo el valor al final del fichero" + "\nIndique el valor a añadir: ");
					escribir(sc.nextInt());
					break;
				case 4:
					System.out.println("Modifcando el valor de una posición" + "\nIndique la posición a modificar: ");
					lugar = sc.nextInt();
					System.out.println("Indique el nuevo valor: ");
					escribir(lugar, sc.nextInt());
					break;
				case 5:
					System.out.println("Saliendo...");
					salir = true;
					break;
				default:
					System.out.println("Solo números entre 1 y 5");
					break;
				}
			} catch (InputMismatchException iME) {
				System.out.println("Debes escribir un número del 1 al 5" + iME);
				sc.next();
			}
		}
	}
}
