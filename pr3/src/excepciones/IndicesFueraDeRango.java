package excepciones;

public class IndicesFueraDeRango extends Exception{
	
	/**
	 * Constructor.
	 */
	public IndicesFueraDeRango() { }
	
	/**
	 * Constructor.
	 * @param msj_error
	 */
	public IndicesFueraDeRango(String msj_error) {
		
		super(msj_error);
	}

}
