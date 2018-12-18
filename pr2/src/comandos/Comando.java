package comandos;

import logica.Mundo;

/**
 * Declaracion de la clase Comando.
 * @author Isabel y Patricia
 *
 */
public interface Comando {
	
	// Ejecuta el comando correspondiente sobre el mundo.
	public abstract String ejecuta(Mundo mundo);
	
	// Recibe un array de String, que debe procesar devolviendo el comando que representa el string.
	public abstract Comando parsea(String[] cadenaComando);
	
	// Devuelve un String con la informacion de ayuda que se quiera mostrar sobre el comando.
	public abstract String textoAyuda();

}
