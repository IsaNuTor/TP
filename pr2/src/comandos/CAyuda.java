package comandos;

import controlador.ParserComandos;
import logica.Mundo;

/**
 * Declaracion de la clase CAyuda.
 * @author Isabel y Patricia
 *
 */
public class CAyuda implements Comando{

	// Metodos.
	/**
	 * Metodo ejecuta, devuelve el comando ejecutado.
	 */
	public String ejecuta(Mundo mundo) {
		
		return ParserComandos.AyudaComandos();
	}
	
	/**
	 * Metodo parsea, escribe un array de String, que debe procesar devolviendo 
	 * el comando que representa el string.
	 */
	public Comando parsea(String[] cadenaComando) {
		
		Comando comandoADevolver = null;
		
		// Preguntamos el numero de elementos del String[].
		if(cadenaComando.length == 1) {
			
			// Miramos si es alfabetico.
			if(cadenaComando[0].matches("[a-zA-Z]+")) {
				
				// Miramos que sea el string ayuda.
				if(cadenaComando[0].equalsIgnoreCase("ayuda")) {
					
					// Devolvemos el comando.
					comandoADevolver = new CAyuda();
				}
			}
		}
		
		return comandoADevolver;
	}

	/**
	 * Metodo textoAyuda, devuelve el texto con la descripcion de dicho comando.
	 */
	public String textoAyuda() {
		
		return "AYUDA: muestra esta ayuda.";
	}
	
}
