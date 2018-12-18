package logica;

import java.util.ArrayList;

import logica.celulas.Celula;
import entradaSalidaDeDatos.Entrada;

/**
 * Declaracion de la clase Mundo.
 * @author Isabel y Patricia
 *
 */
public class Mundo {

	// Atributos.
	private Superficie tablero;
	boolean simulacionTerminada = true;
	
	private ArrayList <Casilla> listaDeCasillasDondeSeHanMovidoLasCelulas;
	
	// Constructor.
	public Mundo(){
		
		// Creamos una superficie con todas las posiciones a null.
		tablero = new Superficie(Entrada.getFilasSuperficie(), Entrada.getColumnasSuperficie());
		
		// Anadimos las celulas a la superficie.
		tablero.inicializarTableroConCelulasAleatorias();
		
		listaDeCasillasDondeSeHanMovidoLasCelulas = new ArrayList <Casilla>();
	}
	
	// Getter y Setter.
	/**
	 * Getter, getTablero.
	 * @return tablero
	 */
	public Superficie getTablero() {
		
		return tablero;
	}
	
	/**
	 * Setter, setSimulacionTerminada
	 * @param simulacionTerminada.
	 * @return string que indica el fin de la simulacion
	 */
	public String setSimulacionTerminada(boolean simulacionTerminada) {
		
		this.simulacionTerminada = simulacionTerminada;
		
		return "Fin de la simulacion......";
	}

	//Metodos.
	/**
	 * Metodo comprobacionDePosicionDeCelula, comprueba si la posicion donde esta la celula ya
	 * se ha movido anteriormente, devuelve true si la ha encontrado en el arraylist.
	 * @param f, fila a comprobar.
	 * @param c, columna a comprobar.
	 * @return ejecutamovimiento
	 */
	public boolean comprobacionDePosicionDeCelula(int f, int c) {
		
		boolean ejecutarMovimiento = true, encontrado = false;
		Casilla casilla;
		
		// Si lo encuentra en la lista, entonces devolvemos false para no ejecutar el movimiento
		// pq la celula ya se ha movido anteriormente.
		for(int i = 0; (i < listaDeCasillasDondeSeHanMovidoLasCelulas.size() && !encontrado); i++) {
			
			casilla = listaDeCasillasDondeSeHanMovidoLasCelulas.get(i);
			
			if((f == casilla.getX()) && (c == casilla.getY())) {
				encontrado = true;
				ejecutarMovimiento = false;
			}
		}
		
		return ejecutarMovimiento;
	}
	
	/**
	 * Metodo String crearCelulaEnSuperficie, coge una fila
	 * y una columna y el tipo de celula para introducirla en la superficie.
	 * @param fila, fila donde va a ir la celula.
	 * @param columna, columna a donde va a ir la celula.
	 * @param celula, tipo de celula.
	 * @return tablero con la celula creada en la superficie
	 */
	public String crearCelulaEnSuperficie(int fila, int columna, Celula celula) {
		
		return tablero.crearCelula(fila, columna, celula);
	}
	
	/**
	 * Metodo eliminarCelulaEnSuperficie, elimina la celula en la posicion
	 * que se le indica.
	 * @param fila, en la que se encuentra la celula que queremos borrar.
	 * @param columna, en lka que se encuentra la columna que queremos borrar.
	 * @return tablero con la celula eliminada
	 */
	public String eliminarCelulaEnSuperficie(int fila, int columna) {
		
		return tablero.eliminarCelula(fila, columna);
	}
	
	/**
	 * Metodo esSimulacionTerminada, devuelve true cuando la simulacion ha finalizado, en cas
	 * contrario devuelve false.
	 * @return simulacionTerminada
	 */
	public boolean esSimulacionTerminada() {
		
		return simulacionTerminada;
	}
	
	/**
	 * Metodo evoluciona, recorre el tablero y comprueba las posiciones, cuando encuentra una celula
	 * procede a ejecutar el movimiento a esa casilla libre.
	 * Si la casilla deja de estar a null, aniadimos esa casilla a la lista de casillas donde se han movido las
	 * celulas para que posteriormente no vuelva a coger esa posicion de la casilla como una posicion libre.
	 * Finalmente limpiarmos esa lista.
	 * @return cas
	 */
	public Casilla evoluciona() {
		
		Casilla cas = null;
		
		for(int i = 0; i < Entrada.getFilasSuperficie(); i++) {
			
			for(int j = 0; j < Entrada.getColumnasSuperficie(); j++) {
				
				if(tablero.getCelula(i, j) != null) {
					
					if(comprobacionDePosicionDeCelula(i, j)) {
						cas = tablero.ejecutaMovimiento(i, j); 
						if(cas != null) {
							listaDeCasillasDondeSeHanMovidoLasCelulas.add(cas);
						}
					}
				}
			}
		}
		listaDeCasillasDondeSeHanMovidoLasCelulas.clear();
		return cas;
	}
	
	/**
	 * Metodo iniciar, reinicia el tablero con celulas aleatorias nuevas.
	 * @return ""
	 */
	public String iniciar(){
		
		// Ponemos todas las posiciones a null.
		tablero.inicializarTablero();
		
		// Incializamos a aleatorias.
		tablero.inicializarTableroConCelulasAleatorias();
		
		return "";
	}

	/**
	 * Metodo vaciar, pone todo el tablero a null.
	 * @return ""
	 */
	public String vaciar(){
		
		tablero.inicializarTablero();
		
		return "";
	}
}
