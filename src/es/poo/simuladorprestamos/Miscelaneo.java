package es.poo.simuladorprestamos;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import es.poo.simuladorprestamos.TipoPrestamo.ModoPrestamo;

/**La clase 'Miscelaneo' contiene funciones extras que pueden ser utiles para la ejecucion del programa.*/
public class Miscelaneo 
{
	static DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	private static final Scanner scanIn = new Scanner(System.in);
	/*La funcion "Escanear()" Escanea los datos introducidos por pantalla
	 * @return Esta funcion devuelve un String.*/
	public static String Escanear(String cadena)
	{
		System.out.println(cadena);
		return (scanIn.nextLine());
	}
	
	
	
	/**La funcion 'pedirPrestamo()' pide por pantalla al usuario que elija entre prestamo personal o hipotecario,
	 * despues llama al array y printea por pantalla los dos prestamos del tipo escogido para que el usuario escoja entre
	 * uno de los dos*/
	public static TipoPrestamo pedirPrestamo()
	{
		TipoPrestamo tipoPrest = null;	
		int elec = Integer.parseInt(Escanear("¿Que tipo de prestamo desea?:\n" + "(1)Personal\n" + "(2)Hipotecario"));
		if(elec == 1)
		{
			
			ArrayList<TipoPrestamo> array = TipoPrestamo.obtenerPrestamo(ModoPrestamo.PERSONAL);
			System.out.println("Escoja el tipo de prestamo Hipotecario que desea:");
			for (int i = 0; i < array.size(); i++) 
			{
				System.out.println("(" + (i + 1) + ") " + array.get(i).dameCadena());
			}
			elec = Integer.parseInt(Escanear(""));
				if(elec == 1)
				{	
					tipoPrest = array.get(elec - 1);
				}else if(elec == 2)
				{
					tipoPrest = array.get(elec - 1);
				}else
				{
					System.out.println("El prestamo elegido no existe.");
					System.exit(-1);
				}	
		}else if(elec == 2)	{
			
			ArrayList<TipoPrestamo> array = TipoPrestamo.obtenerPrestamo(ModoPrestamo.HIPOTECARIO);
			System.out.println("Escoja el tipo de prestamo Hipotecario que desea:");
			for (int i = 0; i < array.size(); i++) 
			{
				System.out.println("(" + (i + 1) + ")" + array.get(i).dameCadena());
			}
			elec = Integer.parseInt(Escanear(""));
				if(elec == 1)
				{
					tipoPrest = array.get(elec - 1);
				}else if(elec == 2)
				{
					tipoPrest = array.get(elec - 1);
				}else
				{
					System.out.println("El prestamo elegido no existe.");
					System.exit(-1);
				}
			
		}else {
			System.out.println("El prestamo elegido no existe.");
			System.exit(-1);
		}
		return tipoPrest;
	}
	
}