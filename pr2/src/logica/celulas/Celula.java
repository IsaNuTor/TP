package logica.celulas;

import logica.Casilla;
import logica.Superficie;

/**
 * Declaracion de la clase Celula.
 * @author Isabel y Patricia
 * 
 */
public abstract class Celula {
	
	// Atributos.
	protected boolean esComestible;
	
	//Constructor.
	public Celula() {
		
	}
	
	// Metodos.
	/**
	 * Metodo ejecutaMovimiento(int f, int c, Superficie superficie), realiza el movimiento de una celula colocada
	 * en la posicion (f,c) de la superficie, y devuelve la casilla a la que se ha movido la celula, null en caso
	 * de que la celula no se pueda mover.
	 * @param f, fila
	 * @param c, columna
	 * @param  tablero, tablero
	 * @return Casilla
	 */
	public abstract Casilla ejecutaMovimiento(int f, int c, Superficie tablero);
	
	/**
	 * Metodo esComestible(), devuelve el valor del atributo esComestible.
	 * @return boolean, true si es una celula simple y false si es compleja.
	 */
	public abstract boolean esComestible();
	
	/**
	 * Metodo mensajeCrearCelulaSimple(), devuelve el mensaje de celula creada.
	 * @return mensajeDeSalida
	 */
	public abstract String mensajeCrearCelula();
	
	/**
	 * M�todo mensajeEliminarCelula(int f, int c), devuelve el mensaje de la eliminacion de
	 * la celula.
	 * @param f, fila de la que se ha eliminado la celula.
	 * @param c, columna de la que se ha eliminado la celula.
	 * @return mensajeDeSalida
	 */
	public abstract String mensajeEliminarCelula(int f, int c);
	
}
