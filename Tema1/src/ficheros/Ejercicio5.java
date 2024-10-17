package ficheros;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio5 {

	private final static int tamaño = 4;
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

	public static void escribir() {
		try {
			for (int i = 1; i <= 100; i++) {
				rFile.writeInt(i);
			}
		} catch (Exception e) {
			System.out.println("Error desconocido: " + e);
		}
	}

	public static void leer() {
		try {
			rFile.seek(0);
			while (rFile.getFilePointer() != rFile.length()) {
				System.out.println(rFile.readInt() + "\t");
			}

		} catch (Exception e) {
			System.out.println("Error desconocido: " + e);
		}
	}

	/**
	 * Método que muestra la posición del elemento que ocupa el lugar en el fichero
	 * 
	 * @param lugar número de elemento a mostrar
	 */
	public static void consultar(int lugar) {
		try {
			// calcular posicion
			int posicion = (lugar - 1) * tamaño;
			// comprobar posicion correcta (no > tamaño fichero, ni inferior a 0)
			if (posicion >= rFile.length() || posicion < 0) {
				System.out.println("La posicion no es correcta");
			} else {
				rFile.seek(posicion);
				System.out.println("Valor en la posicion " + lugar + ": " + rFile.readInt());
			}

		} catch (Exception e) {
			System.out.println("Error desconocido: " + e);
		}
	}

	/**
	 * Método que añade un valor al final
	 * 
	 * @param lugar número de valor a añadir
	 */
	public static void añadirFinal(int valor) {
		try {
			rFile.seek(rFile.length());
			rFile.writeInt(valor);
		} catch (Exception e) {
			System.out.println("Error desconocido: " + e);
		}
	}

	/**
	 * Método que modifica un numero en una determinada posicion
	 * 
	 * @param valor nuevo numero a escribir en la nueva posicion
	 * @param lugar numero de elemento que se modificará
	 */
	public static void modificarValor(int valor, int lugar) {
		try {
			// calcular posicion
			int posicion = (lugar - 1) * tamaño;
			// comprobar posicion correcta (no > tamaño fichero, ni inferior a 0)
			if (posicion >= rFile.length() || posicion < 0) {
				System.out.println("La posicion no es correcta");
			} else {
				rFile.seek(posicion);
				rFile.writeInt(valor);
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
				consultar(sc.nextInt());
				break;
			case 3:
				System.out.println("Añadiendo el valor al final del fichero" + "\nIndique el valor a añadir: ");
				añadirFinal(sc.nextInt());
				break;
			case 4:
				System.out.println("Modifcando el valor de una posición" + "\nIndique la posición a modificar: ");
				lugar = sc.nextInt();
				System.out.println("Indique el nuevo valor: ");
				modificarValor(sc.nextInt(), lugar);
				break;
			case 5:
				System.out.println("Saliendo...");
				salir = true;
				break;
			default:
				System.out.println("Solo números entre 1 y 5");
				break;
			}
			}catch(InputMismatchException iME) {
				System.out.println("Debes escribir un número del 1 al 5");
				sc.next();
			}
		}
	}
}
