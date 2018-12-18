package principal;

import e_s_datos.EntradaDeDatos;
import aplicacion.Controlador;

/**
 * Declaracion de la clase Main.
 * @author Isabel y Patricia
 *
 */
public class Main {
	
	/**
	 * Atributos.
	 */
	Controlador c;
	
	/**
	 * Constructor.
	 */
	public Main()
	{
	 // Instancia un controlador.
	  c = new Controlador();
	}
	 
	/**
	 * Metodo que lanza la aplicacion.
	 */
	public void lanzaAplicacion()
	{
		 c.realizaSimulacion();
	}
	 
	/**
	 * Main principal.
	 * @param args, argumentos del Main.
	 */
	public static void main(String[] args) {
		
		// Recibe los argumentos, si no se hace de esta forma, toma los valores de las constantes.
		EntradaDeDatos.capturarArgumentosFilasYColumnasRun(args);
		
		// Instanciamos un objeto main.
		Main m = new Main();
		
		// Llamamos al metodo.
		m.lanzaAplicacion();
	}

}
