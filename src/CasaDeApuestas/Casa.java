package CasaDeApuestas;

public class Casa {

	int numeroCuenta;
	double saldo;	

	public Casa(int numeroCuenta, double saldo) {
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
	}
	
	public Casa() {

	}
	
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	@Override
	public String toString() {
		return "Número de Cuenta:"+" "+numeroCuenta+" "+"Saldo:"+" "+saldo;
	}	
	
}
