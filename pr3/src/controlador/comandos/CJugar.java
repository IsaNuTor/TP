package controlador.comandos;

import logica.mundos.Mundo;
import logica.mundos.MundoComplejo;
import logica.mundos.MundoSimple;
import controlador.Controlador;
import excepciones.ErrorDeInicializacion;

/**
 * Declaracion de la clase CJugar.
 * @author Isabel y Patricia
 *
 */

public class CJugar implements Comando{

	/**
	 * Atributo.
	 */
	private Mundo mundo;
	
	/**
	 * Constructor.
	 * @param mundo
	 */
	public CJugar(Mundo mundo) {
		
		this.mundo = mundo;
	}

	/**
	 * Metodo ejecuta, devuelve el comando ejecutado.
	 */
	public String ejecuta(Controlador controlador) {
		
		controlador.ejecutaJugar(this.mundo);

		return "";
	}

	/**
	 * Metodo parsea, escribe un array de String, que debe procesar devolviendo  
	 * el comando que representa el string.
	 * @throws ErrorDeInicializacion 
	 */
	public Comando parsea(String[] cadenaComando) throws NumberFormatException, ErrorDeInicializacion{
		
		Comando comandoADevolver = null;
		// n = filas, m = columnas, s = celulasSimples, c = celulasComplejas.
		int n, m, s, c;
		
		// Preguntamos el numero de elementos del String[], para saber si la longitud corresponde con la del comando jugar.
		// jugar simple N M S -> longitud 5.
		// jugar complejo N M S C -> longitud 6.
		if((cadenaComando.length == 5) || (cadenaComando.length == 6)) {
			
			// Miramos si es alfabetico y que la primera palabra sea jugar.
			if((cadenaComando[0].matches("[a-zA-Z]+")) && (cadenaComando[0].equalsIgnoreCase("jugar"))) {
				
				if(cadenaComando[1].matches("[a-zA-Z]+")) {
					
					if(cadenaComando[1].equalsIgnoreCase("simple") && (cadenaComando.length == 5)) {
						
						// Ahora cogemos las filas N, las columnas M y las celulasSimples S.
						n = Integer.parseInt(cadenaComando[2]);
						m = Integer.parseInt(cadenaComando[3]);
						s = Integer.parseInt(cadenaComando[4]);
								
						// Que las celulas no sean mas que los espacios que tendra superficie.
						if((s)<= (n*m)) {
							comandoADevolver = new CJugar(new MundoSimple(n, m, s));
						}
						else {
				
							throw new ErrorDeInicializacion("EXCEPCION: Hay mas celulas que espacios en la superficie.");	
						}	
					} else if(cadenaComando[1].equalsIgnoreCase("complejo") && (cadenaComando.length == 6)) {
						
						// Ahora cogemos las filas N, las columnas M y las celulasSimples S.
						n = Integer.parseInt(cadenaComando[2]);
						m = Integer.parseInt(cadenaComando[3]);
						s = Integer.parseInt(cadenaComando[4]);
						c = Integer.parseInt(cadenaComando[5]);
									
						if((s + c) <= (n*m)) {
							comandoADevolver = new CJugar(new MundoComplejo(n, m, s, c));
						}
						else {
							throw new ErrorDeInicializacion("EXCEPCION: Hay mas celulas que espacios en la superficie.");
						}			
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

		return "JUGAR SIMPLE N M S: inicia la simulaci�n del juego simple, con una superficie de N filas,"
				+ "\nM columnas y con S celulas simples colocadas de forma aleatoria.\n"
				+ "JUGAR COMPLEJO N M S C: es similar al comando anterior, donde C representa el numero\n"
				+ "de celulas complejas que debe contener el mundo complejo.";
	}

}
