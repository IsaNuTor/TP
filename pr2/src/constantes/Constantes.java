package constantes;

/**
 * Declaracion de la clase Constantes.
 * @author Isabel y Patricia
 *
 */
public class Constantes {

	/**
	 * Declaramos los atributos privados.
	 * NUMERO_CELULAS_SIMPLES: El numero de celulas simples iniciales en la superficie.
	 * NUMERO_CELULAS_COMPLEJAS: El numero de celulas complejas iniciales en la superficie.
	 * MAX_PASOS_SIN_MOVER: El numero de pasos en los que la celula no se ha movido.
	 * PASOS_REPRODUCCION: El numero de pasos dados por la celula en el mundo.
	 * MAX_COMER: El numero maximo de celulas simples que pueden comer las celulas complejas.
	 * FILAS: Numero de filas que contiene la superficie.
	 * COLUMNAS: Numero de columnas que contiene la superficie.
	 * NUMERO_POSIBLE_DE_CELDAS_LIBRES: El numero maximo de posiciones libres que va a tener la celula a su alrededor.
	 */
	
	// Celula.
	public final static int NUMERO_CELULAS_SIMPLES = 3;
	
	public final static int NUMERO_CELULAS_COMPLEJAS = 2;
	
	public final static int MAX_PASOS_SIN_MOVER = 1;
	
	public final static int PASOS_REPRODUCCION = 2;
	
	public final static int MAX_COMER = 3;
	
	// Superficie.
	public final static int FILAS = 5;
	
	public final static int COLUMNAS = 4;
	
	public final static int NUMERO_POSIBLE_DE_CELDAS_LIBRES = 8;
	
}
