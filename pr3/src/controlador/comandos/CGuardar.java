package controlador.comandos;

import controlador.Controlador;

/**
 * Declaracion de la clase CGuardar.
 * @author Isabel y Patricia
 *
 */

public class CGuardar implements Comando{
	
	/**
	 * Atributo.
	 */
	private String nombreFichero;
	
	/**
	 * Constructor.
	 * @param nombreFichero
	 */
	public CGuardar(String nombreFichero) {
		
		this.nombreFichero = nombreFichero;
	}
	
	/**
	 * Metodo ejecuta, devuelve el comando ejecutado.
	 */
	public String ejecuta(Controlador controlador) {
		
		return controlador.guardar(nombreFichero);
	}
	
	/**
	 * Metodo parsea, escribe un array de String, que debe procesar devolviendo  
	 * el comando que representa el string.
	 */
	public Comando parsea(String[] cadenaComando) {
		
		Comando comandoADevolver = null;
		
		// Preguntamos el numero de elementos del String[].
		if(cadenaComando.length == 2) {
			
			// Miramos si es alfabetico.
			if(cadenaComando[0].matches("[a-zA-Z]+")) {
				
				// Miramos que sea el string iniciar.
				if(cadenaComando[0].equalsIgnoreCase("guardar")) {
					
					// Comprobamos que sea un archivo txt.
					// Lo que haremos es comprobar si las ultimas 4 letras son .txt. Y si tiene algo escrito delante.
					int totalLetrasNomFichero = cadenaComando[1].length();
					if((cadenaComando[1].charAt(totalLetrasNomFichero-1) == 't') && (cadenaComando[1].charAt(totalLetrasNomFichero-2) == 'x') 
						&& (cadenaComando[1].charAt(totalLetrasNomFichero-3) == 't') && (cadenaComando[1].charAt(totalLetrasNomFichero-4) == '.') 
						&& (cadenaComando[1].length() >=5)){
					
						// Devolvemos el comando.
						comandoADevolver = new CGuardar(cadenaComando[1]);
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
		
		return "GUARDAR NOMFICH: permite almacenar en un fichero de texto, de nombre nomFich, "
				+ "la configuracion del juego actual.";
	}

}
