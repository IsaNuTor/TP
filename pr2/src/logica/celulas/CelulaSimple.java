package logica.celulas;

import constantes.Constantes;
import logica.Casilla;
import logica.Superficie;

/**
 * Declaracion de la clase CelulaSimple.
 * @author Isabel y Patricia
 *
 */
public class CelulaSimple extends Celula{
	
	// Atributos.
	private int n;
	private int m;
	
	// Constructor.
	public CelulaSimple() {
		
		n = Constantes.MAX_PASOS_SIN_MOVER;
		m = Constantes.PASOS_REPRODUCCION;	
		esComestible = true;
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
	
	// Metodos.
	/**
	 * Metodo ejecutaMovimiento(int f, int c, Superficie superficie), ejecuta un movimiento de la
	 * celula simple ajustandose a sus reglas basicas que son que si la n llega a 0 la celula simple
	 * muere por no poder moverse, asi mismo la celula simple puede morir por no poder reproducrise.
	 */
	public Casilla ejecutaMovimiento(int f, int c, Superficie superficie) {
		
		Casilla casillaElegida = null;
		
		if(n == 0) {
			superficie.defuncion(f, c);
		}
		else {
			// Almacenamos las casillas libres en un arraylist.
			if(superficie.almacenarCasillasLibresParaUnaCelula(f, c)) {
				
				// Elegimos una casilla al azar y la devolvemos.
				casillaElegida = superficie.devuelveCasillaLibreAleatoria(superficie.posRandomDeCeldasLibres());
				// Si la m = 0, entonces reproducimos la celula.
				if(m == 0){
					n = Constantes.MAX_PASOS_SIN_MOVER;
					m = Constantes.PASOS_REPRODUCCION;
					superficie.reproduccionDeLaCelula(casillaElegida.getX(), casillaElegida.getY(), f, c);
				}
				else {
					
					m--;
					superficie.ejecutaMovimientoDeCelula(f, c, casillaElegida);
					System.out.println("Movimiento de (" + f + "," + c + ") a "
							+ casillaElegida);
				}
			}
			else { // Si no hay casillas libres, no se puede mover.
				
				if(m == 0) {
					superficie.defuncion(f, c);
				}
				else {
					n--;
				}
				
			}
		}
		
		return casillaElegida;
	}
	
	/**
	 * Metodo esComestible(), devuelve que la celula simple es comestible.
	 */
	public boolean esComestible() {
		
		return esComestible;
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
	 * Metodo toString, devuelve la representacion de la celula simple.
	 */
	public String toString() {
		
		return " X ";
	}

}
