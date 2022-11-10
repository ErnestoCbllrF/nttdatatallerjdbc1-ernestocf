package com.nttdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Programa que ejecuta consulta jdbc tras establecer conexion con la BBDD
 * 
 * @author ernestocf
 *
 */
public class App {
	/**
	 * Metodo principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//Llamada a la funcion que ejecuta consulta en BBDD
		runQueryInfoTeams();
	}

	/**
	 * Metodo para establecer la conexión y dejarla abierta para su uso en la 
	 * ejecucion de la consulta.
	 * 
	 * @return
	 */
	private static Connection connectDatabase() {

		String url = "jdbc:mysql://localhost/nttdata_jdbc_ex";
		url += "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String usuario = "root";
		String password = "root";
		Connection conexion = null;
		try {
			//Comprobacíon de las credenciales para acceder a la BBDD y establecer la conexion
			conexion = DriverManager.getConnection(url, usuario, password);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Intento de establecer conexión finalizado");
		}
		if (conexion == null) {
			throw new NullPointerException("Datos incorrectos");
		}
		return conexion;

	}

	/**
	 * Metodo para la ejecución e impresión de la consulta
	 * 
	 */
	private static void runQueryInfoTeams() {
		Connection dbConection = connectDatabase();
		try (java.sql.Statement sentencia = dbConection.createStatement()) {
			// Declaracion de la consulta
			ResultSet resul = sentencia.executeQuery
					("SELECT id_soccer_team,name,stadium,stadium_capacity from nttdata_mysql_soccer_team");

			// Imprimir resultados
			while (resul.next()) {
				System.out.println(resul.getInt(1) + ", " + resul.getString(2) + ", " + resul.getString(3)+", "
						+ resul.getInt(4));
			}
			resul.close();
		} catch (SQLException ex) {
			System.out.println("Algun error se ha producido");
		} finally {
			System.out.println("Conexión Finalizada");
		}
	}

}
