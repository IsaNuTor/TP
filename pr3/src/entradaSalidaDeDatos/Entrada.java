package entradaSalidaDeDatos;

import java.util.InputMismatchException;
import java.util.Scanner;

import constantes.Constantes;

/**
 * Declaracion de la clase Entrada.
 * @author Isabel y Patricia
 *
 */
public class Entrada {
	
	/**
	 * Atributos.
	 */
	private Scanner entradaUsuario;
	private static int filasSuperficie;
	private static int columnasSuperficie;
	
	/** 
	 * Constructor.
	 */
	public Entrada(){
		
		entradaUsuario = new Scanner(System.in);
	}

	// Getter y Setter.
	/**
	 * Getter, getFilasSuperficie.
	 * @return filasSuperficie
	 */
	public static int getFilasSuperficie() {
		return filasSuperficie;
	}

	/**
	 * Getter, getColumnasSuperficie.
	 * @return columnasSuperficie
	 */
	public static int getColumnasSuperficie() {
		return columnasSuperficie;
	}
		
	
	// Metodos.
	/**
	 * Metodo capturarArgumentosFilasYColumnasRun, recoge los argumentos deseados para dar despues
	 * las dimensiones deseadas a la superficie.
	 * @param args, los argumentos introducidos para el tamanio de la superficie.
	 */
	// public void recoger los argumentos, run, run configurations, arguments, y ponemos ejemplo 5 5 u otros.
	public static void capturarArgumentosFilasYColumnasRun(String[] args) {
		
		capturarFilaIntroducidaPorArgumentos(args);
		capturarColumnaIntroducidaPorArgumentos(args);
	}
	
	/**
	 * Metodo capturarFilaIntroducidaPorArgumentos, indica el tamanio de filas que se quiere tener en 
	 * la superficie.
	 * @param args, entrada de los datos de filas por argumentos.
	 */
	// Entrada por argumentos.
	// Para los argumentos run, run configurations, arguments, y ponemos ejemplo 5 5 u otros.
	public static void capturarFilaIntroducidaPorArgumentos(String[] args) {
			
		String filaString;
		int fila;
			
		if(args.length != 2) { // No se le han metido dos argumentos, le pasamos la constante.
				
			fila = Constantes.FILAS;
		} 
		else { // El usuario ha introducido dos argumentos.
			
			// Primer argumento.
			filaString = args[0];
				
			// Parseamos la filaString para convertirlo a un valor entero.
			fila = Integer.parseInt(filaString);
		}
		
		filasSuperficie = fila;
	}
	
	/**
	 * Metodo capturarFilaIntroducidaPorArgumentos, indica el tamanio de columnas que se quiere tener en 
	 * la superficie.
	 * @param args, entrada de los datos de columnas por argumentos.
	 */
	// Para los argumentos run, run configurations, arguments, y ponemos ejemplo 5 5 u otros.
	public static void capturarColumnaIntroducidaPorArgumentos(String[] args) {
			
		String columnaString;
		int columna;
			
		if(args.length != 2) { // No se le han metido dos argumentos, le pasamos la constante.
				
			columna = Constantes.COLUMNAS;
		}
		else {
			
			// Segundo argumento.
			columnaString = args[1];
				
			// Parseamos la filaString para convertirlo a un valor entero.
			columna = Integer.parseInt(columnaString);
			
		}
			
		columnasSuperficie = columna;
	}
	
	/**
	 * Metodo pedirComandoPorConsolaAlUsuario, pide el comando al usuario para posteriormente ejecutarlo.
	 * @return cadenaUsuario.
	 */
	public String[] pedirComandoPorConsolaAlUsuario() {
		
		String ordenUsuario;
		
		// Pide el comando al usuario.
		System.out.print("Comando > ");
		
		ordenUsuario = entradaUsuario.nextLine();
	
		String cadenaUsuario[] = ordenUsuario.split(" ");
		
		return cadenaUsuario;
	}
	
	/**
	 * Metodo pedirMundoPorConsolaAlUsuario, pide un mundo al usuario para posteriormente iniciarlo.
	 * @return numeroMundo.
	 */
	public int pedirMundoPorConsolaAlUsuario() {
		
		int numeroMundo = 0;
		boolean numeroCorrecto = false;
		
		// Pide el mundo al usuario.
		while(!numeroCorrecto) {
			
			try {
				System.out.print("De que tipo quiere el mundo:\n"
						+ "1: Mundo Simple\n" + "2: Mundo Complejo\n" + "Opcion: ");
				
				numeroMundo = entradaUsuario.nextInt();
				
				if((numeroMundo == 1) || (numeroMundo == 2)) {
					numeroCorrecto = true;
				}
				else {
					System.out.println("Numero de Mundo incorrecto, por favor vuelva a insertarlo");
				}
				
			}
			catch(InputMismatchException ex) {
				System.out.println("EXCEPCION: El formato ha de ser numerico no alfabetico, vuelve a intentarlo");
				entradaUsuario.nextLine();
			}
				
		}
		
		entradaUsuario.nextLine(); // Lo hacemos para limpiar el buffer.
		
		return numeroMundo;
	}

	/**
	 * Metodo pedirMundoPorConsolaAlUsuario, pide un mundo al usuario para posteriormente iniciarlo.
	 * @return numeroMundo.
	 */
	public int pedirCelulaPorConsolaAlUsuario() {
		
		int numeroCelula = 0;
		boolean numeroCorrecto = false;
		
		// Pide el mundo al usuario.
		while(!numeroCorrecto) {
			
			try {
				System.out.print("De que tipo: Compleja (1) o Simple (2): ");
					
				numeroCelula = entradaUsuario.nextInt();
				
				if((numeroCelula == 1) || (numeroCelula == 2)) {
					numeroCorrecto = true;
				}
				else {
					System.out.println("Numero de Celula incorrecto, por favor vuelva a insertarlo");
				}
				
			}
			catch(InputMismatchException ex) {
				System.out.println("EXCEPCION: El formato ha de ser numerico no alfabetico, vuelve a intentarlo");
				entradaUsuario.nextLine();
			}
				
		}
		
		entradaUsuario.nextLine(); // Lo hacemos para limpiar el buffer.
		
		return numeroCelula;
	}
	
}
