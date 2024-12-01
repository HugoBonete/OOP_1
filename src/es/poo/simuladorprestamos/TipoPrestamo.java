package es.poo.simuladorprestamos;

import java.util.ArrayList;

/** La clase 'TipoPrestamo' es una clase que contiene las diferentes caracteristicas de los diferentes tipos de prestamos
 * asi como el metodo 'obtenerPrestamo(modo)' para crear un array con los diferentes tipos de prestamos.*/
public class TipoPrestamo {
	
	private static ModoPrestamo tPrestam;
	private int plazoMax;
	private int importMax;
	private double tipo;
	
	
	public enum ModoPrestamo
	{
		PERSONAL, HIPOTECARIO;
	}
	
	/**Obtiene el valor del plazo maximo*/
	public int getPlazoMax() {
		return plazoMax;
	}
	
	/**Asigna el valor del plazo maximo*/
	public void setPlazoMax(int plazoMax) {
		this.plazoMax = plazoMax;
	}
	
	/**Obtiene el importe maximo*/
	public int getImportMax() {
		return importMax;
	}
	
	/**Asigna el importe maximo*/
	public void setImportMax(int importMax) {
		this.importMax = importMax;
	}
	
	/**Obtiene el tipo de interes*/
	public double getTipo() {
		return tipo;
	}
	
	/**Asigna el tipo de interes*/
	public void setTipo(double tipo) {
		this.tipo = tipo;
	}
	
	/**Obtiene el modo de prestamo*/
	public ModoPrestamo gettPrestam() {
		return tPrestam;
	}
	
	/**Asigna el modo de prestamo*/
	public void settPrestam(ModoPrestamo tPrestam) {
		TipoPrestamo.tPrestam = tPrestam;
	}
	
	/**El metodo 'obtenerPrestamo(ModoPrestamo)' crea un 'ArrayList' con objetos 'TipoPrestamo' con diferentes
	 * valores para asi despues poder elegir el que el usuario mas le interese
	 * 
	 * @return Este metodo devuelve un objeto TipoPrestamo con las variables instanciadas con valores predeterminados.*/
	public static ArrayList<TipoPrestamo> obtenerPrestamo(ModoPrestamo modo)
	{
		ArrayList<TipoPrestamo> array = new ArrayList<TipoPrestamo>();
		if(modo == ModoPrestamo.PERSONAL)
		{
			TipoPrestamo tp1 = new TipoPrestamo();
			tp1.settPrestam(ModoPrestamo.PERSONAL);
			tp1.setTipo(5);
			tp1.setPlazoMax(48);
			tp1.setImportMax(15000);
			array.add(tp1);
		
			TipoPrestamo tp2 = new TipoPrestamo();
			tp2.settPrestam(ModoPrestamo.PERSONAL);
			tp2.setTipo(12);
			tp2.setPlazoMax(72);
			tp2.setImportMax(30000);
			array.add(tp2);
		}else {
			TipoPrestamo th1 = new TipoPrestamo();
			th1.settPrestam(ModoPrestamo.HIPOTECARIO);
			th1.setTipo(0.99);
			th1.setPlazoMax(240);
			th1.setImportMax(200000);
			array.add(th1);
			
			TipoPrestamo th2 = new TipoPrestamo();
			th2.settPrestam(ModoPrestamo.HIPOTECARIO);
			th2.setTipo(1.5);
			th2.setPlazoMax(420);
			th2.setImportMax(300000);
			array.add(th2);
			}
		return array;
	}
	
	/**El metodo 'dameCadena()' imprime por pantalla todos los datos de un tipo de prestamo
	 * 
	 * @return Este metodo devuelve un string con todas las variables de un objeto tipo de prestamo*/
	public String dameCadena() {
		return "Tipo " +  tipo + ", Plazo maximo " + plazoMax + ", Importe maximo " + importMax;
	}

}
