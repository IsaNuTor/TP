package controlador.comandos;

import controlador.Controlador;
import excepciones.ErrorDeInicializacion;
import excepciones.FormatoDeOrdenDesconocida;

/**
 * Declaracion de la clase Comando.
 * @author Isabel y Patricia
 *
 */
public interface Comando {
	
	/** 
	 * Ejecuta el comando correspondiente sobre el mundo.
	 * @param controlador
	 * @return
	 * @throws FormatoDeOrdenDesconocida el comando escrito no corresponde con ninguno
	 */
	public abstract String ejecuta(Controlador controlador) throws FormatoDeOrdenDesconocida;
	
	/** 
	 * Recibe un array de String, que debe procesar devolviendo el comando que 
	 * representa el string.
	 * @param cadenaComando
	 * @return Comando
	 * @throws ErrorDeInicializacion la superficie no tiene capacidad para almacenar el numero de celulas iniciales
	 * @throws NumberFormatException formato numerico incorrecto
	 */
	public abstract Comando parsea(String[] cadenaComando) throws NumberFormatException, ErrorDeInicializacion;
	
	/** 
	 * Devuelve un String con la informacion de ayuda que se quiera mostrar sobre el comando.
	 * @return 
	 */
	public abstract String textoAyuda();
	
}