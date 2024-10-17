package ejercicio7;

import java.io.*;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Scanner;

public class ejercicio7 {
	private static Properties conf = new Properties();
	
	public static void main(String[] args) {
		crearConfiguracion();
		leerConfiguracion();
		modificarConfiguracion();
		leerConfiguracion();
	}
	
	private static void crearConfiguracion() {
		conf.setProperty("user", "usuario");
		conf.setProperty("password", "micontrasena");
		conf.setProperty("server", "localhost");
		conf.setProperty("port", "3306");
		conf.setProperty("date", "2022-08-11");
		conf.setProperty("power", "true");
		
		try {
			conf.store(new FileOutputStream("configuracion.props"), "fichero de configuración");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void leerConfiguracion() {
		try {
			conf.load(new FileInputStream("configuracion.props"));
			conf.list(System.out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void modificarConfiguracion() {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("MODIFICANDO CONFIGURACIÓN");
			conf.load(new FileInputStream("configuracion.props"));
			System.out.println("user: ");
			conf.setProperty("user", sc.nextLine());
			System.out.println("password: ");
			conf.setProperty("password", sc.nextLine());
			System.out.println("server: ");
			conf.setProperty("server", sc.nextLine());
			
			System.out.println("valor a sumar al puerto: ");
			int puerto = Integer.valueOf(sc.nextLine());
			puerto = Integer.valueOf(conf.getProperty("port")) + puerto;
			conf.setProperty("port", String.valueOf(puerto));
			
			System.out.println("Nuevo mes para la fecha: ");
			int mes = Integer.valueOf(sc.nextLine());
			conf.setProperty("date", String.valueOf(LocalDate.parse(conf.getProperty("date")).withMonth(mes)));
			
			conf.setProperty("power",String.valueOf(!Boolean.valueOf(conf.getProperty("power"))));
			
			conf.store(new FileOutputStream("configuracion.props"), "fichero modificado");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
