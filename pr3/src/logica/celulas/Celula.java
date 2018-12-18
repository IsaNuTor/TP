package logica.celulas;

import java.io.BufferedWriter;

import logica.Casilla;
import logica.Superficie;

public interface Celula {
	
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
	  * Metodo mensajeEliminarCelula(int f, int c), devuelve el mensaje de la eliminacion de
	  * la celula.
	  * @param f, fila de la que se ha eliminado la celula.
	  * @param c, columna de la que se ha eliminado la celula.
      * @return mensajeDeSalida
	  */
	 public abstract String mensajeEliminarCelula(int f, int c);
	 
	 /**
	  * Metodo muere(Casilla cas), metodo para identificar si la celula muere.
	  * En el caso de la celula simple se pone a true cuando no se pueda mover.
	  * En el caso de la celula compleja se pone a true cuando coma mas celulas del maximo permitido.
	  * @param cas
	  * @return true si muere, false si no muere.
	  */
	 public abstract boolean muere(Casilla cas);
	 
	 /**
	  * MetodoteReproduces(), metodo para identificar si la celula se puede reproducir.
	  * En el caso de la celula simple se pone a true cuando no se pueda reproducir.
	  * En el caso de la celula compleja nunca se pone a true.
	  * @return true si se reproduce, false si no se reproduce.
	  */
	 public abstract boolean teReproduces();
	 
	 /**
	  * Metodo comes(), metodo que indica que la celula compleja come.
	  */
	 public abstract void comes();
	  
	 /**
	  * Metodo cargarCelulaFichero(int n, int m), carga la celula indicada por el fichero.
	  * @param n, numero de pasos sin mover de la celula.
	  * @param m, numero de pasos en reproduccion de la celula.
	  */
	 public abstract void cargarCelulaFichero(int n, int m);
	  
	 /**
	  * guardarCelulaFichero(BufferedWriter bufferEscritura), guarda en el fichero los pasos sin mover 
	  * y los pasos en reproduccion de la celula simple y el numero de celulas comidas de la 
	  * celula compleja.
	  * @param bufferEscritura
	  */
	 public abstract void guardarCelulaFichero(BufferedWriter bufferEscritura);
	  

}
