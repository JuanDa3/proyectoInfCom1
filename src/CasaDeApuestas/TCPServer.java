package CasaDeApuestas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;

public class TCPServer {

	// ---------------------------------- SERVIDOR
	// -----------------------------------------

	public static final int PORT = 9999;

	private ServerSocket listener;
	private Socket serverSideSocket;

	int contador = 1;
	int contApuestas = 0;
	HashMap<String, Cuenta> hashMapCuentas = new HashMap<String, Cuenta>();
	int cuentasEnHashmap = 0;
	Boolean run = true;

	public TCPServer() {
		System.out.println("Servidor corriendo en el puerto:" + " " + PORT);
	}

	protected void init() {

		try {

			listener = new ServerSocket(PORT);

			while (true) {
				serverSideSocket = listener.accept();
				protocol(serverSideSocket);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// ---------------------------------- PROTOCOLO
	// -----------------------------------------

	private static PrintWriter toNetwork;
	private static BufferedReader fromNetwork;

	private void protocol(Socket serverSideSocket) {

		createStreams(serverSideSocket);

		try {

			// Ciclo para mantener el servidor constantemente funcioniando

			do {
				// Variables utilizadas
				Cuenta datosUsuario = new Cuenta();
				String nombre;
				double saldoADepositar = 0;
				double saldoARetirar = 0;
				double saldoAnterior = 0;
				String message = fromNetwork.readLine();

				if (message.equals("SALIR")) {
					run = false;
				} else {

					if (message.contains("CARGAR")) {
						Casa casa = new Casa(0, 0);
						toNetwork.println("Carga completa");

					}

					// --------------------- CREAR CUENTA ----------------------------------

					if (message.contains("CREAR_CUENTA")) {
						nombre = message.split(",")[1];
						boolean verificarDatos = verificarLetras(nombre);
						if (verificarDatos == true) {
							crearCuenta(nombre, datosUsuario);
						} else {
							toNetwork.println("ERROR: Datos incosistentes");
						}
					}

					// --------------------- DEPOSITAR DINERO ------------------------------

					else if (message.contains("DEPOSITAR")) {
						if (message.length() <= 10) {
							toNetwork.println("ERROR: Datos insuficientes");
						} else if (message.contains(",")) {
							nombre = message.split(",")[1];
							Boolean esNumerico = isNumeric(nombre);
							if (esNumerico == true) {
								depositar(saldoAnterior, saldoADepositar, message, nombre);
							} else {
								toNetwork.println("ERROR: Datos incosistentes");
							}
						}
					}

				}
			} while (run);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------- MÉTODO CREAR CUENTA
	// ---------------------------------------

	private static boolean isNumeric(String valor) {
		try {
			Double.parseDouble(valor);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public boolean verificarLetras(String valor) {
		for (int x = 0; x < valor.length(); x++) {
			char c = valor.charAt(x);
			// Si no está entre a y z, ni entre A y Z, ni es un espacio
			if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
				return false;
			}
		}
		return true;
	}

	public void crearCuenta(String nombre, Cuenta datosUsuario) {

		// Condición para crear la primer cuenta (No tiene restricción)
		if (cuentasEnHashmap == 0) {
			Cuenta cuenta = new Cuenta(contador, nombre, 0, 0);
			String numCadena = String.valueOf(contador);// -1
			hashMapCuentas.put(numCadena, cuenta);
			cuentasEnHashmap++;
			datosUsuario = hashMapCuentas.get(numCadena);
			contador++;
			toNetwork.println("¡Cuenta creada con éxito!" + " " + datosUsuario.toString());
			System.out.println("[Server] From client se ha solicitado la creación de una cuenta" + " " + "|" + " "
					+ datosUsuario.toString());
		} else
			// En caso de que ya hayan cuentas creadas, debemos verificar que no hayan
			// nombres repetidos y el procedimiento será diferente
		{
			String nombreActual = "";
			String nombreRepetido = "";
			Set<String> cuentas = hashMapCuentas.keySet();
			// for-each para recorrer todas las cuentas
			for (String valor : cuentas) {
				Cuenta nombres = hashMapCuentas.get(valor);
				nombreActual = nombres.getNombreUsuario();
				// En caso de haber cuentas repetidas, no se podrá crear la cuenta
				if (nombreActual.equals(nombre)) {
					nombreRepetido = nombreActual;
				}
			}
			if (nombreRepetido.equals(nombre)) {
				toNetwork.println("ERROR: Nombre repetido, por favor, inténtelo de nuevo e ingrese otro nombre");
			} else {
				// En caso de que no hayan nombres repetidos, la cuenta se creará normalmente
				Cuenta cuenta = new Cuenta(contador, nombre, 0, 0);
				String numCadena = String.valueOf(contador);
				hashMapCuentas.put(numCadena, cuenta);
				cuentasEnHashmap++;
				datosUsuario = hashMapCuentas.get(numCadena);
				contador++;
				toNetwork.println("¡Cuenta creada con éxito!" + " " + datosUsuario.toString());
				System.out.println("[Server] From client se ha solicitado la creación de una cuenta" + " " + "|" + " "
						+ datosUsuario.toString());
			}
		}
	}

	// ---------------------------------- MÉTODO DEPOSITAR
	// ---------------------------------------

	private void depositar(double saldoAnterior, double saldoADepositar, String message, String valor) {
		Boolean existeCuenta = false;
		double nuevoSaldo = 0;
		int numeroEscritoPorElUsuario = Integer.parseInt(valor);
		int cuentaActual = 0;
		Boolean esNumerico = isNumeric(message.split(",")[2]);

		if (esNumerico == true) {
			saldoADepositar = Double.parseDouble(message.split(",")[2]);
			Set<String> cuentas = hashMapCuentas.keySet();
			Cuenta cuentaEncontrada = new Cuenta(); // Para usar el constructor sin apuestas
			// for-each para recorrer todas las cuentas
			for (String valores : cuentas) {
				Cuenta nombres = hashMapCuentas.get(valores);
				cuentaActual = nombres.getNumeroCuenta();
				if (cuentaActual == numeroEscritoPorElUsuario) {
					saldoAnterior = nombres.getSaldo();
					existeCuenta = true;
					cuentaEncontrada = hashMapCuentas.get(valores);
				}
			}
			if (cuentasEnHashmap == 0) {
				toNetwork.println("ERROR: Aún no hay cuentas registradas en el sistema, por favor cree una");
			} else {
				if (existeCuenta == true) {
					nuevoSaldo = cuentaEncontrada.getSaldo() + saldoADepositar;
					cuentaEncontrada.setSaldo(nuevoSaldo);
					System.out.println("[Server] From client se ha solicitado un deposito de:" + " " + saldoADepositar
							+ "$" + " " + "en la cuenta:" + " " + cuentaEncontrada.getNumeroCuenta() + " | "
							+ "saldo anterior:" + " " + saldoAnterior + " " + "nuevo saldo:" + " " + nuevoSaldo + " "
							+ "$");
					toNetwork.println("¡Deposito exitoso, se han depositado" + " " + saldoADepositar + "$" + " "
							+ "en la cuenta:" + " " + cuentaEncontrada.getNumeroCuenta() + " " + "saldo anterior:" + " "
							+ saldoAnterior + " " + "nuevo saldo:" + " " + nuevoSaldo + " " + "$");
				} else {
					toNetwork.println("ERROR: La cuenta ingresada no existe");
				}
			}
		} else {
			toNetwork.println("ERROR: Datos incosistentes");
		}

	}

	// ---------------------------------- MÉTODO PARA CREAR STREAMS
	// ---------------------------------------

	private static void createStreams(Socket socket) {

		try {

			toNetwork = new PrintWriter(socket.getOutputStream(), true);
			fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// ---------------------------------- MÉTODO MAIN
	// ---------------------------------------

	public static void main(String[] args) {

		TCPServer server = new TCPServer();
		server.init();

	}

}
