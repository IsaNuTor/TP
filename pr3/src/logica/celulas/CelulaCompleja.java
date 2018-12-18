package logica.celulas;

import java.io.BufferedWriter;
import java.io.IOException;

import constantes.Constantes;
import logica.Casilla;
import logica.Superficie;

public class CelulaCompleja implements Celula{

	/**
	 * Atributos.
	 */
	private int numero_celulas_comidas;
	
	/**
	 * Constructor.
	 */
	public CelulaCompleja() {
			
		numero_celulas_comidas = 0;
	}
	
	// Getter.
	/**
	 *Getter,  getNumero_celulas_comidas() , devuelve el numero de celulas simples comidas.
	 * @return numero_celulas_comidas
	 */
	public int getNumero_celulas_comidas() {
			
		return numero_celulas_comidas;
	}
	
	// Metodos.
	/**
	 * Metodo esComestible(), devuelve que la celula compleja no es comestible.
	 */
	public boolean esComestible() {
	
		return false;
	}

	/**
	 * Metodo mensajeCrearCelula(), devuelve el mensaje de creacion de una celula compleja.
	 */
	public String mensajeCrearCelula() {
	
		return "Creamos nueva celula compleja ";
	}

	/**
	 * Metodo mensajeEliminarCelula(int f, int c), devuelve el mensaje de explosion de la celula compleja
	 * por haber llegado al MAX_COMER.
	 */
	public String mensajeEliminarCelula(int f, int c) {
		
		return "Explota la celula compleja en (" + f + "," + c + ")";
	}

	/**
	 * Metodo cargarCelulaFichero(int n, int m), carga del fichero el numero de celulas comidas de la 
	 * celula compleja.
	 */
	public void cargarCelulaFichero(int n, int m) {
	
		this.numero_celulas_comidas = n;
	}

	/**
	 * Metodo guardarCelulaFichero(BufferedWriter bufferEscritura), guarda el numero de celulas comidas
	 * por esa celula en el fichero.
	 */
	public void guardarCelulaFichero(BufferedWriter bufferEscritura) {
		
		try{
			
			bufferEscritura.append(" compleja" + " " + numero_celulas_comidas + "\n");
			
		}catch (IOException e){
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo toString, devuelve la representacion de la celula compleja.
	 */
	public String toString() {
		
		return " " + numero_celulas_comidas + " ";
	}

	/**
	 *  Metodo ejecutaMovimiento, metodo que devuelve una casilla aleatoria.
	 */
	public Casilla ejecutaMovimiento(int f, int c, Superficie tablero) {
		
		Casilla casilla = null;
			
		casilla = tablero.generarCasillaAleatoriaDeSuperficie();
		
		return casilla;
	}

	/**
	 * Metodo muere, comprueba que la celula muera o no, si muere se pone a true, si no muere false.
	 */
	public boolean muere(Casilla cas) {
		
		boolean si = false;
		
		if(numero_celulas_comidas == Constantes.MAX_COMER) {
			
			si = true;
		}
		
		return si;
	}
	
	/**
	 * Metodo teReproduces, false, la celula compleja no se reproduce.
	 */
	public boolean teReproduces() {
	
		return false;
	}

	/**
	 * Metodo comes, incrementa el contador de celulas comidas.
	 */
	public void comes() {
		
		numero_celulas_comidas++;	
	}
}
