package CasaDeApuestas;

public class Apostar {

	int numeroCuenta;
	char tipoApuesta;
	int numeroCualApostar;
	Cuenta cuenta;

	public Apostar(int numeroCuenta, char tipoApuesta, int numeroCualApostar, Cuenta cuenta) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.tipoApuesta = tipoApuesta;
		this.numeroCualApostar = numeroCualApostar;
		this.cuenta = cuenta;
	}
	
	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public char getTipoApuesta() {
		return tipoApuesta;
	}

	public void setTipoApuesta(char tipoApuesta) {
		this.tipoApuesta = tipoApuesta;
	}

	public int getNumeroCualApostar() {
		return numeroCualApostar;
	}

	public void setNumeroCualApostar(int numeroCualApostar) {
		this.numeroCualApostar = numeroCualApostar;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	@Override
	public String toString() {
		return "Apostar [numeroCuenta=" + numeroCuenta + ", tipoApuesta=" + tipoApuesta + ", numeroCualApostar="
				+ numeroCualApostar + ", cuenta=" + cuenta + "]";
	}

}
