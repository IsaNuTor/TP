package logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import excepciones.IndicesFueraDeRango;
import logica.celulas.Celula;
import logica.celulas.CelulaCompleja;
import logica.celulas.CelulaSimple;


/**
 * Declaracion de la clase Superficie.
 * @author Isabel y Patricia
 * 
 */
public class Superficie {
	
	/**
	 * Atributos.
	 */
	private int filas;
	private int columnas;
	private Celula[][] tablero;
	private ArrayList <Casilla> listaCasillasLibresDeCelulaSimple;
	private String mensajePaso;
	
	/**
	 * Constructor, Superficie.
	 * @param nf, numero de filas.
	 * @param nc, numero de columnas.
	 */
	public Superficie(int nf, int nc) {
		
		filas = nf;
		columnas = nc;
	
		tablero = new Celula[filas][columnas];
	
		listaCasillasLibresDeCelulaSimple = new ArrayList <Casilla>();
		
		// Inicilizamos todas las posiciones a null de la superficie.
		inicializarTablero();	
	}
	
	// Getter.
	 /**
	  * Getter, getCelula(int f, int c)
	  * @param f, fila donde esta la celula en la posicion del tablero.
	  * @param c, columna donde esta la celula en la posicion del tablero.
	  * @return tablero[f][c]
	  */
	 public Celula getCelula(int f, int c) {
		 
		 return tablero[f][c];
	 }

	// Metodos.
	 /**
	  * Metodo almacenarCasillasLibresParaUnaCelula, metodo para averiguar todas las 
	  * casillas libres alrededor de una celula de la superficie, hacemos uso de un ArrayList para guardarlas.
	  * Devuelve true cuando no existen posiciones libres.
      * @param posCelulaFila, posicion de la celula en la fila.
      * @param posCelulaColumna, posicion de la celula en la columna.
      * @return hayCasillasLibres
      */
	 public boolean almacenarCasillasLibresParaUnaCelula(int posCelulaFila, int posCelulaColumna) {
				
		int filaARevisar, columnaARevisar;
		boolean hayCasillasLibres = false;
			
		Enum_Direcciones[] enumeradoDirecciones = Enum_Direcciones.getEnumDirecciones();
				
		// Para recorrer el array de enumerados de Direcciones.
		for(int i = 0; i < enumeradoDirecciones.length; i++) {
				
			// Cogemos las posiciones fila y columna a comprobar.
			filaARevisar = posCelulaFila + (enumeradoDirecciones[i].getPosFila());
			columnaARevisar = posCelulaColumna + (enumeradoDirecciones[i].getPosColumna());				

			// Comprobamos si se sale del tablero.
			if(!((filaARevisar < 0) || (filaARevisar >= filas) || (columnaARevisar < 0) || (columnaARevisar >= columnas))) {
					
				// Si en esa posicion hay celula, seguimos con la siguiente.
				if(!(comprobarSihayCelula(filaARevisar, columnaARevisar))) {
					
					Casilla c = new Casilla(filaARevisar, columnaARevisar);
						
					// Entonces no hay celula, posicion libre, la guardamos en el ArrayList.
					listaCasillasLibresDeCelulaSimple.add(c);
							
					hayCasillasLibres = true;
				}
			}
		}
				
		return hayCasillasLibres;
	}
	 
	 /**
	  * Metodo dameCasillaLibreAleatoriaAlrededorDeCelula, nos devuelve una casilla aleatoria alrededor
	  * de la celula a la que puede ir.
	  * @param f, filas.
	  * @param c, columnas.
	  * @return casilla libre a la que puede ir la celula.
	  */
	 public Casilla dameCasillaLibreAleatoriaAlrededorDeCelula(int f, int c) {
		 
		 Casilla casillaLibre;
	
		 almacenarCasillasLibresParaUnaCelula(f, c);
		
		 casillaLibre = devuelveCasillaLibreAleatoriaSimple(posRandomDeCeldasLibres());
		
		 return casillaLibre;
	 }
	 
	 /**
	  * Metodo crearCelula, crea una celula en la posicion fila y columna del tablero.
	  * @param fila, fila donde se crea la celula.
	  * @param columna, columna donde se crea la celula.
	  * @param celula, celula que se va a crear.
	  * @return cadenaSalida con el texto correspondiente.
	  */
	public String crearCelula(int fila, int columna, Celula celula) {
			
		String cadenaSalida = "";
		Casilla casilla;
			
		// Comprobamos si fila y columna estan dentro del rango.
		if(((fila >= 0) && (fila < filas)) && ((columna >= 0) && (columna < columnas))) {
			
			if(!comprobarSihayCelula(fila, columna)) {
				
				tablero[fila][columna] = celula;
				casilla = new Casilla(fila, columna);
				
				cadenaSalida += celula.mensajeCrearCelula() + "en la posicion: (" +
				fila + "," + columna + ").";
			}
			else {
				cadenaSalida = "Error, ya hay una celula en la posicion (" + fila + "," + columna + ").";
			}		
		}
		else {
			try {
				throw new IndicesFueraDeRango("EXCEPCION: fila o columna fuera de rango, dimensiones de la superficie filas: " 
											+ filas + " columnas: " + columnas);
			} catch (IndicesFueraDeRango e) {
				
				cadenaSalida = e.getMessage();
			}
		}
			
		return cadenaSalida;
	}
	
	/**
	 * Metodo crearCelulaEnCasillaAleatoria(Celula celula), dada una casilla aleatoria, nos crea la celula
	 * en esa posicion del tablero.
	 * @param celula, tipo de celula.
	 */
	public void crearCelulaEnCasillaAleatoria(Celula celula) {
		
		Casilla casilla;
			
		// Si la casilla esta ocupada por otra celula, volvemos a crear
		do {
			
			casilla = generarCasillaAleatoriaDeSuperficie();
		}while(comprobarSihayCelula(casilla.getX(), casilla.getY()));
				
		tablero[casilla.getX()][casilla.getY()] = celula;	
	}
	
	/**
	 * Metodo comprobarSiHayCelula, comprueba si en la posicion indicada hay una celula, 
	 * si hay una celula devolveremos true, en caso contrario, devolveremos false.
	 * @param fila, fila donde se encuentra la celula.
	 * @param columna, columna donde se encuentra la celula.
	 * @return hayCelula
	 */
	public boolean comprobarSihayCelula(int fila, int columna) {
		boolean hayCelula = true;
		
		// Si hay celula devolvemos false porque la posicion esta ocupada.
		if(tablero[fila][columna] == null) {
			hayCelula = false;
		}
		
		return hayCelula;	
	}
	
	/**
	 * Metodo devuelveCasillaLibreAleatoria(int random), devuelve la casilla aleatoria dado
	 * un entero random.
	 * @param random, random de la casilla.
	 * @return cas
	 */
	// Metodo que devuelve casilla aleatoria dado un entero random.
	public Casilla devuelveCasillaLibreAleatoriaSimple(int random) {
	
		Casilla cas;
		
		try {
			cas = listaCasillasLibresDeCelulaSimple.get(random);
		}catch(IndexOutOfBoundsException ex) {
			
			cas = null;	
		}
			
		return cas;
	}
	
	/**
	 * Metodo ejecutaMovimiento(int fila, int columna), ejecuta los movimientos correspondientes a las celulas
	 * y posteriormente borra todo el arraylist para dejarlo libre y volver a coger nuevas posiciones que 
	 * antes de limpiarlo podrian haber cambiado de estar libres a ocupadas.
	 * @param fila, fila a donde se va a mover la celula.
	 * @param columna, columna a donde se va a mover la celula.
	 * @return cas
	 */
	public Casilla ejecutaMovimiento(int fila, int columna) {
		
		mensajePaso = "";
		Casilla cas = null;
		
		cas = tablero[fila][columna].ejecutaMovimiento(fila, columna, this);
		
		// Preguntamos si se tiene que morir.
		if(tablero[fila][columna].muere(cas)) {
						
			mensajePaso = tablero[fila][columna].mensajeEliminarCelula(fila, columna) + "\n";
			
			tablero[fila][columna] = null;
										
		}else if(cas != null){ // No muere, por lo que realizamos el movimiento.
			
			// Miramos si en la casilla seleccionada para moverse está vacía.
			if(tablero[cas.getX()][cas.getY()] == null) {
				
				// Preguntamos si se reproduce.
				if(tablero[fila][columna].teReproduces()) {
					
					// En el caso de que sea true.
					reproduccionDeLaCelula(cas.getX(), cas.getY(), fila, columna);
				}
				else { // Realizamos el movimiento.
					
					if(!tablero[fila][columna].esComestible()) {
						
						tablero[cas.getX()][cas.getY()] = tablero[fila][columna];
						
						mensajePaso = "Celula compleja en (" + fila + "," + columna + ") se mueve a " + cas + " --NO COME--\n";	
					}
					else {
						tablero[cas.getX()][cas.getY()] = tablero[fila][columna];

						mensajePaso = "Movimiento de (" + fila + "," + columna + ") a "
								+ cas + "\n";
						
					}
					
					tablero[fila][columna] = null;
					
				}
			} else {
				if(tablero[cas.getX()][cas.getY()].esComestible()) {
				
					tablero[fila][columna].comes();
				
					tablero[cas.getX()][cas.getY()] = tablero[fila][columna];
					mensajePaso = "Celula compleja en (" + fila + "," + columna + ") se mueve a " + cas + " --COME--\n";
					
					tablero[fila][columna] = null;	
				}
				else {
					mensajePaso = "La celula compleja de la posicion (" + fila + "," + columna + ")" + " no se mueve a " + cas + " porque hay otra celula compleja\n";
					cas = null;
				}
			}
			
		}
		
		listaCasillasLibresDeCelulaSimple.clear();
		
		return cas;
	}

	/**
	 * Metodo getMensajePaso, muestra el mensaje de paso de la celula.
	 * @return mensaje de paso por consola.
	 */
	public String getMensajePaso() {
		return mensajePaso;
	}

	/**
	 * Metodo eliminarCelula(int fila, int columna), elimina la celula de la posicion dada.
	 * @param fila, fila de la cual se elimina la celula.
	 * @param columna, columna de la cual se elimina la celula.
	 * @return cadenaSalida
	 */
	public String eliminarCelula(int fila, int columna) {
		
		String cadenaSalida = "";
		
		// Comprobamos si fila y columna estan dentro del rango.
		if(((fila >= 0) && (fila < filas)) && ((columna >= 0) && (columna < columnas))) {
			
			if(tablero[fila][columna] != null){
				
				cadenaSalida = "Celula eliminada con exito en la posicion: (" +
						       fila + "," + columna + ").";
				tablero[fila][columna] = null;
				
			}
			else {
				cadenaSalida = "Error, no hay celula en la posicion (" + fila + "," + columna + ").";
				
			}
		}
		else {
			
			try {
				throw new IndicesFueraDeRango("EXCEPCION: fila o columna fuera de rango, dimensiones de la superficie filas: " 
											+ filas + " columnas: " + columnas);
			} catch (IndicesFueraDeRango e) {
				
				cadenaSalida = e.getMessage();
			}
		}
		
		return cadenaSalida;
	}

	/**
	 * Metodo generarCasillaAleatoriaDeSuperficie, metodo con el cual se genera una casilla aleatoria.
	 * @return casilla aleatoria.
	 */
	public Casilla generarCasillaAleatoriaDeSuperficie(){
		
		int fila, columna;
		Casilla casilla; 
		
		do {
			
			fila = (int) (Math.random() * filas);
			columna = (int) (Math.random() * columnas);
			
		}while((fila == filas) || (columna == columnas) && tablero[fila][columna] != null);
		
		casilla = new Casilla(fila, columna);
		
		return casilla;
	
	}
	
	/**
	 * Metodo que pone todas las posiciones de la superficie a null.
	 */
	public void inicializarTablero() {
		
		for(int i = 0; i < filas; i++) {
			for(int j = 0; j < columnas; j++) {
				tablero[i][j] = null;
			}
		}
	}
	
	/**
	 * Metodo inicializarTableroConCelulasAleatorias, metodo con el cual inicializamos 
	 * el tablero colocando celulas simples y complejas en casillas aleatorias.
	 */
	public void inicializarTableroConCelulas(int num_celulas_simples, int num_celulas_complejas) {
	
		// Creamos celulas simples y la anadimos a la superficie.
		for(int i = 0; i < num_celulas_simples; i++) {
				
			crearCelulaEnCasillaAleatoria(new CelulaSimple());
		}

		// Creamos celulas complejas y la anadimos a la superficie.
		for(int i = 0; i < num_celulas_complejas; i++) {
				
			crearCelulaEnCasillaAleatoria(new CelulaCompleja());
		}
			
	}
	
	 /**
	  * Metodo nacimiento, si al llegar el numero maximo de pasos (m) a 0 la celula sigue
	  * teniendo posibilidades de desplazarse a una posicion libre, se eliminara la 
	  * celula de la posicion y se creara una nueva celula (hija).
	  * @param filaCelula, entra la fila donde esta la celula.
	  * @param columnaCelula, entra la columna donde esta la celula.
	  */
	public void nacimientoCelulaSimple(int filaCelula, int columnaCelula) {
		
		// Elimina la celula en la posicion.
		eliminarCelula(filaCelula, columnaCelula);
		
		// Y crea una nueva.
		crearCelula(filaCelula, columnaCelula, new CelulaSimple());
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
		posicionRandom = (int) (Math.random() * listaCasillasLibresDeCelulaSimple.size());
			
		return posicionRandom;
	}

	/**
	 * Metodo reproduccionDeLaCelula, cuando la (m) llega a 0 la celula si tiene la posibilidad se desplaza
	 * y si es asi, se va a otra celda libre (padre), dejando una celula en la posicion actual (hija) donde se encontraba
	 * el padre antes de moverse a la casilla aleatoria.
	 * @param filaADondeMoverLaCelula, fila a donde queremos mover la celula.
	 * @param columnaADondeMoverLaCelula, columna a donde queremos mover la celula.
	 * @param filaCelula, fila donde se encuentra la celula actualmente.
	 * @param columnaCelula, columna donde se encuentra la celula actualmente.
	 */
	 public void reproduccionDeLaCelula(int filaADondeMoverLaCelula, int columnaADondeMoverLaCelula, int filaCelula, int columnaCelula) {
		
		tablero[filaADondeMoverLaCelula][columnaADondeMoverLaCelula] = getCelula(filaCelula, columnaCelula);
		// La hija.
		nacimientoCelulaSimple(filaCelula, columnaCelula);
		
		mensajePaso = "Nace nueva celula en (" + filaCelula + "," + columnaCelula + 
					 		") cuyo padre ha sido (" + filaADondeMoverLaCelula + "," +
					 		columnaADondeMoverLaCelula + ")" + "\n";
		
	}
	 
	 /**
	 * Metodo toString, devuelve un String con la superficie.
	 */
	public String toString() {
			
		String resultado = "";
			
		System.out.println();
			
		for(int i = 0; i < filas; i++) {
			for(int j = 0; j < columnas; j++) {
				if(tablero[i][j] == null)
				{
					resultado += " - " + " ";
				}
				else {
					resultado += tablero[i][j] + " ";
				}
			}
			resultado += "\n";
		}
		
		return resultado;
	}
	
	/**
	 * Metodo cargarFicheroSuperficie, carga la posicion de la celula en la superficie.
	 * @param bufferLectura para conocer la posicion en la que se queda realizando la carga del
	 * archivo.
	 * @return numero de celulas simples y complejas que hay en el fichero.
	 */
	public int [] cargarFicheroSuperficie(BufferedReader bufferLectura) {
		 
		// num_celulas[0] guarda el numero de celulas simples que hay en el fichero.
		// num_celulas[1] guarda el numero de celulas complejas que hay en el fichero.
		int [] num_celulas = new int[2]; 
		int fila, columna;
		String lineaLeida;
		String lineaLeidaSeparada[];

		try {
			
			lineaLeida = bufferLectura.readLine();
		
			while(lineaLeida != null) {
				
				lineaLeidaSeparada = lineaLeida.split(" ");
				
				fila = Integer.parseInt(lineaLeidaSeparada[0]);
				
				columna = Integer.parseInt(lineaLeidaSeparada[1]);
				
				Casilla casilla = new Casilla(fila, columna);
				
				if(lineaLeidaSeparada[2].equals("simple")) {
					
					Celula celula = new CelulaSimple();
					celula.cargarCelulaFichero(Integer.parseInt(lineaLeidaSeparada[3]), Integer.parseInt(lineaLeidaSeparada[4]));
					// Colocamos la celula en el tablero.
					tablero[fila][columna] = celula;	
					num_celulas[0]++;
				}
				else {
					
					Celula celula = new CelulaCompleja();
					celula.cargarCelulaFichero(Integer.parseInt(lineaLeidaSeparada[3]), 0);	
					// Colocamos la celula en el tablero.
					tablero[fila][columna] = celula;
					num_celulas[1]++;
				}
				
				lineaLeida = bufferLectura.readLine();
			}
			
		}catch (IOException e) {
		
			System.out.println("No se ha leido correctamente el archivo o no se ha cerrado bien el flujo");
		}	
		
		return num_celulas;
	}
	
	/**
	 * Metodo guardarFicheroSuperficie, guarda la posicion del tablero donde hay celula.
	 * @param bufferEscritura para conocer la posicion en la que se queda realizando el guardado en el
	 * archivo.
	 */
	public void guardarFicheroSuperficie(BufferedWriter bufferEscritura) {
		
		try{
			
			for(int i = 0; i < filas; i++) {
				for(int j = 0; j < columnas; j++) {
					if(tablero[i][j] != null){
						bufferEscritura.append(i + " " + j);
						tablero[i][j].guardarCelulaFichero(bufferEscritura);
					}
				}
			}
			
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	 
}
