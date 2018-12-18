package comandos;

import logica.Mundo;

/**
 * Declaracion de la clase CPaso.
 * @author Isabel y Patricia
 *
 */
public class CPaso implements Comando{

	// Metodos.
	/**
	 * Metodo ejecuta, devuelve el comando ejecutado.
	 */
	public String ejecuta(Mundo mundo) {
		
		mundo.evoluciona();
		
		return "";
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
				
				// Miramos que sea el string paso.
				if(cadenaComando[0].equalsIgnoreCase("paso")) {
					
					// Devolvemos el comando
					comandoADevolver = new CPaso();
				}
			}
		}
		
		return comandoADevolver;
	}

	/**
	 * Metodo textoAyuda, devuelve el texto con la descripcion de dicho comando.
	 */
	public String textoAyuda() {
	
		return "PASO: realiza un paso en la simulacion.";
	}

}
