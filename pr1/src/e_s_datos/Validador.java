package e_s_datos;

import herramientas.Enum_Instrucciones;

/**
 * Declaracion de la clase Validador.
 * @author Isabel y Patricia
 *
 */
public class Validador {

	/**
	 * Metodo comprobarSiLaOrdenDelUsuarioEsCorrecta, comprobamos si la orden introducida del
	 * usuario es correcta, si lo es, devolvemos el enumerado correspondiente. 
	 * @param ordenUsuario.
	 * @return enumeradoADevolver
	 */
	public static Enum_Instrucciones comprobarSiLaOrdenDelUsuarioEsCorrecta(String ordenUsuario) {
		
		Enum_Instrucciones enumeradoADevolver = null;
		String ordenEnumeradoEnString;
		int i = 0; // Para movernos por el array.
		boolean encontrado = false;
		
		// Si no hemos llegado al final del array o los valores no han coincidido, seguimos.
		while(i < Enum_Instrucciones.tamanoArrayEnumerados() && (!encontrado)) {
			
			// Pasamos el enumerado de la posicion i a String y lo almacenamos en la variable ordenEnumeradoEnSring.
			ordenEnumeradoEnString = Enum_Instrucciones.getArrayEnumerados()[i].getOrdenString();
			
			// Comparamos, el valor de entrada del usuario con cada valor del array convertido a String.
			if(ordenUsuario.equalsIgnoreCase(ordenEnumeradoEnString)) {
				// Si son iguales es que lo hemos encontrado y devolvemos el enumerado de esa posicion.
				enumeradoADevolver = Enum_Instrucciones.getArrayEnumerados()[i];
				encontrado = true; // Ponemos encontrado a true para que salga del bucle.
			}
			else {
				// Actualizamos el contador.
				i++;
			}
		}
		
		if(!encontrado) {
			// Si no lo ha encontrado devolvemos el enumerado Desconocido.
			enumeradoADevolver = Enum_Instrucciones.DESCONOCIDO;
		}
		
		return enumeradoADevolver;
	}
	
	/**
	 * Metodo parsearArgumentoString, convierte el string que le llega a un valor entero.
	 * @param argumento, argumento recibido en string.
	 * @return valorEnteroArgumento
	 */
	// Parsear los argumentos, para fila y columna, le pasamos por parametro un String y nos devuelve el valor en entero.
	public static int parsearArgumentoString(String argumento) {
		
		int valorEnteroArgumento;
		
		valorEnteroArgumento = Integer.parseInt(argumento);
		
		return valorEnteroArgumento;
	}
}
