package e_s_datos;

import herramientas.Constantes;
import herramientas.Enum_Instrucciones;

import java.util.Scanner;

/**
 * Declaracion de la clase EntradaDeDatos.
 * @author Isabel y Patricia
 *
 */
public class EntradaDeDatos {

	/**
	 * Atributos.
	 */
	private Scanner entradaUsuario;
	private static int filasSuperficie;
	private static int columnasSuperficie;
	
	/**
	 * Constructor.
	 */
	public EntradaDeDatos(){
		
		entradaUsuario = new Scanner(System.in);
	}

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
		
	
	/**
	 * Metodos.
	 */
	/**
	 * Metodo capturarArgumentosFilasYColumnasRun, recoge los argumentos deseados para dar despues
	 * las dimensiones deseadas a la superficie.
	 * @param args, los argumentos introducidos para el tamano de la superficie.
	 */
	// public void recoger los argumentos, run, run configurations, arguments, y ponemos ejemplo 5 5 u otros.
	public static void capturarArgumentosFilasYColumnasRun(String[] args) {
		
		capturarFilaIntroducidaPorArgumentos(args);
		capturarColumnaIntroducidaPorArgumentos(args);
	}
	
	/**
	 * Metodo capturarFilaIntroducidaPorArgumentos, indica el tamano de filas que se quiere tener en 
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
			fila = Validador.parsearArgumentoString(filaString);
		}
		
		filasSuperficie = fila;
	}
	
	/**
	 * Metodo capturarFilaIntroducidaPorArgumentos, indica el tamano de filas que se quiere tener en 
	 * la superficie.
	 * @param args, entrada de los datos de filas por argumentos.
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
			columna = Validador.parsearArgumentoString(columnaString);
			
		}
			
		columnasSuperficie = columna;
	}
	
	/**
	 * Metodo devuelveFilaIntroducidaPorConsolaAlUsuario, nos devuelve la fila que ha sido introducida
	 * por consola por parte del usuario.
	 * @return fila
	 */
	// Nos devuelve la fila que haya insertado el usuario despues de los comandos crearCelula y eliminarCelula.
	public int devuelveFilaIntroducidaPorConsolaAlUsuario() {
			
		int fila;
			
		fila = entradaUsuario.nextInt();
			
		return fila;
	}
	
	/**
	 * Metodo devuelveColumnaIntroducidaPorConsolaUsuario, nos devuelve la columna que ha sido introducida
	 * por consola por parte del usuario.
	 * @return columna
	 */
	// Nos devuelve la columna que haya insertado el usuario despues de la fila.
	public int devuelveColumnaIntroducidaPorConsolaUsuario() {
				
		int columna;
			
		columna = entradaUsuario.nextInt();
				
		return columna;
	}
	
	/**
	 * Metodo pedirComandoPorConsolaAlUsuario, pide el comando al usuario y segun la opcion que 
	 * este elija, el metodo nos devolvera el enumerado correspondiente a esa eleccion.
	 * @return ordenUsuarioEnEnumerado
	 */
	// Le pedimos el comando y nos devuelve el enumerado correspondiente.
	public Enum_Instrucciones pedirComandoPorConsolaAlUsuario() {
		
		Enum_Instrucciones ordenUsuarioEnEnumerado;
		String ordenUsuario;
		
		// Pide el comando al usuario.
		System.out.print("Comando > ");
				
		ordenUsuario = entradaUsuario.next();
		
		// Comprobamos llamando a validador, si el dato es correcto.
		// Comprobamos si la orden es correcta, si es correcta nos devuelve el enumerado.
		ordenUsuarioEnEnumerado = Validador.comprobarSiLaOrdenDelUsuarioEsCorrecta(ordenUsuario);
		
		return ordenUsuarioEnEnumerado;
	}
}
