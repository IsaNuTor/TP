package comandos;

import entradaSalidaDeDatos.ParserEntrada;
import logica.Mundo;
import logica.celulas.CelulaSimple;

/**
 * Declaracion de la clase CCrearCelulaSimple.
 * @author Isabel y Patricia
 *
 */
public class CCrearCelulaSimple implements Comando{
	
	private int fila;
	private int columna;

	// Constructor.
	public CCrearCelulaSimple(int fila, int columna) {
		
		this.fila = fila;
		this.columna = columna;
		
	}
	
	// Metodos.
	/**
	 * Metodo ejecuta, devuelve el comando ejecutado.
	 */
	public String ejecuta(Mundo mundo) {
		
		return mundo.crearCelulaEnSuperficie(fila, columna, new CelulaSimple());
	}
	
	/**
	 * Metodo parsea, escribe un array de String, que debe procesar devolviendo  
	 * el comando que representa el string.
	 */
	public Comando parsea(String[] cadenaComando) {
		
		Comando comandoADevolver = null;
		
		// Preguntamos el numero de elementos del String[].
		if(cadenaComando.length == 3) {
			
			// Miramos si es alfabetico.
			if(cadenaComando[0].matches("[a-zA-Z]+")) {
				
				// Miramos que sea el string crearCelulaSimple.
				if(cadenaComando[0].equalsIgnoreCase("crearCelulaSimple")) {
					
					// Miramos que los siguientes sean numericos, corresponden a la fila y a la columna.
					if((cadenaComando[1].matches("[0-9]+")) && (cadenaComando[2].matches("[0-9]+"))) {
						
						// Devolvemos el comando.
						int fila = ParserEntrada.parsearArgumentoString(cadenaComando[1]);
						int columna = ParserEntrada.parsearArgumentoString(cadenaComando[2]);
						
						comandoADevolver = new CCrearCelulaSimple(fila, columna);
					}	
				}
			}
		}
		
		return comandoADevolver;
	}

	/**
	 * Metodo textoAyuda, devuelve el texto con la descripcion de dicho comando.
	 */
	public String textoAyuda() {

		return "CREARCELULASIMPLE F C: crea una nueva celula simple en la posicion (f, c) si es posible.";
	}
	
}
