package controlador;

import comandos.Comando;
import entradaSalidaDeDatos.Entrada;
import entradaSalidaDeDatos.Salida;
import logica.Mundo;

/**
 * Declaracion de la clase Controlador.
 * @author Isabel y Patricia
 * 
 */
public class Controlador {
	
	// Atributos.
	private Mundo mundo;
	
	// Constructor.
	public Controlador() {
		
		mundo = new Mundo();
	}

	// Metodo.
	/**
	 * Metodo realizaSimulacion, se encarga de pedir el comando al usuario
	 * para posteriormente ejecutar la opcion elegida.
	 */
	public void realizaSimulacion() {
	
		// Instanciamos una entrada.
		Entrada entradaDatosUsuario = new Entrada();
		
		// Instanciamos una salida.
		Salida mensajeDeSalida;
		
		while(mundo.esSimulacionTerminada()) {
			
			// Mostramos la superficie.
			System.out.println(mundo.getTablero());
			
			String[] words = entradaDatosUsuario.pedirComandoPorConsolaAlUsuario();
			
			Comando comando = ParserComandos.parseaComando(words);
		
			mensajeDeSalida = new Salida(comando.ejecuta(this.mundo));
			System.out.print(mensajeDeSalida);	
		}
	}
}
