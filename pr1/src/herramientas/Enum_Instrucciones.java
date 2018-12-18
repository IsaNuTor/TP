package herramientas;

/**
 * Declaracion del enumerado Enum_Instrucciones.
 * @author Isabel y Patricia
 *
 */
public enum Enum_Instrucciones {
	
	PASO("paso", "PASO: realiza un paso en la simulacion."), 
	INICIAR("iniciar", "INICIAR: inicia una nueva simulacion."),
	CREARCELULA("crearcelula", "CREARCELULA F C: crea una nueva celula en la posicion (f, c) si es posible."),
	ELIMINARCELULA("eliminarcelula", "ELIMINARCELULA F C: elimina una celula de la posicion (f, c) si es posible."),
	AYUDA("ayuda", "AYUDA: muestra esta ayuda."),
	VACIAR("vaciar", "VACIAR: crea un mundo vacio."),
	SALIR("salir", "SALIR: cierra la aplicacion."),
	DESCONOCIDO("error", " ");
	
	/**
	 * Atributos.
	 */
	// Hemos creado un array de enumerados, {PASO, INICIAR, CREARCELULA, ELIMINARCELULA, AYUDA, VACIAR, SALIR, DESCONOCIDO}.
	// El metodo values sirve para devolvernos un array de enumerados.
	private static Enum_Instrucciones[] enumDeOrdenes = Enum_Instrucciones.values();
	private String ordenString;
	private String descripcionOrden;
	
	/**
	 * Constructor, Enum_Instrucciones.
	 * @param ordenString
	 * @param descripcionOrden
	 */
	private Enum_Instrucciones(String ordenString, String descripcionOrden) {
		
		this.ordenString = ordenString;
		this.descripcionOrden = descripcionOrden;
		
	}
	
	/**
	 * Getter, getArrayEnumerados.
	 * @return enumDeOrdenes
	 */
	// Devolver el array de enumerados.
	public static Enum_Instrucciones[] getArrayEnumerados() {
		
		return enumDeOrdenes;
	}
	
	/**
	 * Getter, getOrdenString.
	 * @return ordenString
	 */
	// Getter -> devuelve ordenString.
	public String getOrdenString() {
		return ordenString;
	}
	
	/**
	 * Getter, getDescripcionOrden.
	 * @return descripcionOrden
	 */
	// Getter -> devuelve descripcionOrden.
	public String getDescripcionOrden() {
		return descripcionOrden;
	}
	
	/**
	 * Metodos.
	 */
	/**
	 * Metodo tamanoArrayEnumerados, creamos un metodo que nos devuelve el numero de enumerados 
	 * que hay en el array.
	 * @return tamanoArrayEnum
	 */
	// En nuestro caso actual nos devuelve 8.
	public static int tamanoArrayEnumerados() {
		int tamanoArrayEnum;
		
		tamanoArrayEnum = enumDeOrdenes.length;
		
		return tamanoArrayEnum;
	}	
}
