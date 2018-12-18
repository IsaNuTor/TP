package controlador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import logica.celulas.CelulaCompleja;
import logica.celulas.CelulaSimple;
import logica.mundos.Mundo;
import logica.mundos.MundoComplejo;
import logica.mundos.MundoSimple;
import controlador.comandos.Comando;
import entradaSalidaDeDatos.Entrada;
import entradaSalidaDeDatos.Salida;
import excepciones.ErrorDeInicializacion;
import excepciones.FormatoDeOrdenDesconocida;
import excepciones.PalabraIncorrecta;
import excepciones.SinUnMundoInicial;


public class Controlador {

	/**
	 * Atributos.
	 */
	private boolean simulacionTerminada = false;
	private Mundo mundo;

	/**
	 * Constructor.
	 */
	public Controlador() {
		
		mundo = new MundoSimple();
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
		Salida salida;
			
		while(!this.simulacionTerminada) {

			try {
				if(mundo.getTablero() == null) {
					
					throw new SinUnMundoInicial();
				}
			}
			catch(SinUnMundoInicial ex) {
				// Iniciamos un mundo, segun la opcion que escoja el usuario sera un mundo simple o complejo.
				
				if(entradaDatosUsuario.pedirMundoPorConsolaAlUsuario() == 1) {
					mundo = new MundoSimple(Entrada.getFilasSuperficie(), Entrada.getColumnasSuperficie());
				}
				else {
					mundo = new MundoComplejo(Entrada.getFilasSuperficie(), Entrada.getColumnasSuperficie());
				}
			}
			
			// Mostramos la superficie.
			System.out.println(mundo.getTablero());
				
			try {
				String[] words = entradaDatosUsuario.pedirComandoPorConsolaAlUsuario();
				
				Comando comando = ParserComandos.parseaComando(words);
				
				salida = new Salida(comando.ejecuta(this));
				
				System.out.print(salida);	
				
			} catch (FormatoDeOrdenDesconocida e) {
				
				System.out.println("EXCEPCION: comando incorrecto.");
			}
			catch(NumberFormatException ex) {
			
				System.out.println("EXCEPCION: comando incorrecto (Formato numerico incorrecto).");
			}
			catch(ErrorDeInicializacion ex) {
				
				System.out.println(ex.getMessage());
			}

		}
	}
	
	/**
	 * Metodo ejecutaAyuda, hace una llamada al metodo AyudaComandos para mostrar la ayuda
	 * de los comandos.
	 * @return el string con la ayuda de los comandos.
	 */
	public String ejecutaAyuda() {
		
		return ParserComandos.AyudaComandos();
	}
	
	/**
	 * Metodo cargar, se encarga de cargar el fichero.
	 * @param nombreFichero
	 */
	public String cargar(String nombreFichero) {
		
		String mensaje = " ";
		/* Primero combrobamos si el Fichero está en el formato correcto, de no ser así se lanzará un error por 
		   pantalla y seguirá con el juego como antes de intentar cargar el fichero. */
		if(comprobarFormatoDeFichero(nombreFichero)) {
			
			String lineaLeida = "";
			
			try {
				// Abrimos el archivo.
				FileReader archivo = new FileReader(nombreFichero);
				
				// Instanciamos buffer.
				BufferedReader bufferLectura = new BufferedReader(archivo);
				
				lineaLeida = bufferLectura.readLine();
			
				if(lineaLeida.equals("simple")) {
					
					mundo = new MundoSimple();
				} else {
				
					mundo = new MundoComplejo();
				}
				
				mundo.cargarMundoFichero(bufferLectura);
				
				mensaje = "Fichero cargado con exito.";
				
				// Cerramos el flujo.
				bufferLectura.close();
				archivo.close();
				
			} catch (FileNotFoundException e) {
				
				System.out.println("No se ha encontrado el archivo");
			}
			catch (IOException e) {
				
				System.out.println("No se ha leido correctamente el archivo o no se ha cerrado bien el flujo");
			}
		}
			
		return mensaje;
	}
	
	/**
	 * Metodo comprobarFormatoDeFichero, compueba si el fomato del fichero es el correcto.
	 * En caso de que no sea asi, devuelve una excepcion.
	 * @param nombreFichero, introducido por el usuario.
	 * @return boolean archivoCorrecto si el formato es correcto.
	 */
	public boolean comprobarFormatoDeFichero(String nombreFichero) {
		
		String lineaLeida = "", tipoDeMundo;
		String [] cadenaCelulaSuperficie;
		int filas, columnas, fila, columna, numeroLinea = 0;
		boolean archivoCorrecto = true;
		
		// Para comprobar que en el archivo no haya dos celulas con las mismas posiciones, y que en caso de haberlas
		// se lanze una excepcion, vamos a crear una matriz de booleanos inicialmente a false, dónde iremos marcando a
		// true las celulas que vayamos leyendo.
		boolean [][] miMatrizCelulasColocadas;
		
		try {
			// Abrimos el archivo.
			FileReader archivo = new FileReader(nombreFichero);
			
			// Instanciamos buffer.
			BufferedReader bufferLectura = new BufferedReader(archivo);
			
			try {
					tipoDeMundo = bufferLectura.readLine();
					numeroLinea++;
					
					// Leemos el tipoDeMundo, si es distinto de simple o complejo lanzamos excepcion.
					if(!(tipoDeMundo.equals("simple") || tipoDeMundo.equals("complejo"))) {	
						
						// Lanzamos la excepcion palabra incorrecta.
						throw new PalabraIncorrecta("EXCEPCION: Formato incorrecto. En la la linea " + numeroLinea +
								" del fichero tiene que escribir (simple o complejo).");
					}
					
					// Leemos, si filas y columnas son correctas, es decir son un número, sino se lanza excepcion.
					lineaLeida = bufferLectura.readLine();
					numeroLinea++;
					filas = Integer.parseInt(lineaLeida);
						
					lineaLeida = bufferLectura.readLine();
					numeroLinea++;
					columnas = Integer.parseInt(lineaLeida);
					
					// Creamos la matriz.
					miMatrizCelulasColocadas = new boolean[filas][columnas];
					
					// Ahora leemos las posiciones de las celulas en la superficie.
					lineaLeida = bufferLectura.readLine();
					numeroLinea++;
					
					while(lineaLeida != null) {
						
						// Pasamos el String a una cadena de String con los datos de la linea del archivo. Ej(2 1 simple 1 0).
						cadenaCelulaSuperficie = lineaLeida.split(" ");
						
						// Leemos la fila.
						fila = Integer.parseInt(cadenaCelulaSuperficie[0]);
						
						// Leemos la columna.
						columna = Integer.parseInt(cadenaCelulaSuperficie[1]);
						
						// Comprobamos que no se salen de la Superficie, si se salen lanzamos Excepcion.
						if(!((fila >= 0) && (fila < filas))){
						
							// Lanzamos la excepción palabra incorrecta.
							throw new PalabraIncorrecta("EXCEPCION: Formato incorrecto en la la linea " + numeroLinea +
									" del fichero. fila = " + fila + " no esta dentro de las dimensiones del tablero, "
									+ "(0 >= fila < " + filas + ").");
						}
						else if(!((columna >= 0) && (columna < columnas))) {
							
							// Lanzamos la excepción palabra incorrecta.
							throw new PalabraIncorrecta("EXCEPCION: Formato incorrecto en la la linea " + numeroLinea +
									" del fichero. columna = " + columna + " no esta dentro de las dimensiones del tablero, "
									+ "(0 >= columna < " + columnas + ").");
						}
						else if(miMatrizCelulasColocadas[fila][columna]){ // Comprobamos que esa posicion de la celula no 
																		 // haya sido ocupada anteriormente por otra celula.
							// Lanzamos la excepción palabra incorrecta.
							throw new PalabraIncorrecta("EXCEPCION: Formato incorrecto en la la linea " + numeroLinea +
									" del fichero. Ya hay una celula en en la posicion (" + fila + "," + columna + ") en el fichero.");
						}
						
						// Ahora leemos el tipo de celula.
						if(cadenaCelulaSuperficie[2].equals("simple")) {
							
							// Leemos N(pasos en los que puede estar sin moverse y M(pasos para reproducirse).
							Integer.parseInt(cadenaCelulaSuperficie[3]); // N

							Integer.parseInt(cadenaCelulaSuperficie[4]); // M
							
						}else if((cadenaCelulaSuperficie[2].equals("compleja")) && (tipoDeMundo.equals("complejo"))) {
							
							Integer.parseInt(cadenaCelulaSuperficie[3]); // N_COMER.
							
						}
						else { // Es otra palabra distinta.
							// Lanzamos la excepción palabra incorrecta.
							throw new PalabraIncorrecta("EXCEPCION: Formato incorrecto en la la linea " + numeroLinea +
									" del fichero. Hay escrito en el tipo de celula algo distinto de simple \n"
									+ "si es un mundo simple, o de simple y compleja si es un mundo complejo.");
						}
						
						// Ponemos a true en la matriz de miMatrizCelulasColocadas.
						miMatrizCelulasColocadas[fila][columna] = true;
						
						// Leemos la linea siguiente.
						lineaLeida = bufferLectura.readLine();	
						numeroLinea++;
					}

					// Cerramos el flujo.
					archivo.close();
					
			}catch(PalabraIncorrecta ex) {
				
				System.out.println(ex.getMessage());
				archivoCorrecto = false;
			}
			catch(NumberFormatException ex) {
				System.out.println("EXCEPCION: Formato incorrecto en la la linea " + numeroLinea +
						" del fichero hay uno o mas caracteres alfabeticos en vez de numericos.");
				archivoCorrecto = false;
			}
			catch(ArrayIndexOutOfBoundsException ex) {
				
				System.out.println("EXCEPCION: Formato incorrecto en la la linea " + numeroLinea +
						" del fichero.");
				archivoCorrecto = false;
			}
			catch(NullPointerException ex) {
				
				System.out.println("El archivo esta vacio.");
				archivoCorrecto = false;
			}
			finally {
				// Cerramos el buffer.
				bufferLectura.close();
			}
			
		}catch (FileNotFoundException e) {
					
			System.out.println("EXCEPCION: Fichero no encontrado.");
			archivoCorrecto = false;
		}
		catch (IOException e) {
					
			System.out.println("EXCEPCION: No se ha leido correctamente el archivo o no se ha cerrado bien el flujo.");
			archivoCorrecto = false;
		}
		
		return archivoCorrecto;
	}
	
	/**
	 * Metodo ejecutaEliminarCelula, hace una llamada al metodoeliminarCelulaEnSuperficie de mundo.
	 * @param fila que se quiere eliminar.
	 * @param columna que se quiere eliminar.
	 * @return llamada al metodo eliminarCelulaEnSuperficie.
	 */
	public String ejecutaEliminarCelula(int fila, int columna) {
		
		return mundo.eliminarCelulaEnSuperficie(fila, columna);
	}
	
	/**
	 * Metodo ejecutaCrearCelula, hace una llamada al metodo crearCelulaEnSuperficie de superficie y 
	 * en un tablero complejo inserta la celula segun haya elegido el usuario, 1 es compleja y 2 es simple.
	 * @param fila donde se quiere insertar la celula.
	 * @param columna donde se quiere insertar la celula.
	 * @return mensaje.
	 */
	public String ejecutaCrearCelula(int fila, int columna) {
		
		String mensaje = "";
		
		// Instanciamos una entrada.
		Entrada entrada = new Entrada();
	
		if(mundo.getTipoDeMundo().equals("simple")) {
			
			// Creamos una celula simple directamente.
			mensaje = mundo.crearCelulaEnSuperficie(fila, columna, new CelulaSimple());
		}
		else {
			if(entrada.pedirCelulaPorConsolaAlUsuario() == 1) {
				
				mensaje = mundo.crearCelulaEnSuperficie(fila, columna, new CelulaCompleja());
			}
			else {
				mensaje = mundo.crearCelulaEnSuperficie(fila, columna, new CelulaSimple());
			}
		}
		
		return mensaje;
	}
	
	/**
	 * Metodo guardar, hace una llamada al metodo guardarMundoFichero.
	 * @param nombreFichero
	 * @return devuelve un string indicando que el guardado se ha realizado con exito.
	 */
	public String guardar(String nombreFichero) {
		
		mundo.guardarMundoFichero(nombreFichero);
		
		return "Guardado con exito.";
	}
	
	/**
	 * Metodo ejecutaIniciar, hace una llamada al metodo iniciar.
	 */
	public void ejecutaIniciar() {
		
		mundo.iniciar();
	}
	
	/**
	 * Metodo ejecutaJugar, inicia al mundo pasado por parametro.
	 * @param mundo
	 */
	public void ejecutaJugar(Mundo mundo) {
		
		this.mundo = mundo;
	}
	
	/**
	 * Metodo daUnPaso, hace una llamada al metodo evoluciona.
	 */
	public String daUnPaso() {
		
		return mundo.evoluciona();
	}
	
	/**
	 * Metodo ejecutaSalir, indica que la simulacion se ha terminado poniendola a true
	 * @return devuelve un string inidanco que es el fin de la simuloacion.
	 */
	public String ejecutaSalir() {
		
		simulacionTerminada = true;
		
		return "Fin de la simulacion...";
	}
	
	/**
	 * Metodo ejecutaVaciar, hace una llamada al metodo vaciar.
	 */
	public void ejecutaVaciar() {
		
		mundo.vaciar();
	}
	
	
	
	
}
