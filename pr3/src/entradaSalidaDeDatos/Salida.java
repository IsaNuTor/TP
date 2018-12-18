package entradaSalidaDeDatos;

/**
 * Declaracion de la clase Salida.
 * @author Isabel y Patricia
 *
 */
public class Salida {
	
	/**
	 * Atributo.
	 */
	private String mensajeDeSalidaConsola;
	
	/**
	 * Constructor.
	 * @param mensaje
	 */
	public Salida(String mensaje) {
		
		mensajeDeSalidaConsola = mensaje;
	}
	
	/**
	 * Metodo toString, devuelve un String con el mensaje de salida.
	 */
	public String toString() {
	
		return mensajeDeSalidaConsola;
	}
}

