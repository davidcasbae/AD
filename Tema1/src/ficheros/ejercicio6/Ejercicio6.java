package ficheros.ejercicio6;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Ejercicio6 {
	private static Scanner sc = new Scanner(System.in);
	private static ObjectOutputStream oOS;

	public static void inicializar() {
		try {
			File file = new File("FicheroSerializado.dat");
			if(file.exists() && file.length()>0) {
				oOS = new MyObjectOutputStream(new FileOutputStream(file, true));
			}else {
				oOS = new ObjectOutputStream(new FileOutputStream(file, true));
			}
		} catch (IOException ex) {
			System.out.println("Error al abrir el fichero para escritura");
			ex.printStackTrace();
		}
	}

	/**
	 * Pide por teclado los datos de la persona
	 * 
	 * @return un objeto de la clase persona
	 */
	public static Persona obtenerDatos() {
		Persona persona = new Persona();
		System.out.println("DATOS DEL USUARIO\n\tNombre: ");
		persona.setNombre(new StringBuilder(sc.nextLine()));
		System.out.println("\tPrimer Apellido: ");
		persona.setApellido1(new StringBuilder(sc.nextLine()));
		System.out.println("\tSegundo Apellido: ");
		persona.setApellido2(new StringBuilder(sc.nextLine()));
		System.out.println("\tFecha de nacimiento (dd-mm-yyyy): ");
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
			persona.setNacimiento(formatter.parse(sc.nextLine()));
		} catch (ParseException e) {
			System.out.println("Error en el formato de la fecha");
			e.printStackTrace();
		}
		return persona;
	}

	/**
	 * Escribe un objeto de la clase persona en un stream de salida
	 * 
	 * @param persona Objeto a escribir
	 */
	public static void escribirObjeto(Persona persona) {
		try {
			oOS.writeObject(persona);
		} catch (IOException ex) {
			System.out.println("Error al abrir el fichero para escritura");
			ex.printStackTrace();
		}

	}

	public static void leerObjetos() {
		try {
			ObjectInputStream oIS = new ObjectInputStream(new FileInputStream(new File("FicheroSerializado.dat")));
			try {
				Persona persona = new Persona();
				while (true) {
					persona = (Persona) oIS.readObject();
					System.out.println(persona);
				}

			} catch (ClassNotFoundException e) {
				System.out.println("Error de lectura");
				e.printStackTrace();
			} catch (EOFException e) {

			} finally {
				oIS.close();
			}
		} catch (IOException e) {
			System.out.println("Error al abrir el fichero para lectura");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		inicializar();
		escribirObjeto(obtenerDatos());
		escribirObjeto(obtenerDatos());
		leerObjetos();
		try {
			oOS.close();
		} catch (IOException e) {
			System.out.println("Error al abrir el fichero para lectura");
			e.printStackTrace();
		}
	}

}
