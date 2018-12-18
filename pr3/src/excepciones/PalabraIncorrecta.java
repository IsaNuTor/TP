package excepciones;

/**
 * Declaracion de la clase PalabraIncorrecta.
 * @author Isabel y Patricia
 *
 */

public class PalabraIncorrecta extends Exception{
	
	/**
	 * Constructor.
	 */
	public PalabraIncorrecta() { }
	
	/**
	 * Constructor.
	 * @param msj_error
	 */
	public PalabraIncorrecta(String msj_error) {
		
		super(msj_error);
	}

}
