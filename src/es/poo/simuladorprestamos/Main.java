package es.poo.simuladorprestamos;

import java.util.Scanner;

public class Main 
{
	private static final Scanner scanIn = new Scanner(System.in);

	public static void main(String[] args) 
	{
		/**En la linea "Persona pers = new Persona();" creo un objeto persona donde guardar los datos del usuario.*/
		Persona pers = new Persona();
		pers.pedirDatos();
		
		TipoPrestamo tipoPrest;
		
		/**En la linea "tipoPrest = Miscelaneo.pedirPrestamo();" creo un objeto "TipoPrestamo" al que le asigno los valores escogidos en la
		*funcion "pedirPrestamo()"*/
		tipoPrest = Miscelaneo.pedirPrestamo();
		
		Cuota cuot = new Cuota();
		
		/**En la linea "Prestamo prest = new Prestamo(pers, tipoPrest, cuot);" creo un objeto prestamo usando el constructor
		 * de dicha clase para instanciar el objeto con las variables de "Persona" "TipoPrestamo" y "Cuota" dentro del mismo
		 */
		Prestamo prest = new Prestamo(pers, tipoPrest, cuot);
		
		//Ejecuto las funciones para elegir el prestamo y pedir los datos necesarios
		prest.imprimirElegido();
		prest.pedirDatosPrestamo();
		
		//Creo las cuotas del prestamo
		prest.calcularAmortizacion();
		
		//Imprimo por pantalla el prestamo y las cuotas dependiendo de lo que elija el usuario
		prest.pedirImprimir();
		scanIn.close();
	}
}