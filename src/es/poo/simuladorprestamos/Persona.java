package es.poo.simuladorprestamos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**La clase 'Persona' contiene los datos introducidos del usuario como nombre, apellidos, fecha de nacimiento, sueldo...*/
public class Persona 
{
	/** La variable 'formato' crea un formato predeterminado para las fechas introducidas por pantalla por el usuario*/
	DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String Nombre;
	private String Apellido1;
	private String Apellido2;
	private int Sueldo;
	private String Fecha;
	private int edad;
	private int salarioMensual;
	
	/** Obtiene el valor del nombre*/
	public String getNombre() {
		return Nombre;
	}
	
	/**Asigna el valor del nombre*/
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	/**Obtiente el valor del primer apellido*/
	public String getApellido1() {
		return Apellido1;
	}
	
	/** Asigna el valor al primer apellido*/
	public void setApellido1(String apellido1) {
		Apellido1 = apellido1;
	}
	
	/**Obtiene el valor del segundo apellido*/
	public String getApellido2() {
		return Apellido2;
	}
	
	/**Asigna el valor del segundo apellido*/
	public void setApellido2(String apellido2) {
		Apellido2 = apellido2;
	}
	
	/**Obtiene el valor del sueldo*/
	public int getSueldo() {
		return Sueldo;
	}
	
	/**Asigna el valor del sueldo*/
	public void setSueldo(int sueldo) {
		Sueldo = sueldo;
	}
	
	/**Obtiene el valor de la fecha*/
	public String getFecha() {
		return Fecha;
	}
	
	/**Asigna el valor a la Fecha*/
	public void setFecha(String fecha) {
			Fecha = fecha;
	}
	
	/**Obtiene la edad*/
	public int getEdad() {
		return edad;
	}
	
	/**Asigna el valor de la edad*/
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	/**Obtiene el valor del salario mensual*/
	public int getSalarioMensual() {
		return salarioMensual;
	}
	
	/**El setter 'setSalarioMensual()' me guarda en la variable 'salarioMensual' el salario bruto anual dividido entre
	 * 12.*/
	public void setSalarioMensual() {
		this.salarioMensual = this.Sueldo/12;
	}
	
	/**
	 * La funcion 'imprimirNombreEdad()' imprime por pantalla los datos de nombre apellidos y edad del usuario.
	 * 
	 * @return La funcion devuelve un string conteniendo los datos del usuario.
	 */
	public  String imprimirNombreEdad()
	{
		return getNombre() + ", " + getApellido1() + " " + getApellido2()
		+ ", " + getEdad();
	}
	
	/**
	 * La funcion 'pedirDatos()' hace que el usuario introduzca sus datos por pantalla como nombre, apellidos, fecha de nacimiento...
	 */
	public void pedirDatos()
	{
		setNombre(Miscelaneo.Escanear("Introduzca su nombre:"));
		setApellido1(Miscelaneo.Escanear("Introduzca su primer apellido:"));
		setApellido2(Miscelaneo.Escanear("Introduzca su segundo apellido:"));
		setSueldo(Integer.parseInt(Miscelaneo.Escanear("Introduzca su sueldo bruto anual:")));
		setFecha(Miscelaneo.Escanear("Introduzca su fecha de nacimiento en formato 'dd/mm/aaaa':"));
		setSalarioMensual();		
		if(validarFecha() == false)/*Preguntar si es mejor crear funcion pedirFecha para asi volver
		 automaticamente a introducir la fecha*/
		{
			System.out.println("Error, la fecha es incorrecta prueba introduciendo otra");
			
		}else {
			System.out.println("La fecha introducida es correcta");
		}
		
		if(calcularEdad() == false)
		{
			System.out.println("Lamentamos comunicarle que los menores de edad no pueden solicitar préstamos, disculpe las molestias.");
			System.exit(-1);
		}else {
			System.out.println("Eres mayor de edad");
		}
		
	}
	
	/**
	 * La funcion 'validarFecha()' se encarga de que el usuario introduzca una fecha valida y con el formato
	 * correcto '(dd/MM/yyyy)'.
	 * 
	 * @return Este metodo devuelve un valor booleano de verdadero si la fecha es valida o falso si no lo es.
	 */
	public boolean validarFecha()
	{
		try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(getFecha());
        } catch (ParseException e) {
            return false;
        }
        return true;
	}
	
	/**
	 * La funcion 'calcularEdad()' calcula la edad basandose en la fecha de nacimiento del usuario y la fecha actual
	 * y de comprobar si el usuario es mayor de edad o no.
	 * 
	 * @return Este metodo devuelve un valor booleano de verdadero si el usuario es mayor de edad o falso si no lo es.
	 */
	public boolean calcularEdad()
	{
		LocalDate fechaNac = LocalDate.parse(getFecha(), formato);
		LocalDate ahora = LocalDate.now();
		Period edad = Period.between(fechaNac, ahora);
		
		if(edad.getYears() < 18)
		{
			return false;
		}else {
			setEdad(edad.getYears());
			return true;
		}
		
	}
}
