package es.poo.simuladorprestamos;

import java.text.DecimalFormat;

/**La clase 'Cuota' contiene toda la informacion de cada cuota del prestamo realizado por el usuario*/
public class Cuota {
	DecimalFormat df = new DecimalFormat("#.00");
	private int numero;
	private double importeInteres;
	private double importeAmortizado;
	private double importeCuota;
	private double capitalPendiente;
	
	/**Obtiene el numero de la cuota*/
	public int getNumero() {
		return numero;
	}
	
	/**Asigna el numero de la cuota*/
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	/**Obtiene el importe de intereses*/
	public double getImporteInteres() {
		return importeInteres;
	}
	
	/**Asigna el importe de intereses*/
	public void setImporteInteres(double importeInteres) {
		this.importeInteres = importeInteres;
	}
	
	/**Obtiene el importe amortizado*/
	public double getImporteAmortizado() {
		return importeAmortizado;
	}
	
	/**Asigna el importe amortizado*/
	public void setImporteAmortizado(double importeAmortizado) {
		this.importeAmortizado = importeAmortizado;
	}
	
	/**Obtiene el importe de la cuota*/
	public double getImporteCuota() {
		return importeCuota;
	}
	
	/**Asigna el importe de la cuota*/
	public void setImporteCuota(double importeCuota) {
		this.importeCuota = importeCuota;
	}
	
	/**Obtiene el capital pendiente*/
	public double getCapitalPendiente() {
		return capitalPendiente;
	}
	
	/**Asigna el capital pendiente*/
	public void setCapitalPendiente(double capitalPendiente) {
		this.capitalPendiente = capitalPendiente;
	}

	/**
	 * La funcion 'dameCuota()' se encarga de printear por pantalla las variables de la clase dame cuota
	 * necesarias para los resumenes del prestamo.
	 * 
	 * @return un String conteniendo toda la informacion de la cuota.
	 */
	public String dameCuota()
	{
		return "| Cuota Numero: " + numero + " | Importe de la cuota: " + df.format(importeCuota) + " | Importe de intereses: "
				+ df.format(importeInteres) + " | Importe amortizado: " + df.format(importeAmortizado) + " | Capital pendiente: " + df.format(capitalPendiente) + " |\n";
	}
	
	
}
