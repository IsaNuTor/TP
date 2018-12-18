package entradaSalidaDeDatos;

/**
 * Declaracion de la clase ParserEntrada.
 * @author Isabel y Patricia
 *
 */
public class ParserEntrada {

	/**
	 * Metodo parsearArgumentoString, convierte el string que le llega a un valor entero.
	 * @param argumento, argumento recibido en string.
	 * @return valorEnteroArgumento
	 */
	// Parsear los argumentos, para fila y columna, le pasamos por parametro un String y nos devuelve el valor en entero.
	public static int parsearArgumentoString(String argumento) {
		
		int valorEnteroArgumento;
		
		valorEnteroArgumento = Integer.parseInt(argumento);
		
		return valorEnteroArgumento;
	}
}
