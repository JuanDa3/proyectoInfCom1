package CasaDeApuestas;

public class Apuesta {
	
	int numeroCuenta;
	String tipoApuesta;
	String numeroCualApostar;

	public Apuesta(int numeroCuenta, String tipoApuesta, String numeroCualApostar) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.tipoApuesta = tipoApuesta;
		this.numeroCualApostar = numeroCualApostar;
	}

	public int getNumeroCuenta() {
		return numeroCuenta;
	}
	
	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
	public String getTipoApuesta() {
		return tipoApuesta;
	}
	
	public void setTipoApuesta(String tipoApuesta) {
		this.tipoApuesta = tipoApuesta;
	}
	
	public String getNumeroCualApostar() {
		return numeroCualApostar;
	}
	
	public void setNumeroCualApostar(String numeroCualApostar) {
		this.numeroCualApostar = numeroCualApostar;
	}

	
	@Override
	public String toString() {
		return "Apostar [numeroCuenta=" + numeroCuenta + ", tipoApuesta=" + tipoApuesta + ", numeroCualApostar="
				+ numeroCualApostar + "]";
	}


	
}
