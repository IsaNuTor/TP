package aplicacion;

import herramientas.Enum_Instrucciones;

import e_s_datos.EntradaDeDatos;

/**
 * Declaracion de la clase Controlador.
 * @author Isabel y Patricia
 * 
 */
public class Controlador {

	/**
	 * Atributos.
	 */
	private Mundo mundo;
	
	/**
	 * Constructor.
	 */
	public Controlador() {
		
		mundo = new Mundo();
	}
	
	/**
	 * Metodos.
	 * 
	 */
	/**
	 * Metodo ayuda, muestra la ayuda por consola.
	 * Coge todos los enumerados que se encuentran en el array y los muestra.
	 */
	public void mostrarAyuda() {
		
		Enum_Instrucciones[] arrayEnumerado;
		
		arrayEnumerado = Enum_Instrucciones.getArrayEnumerados();
		
		// El tamano del array de enumerados es 8.
		for(int i = 0; i < Enum_Instrucciones.tamanoArrayEnumerados(); i++) {
			System.out.println(arrayEnumerado[i].getDescripcionOrden());
		}
	}
	
	/**
	 * Metodo realizaSimulacion, se encarga de pedir el comando al usuario
	 * para posteriormente ejecutar la opcion elegida.
	 */
	public void realizaSimulacion(){
		
		EntradaDeDatos entradaDatosUsuario = new EntradaDeDatos();
		Enum_Instrucciones ordenUsuarioEnEnumerado;
		int filaEntrada, columnaEntrada;
		boolean salir = false;
		
		// Mostramos la superficie.
		mundo.mostrarSuperficie();
			
		while(!salir) {
			
			ordenUsuarioEnEnumerado = entradaDatosUsuario.pedirComandoPorConsolaAlUsuario();
			
			// Dependiendo del enumerado haremos que se ejecute una accion u otra.
			switch(ordenUsuarioEnEnumerado) {
			case PASO:
				
				mundo.evoluciona();
				
				break;
			case INICIAR:
	
				mundo.iniciar();
				
				break;
			case CREARCELULA:
				
				// Almacenamos en la variable filaEntrada el valor que haya introducido el usuario.
				filaEntrada = entradaDatosUsuario.devuelveFilaIntroducidaPorConsolaAlUsuario();
				
				// Almacenamos en la variable columnaEntrada el valor que haya introducido el usuario.
				columnaEntrada = entradaDatosUsuario.devuelveColumnaIntroducidaPorConsolaUsuario();
				
				// Creamos la celula, si se ha podido nos notifica exito, y si no, nos informa de un error.
				if(mundo.crearCelula(filaEntrada, columnaEntrada)) {
					System.out.println("Creamos nueva celula en la posicion: (" +
										filaEntrada + "," + columnaEntrada + ").");
				}
				else {
					System.out.println("Error, no se ha podido crear la celula");
				}
				
				break;
			case ELIMINARCELULA:
			
				// Almacenamos en la variable filaEntrada el valor que haya introducido el usuario.
				filaEntrada = entradaDatosUsuario.devuelveFilaIntroducidaPorConsolaAlUsuario();
				
				// Almacenamos en la variable columnaEntrada el valor que haya introducido el usuario.
				columnaEntrada = entradaDatosUsuario.devuelveColumnaIntroducidaPorConsolaUsuario();
				
				// Creamos la c�lula, si se ha podido nos notifica exito, y si no error.
				if(mundo.eliminarCelula(filaEntrada, columnaEntrada)) {
					System.out.println("Celula eliminada con exito en la posicion: (" +
										filaEntrada + "," + columnaEntrada + ").");
				}
				else {
					System.out.println("Error, no se ha podido eliminar la celula");
				}
		
				break;
			case AYUDA:
				
				mostrarAyuda();
				
				break;
			case VACIAR:
				
				mundo.vaciar();
				
				break;
			case SALIR:
				
				salir = true;
				 
				break;
			case DESCONOCIDO:
				
				System.out.println("Error de entrada.");
				
				break;
			}
			
			if(!salir) {
				
				// Muestra la superficie.
				System.out.println();
				mundo.mostrarSuperficie();
			}
		}
		
		System.out.println("Fin de la simulacion......");
	}
}
