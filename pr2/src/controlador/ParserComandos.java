package controlador;

import comandos.CAyuda;
import comandos.CCrearCelulaCompleja;
import comandos.CCrearCelulaSimple;
import comandos.CDesconocido;
import comandos.CEliminarCelula;
import comandos.CIniciar;
import comandos.CPaso;
import comandos.CSalir;
import comandos.CVaciar;
import comandos.Comando;

/**
 * Declaracion de la clase ParserComandos.
 * @author Isabel y Patricia
 *
 */
public class ParserComandos {
	
	// Atributos.
	private static int fila;
	private static int columna;

	private static Comando[] prototipos = {	new CAyuda(),
											new CCrearCelulaCompleja(fila, columna),
											new CCrearCelulaSimple(fila, columna),
											new CEliminarCelula(fila, columna),
											new CIniciar(),
											new CPaso(),
											new CSalir(),
											new CVaciar(),
											new CDesconocido()
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
	 */
	public static Comando parseaComando(String[] cadenas) {
		
		boolean comandoEncontrado = false;
		Comando com = null;
		
		// Mientras no se haya encontrado y no hayamos llegado al final del array.
		for(int i = 0; (i < (prototipos.length)&&(!comandoEncontrado)); i++) {
			
			com = prototipos[i].parsea(cadenas);
			if(com != null) {
				comandoEncontrado = true;
			}
		}
		
		return com;
	}	
}
