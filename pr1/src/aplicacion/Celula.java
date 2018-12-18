package aplicacion;

import herramientas.Constantes;

/**
 * Declaracion de la clase Celula.
 * @author Isabel y Patricia
 * 
 */
public class Celula {
	
	/**
	 * Atributos.
	 */
	private int n;
	private int m;
	
	/**
	 * Constructor.
	 */
	public Celula() {
		
		n = Constantes.MAX_PASOS_SIN_MOVER;
		m = Constantes.PASOS_REPRODUCCION;	
	}
	
	/**
	 * Setter, setN.
	 * @param n, define la n que va a tener
	 */
	public void setN(int n) {
		this.n = n;
	}
	
	/**
	 * Setter, setM.
	 * @param m, define la m que va a tener
	 */
	public void setM(int m) {
		this.m = m;
	}
	
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
	 * Metodo toString.
	 */
	public String toString() {
		return n + "-" + m;
	}
}
