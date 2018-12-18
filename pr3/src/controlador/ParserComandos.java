package controlador;

import logica.mundos.Mundo;
import controlador.comandos.CAyuda;
import controlador.comandos.CCargar;
import controlador.comandos.CCrearCelula;
import controlador.comandos.CEliminarCelula;
import controlador.comandos.CGuardar;
import controlador.comandos.CIniciar;
import controlador.comandos.CJugar;
import controlador.comandos.Comando;
import controlador.comandos.CPaso;
import controlador.comandos.CSalir;
import controlador.comandos.CVaciar;
import excepciones.ErrorDeInicializacion;
import excepciones.FormatoDeOrdenDesconocida;

/**
 * Declaracion de la clase ParserComandos.
 * @author Isabel y Patricia
 *
 */
public class ParserComandos {
	
	// Atributos.
	private static int fila;
	private static int columna;
	private static Mundo mundo;
	private static String nombreFichero;

	private static Comando[] prototipos = {	new CAyuda(),
											new CCargar(nombreFichero),
											new CCrearCelula(fila, columna),
											new CEliminarCelula(fila, columna),
											new CGuardar(nombreFichero),
											new CIniciar(),
											new CJugar(mundo),
											new CPaso(),
											new CSalir(),
											new CVaciar(),
									};
	// Metodos.
	/**
	 * Metodo AyudaComandos, muestra la ayuda, es decir, la informacion sobre lo que hace cada comando.
	 * @return cadenaAyuda, texto con la ayuda.
	 */
	public static String AyudaComandos() {
		
		String cadenaAyuda = "";
		
		// Recorremos el array de prototipos llamando al metodo textoAyuda de todos los comandos que tengamos.
		for(Comando c: prototipos) {
			cadenaAyuda += c.textoAyuda() + "\n";
		}
		
		return cadenaAyuda;	
	}
	
	/**
	 * Metodo parseaComando, parsea el array String[], para construir el comando que representa
	 * o en caso de no representar ninguno, devolver null.
	 * @param cadenas.
	 * @return com, el comando si ha coincidido, sino null
	 * @throws FormatoDeOrdenDesconocida 
	 * @throws ErrorDeInicializacion 
	 * @throws NumberFormatException 
	 */
	public static Comando parseaComando(String[] cadenas) throws FormatoDeOrdenDesconocida, NumberFormatException, ErrorDeInicializacion {
		
		boolean comandoEncontrado = false;
		Comando com = null;
		
		// Mientras no se haya encontrado y no hayamos llegado al final del array.
		
		for(int i = 0; (i < (prototipos.length) && (!comandoEncontrado)); i++) {
				
			com = prototipos[i].parsea(cadenas);
			
			if(com != null) {
				comandoEncontrado = true;
			}
		}
			
		if(!comandoEncontrado) {
			// Si no se ha encontrado el comando lanzamos una excepción.
			throw new FormatoDeOrdenDesconocida();
		}
		
		return com;
	}
}
