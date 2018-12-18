package aplicacion;

import java.util.Arrays;

import e_s_datos.EntradaDeDatos;
import herramientas.Constantes;

/**
 * Declaracion de la clase Mundo.
 * @author Isabel y Patricia
 *
 */
public class Mundo {
	
	/**
	 * Atributos.
	 */
	private Superficie superficie;
	
	/**
	 * Contructor.
	 */
	public Mundo(){
		
		// Creamos una superficie con todas las posiciones a null.
		superficie = new Superficie(EntradaDeDatos.getFilasSuperficie(), EntradaDeDatos.getColumnasSuperficie());
		
		// Anadimos las celulas a la superficie.
		superficie.inicializarTableroConCelulasAleatorias();
	}
	
	/**
	 * Getter, getSuperficie.
	 * @return superficie
	 */
	public Superficie getSuperficie() {
		return superficie;
	}


	/**
	 * Metodos.
	 */
	/**
	 * Metodo crearCelula, crea una nueva celula en la superficie, devolvera
	 * false cuando en la posicion elegida ya haya una celula, o las posiciones se salgan de la superficie.
	 * Cuando se pueda realizar, creara la celula, la colocara en la posicion y devolvera true.
	 * @param f, define la fila que entra.
	 * @param c, define la columna que entra.
	 * @return celulaCreada
	 */
	public boolean crearCelula(int f, int c) {
		
		boolean celulaCreada = false;
		boolean hayCelula;
		
		// Comprobamos si fila y columna estan dentro del rango.
		if(((f >= 0) && (f < EntradaDeDatos.getFilasSuperficie())) && ((c >= 0) && (c < EntradaDeDatos.getColumnasSuperficie()))) {
			
			// Comprobamos si hay celula en la posicion.
			hayCelula = superficie.comprobarSihayCelula(f, c);
			if(!hayCelula) {
				// Si no habia celula, colocamos una nueva en la posicion (f, c).
				superficie.colocarCelulaEnLaPosicion(f, c);
				// Devolvemos true.
				celulaCreada = true;
			}
		}
		
		return celulaCreada;
	}
	
	/**
	 * Metodo eliminarCelula, elimina una celula de la superficie, devolvera 
	 * false cuando en la posicion elegida no exista una celula o las posiciones se salgan de la superficie.
	 * Cuando se pueda realizar, eliminara la celula, colocara esa posicion a null y devolvera true.
	 * @param f, define la fila que entra.
	 * @param c, define la columna que entra.
	 * @return celulaEliminada
	 */
	//boolean dara false cuando la posicion ya esta a null o las posiciones se salgan del tablero.
	public boolean eliminarCelula(int f, int c) {
		
		boolean celulaEliminada = false;
		boolean hayCelula;
		
		// Comprobamos si fila y columna estan dentro del rango.
		if(((f >= 0) && (f < EntradaDeDatos.getFilasSuperficie())) && ((c >= 0) && (c < EntradaDeDatos.getColumnasSuperficie()))) {
			
			// Comprobamos si hay celula en la posicion.
			hayCelula = superficie.comprobarSihayCelula(f, c);
			
			if(hayCelula) {
				// Si hay celula la eliminamos de la posicion (f, c).
				superficie.eliminarCelulaPosicion(f, c);
				celulaEliminada = true;
			}
		}
		return celulaEliminada;
	}
	
	/**
	 * Metodo evoluciona, para cada celula de la superficie ejecuta un paso de acuerdo a las reglas 
	 * de movimiento de las celulas.
	 * Generamos dos arrays auxiliares (uno de filas y otro de columnas) para tener copias 
	 * de los arrays originales y poder modificarlos.
	 */
	public void evoluciona() {
		
		int fila, columna, numeroDeCelulasRecorrerPaso;
		// Crear arrays auxiliares, copias de los que tenemos para poder modificarlos.
		// Lo inicializamos al valor de las celulas.
		int[] arrayFilasPaso = new int[Constantes.NUMERO_CELULAS];
		int[] arrayColumnasPaso = new int[Constantes.NUMERO_CELULAS];
		
		// Copiamos los valores. La copia es la que vamos a dejar igual, para asi poder recorrerla
		// sin ningun problema, donde actualizamos los valores en la propia de superficie
		// Es decir en la de filasDeCelulas y columnasDeCelulas.
		arrayFilasPaso = Arrays.copyOf(superficie.getFilasDeCelulas(), superficie.getNumeroDeCelulasEnSuperficie());
		arrayColumnasPaso = Arrays.copyOf(superficie.getColumnasDeCelulas(), superficie.getNumeroDeCelulasEnSuperficie());
		
		numeroDeCelulasRecorrerPaso = superficie.getNumeroDeCelulasEnSuperficie();
		
		// Recorremos la superficie.
		for(int i = 0; i < numeroDeCelulasRecorrerPaso; i++) {
			
			// Para cada celula realizamos el movimiento.
			fila = arrayFilasPaso[i];
			columna = arrayColumnasPaso[i];
			
			// Generamos el movimiento de las celulas.
			Reglas.movimientoDeLaCelula(fila, columna, superficie, arrayFilasPaso, arrayColumnasPaso);
		}
	}
	
	/**
	 * Metodo iniciar, reinicia la superficie eliminando las celulas de 
	 * la superficie actual y poniendo en ella nuevas celulas de manera aleatoria.
	 */
	public void iniciar() {
		
		// Eliminamos las celulas del tablero.
		superficie.vaciarSuperficie();
		
		// Introducimos celulas aleatoriamente.
		superficie.inicializarTableroConCelulasAleatorias();
	}
	
	/**
	 * Metodo mostrarSuperficie, muestra superficie por consola.
	 */
	public void mostrarSuperficie() {
		
		System.out.println(superficie.toString());
	}
	
	/**
	 * Metodo vaciar, hace referencia al metodo vaciarSuperficie la cual pone toda la superficie 
	 * a null quitando las celulas que se encuentran en la superficie en ese momento.
	 */
	public void vaciar() {
		
		superficie.vaciarSuperficie();
	}
}
