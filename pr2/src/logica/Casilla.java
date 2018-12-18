package logica;

/**
 * Declaracion de la clase Casilla.
 * @author Isabel y Patricia
 *
 */
public class Casilla {

	// Atributos.
	private int x;
	private int y;
	
	/**
	 * Constructor Casilla(int x, int y).
	 * @param x, filas.
	 * @param y, columnas.
	 */
	public Casilla(int x, int y) {
		
		this.x = x;
		this.y = y;
	}

	// Getter y Setter.
	/**
	 * Getter, getX
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Setter, setX
	 * @param x.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Getter, getY
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setter, setY
	 * @param y.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	// Metodo.
	/**
	 * Metodo toString, devuelve un String con la casilla.
	 */
	public String toString() {
		
		return "(" + x + "," + y + ")";
	}

}
