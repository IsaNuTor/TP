package excepciones;

public class ErrorDeInicializacion extends Exception{
	
	/**
	 * Constructor.
	 */
	public ErrorDeInicializacion() { }
	
	/**
	 * Constructor.
	 * @param msj_error
	 */
	public ErrorDeInicializacion(String msj_error) {
		
		super(msj_error);
	}

}
