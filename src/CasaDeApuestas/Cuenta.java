package CasaDeApuestas;

public class Cuenta {
	
	int numeroCuenta;
	String nombreUsuario;
	double saldo;
	int numeroApuestas;
	Apuesta apostar;

	public Cuenta(int numeroCuenta, String nombreUsuario, double saldo, Apuesta apostar, int numeroApuestas) {
		this.numeroCuenta = numeroCuenta;
		this.nombreUsuario = nombreUsuario;
		this.saldo = saldo;
		this.apostar = apostar;
		this.numeroApuestas = numeroApuestas;
	}
	
	public Cuenta(int numeroCuenta, String nombreUsuario, double saldo, int numeroApuestas) {
		this.numeroCuenta = numeroCuenta;
		this.nombreUsuario = nombreUsuario;
		this.saldo = saldo;
		this.numeroApuestas = numeroApuestas;
	}

	public Cuenta() {

	}

	public int getNumeroApuestas() {
		return numeroApuestas;
	}

	public void setNumeroApuestas(int numeroApuestas) {
		this.numeroApuestas = numeroApuestas;
	}

	public Apuesta getApostar() {
		return apostar;
	}

	public void setApostar(Apuesta apostar) {
		this.apostar = apostar;
	}

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Número de cuenta:"+" "+numeroCuenta+" "+"Propietario:" +" "+ nombreUsuario + " "+"Saldo:" +" "+ saldo;
	}

	
}
