package logica.mundos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import logica.Superficie;
import constantes.Constantes;

public class MundoComplejo extends Mundo{

	/**
	 * Constructor.
	 */
	public MundoComplejo() {
		
		//////////////////////Creo que no haria falta ponerlo pq siempre llama al de por defecto si no hay super.
		super();
		tipoDeMundo = "complejo";
	}
	
	/**
	 * Constructor.
	 * @param filas
	 * @param columnas
	 */
	public MundoComplejo(int filas, int columnas) {
		
		super(filas, columnas);
		
		inicializaMundo(); 
		
		tipoDeMundo = "complejo";
	}
	
	/**
	 * Constructor.
	 * @param filas
	 * @param columnas
	 * @param celulasSimples
	 * @param celulasComplejas
	 */
	public MundoComplejo(int filas, int columnas, int celulasSimples, int celulasComplejas) {
		
		super(filas, columnas);
		
		cSimples = celulasSimples;
		cComplejas = celulasComplejas;
		
		tipoDeMundo = "complejo";
		
		tablero.inicializarTableroConCelulas(celulasSimples, celulasComplejas);		
	}

	/**
	 * Metodo inicializaMundo, inicializa el mundo con las constantes.
	 */
	public void inicializaMundo() {

		cSimples = Constantes.NUMERO_CELULAS_SIMPLES;
		cComplejas = Constantes.NUMERO_CELULAS_COMPLEJAS;
		
		// Incializamos la superficie de mundo con Células aleatorias.
		tablero.inicializarTableroConCelulas(cSimples, cComplejas);		
	}

	/**
	 * Metodo cargarMundoFichero, carga el mundo a partir del fichero.
	 */
	public void cargarMundoFichero(BufferedReader bufferLectura) {

		String lineaLeida = "";
		int [] numCelulas;
		
		try {
				
			lineaLeida = bufferLectura.readLine();
			this.setFilas(Integer.parseInt(lineaLeida));
			
			lineaLeida = bufferLectura.readLine();
			this.setColumnas(Integer.parseInt(lineaLeida));
			
			// Creamos una superficie con el tamanio de filas y columnas.
			tablero = new Superficie(filas, columnas);
				
			numCelulas = tablero.cargarFicheroSuperficie(bufferLectura);
			cSimples = numCelulas[0];
			cComplejas = numCelulas[1];
			
		}catch (IOException e) {
				
			System.out.println("No se ha leido correctamente el archivo o no se ha cerrado bien el flujo");
		}
	}

	/**
	 * Metodo guardarMundoFichero, guarda el mundo en el nombre del fichero que se le pasa por parametro.
	 */
	public void guardarMundoFichero(String nombreFichero) {
		
		String cadenaFilas = "";
		String cadenaColumnas = "";
		
		try{
			
			//Para escribir en el.
			FileWriter archivo = new FileWriter(nombreFichero);
			
			BufferedWriter bufferEscritura = new BufferedWriter(archivo);
			
			bufferEscritura.write("complejo");
			bufferEscritura.append("\n");
			cadenaFilas = Integer.toString(filas);
			bufferEscritura.append(cadenaFilas);
			bufferEscritura.append("\n");
			cadenaColumnas = Integer.toString(columnas);
			bufferEscritura.append(cadenaColumnas);
			bufferEscritura.append("\n");
			
			tablero.guardarFicheroSuperficie(bufferEscritura);
			
			bufferEscritura.close();
			archivo.close();
		}catch (IOException e){
			
			e.printStackTrace();
		}
	}

}
