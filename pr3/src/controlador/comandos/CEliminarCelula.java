package controlador.comandos;

import controlador.Controlador;

/**
 * Declaracion de la clase CEliminarCelula.
 * @author Isabel y Patricia
 *
 */
public class CEliminarCelula implements Comando{
	
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
	public CEliminarCelula(int fila, int columna) {
		
		this.fila = fila;
		this.columna = columna;
	}

	// Metodos.
	/**
	 * Metodo ejecuta, devuelve el comando ejecutado.
	 */
	public String ejecuta(Controlador controlador) {
		
		return controlador.ejecutaEliminarCelula(fila, columna);	
	}

	/**
	 * Metodo parsea, escribe un array de String, que debe procesar devolviendo  
	 * el comando que representa el string.
	 */
	public Comando parsea(String[] cadenaComando) throws NumberFormatException{
		
		Comando comandoADevolver = null;
		
		// Preguntamos el numero de elementos del String[].
		if(cadenaComando.length == 3) {
			
			// Miramos si es alfabetico.
			if(cadenaComando[0].matches("[a-zA-Z]+")) {
				
				// Miramos que sea el string eliminarCelula.
				if(cadenaComando[0].equalsIgnoreCase("eliminarCelula")) {
					
					// Devolvemos el comando.
					int fila = Integer.parseInt(cadenaComando[1]);
					int columna = Integer.parseInt(cadenaComando[2]);
					comandoADevolver = new CEliminarCelula(fila, columna);		
				}
			}
		}
		
		return comandoADevolver;
	}

	/**
	 * Metodo textoAyuda, devuelve el texto con la descripcion de dicho comando.
	 */
	public String textoAyuda() {
	
		return "ELIMINARCELULA F C: elimina una celula de la posicion (f, c) si es posible.";
	}
}
