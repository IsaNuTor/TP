package constantes;

/**
 * Declaracion de la clase Constantes.
 * @author Isabel y Patricia
 *
 */
public class Constantes {

	// Celula.
	/**
	 * El numero de celulas simples iniciales en la superficie
	 */
	public final static int NUMERO_CELULAS_SIMPLES = 3;
	
	/**
	 * El numero de celulas complejas iniciales en la superficie
	 */
	public final static int NUMERO_CELULAS_COMPLEJAS = 2;
	
	/**
	 * El numero de pasos en los que la celula no se ha movido
	 */
	public final static int MAX_PASOS_SIN_MOVER = 1;
	
	/**
	 * El numero de pasos dados por la celula en el mundo
	 */
	public final static int PASOS_REPRODUCCION = 2;
	
	/**
	 * El numero maximo de celulas simples que pueden comer las celulas complejas
	 */
	public final static int MAX_COMER = 3;
	
	// Superficie.
	/**
	 * Numero de filas que contiene la superficie
	 */
	public final static int FILAS = 5;
	
	/**
	 *  Numero de columnas que contiene la superficie
	 */
	public final static int COLUMNAS = 6;
	
	/**
	 * El numero maximo de posiciones libres que va a tener la celula a su alrededor
	 */
	public final static int NUMERO_POSIBLE_DE_CELDAS_LIBRES = 8;
}