package logica.celulas;

import constantes.Constantes;
import logica.Casilla;
import logica.Superficie;

/**
 * Declaracion de la clase CelulaCompleja.
 * @author Isabel y Patricia
 *
 */
public class CelulaCompleja extends Celula{
	
	// Atributos.
	private int numero_celulas_comidas;
	
	// Constructor.
	public CelulaCompleja() {
		
		numero_celulas_comidas = 0;
		esComestible = false;
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
	 * Metodo ejecutaMovimiento(int f, int c, Superficie superficie), ejecuta el movimiento de la 
	 * celula compleja escogiendo una posicion aleatoria en todo el tablero y distinguiendo al encontrarse otra
	 * celula si es comestible o no, si es comestible se trata de una celula simple, se la come, incrementa
	 * el contador de MAX_COMER y se mueve a su casilla, por el contrario, si esa celula no es comestible
	 *  se trata de una celula compleja, no se mueve y tampoco se la come.
	 *  Ademas si la celula compleja llega a MAX_COMER explota y desaparece del tablero.
	 */
	public Casilla ejecutaMovimiento(int f, int c, Superficie superficie) {
		
		Casilla casilla = null;
		Celula celulaCasillaElegida;
		
		casilla = superficie.generarCasillaAleatoriaDeSuperficie();
			
		celulaCasillaElegida = superficie.getCelula(casilla.getX(), casilla.getY());
			
		if(celulaCasillaElegida == null) {
			// No hay Celula realizamos el movimiento normal en la superficie.
			superficie.ejecutaMovimientoDeCelula(f, c, casilla);
			System.out.println("Celula compleja en (" + f + "," + c + ") se mueve a " + casilla + " --NO COME--");
		}
		else if(celulaCasillaElegida.esComestible) {
			// Es comestible, por lo tanto se come la celulasimple y hay que sumar a su contador de celulas comidas.
			superficie.ejecutaMovimientoDeCelula(f, c, casilla);
			numero_celulas_comidas++;
			System.out.println("Celula compleja en (" + f + "," + c + ") se mueve a " + casilla + " --COME--");
			if(numero_celulas_comidas == Constantes.MAX_COMER) {
				// Explota.
				superficie.defuncion(casilla.getX(), casilla.getY());
				casilla = null;
			}
		}
		else {
			// Hay una celula compleja por lo tanto no se mueve.
			System.out.println("La celula compleja de la posicion (" + f + "," + c + ")" + " no se mueve a " + casilla + " porque hay otra celula compleja");
			casilla = null;
		}
	
		return casilla;
	}
	
	/**
	 * Metodo esComestible(), devuelve que la celula compleja no es comestible.
	 */
	public boolean esComestible() {
	
		return esComestible;
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
	 * Metodo toString, devuelve la representacion de la celula compleja.
	 */
	public String toString() {
		
		return " * ";
	}

}
