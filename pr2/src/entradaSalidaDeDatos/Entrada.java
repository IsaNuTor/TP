package entradaSalidaDeDatos;

import java.util.Scanner;

import constantes.Constantes;

/**
 * Declaracion de la clase Entrada.
 * @author Isabel y Patricia
 *
 */
public class Entrada {
	
	// Atributos.
	private Scanner entradaUsuario;
	private static int filasSuperficie;
	private static int columnasSuperficie;
	
	// Constructor.
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
			fila = ParserEntrada.parsearArgumentoString(filaString);
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
			columna = ParserEntrada.parsearArgumentoString(columnaString);
			
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
	
}
