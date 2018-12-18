package logica.mundos;

import java.io.BufferedReader;
import java.util.ArrayList;

import logica.Casilla;
import logica.Superficie;
import logica.celulas.Celula;


/**
 * Declaracion de la clase Mundo.
 * @author Isabel y Patricia
 *
 */
public abstract class Mundo {

	/**
	 * Atributos.
	 */
	protected Superficie tablero;
	protected int filas;
	protected int columnas;
	protected int cSimples;
	protected int cComplejas;
	protected String tipoDeMundo;
	private ArrayList <Casilla> listaDeCasillasDondeSeHanMovidoLasCelulas;
	
	/**
	 * Constructor por defecto.
	 */
	public Mundo(){
		
		this.filas = 0;
		this.columnas = 0;
		
		listaDeCasillasDondeSeHanMovidoLasCelulas = new ArrayList <Casilla>();
		
		tablero = null;	
	}
	
	/**
	 * Construcotr.
	 * @param filas
	 * @param columnas
	 */
	public Mundo(int filas, int columnas){
		
		this.filas = filas;
		this.columnas = columnas;
		
		listaDeCasillasDondeSeHanMovidoLasCelulas = new ArrayList <Casilla>();
		
		tablero = new Superficie(filas, columnas);
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
	 * Getter getTipoDeMundo.
	 * @return tipoDeMundo
	 */
	public String getTipoDeMundo() {
		return tipoDeMundo;
	}
	
	/**
	 * Setter setFilas.
	 * @param filas
	 */
	public void setFilas(int filas) {
		this.filas = filas;
	}

	/**
	 * Setter setColumnas.
	 * @param columnas
	 */
	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}

	/**
	 * Metodo inicializaMundo, inicializa el mundo.
	 */
	public abstract void inicializaMundo();
	
	
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
	 * Metodo evoluciona, recorre el tablero y comprueba las posiciones, cuando encuentra una celula
	 * procede a ejecutar el movimiento a esa casilla libre.
	 * Si la casilla deja de estar a null, aniadimos esa casilla a la lista de casillas donde se han movido las
	 * celulas para que posteriormente no vuelva a coger esa posicion de la casilla como una posicion libre.
	 * Finalmente limpiarmos esa lista.
	 * @return cas
	 */
	public String evoluciona() {
		
		Casilla cas = null;
		String mensaje = "";
		
		for(int i = 0; i < filas; i++) {
			
			for(int j = 0; j < columnas; j++) {
				
				if(tablero.getCelula(i, j) != null) {
					
					if(comprobacionDePosicionDeCelula(i, j)) {
						cas = tablero.ejecutaMovimiento(i, j); 
						mensaje += tablero.getMensajePaso();
						if(cas != null) {
							listaDeCasillasDondeSeHanMovidoLasCelulas.add(cas);
						}
					}
				}
			}
		}
		
		listaDeCasillasDondeSeHanMovidoLasCelulas.clear();
		
		return mensaje;
			
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
		// porque la celula ya se ha movido anteriormente.
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
	 * Metodo iniciar, reinicia el tablero con celulas aleatorias nuevas.
	 * @return ""
	 */
	public String iniciar(){
		
		tablero.inicializarTablero();
		tablero.inicializarTableroConCelulas(cSimples, cComplejas);
		
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
	
	/**
	 * Metodo cargarMundoFichero(BufferedReader bufferLectura), carga el mundo a partir del fichero.
	 * @param bufferLectura
	 */
	public abstract void cargarMundoFichero(BufferedReader bufferLectura);
	
	/**
	 * Metodo guardarMundoFichero(String nombreFichero), guarda el mundo en el nombre del fichero.
	 * @param nombreFichero
	 */
	public abstract void guardarMundoFichero(String nombreFichero);
	
}
