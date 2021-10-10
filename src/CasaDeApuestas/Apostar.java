package CasaDeApuestas;

public class Apostar {
	
	int numeroCuenta;
	char tipoApuesta;
	int numeroCualApostar;

	public Apostar(int numeroCuenta, char tipoApuesta, int numeroCualApostar) {
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

	
	@Override
	public String toString() {
		return "Apostar [numeroCuenta=" + numeroCuenta + ", tipoApuesta=" + tipoApuesta + ", numeroCualApostar="
				+ numeroCualApostar + "]";
	}


	
}
