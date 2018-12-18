package aplicacion;

import java.util.Arrays;

import e_s_datos.EntradaDeDatos;
import herramientas.Constantes;
import herramientas.Enum_Direcciones;

/**
 * Declaracion de la clase Superficie.
 * @author Isabel y Patricia
 *
 */
public class Superficie {

	/**
	 * Atributos.
	 * 
	 */
	private int filas;
	
	private int columnas;
	
	private Celula[][] superficie;
	
	
	/*
	 * Declaramos los arrays filas y columnas que guardaran la posicion en la que se encuentran
	 * las celulas, inicialmente el tamano sera a la constante NUMERO_CELULAS.
	 * Ej: Si la celula se encuentra en la posicion 1, 2.
	 * El array de filasDeCelulas[0] = 1;
	 * El array de columnasDeCelulas[0] = 2;
	 */
	private int[] filasDeCelulas;
	private int[] columnasDeCelulas;
	
	private int numeroDeCelulasEnSuperficie;
	
	private int tamanioArrayPosicionesCelulas;
	
	/*
	 * Arrays para saber las posiciones libres de celulas.
	 */
	private int[] filasLibres;
	private int[] columnasLibres;
	
	private int numeroDeCeldasLibres;
	
	/**
	 * Constructor, Superficie.
	 * @param nf, numero de filas.
	 * @param nc, numero de columnas.
	 */
	public Superficie(int nf, int nc) {
		
		filas = nf;
		columnas = nc;
		
		numeroDeCelulasEnSuperficie = 0;

		superficie = new Celula[filas][columnas];
		
		/*
		 * Inicializamos los arrays.
		 */
		tamanioArrayPosicionesCelulas = Constantes.NUMERO_CELULAS;
		filasDeCelulas = new int[tamanioArrayPosicionesCelulas];
		columnasDeCelulas = new int[tamanioArrayPosicionesCelulas];
		
		/*
		 * Inicializamos los arrays de celdas libres.
		 */
		//
		filasLibres = new int[Constantes.NUMERO_POSIBLE_DE_CELDAS_LIBRES];
		columnasLibres = new int[Constantes.NUMERO_POSIBLE_DE_CELDAS_LIBRES];
		
		// Inicilizamos todas las posiciones a null de la superficie.
		inicializarTablero();
		
	}
	
	/**
	 * Getter, devolverNDeCelula.
	 * @param fila, la fila donde esta la celula.
	 * @param columna, la columna donde esta la celula.
	 * @return superficie[fila][columna].getN()
	 */
	public int devolverNDeCelula(int fila, int columna) {
		
		return superficie[fila][columna].getN();
	}
	
	/**
	 * Getter, devolverMDeCelula.
	 * @param fila, fila donde esta la celula.
	 * @param columna, columna donde esta la celula.
	 * @return superficie[fila][columna].getM()
	 */
	public int devolverMDeCelula(int fila, int columna) {
		
		return superficie[fila][columna].getM();
	}
	
	/**
	 * Getter, devueveFilaLibreElegida.
	 * @param random, aleatorio para las filas libres.
	 * @return filasLibres[random]
	 */
	public int devueveFilaLibreElegida(int random) {
		
		return filasLibres[random];	
	}
	
	/**
	 * Getter, devueveColumnaLibreElegida.
	 * @param random, aleatorio para las columnas libres.
	 * @return columnasLibres[random]
	 */
	public int devueveColumnaLibreElegida(int random) {
		
		return columnasLibres[random];
	}
	
	/**
	 * Getter, getNumeroDeCelulasEnSuperficie.
	 * @return numeroDeCelulasEnSuperficie
	 */
	public int getNumeroDeCelulasEnSuperficie() {
		return numeroDeCelulasEnSuperficie;
	}
	
	/**
	 * Getter, getFilasDeCelulas.
	 * @return filasDeCelulas
	 */
	public int[] getFilasDeCelulas() {
		return filasDeCelulas;
	}

	/**
	 * Getter, getColumnasDeCelulas.
	 * @return columnasDeCelulas
	 */
	public int[] getColumnasDeCelulas() {
		return columnasDeCelulas;
	}
	
	/**
	 * Setter, insertarNDeCelula.
	 * @param fila, fila de la celula.
	 * @param columna, columna de la celula
	 * @param valorAIntroducir, para que la N coja el valor introducido.
	 */
	public void insertarNDeCelula(int fila, int columna, int valorAIntroducir) {
		
		superficie[fila][columna].setN(valorAIntroducir);
	}
	
	/**
	 * Setter, insertarMDeCelula.
	 * @param fila, fila de la celula.
	 * @param columna, columna de la célula.
	 * @param valorAIntroducir, para que la M coja el valor introducido.
	 */
	public void insertarMDeCelula(int fila, int columna, int valorAIntroducir) {
		
		superficie[fila][columna].setM(valorAIntroducir);
	}
	
	/**
	 * Setter, setNumeroDeCelulasEnSuperficie.
	 * @param numeroDeCelulasEnSuperficie, numero de celulas que se encuentran en la superficie.
	 */
	public void setNumeroDeCelulasEnSuperficie(int numeroDeCelulasEnSuperficie) {
		this.numeroDeCelulasEnSuperficie = numeroDeCelulasEnSuperficie;
	}
	
	/**
	 * Metodos.
	 */
	/**
	 * Metodo almacenarPosicionesLibresParaUnaCelula, metodo para averiguar todas las 
	 * casillas libres de la superficie, hacemos uso de un array para guardarlas.
	 * Devuelve true cuando no exixten posiciones libres.
	 * @param posCelulaFila, posicion de la celula en la fila.
	 * @param posCelulaColumna, posicion de la celula en la columna.
	 * @return hayPosicionesLibres
	 */
	public boolean almacenarPosicionesLibresParaUnaCelula(int posCelulaFila, int posCelulaColumna) {
			
		int filaARevisar, columnaARevisar;
		// Utilizamos el mismo array porque siempre va a recorrer hasta los que modifiquemos.
		// Por eso no vaciamos los arrays.
		numeroDeCeldasLibres = 0;
		boolean hayPosicionesLibres = false;
			
		Enum_Direcciones[] enumeradoDirecciones = Enum_Direcciones.getEnumDirecciones();
			
		// Para recorrer el array de enumerados de Direcciones.
		for(int i = 0; i < enumeradoDirecciones.length; i++) {
			
			// Cogemos las posiciones fila y columna a comprobar.
			filaARevisar = posCelulaFila + (enumeradoDirecciones[i].getPosFila());
			columnaARevisar = posCelulaColumna + (enumeradoDirecciones[i].getPosColumna());				

			// Comprobamos si se sale del tablero.
			if(!((filaARevisar < 0) || (filaARevisar >= EntradaDeDatos.getFilasSuperficie()) || (columnaARevisar < 0) || (columnaARevisar >= EntradaDeDatos.getColumnasSuperficie()))) {
				
				// Si en esa posicion hay celula, seguimos con la siguiente.
				if(!(comprobarSihayCelula(filaARevisar, columnaARevisar))) {
					
					// Entonces no hay celula, posicion libre, la guardamos en el array.
					filasLibres[numeroDeCeldasLibres] = filaARevisar;
					columnasLibres[numeroDeCeldasLibres] = columnaARevisar;
						
					hayPosicionesLibres = true;
					// Actualizamos contador
					numeroDeCeldasLibres++;
				}
			}
		}
			
		return hayPosicionesLibres;
	}
	
	/**
	 * Metodo anadirPosFilaYColumnaDeLaCelulaAlArray, coloca la posicion de las filas y 
	 * las columnas donde se encuentra la celula en el array de celulas.
	 * @param fila, fila donde se encuentra la celula.
	 * @param columna, columna donde se encuentra la celula.
	 * @param posicion, posicion del array de celulas.
	 * @return celulaColocada
	 */
	public boolean anadirPosFilaYColumnaDeLaCelulaAlArrayDeCelulas(int fila, int columna, int posicion) {
		
		boolean celulaColocada = true;
		
		filasDeCelulas[posicion] = fila;
		columnasDeCelulas[posicion] = columna;
		
		return celulaColocada;
					
	}
	
	/**
	 * Metodo colocarCelulaEnlaPosicion, coloca la celula en la superficie, en el caso de que
	 * el numero de celulas del la superficie sea el mismo que el numero de celulas que hay
	 * en el array creado que almacena las posiciones de celulas, lo redimensiona.
	 * @param fila, fila donde se encuentra la celula.
	 * @param columna, columna donde se encuentra la celula.
	 */
	public void colocarCelulaEnLaPosicion(int fila, int columna) {
		// Colocar en el array de posiciones de las celulas.
		// Si el numero de celulas es igual que el tamano de posiciones del array se redimensiona al doble.
		if(numeroDeCelulasEnSuperficie == tamanioArrayPosicionesCelulas) {
			redimensionarArray();
		}
		
		// Se inserta la celula en el array.
		insertarCelulaEnArrays(fila,columna);
		
		// Crea una nueva celula y la inserta en la posicion de la superficie.
		superficie[fila][columna] = new Celula();
			
	}
	
	/**
	 * Metodo comprobarSiHayCelula, comprueba si en la posicion indicada hay una celula, 
	 * si hay una celula devolveremos false porque la posicion ya se encuentra ocupada.
	 * @param fila, fila donde se encuentra la celula.
	 * @param columna, columna donde se encuentra la celula.
	 * @return hayCelula
	 */
	public boolean comprobarSihayCelula(int fila, int columna) {
		boolean hayCelula = true;
		
		// Si hay celula devolvemos false porque la posicion esta ocupada.
		if(superficie[fila][columna] == null) {
			hayCelula = false;
		}
		
		return hayCelula;	
	}
	
	/**
	 * Metodo desplazarALaDerecha, desplaza el array a la derecha para poder incrementar
	 * el array.
	 * @param principio, inicio del array.
	 * @param fin, fin del array.
	 */
	public void desplazarALaDerecha(int principio, int fin) {
		
		for(int i = fin - 1; i >= principio; i--) {
			
			filasDeCelulas[i+1] = filasDeCelulas[i];
			columnasDeCelulas[i+1] = columnasDeCelulas[i];
		}
	}
	
	/**
	 * Metodo eliminarCelulaPosicion, elimina la celula que se encuentra en la posicion que 
	 * indica el usuario.
	 * @param fila, entra la fila donde se encuentra la celula.
	 * @param columna, entra la columna donde se encuentra la celula.
	 */
	// Elimina la Celula de la superficie en la posicion elegida.
	public void eliminarCelulaPosicion(int fila, int columna) {
		superficie[fila][columna] = null;
		
		// Eliminamos las posiciones de esa celula de los arrays de las posiciones de las celulas.
		eliminarPosicionesDeLaCelulaEnLosArraysDeCelulas(fila, columna);
		numeroDeCelulasEnSuperficie--;
	}
	
	/**
	 * Metodo eliminarPosicionesDeLaCelulaEnLosArraysDeCelulas, en el caso de que se quiera eliminar
	 * una celula, este metodo elimina las posiciones de los arrays donde estan las celulas para 
	 * quitar la posicion que ocupaba esa celula en el array.
	 * @param fila, fila donde se encuentra la celula.
	 * @param columna, columna donde se encuentra la celula.
	 */
	public void eliminarPosicionesDeLaCelulaEnLosArraysDeCelulas(int fila, int columna) {
		
		// Primero lo tenemos que buscar.
		boolean encontrado = false;
		int contador_recorrido = 0;
		
		while((!encontrado) && (contador_recorrido < numeroDeCelulasEnSuperficie)) {
			
			if(fila == filasDeCelulas[contador_recorrido]){
				if(columna == columnasDeCelulas[contador_recorrido]) {
					encontrado = true;
					
					// Las eliminamos del array, desplazamos las posiciones a la izquierda.
					for(int i = contador_recorrido; i < numeroDeCelulasEnSuperficie - 1; i++) {
						
						filasDeCelulas[i] = filasDeCelulas[i+1];
						columnasDeCelulas[i] = columnasDeCelulas[i+1];
					}
				}
			}
			
			// Actualizamos el contador.
			contador_recorrido++;
	
		}
	}
	
	/**
	 * Metodo que pone todas las posiciones de la superficie a null.
	 */
	private void inicializarTablero() {
		
		for(int i = 0; i < filas; i++) {
			for(int j = 0; j < columnas; j++) {
				superficie[i][j] = null;
			}
		}
	}
	
	/**
	 * Metodo insertarCelulaEnArrays, metodo que coloca de manera ordenada las celulas en los arrays.
	 * @param fila, fila donde se encuentra la celula.
	 * @param columna, columna donde se encuentra la celula.
	 */
	// Para insertar ordenadamente.
	// Para organizar el array de celulas y que aparezcan ordenadas.
	public void insertarCelulaEnArrays(int fila, int columna) {
					
		boolean celulaColocada = false;
		int contador = 0;
			
		while((!celulaColocada) && (contador < numeroDeCelulasEnSuperficie)) {
						
			// Comparar la fila.
			if(fila < filasDeCelulas[contador]) {
						
				// Desplazamos a la derecha.
				desplazarALaDerecha(contador, numeroDeCelulasEnSuperficie);
						
				// Insertamos el nuevo en la posicion contador(el hueco que ha quedado al desplazar lo otro a la derecha).
				celulaColocada = anadirPosFilaYColumnaDeLaCelulaAlArrayDeCelulas(fila, columna, contador);
			}
			else if(fila == filasDeCelulas[contador]) {
				// Si son iguales comparamos las columnas.
				if(columna < columnasDeCelulas[contador]) {
							
					// Desplazamos a la derecha.
					desplazarALaDerecha(contador, numeroDeCelulasEnSuperficie);
							
					// Insertamos el nuevo en la posicion contador(el hueco que ha quedado al desplazar lo otro a la derecha).
					celulaColocada = anadirPosFilaYColumnaDeLaCelulaAlArrayDeCelulas(fila, columna, contador);
				}
			}
				
			// Actualizamos el contador.
			contador++;
						
		}

		if(!celulaColocada) {
			celulaColocada = anadirPosFilaYColumnaDeLaCelulaAlArrayDeCelulas(fila, columna, contador);
		}
			
		// Hay una celula mas.
		numeroDeCelulasEnSuperficie++;
	}
	
	/**
	 * Metodo inicializarTableroConCelulasAleatorias, metodo con el cual inicializamos 
	 * el tablero colocando celulas en posiciones aleatorias.
	 */
	public void inicializarTableroConCelulasAleatorias() {
		
		int fila, columna, contadorCelulas = 0;
		
		// Cuando el contador de celulas sea menos que el numero de celulas 6.
		while(contadorCelulas < Constantes.NUMERO_CELULAS) {
			
			// Genera un random de filas(3) y de columnas(4).
			do {
				
				fila = (int) (Math.random() * EntradaDeDatos.getFilasSuperficie());
				columna = (int) (Math.random() * EntradaDeDatos.getColumnasSuperficie());
				
			}while((fila == EntradaDeDatos.getFilasSuperficie()) || (columna == EntradaDeDatos.getColumnasSuperficie()));
			
			// Cuando esa posicion de la superficie esta a null.
			if(superficie[fila][columna] == null) {
				
				// Creamos una celula y la anadimos a la superficie.
				Celula celula = new Celula();
				superficie[fila][columna] = celula;
				
				
			//  Anadimos los valores fila y columna a los arrays.
				insertarCelulaEnArrays(fila, columna);
				
				// Actualizamos contador de celulas.
				contadorCelulas++;
			}
			
		}
	}
	
	/**
	 * Metodo posRandomDeCeldasLibres, generamos un random con las posiciones del array 
	 * que estan libres para posteriormente poder introducir la celula en una de esas posiciones.
	 * @return posicionRandom
	 */
	// Creamos un random con las posiciones del array de libres.
	public int posRandomDeCeldasLibres() {
			
		int posicionRandom;
		
		// Nos devuelve una posicion dada por un random y el numero de celdas libres. 
		posicionRandom = (int) (Math.random() * numeroDeCeldasLibres);
			
		return posicionRandom;
	}
	
	/**
	 * Metodo redimensionarArray, redimensiona los arrays de filas y de columnas aumentandolo 
	 * a una capacidad doble de la que que inicialmente tiene.
	 */
	public void redimensionarArray() {
		
		int nuevaCapacidad = tamanioArrayPosicionesCelulas * 2;
		// Copia los arrays originales y les da la nueva capacidad se quiere tener.
		filasDeCelulas = Arrays.copyOf(filasDeCelulas, nuevaCapacidad);
		columnasDeCelulas = Arrays.copyOf(columnasDeCelulas, nuevaCapacidad);
		
		// Actualizamos el valor del array.
		tamanioArrayPosicionesCelulas = nuevaCapacidad;
	}

	/**
	 * Metodo toString, devuelve un String con la superficie.
	 */
	public String toString() {
		String resultado = "";
		
		for(int i = 0; i < filas; i++) {
			for(int j = 0; j < columnas; j++) {
				if(superficie[i][j] == null)
				{
					resultado += " - " + " ";
				}
				else {
					resultado += superficie[i][j] + " ";
				}
			}
			resultado += "\n";
		}
		
		return resultado;
	}
	
	/**
	 * Metodo vaciarSuperficie, vaciar la superficie poniendo todas las posiciones a null
	 * y poniendo tanto las posiciones de los arrays de filas y columnas como el numero de
	 * celulas en la superficie a 0. 
	 */
	public void vaciarSuperficie() {
		
		// Recorremos las posiciones del array donde se encuentran las posiciones de las celulas.
		for(int i = 0; i < numeroDeCelulasEnSuperficie; i++) {
			
			//eliminarCelulaSuperficie(filasDeCelulas[i], columnasDeCelulas[i]);
			superficie[filasDeCelulas[i]][columnasDeCelulas[i]] = null;
	
			// Eliminamos las posiciones de esa celula de los arrays de las posiciones de las celulas.
			filasDeCelulas[i] = 0;
			columnasDeCelulas[i] = 0;
		}
		
		// Deja de haber celulas en la superficie.
		numeroDeCelulasEnSuperficie = 0;
	}
}
