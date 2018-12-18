package aplicacion;

/**
 * Declaracion de la clase Reglas.
 * @author Isabel y Patricia
 *
 */

public class Reglas {
	
	/**
	 * Metodo defuncion, si al ejecutar un paso, alguna celula queda sin posibilidad
	 * de movimiento, el numero de pasos de la celula llega a 0 y muere.
	 * @param filaCelula, entra la fila donde esta la celula.
	 * @param columnaCelula, entra la columna donde esta la celula.
	 * @param superficie, entra la superficie.
	 */
	public static void defuncion(int filaCelula, int columnaCelula, Superficie superficie) {
		
		// La celula se elimina.
		superficie.eliminarCelulaPosicion(filaCelula, columnaCelula);
		
		// Mostramos el mensaje.
		System.out.print("Muere la celula de la casilla (" + filaCelula + "," + columnaCelula + ")");
		
	}
	
	/**
	 * Metodo movimientoDeLaCelula, elegimos posiciones aleatorias libres donde se podra
	 * mover la celula, en el caso de que el numero de pasos de reproduccion de la celula
	 * llegue a 0 y siga teniendo posibilidades moverse a una posicion libre, se generara
	 * una nueva celula, es decir, dejara una hija en la ultima posicion donde ha llegado a 0 
	 * y el padre se movera a la posicion libre. 
	 * @param filaCelula, entra la fila donde esta la celula.
	 * @param columnaCelula, entra la columna donde esta la celula.
	 * @param superficie, entra la superficie.
	 * @param arrayFilasPaso, se utilizara como copia del atributo filasDeCelulas de Superficie.
	 * @param arrayColumnasPaso se utilizara como copia del atributo columnasDeCelulas de Superficie.
	 */
	public static void movimientoDeLaCelula(int filaCelula, int columnaCelula, Superficie superficie, int[] arrayFilasPaso, int[] arrayColumnasPaso) {
		
		int pos_random, filaADondeMoverLaCelula, columnaADondeMoverLaCelula, m, n;
		boolean sePuedeMover;
		
		// n y m de la celula.
		m = superficie.devolverMDeCelula(filaCelula, columnaCelula);
		
		n = superficie.devolverNDeCelula(filaCelula, columnaCelula);
		
		// Almacenamos las posiciones libres en un array.
		// Se podra mover si hay posiciones Libres.
		sePuedeMover = superficie.almacenarPosicionesLibresParaUnaCelula(filaCelula, columnaCelula);
		
		// Si hay posiciones libres alrededor para que se pueda mover.
		if(sePuedeMover) {
			// Seleccionamos un aleatorio de esas posiciones libres.
			pos_random = superficie.posRandomDeCeldasLibres();
			
			// Hacemos que nos devuelva la fila y la columna de ese random del array de posiciones libres.
			filaADondeMoverLaCelula = superficie.devueveFilaLibreElegida(pos_random);
			columnaADondeMoverLaCelula = superficie.devueveColumnaLibreElegida(pos_random);
			
			if(n == 0) { // Muere por inactividad.
				
				defuncion(filaCelula, columnaCelula, superficie);
				System.out.println(" por inactividad");
				
			}else {
				if(m == 0) {
					
					reproduccionDeLaCelula(filaADondeMoverLaCelula, columnaADondeMoverLaCelula, filaCelula, columnaCelula, superficie); 
				}
				else { // Realizamos el movimiento normal, la celula pasa una casilla que este libre de su alrededor.
					System.out.println("Movimiento de (" + filaCelula + "," + columnaCelula + ") a ("
										+ filaADondeMoverLaCelula + "," + columnaADondeMoverLaCelula + ")");
					// Creamos la celula en la posicion aleatoria dada.
					superficie.colocarCelulaEnLaPosicion(filaADondeMoverLaCelula, columnaADondeMoverLaCelula);
					
					superficie.insertarMDeCelula(filaADondeMoverLaCelula, columnaADondeMoverLaCelula, m-1);
					
					// Eliminar la celula de la posicion actual.
					superficie.eliminarCelulaPosicion(filaCelula, columnaCelula);
				}
		   }
		}
		else { // No se puede mover porque no hay posiciones libres.
			
			if(n == 0) { // Muere por inactividad.
				
				defuncion(filaCelula, columnaCelula, superficie);
				System.out.println(" por inactividad");
			}
			
			// Si tiene posiciones libres y no puede reproducirse, muere.
			else if(m == 0) { // No puede reproducirse, muere por no poder reproducirse.
				
				defuncion(filaCelula, columnaCelula, superficie);
				System.out.println(" por no poder reproducirse");
			}
			else { // No se puede mover, no tiene n = 0, ni m = 0, por lo que descontamos un movimiento a la n.
		
					// Le restamos 1 a la n.
					n = n-1;
					
					// Actualizamos el valor de la n de celula.
					superficie.insertarNDeCelula(filaCelula, columnaCelula, n);
			}
		}
	}
	
	/**
	 * Metodo nacimiento, si al llegar el numero maximo de pasos (m) a 0 la celula sigue
	 * teniendo posibilidades de desplazarse a una posicion libre, se eliminara la 
	 * celula de la posicion y se creara una nueva celula (hija).
	 * @param filaCelula, entra la fila donde esta la celula.
	 * @param columnaCelula, entra la columna donde esta la celula.
	 * @param superficie, entra la superficie.
	 */
	public static void nacimiento(int filaCelula, int columnaCelula, Superficie superficie) {
		
		// Elimina la celula en la posicion.
		superficie.eliminarCelulaPosicion(filaCelula, columnaCelula);
		
		// Y crea una nueva.
		superficie.colocarCelulaEnLaPosicion(filaCelula, columnaCelula);
	}
	
	/**
	 * Metodo reproduccionDeLaCelula, cuando la (m) llega a 0 la celula si tiene la posibilidad se desplaza
	 * y si es asi, se va a otra celda libre (padre), dejando una celula en la posicion actual (hija) donde se encontraba
	 * el padre antes de moverse a la casilla aleatoria.
	 * @param filaADondeMoverLaCelula, fila a donde queremos mover la celula.
	 * @param columnaADondeMoverLaCelula, columna a donde queremos mover la celula.
	 * @param filaCelula, fila donde se encuentra la celula actualmente.
	 * @param columnaCelula, columna donde se encuentra la celula actualmente.
	 * @param superficie, superficie donde esta situada la celula.
	 */
	 public static void reproduccionDeLaCelula(int filaADondeMoverLaCelula, int columnaADondeMoverLaCelula, int filaCelula, int columnaCelula, Superficie superficie) {
		 
			// Reproducir.
			// El padre.
			superficie.colocarCelulaEnLaPosicion(filaADondeMoverLaCelula, columnaADondeMoverLaCelula);
	
			// La hija.
			nacimiento(filaCelula, columnaCelula, superficie);
			System.out.println("Nace nueva celula en (" + filaCelula + "," + columnaCelula + 
						 		") cuyo padre ha sido (" + filaADondeMoverLaCelula + "," +
						 		columnaADondeMoverLaCelula + ")");
		 }
}