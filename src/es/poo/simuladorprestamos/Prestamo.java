package es.poo.simuladorprestamos;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/** La clase 'Prestamo' contiene todos los datos necesarios para crear un prestamo de una persona con sus cuotas y datos personales necesarios.*/
public class Prestamo {
	DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private Persona pers1;
	private TipoPrestamo tipoP;
	private Cuota cuot;
	
	
	private String fecha = formato.format(LocalDate.now());
	
	private int importeSol;
	private int plazoEnMes;
	private int tipoInteres;
	private double importeTotInteres;
	private double importeTot;
	private String fechaFinal;

	/** La siguiente funcion es un constructor para la clase 'Prestamo' donde coje tres paramteros:
	  `pers1`, `tipoP`, y `cuot`. Estos parametros son usados para instanciar las variables correspondientes
	 del objeto `Prestamo`. La palabra `this` es usada para referirse a la variable del objeto que se esta construyendo.*/
	public Prestamo(Persona pers1, TipoPrestamo tipoP,Cuota cuot)
	{
		this.pers1 = pers1;
		this.tipoP = tipoP;
		this.cuot = cuot;
	}
	
	/** Obtengo el valor del objeto tipo prestamo tipoP*/
	public TipoPrestamo getTipoP() {
		return tipoP;
	}
	
	/** Asigno el valor del objeto tipo Prestamo tipoP*/
	public void setTipo(TipoPrestamo tipoP) {
		this.tipoP = tipoP;
	}
	
	/** Obtengo el valor del importe solicitado*/
	public int getImporteSol() {
		return importeSol;
	}
	
	/** Asigno el valor del importe solicitado*/
	public void setImporteSol(int importeSol) {
		this.importeSol = importeSol;
	}
	
	/** Obtengo el valor del plazo en meses*/
	public int getPlazoEnMes() {
		return plazoEnMes;
	}
	
	/** Asigno el valor del plazo en meses*/
	public void setPlazoEnMes(int plazoEnMes) {
		this.plazoEnMes = plazoEnMes;
	}
	
	/** Obtengo el tipo de intereses*/
	public int getTipoInteres() {
		return tipoInteres;
	}
	
	/** Obtengo el importe total de intereses*/
	public double getImporteTotInteres() {
		return importeTotInteres;
	}
	
	/** Asigno el importe total de intereses*/
	public void setImporteTotInteres(double importeTotInteres) {
		this.importeTotInteres = importeTotInteres;
	}

	/** Asigno el importe total*/
	public void setImporteTot(double imTot) {
		this.importeTot = imTot;
	}

	/**Obtengo el importe total*/
	public double getImporteTot() {
		return importeTot;
	}
	
	/** Obtengo el objeto Persona Pers1*/
	public Persona getPers1() {
		return pers1;
	}
	
	/** Obtengo la fecha actual.*/
	public String getFecha() {
		return fecha;
	}
	
	/** Asigno el objeto Persona pers1*/
	public void setPers1(Persona pers1) {
		this.pers1 = pers1;
	}
	
	/** Obtengo la fecha de cuando acaba el prestamo*/
	public String getFechaFinal() {
		return fechaFinal;
	}
	
	/** Asigno el valor de la fecha de cuando va a acabar el prestamo*/
	public void setFechaFinal() {
		this.fechaFinal = formato.format(LocalDate.parse(fecha, formato).plusMonths(plazoEnMes));;
	}

	/** Obtengo el objeto Cuota cuot*/
	public Cuota getCuot() {
		return cuot;
	}

	/** Asigno el objeto Cuota cuot*/
	public void setCuot(Cuota cuot) {
		this.cuot = cuot;
	}

	/** La funcion 'calcularAmortizacion()' realiza todos los calculos necesarios para crear una cuota del prestamo escogido por el usuario
	 * comprobando tambien que el importe de la cuota no sobrepase el 40% del salario mensual del usuario, y crea un 'ArrayList' de objetos 'Cuota'
	 * con todas las cuotas pertenecientes al prestamo.
	 * 
	 * @return Este metodo devuelve un array con las cuotas correspondientes al prestamo creado por el usuario.*/
	public ArrayList<Cuota> calcularAmortizacion()
	{
		double interesMes = (getTipoP().getTipo()/12) * 0.01;
		getCuot().setImporteCuota( getImporteSol()*((interesMes*Math.pow((1 + interesMes), getPlazoEnMes()))/(Math.pow((1 + interesMes), getPlazoEnMes()) - 1)));
		ArrayList<Cuota >arrCuota = new ArrayList<Cuota>();
		getCuot().setCapitalPendiente(getImporteSol());
		double imInteres;
		double cPendiente = getCuot().getCapitalPendiente();
		double iAmortizado = getCuot().getImporteAmortizado();
		double imTot = getImporteSol();
		double imTotInteres = 0;
		if((getPers1().getSalarioMensual() * 0.4) < getCuot().getImporteCuota())
		{
			System.out.println("Lamentamos comunicarle que el importe de la cuota supera el 40% de su salario bruto mensual,"
				+ "no podemos concederle el prestamo");
			System.exit(0);
		}else
		{
			for(int i = 0; i < getPlazoEnMes(); i++)
			{
				
				imInteres = cPendiente * interesMes;
				iAmortizado = getCuot().getImporteCuota() - imInteres;
				cPendiente = cPendiente - iAmortizado;
				Cuota cuota1  = new Cuota();
				cuota1.setNumero(i + 1);
				cuota1.setImporteCuota(getCuot().getImporteCuota());
				cuota1.setImporteInteres(imInteres);
				cuota1.setImporteAmortizado(iAmortizado);
				cuota1.setCapitalPendiente(cPendiente);
				imTot = imInteres + imTot;
				arrCuota.add(cuota1);
				imTotInteres = imTotInteres + imInteres;
				setImporteTotInteres(imTotInteres);
				setImporteTot(imTot);
			}
			
		}		
		return arrCuota;
	}
	
	/** La funcion 'calcularComodidad()' calcula la comodiad con la que el usuario seria capaz de realizar los pagos de las cuotas del prestamo
	 * basandose en su sueldo mensual.
	 * 
	 * @return Esta funcion devuelve un tipo string dependiendo de como sea la comodidad de los pagos.*/
	public  String calcularComodidad()
	{
		String comodidad;
		if(getCuot().getImporteCuota() < (0.2 * getPers1().getSalarioMensual()))
		{
			comodidad = "Muy Buena";
		}else if(getCuot().getImporteCuota() < (0.3 * getPers1().getSalarioMensual()) || getCuot().getImporteCuota() >= (0.2 * getPers1().getSalarioMensual()))
		{
			comodidad = "Buena";
		}else
		{
			comodidad = "Regular";
		}
		return comodidad;
	}
	
	/**La funcin 'imprimirResumen()' imprime por pantalla la version resumida del prestamo realizado.*/
	public  void imprimirResumen()
	{
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Fecha de solicitud: " + getFecha());
		System.out.println("Solicitante: " + getPers1().imprimirNombreEdad());
		System.out.println("Importe solicitado: " + getImporteSol());
		System.out.println("Plazo de amortizacion es: " + (getPlazoEnMes()/12) + " anos y " + (getPlazoEnMes()%12) + " meses");
		System.out.println("\n" + imprimirElegido());
		System.out.printf("\nEl importe total en intereses es: %.2f%n", getImporteTotInteres());
		System.out.printf("El importe total del prestamo es: %.2f%n", getImporteTot());
		System.out.println("Comodidad: " + calcularComodidad());
		System.out.println("-----------------------------------------------------------------\n");
	}
	
	/** La funcion 'imprimirElegido()' imprime por pantalla el objeto 'TipoPrestamo' escogido por el usuario.
	 * 
	 * @return Este metodo devuelve un string con los datos del tipo de prestamo escogido por el usuario.*/
	public String imprimirElegido()
	{
		return "El prestamo escogido es:\n" + getTipoP().gettPrestam() + "\nTipo: " + getTipoP().getTipo() +
				"\nImporte Maximo: " + getTipoP().getImportMax() + "\nPlazo Maximo: " + getTipoP().getPlazoMax();
	}
	
	/** La funcion 'imprimirDetallado()' imprime el resumen detallado del prestamo realizado por pantalla utilizando las funciones:
	 * 'imprimirResumen()' y 'imprimirCuota()'.*/
	public void imprimirDetallado()
	{
		imprimirResumen();
		imprimirCuota();
	}
	
	/** La funcion 'imprimirCuota()' imprime por pantalla todas las cuotas realizadas del prestamo.*/
	public void imprimirCuota()
	{
		ArrayList<Cuota> arrCuota = calcularAmortizacion();
		for(int i = 0; i < arrCuota.size(); i++)
		{
			System.out.println(arrCuota.get(i).dameCuota());
		}
	}
	
	/** La funcion 'pedirImprimir()' pide al usuario que tipo de informe sobre el prestamo desea y dependiendo de su eleccion realiza uno o el otro*/
	public void pedirImprimir()
	{
		int elec = Integer.parseInt(Miscelaneo.Escanear("Que tipo de informe desea?:\n" + "(1) Resumido\n" + "(2) Detallado"));
		if(elec == 1)
		{
			imprimirResumen();
		}else if(elec == 2)
		{
			imprimirDetallado();
		}else
		{
			System.out.println("La opcion escogida no exista pruebe de nuevo");
			pedirImprimir();
		}
	}
	
	/**La funcion 'validarDatosPrestamo()' valida que los datos introducidos por el usuarios sean compatibles con el tipo de prestamo escogido.*/
	public  void validarDatosPrestamo()
	{
		if(getPlazoEnMes() > getTipoP().getPlazoMax())
		{
			System.out.println("Lo siento el plazo escogido excede el plazo maximo de este tipo de prestamo.");
			System.exit(-1);
		}else if(getImporteSol() > getTipoP().getImportMax())
		{
			System.out.println("Lo siento el importe solicitado excede el importe maximo de este tipo de prestamo.");
			System.exit(-1);
		}else 
		{
			System.out.println("Los datos introducidos son compatibles con este prestamo");
		}
	}

	/** La funcion 'aplicacionDescuento()' aplica una reduccion sobre el tipo de interes del prestamo basandose en la edad del usuario.*/
	public void aplicacionDescuento()
	{
		if(comprobarMenorTreinta(getPers1()) == true)
		{
			getTipoP().setTipo(getTipoP().getTipo() - ((getTipoP().getTipo())*0.1));
		}else if(comprobarMenorCuarenta(getPers1()) == true)
		{
			getTipoP().setTipo(getTipoP().getTipo() - ((getTipoP().getTipo())*0.05));
		}else {
			System.out.println("Lo sentimos no se te puede reducir el porcentaje");
		}
	}
	
	/**La funcion 'comprobarMenorTreinta()' comprueba si la persona es menor de 35 años
	 * 
	 * @return Este metodo devuelve un booleano falso si es mayor de 35 años y verdadero si es menor de 35 años.*/
	public boolean comprobarMenorTreinta(Persona pers)
	{
		if(pers.getEdad() >= 35)
		{
			return false;
		}
		return true;
	}
	
	/** La funcion 'comprobarMenorCuarenta' comprueba si la persona es menor de 45 años o mayor de 65 años.
	 * 
	 * @return Este metodo devuelve un valor booleano de verdadero si es menor de 45 o mayor de 65 y falso si no lo es.*/
	public static boolean comprobarMenorCuarenta(Persona pers)
	{
		if(pers.getEdad() <= 45 || pers.getEdad() > 65)
		{
			return true;
		}
		return false;
	}
	
	/** La funcion 'pedirDatosPrestamo()' pide al usuario por pantalla los datos: 'importeSol' y 'plazoEnMes' del prestamo que desea realizar.*/
	public void pedirDatosPrestamo()
	{
		setImporteSol(Integer.parseInt(Miscelaneo.Escanear("¿Cual es el importe que desea solicitar?\n")));
		setPlazoEnMes(Integer.parseInt(Miscelaneo.Escanear("¿Cual es el plazo en meses que desea para pagar el prestamo?\n")));
		validarDatosPrestamo();
		setFechaFinal();
		if(comprobarUltimaFecha() == true)
		{
			aplicacionDescuento();
		}else
		{
			System.out.println("Lamentamos comunicarle que la fecha de\r\n"
					+ "amortización del último pago supera la edad máxima de 75\r\n"
					+ "años y no podemos concederle el préstamo, disculpe las\r\n"
					+ "molestias.");
			System.exit(-1);
		}
		
	}
	
	/** La funcion 'comprobarUltimaFecha()' comprueba que el usuario no sea mayor de 75 años cuando termine de pagar el prestamo
	 * 
	 * @return Este metodo devuelve un valor booleano de falso si la persona es mayor de 75 años al terminar el prestamo y de verdadero si no lo es.*/
	private boolean comprobarUltimaFecha()
	{
		Period periodo = Period.between(LocalDate.now(), LocalDate.parse(getFechaFinal(), formato));
		if((periodo.getYears() + getPers1().getEdad()) >= 75)
		{	
			return false;
		}else {
			System.out.println("La fecha final del prestamo es antes de que usted cumpla 75 anos");
			return true;
		}
		
	}
		
}
