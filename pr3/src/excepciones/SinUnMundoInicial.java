package excepciones;

/**
 * Declaracion de la clase SinUnMundoInicial.
 * @author Isabel y Patricia
 *
 */

public class SinUnMundoInicial extends Exception{
	
	/**
	 * Constructor.
	 */
	public SinUnMundoInicial() { }
	
	/**
	 * Constructor.
	 * @param msj_error
	 */
	public SinUnMundoInicial(String msj_error) {
		
		super(msj_error);
	}

}
