package comandos;

import logica.Mundo;

/**
 * Declaracion de la clase CDesconocido.
 * @author Isabel y Patricia
 *
 */
public class CDesconocido implements Comando{

	// Metodos.
	/**
	 * Metodo ejecuta, devuelve el comando ejecutado.
	 */
	public String ejecuta(Mundo mundo) {
		
		return "Error comando desconocido.";
		
	}

	/**
	 * Metodo parsea, escribe un array de String, que debe procesar devolviendo  
	 * el comando que representa el string.
	 */
	public Comando parsea(String[] cadenaComando) {
	
		return new CDesconocido();
	}

	/**
	 * Metodo textoAyuda, implementa un metodo vacio.
	 */
	public String textoAyuda() {
		
		return "";
	}

}
