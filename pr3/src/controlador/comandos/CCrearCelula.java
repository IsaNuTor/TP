package controlador.comandos;

import controlador.Controlador;

/**
 * Declaracion de la clase CCrearCelula.
 * @author Isabel y Patricia
 *
 */

public class CCrearCelula implements Comando{
	
	/**
	 * Atributos.
	 */
	private int fila;
	private int columna;

	/** 
	 * Constructor. 
	 * @param fila
	 * @param columna
	 */
	public CCrearCelula(int fila, int columna) {
		
		this.fila = fila;
		this.columna = columna;
		
	}

	/**
	 * Metodo ejecuta, devuelve el comando ejecutado.
	 */
	public String ejecuta(Controlador controlador) {
		
		return controlador.ejecutaCrearCelula(fila, columna);
	}

	/**
	 * Metodo parsea, escribe un array de String, que debe procesar devolviendo  
	 * el comando que representa el string.
	 */
	public Comando parsea(String[] cadenaComando) throws NumberFormatException{
		
		Comando comandoADevolver = null;
		
		// Preguntamos el numero de elementos del String[].
		if(cadenaComando.length == 3) {
			
			// Miramos si es alfabetico y que sea el string crearCelula.
			if((cadenaComando[0].matches("[a-zA-Z]+")) && (cadenaComando[0].equalsIgnoreCase("crearCelula"))) {
		
				// Devolvemos el comando.
				fila = Integer.parseInt(cadenaComando[1]);
				columna = Integer.parseInt(cadenaComando[2]);
						
				comandoADevolver = new CCrearCelula(fila, columna);	
			}
		}
		
		return comandoADevolver;
	}

	/**
	 * Metodo textoAyuda, devuelve el texto con la descripcion de dicho comando.
	 */
	public String textoAyuda() {
		
		return "CREARCELULA F C: crea una nueva celula en la posicion (f, c) si es posible.";
	}

}
