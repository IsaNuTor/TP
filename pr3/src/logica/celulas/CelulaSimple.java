package logica.celulas;

import java.io.BufferedWriter;
import java.io.IOException;

import constantes.Constantes;
import logica.Casilla;
import logica.Superficie;

public class CelulaSimple implements Celula{

	// Atributos.
	private int n;
	private int m;
	
	// Constructor.
	public CelulaSimple() {
			
		n = Constantes.MAX_PASOS_SIN_MOVER;
		m = Constantes.PASOS_REPRODUCCION;
	}
	
	// Getter y Setter.
	/**
	 * Getter, getN.
	 * @return n
	 */
	public int getN() {
		return n;
	}
	
	/**
	 * Getter, getM.
	 * @return m
	 */
	 public int getM() {
		return m;
	 }
		
	 /**
	  * Setter, setN.
	  * @param n, define la n que va a tener.
	  */
	 public void setN(int n) {
		this.n = n;
	 }
		
	 /**
	  * Setter, setM.
	  * @param m, define la m que va a tener.
	  */
	 public void setM(int m) {
	 	this.m = m;
	 }
	
	/**
	 * Metodo ejecutaMovimiento, metodo que devuelve una casilla aleatoria.
	 */
	public Casilla ejecutaMovimiento(int f, int c, Superficie tablero) {
		
		Casilla casillaLibre = null;
		
		casillaLibre = tablero.dameCasillaLibreAleatoriaAlrededorDeCelula(f, c);
		
		// Le pedimos a la superficie que nos de una casilla libre.
		return casillaLibre;
	}
	
	/**
	 * Metodo muere, comprueba que la celula muera o no, si muere se pone a true, si no muere false.
	 */
	public boolean muere(Casilla cas) {
		
		boolean si = false;
		
		if((n == 0) || ((cas == null) && (m == 0))){
			si = true;
		}
		else if(cas == null) {
			n--;
		}
		else {
			m--;
		}
		
		return si;
	}

	/**
	 * Metodo esComestible(), devuelve que la celula simple es comestible.
	 */
	public boolean esComestible() {
		
		return true;
	}

	/**
	 * Metodo mensajeCrearCelula(), devuelve el mensaje de creacion de una celula simple.
	 */
	public String mensajeCrearCelula() {
		
		return "Creamos nueva celula simple ";
	}

	/**
	 * Metodo mensajeEliminarCelula(int f, int c), devuelve el mensaje de muerte de la celula simple
	 * tanto por el caso de que la n llegue a 0 o que muera por no poder reproducirse.
	 */
	public String mensajeEliminarCelula(int f, int c) {
		
		String cadena;
		
		if(n == 0) {
			cadena = "Muere la celula de la casilla (" + f + "," + c + ") por inactividad";
		}
		else {
			cadena = "Muere la celula de la casilla (" + f + "," + c + ") por no poder reproducirse";
		}
		
		return cadena;
	}

	/**
	 * Metodo cargarCelulaFichero(int n, int m), carga del fichero el numero de pasos sin mover y los
	 * pasos en reproduccion.
	 */
	public void cargarCelulaFichero(int n, int m) {
		
		this.n = n;
		this.m = m;
	}
	
	/**
	 * Metodo guardarCelulaFichero(BufferedWriter bufferEscritura), guarda en el fichero el numero de
	 * pasos sin mover y los pasos en reproduccion.
	 */
	public void guardarCelulaFichero(BufferedWriter bufferEscritura) {
		
		try{
			
			bufferEscritura.append(" simple" + " " + n + " " + m + "\n");
			
		}catch (IOException e){
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Metodo toString, devuelve la representacion de la celula simple.
	 */
	public String toString() {
		
		return (n + "-" + m);
	}

	/**
	 * Metodo teReproduces, pone a true si se puede reproducir la celula, en caso contrario false.
	 */
	public boolean teReproduces() {
		
		boolean si = false;
		
		if(m == -1) {
			si = true;
			n = Constantes.MAX_PASOS_SIN_MOVER;
			m = Constantes.PASOS_REPRODUCCION;
		}
		return si;
	}

	/**
	 * Metodo comes.
	 */
	public void comes() {
	
	}
	
}
