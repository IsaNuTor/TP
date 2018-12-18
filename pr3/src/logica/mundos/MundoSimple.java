package logica.mundos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import constantes.Constantes;
import logica.Superficie;

public class MundoSimple extends Mundo{
	
	/**
	 * Constructor.
	 */
	public MundoSimple() {

		super();
		
		tipoDeMundo = "simple";
	}

	/**
	 * Constructor.
	 * @param filas
	 * @param columnas
	 */
	public MundoSimple(int filas, int columnas) {
		
		super(filas, columnas);
		
		tipoDeMundo = "simple";
		
		inicializaMundo();
		
	}
	
	/**
	 * Constructor.
	 * @param filas
	 * @param columnas
	 * @param celulasSimples
	 */
	public MundoSimple(int filas, int columnas, int celulasSimples) {
		
		super(filas, columnas);
		
		tipoDeMundo = "simple";
		
		cSimples = celulasSimples;
		
		tablero.inicializarTableroConCelulas(celulasSimples, 0);		
	}

	/**
	 * Metodo inicializaMundo, inicializa el mundo con las constantes.
	 */
	public void inicializaMundo() {
			
		cSimples = Constantes.NUMERO_CELULAS_SIMPLES;
		
		// Incializamos la superficie de mundo con Células aleatorias.
		tablero.inicializarTableroConCelulas(cSimples, 0);	
		
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
			
			// Creamos una superficie con el tamaño de filas y columnas.
			tablero = new Superficie(filas, columnas);
				
			numCelulas = tablero.cargarFicheroSuperficie(bufferLectura);
			cSimples = numCelulas[0];
			
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
					
			bufferEscritura.write("simple");
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
