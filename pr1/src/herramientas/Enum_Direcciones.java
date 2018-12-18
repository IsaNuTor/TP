package herramientas;

/**
 * Declaracion del enumerado Enum_Direcciones.
 * @author Isabel y Patricia
 *
 */
public enum Enum_Direcciones {

	/**
	 * Valores de coordenadas de las ocho posiciones libres que puede adoptar una celula cuando 
	 * tiene la posibilidad de moverse.
	 */
	NORTE (-1, 0), 
	NORESTE (-1, +1),
	ESTE (0, +1),
	SURESTE (+1, +1),
	SUR(+1, 0),
	SUROESTE(+1, -1),
	OESTE(0, -1),
	NOROESTE(-1, -1);
	
	/**
	 *  Atributos.
	 */
	private int posFila;
	private int posColumna;
	
	private static Enum_Direcciones[] enumDirecciones = Enum_Direcciones.values();
	
	/**
	 * Getter, getEnumDirecciones.
	 * @return enumDirecciones
	 */
	public static Enum_Direcciones[] getEnumDirecciones() {
		
		return enumDirecciones;
	}
	
	/**
	 * Getter, getPosFila.
	 * @return posFila
	 */
	public int getPosFila() {
		return posFila;
	}
	
	/**
	 * Getter, getPosColumna.
	 * @return posColumna
	 */
	public int getPosColumna() {
		return posColumna;
	}

	/**
	 * Constructor, Enum_Direcciones.
	 * @param posFila.
	 * @param posColumna.
	 */
	private Enum_Direcciones(int posFila, int posColumna) {
		
		this.posFila = posFila;
		this.posColumna = posColumna;
	}
}
