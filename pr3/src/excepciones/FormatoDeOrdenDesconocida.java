package excepciones;

/**
 * Declaracion de la clase FormatoDeOrdenDesconocida.
 * @author Isabel y Patricia
 *
 */

public class FormatoDeOrdenDesconocida extends Exception{

	/**
	 * Constructor.
	 */
	public FormatoDeOrdenDesconocida() { }
	
	/**
	 * Constructor.
	 * @param msj_error
	 */
	public FormatoDeOrdenDesconocida(String msj_error) {
		
		super(msj_error);
	}
	
}
