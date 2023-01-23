package clases;

import java.util.ArrayList;
import java.util.Scanner;

import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorArboles {
	Scanner teclado = new Scanner(System.in);

	public void run() throws ClassNotFoundException {

		Connection conexion = null;
		Statement st = null;
		ArrayList<Arbol> arboles = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://" + "localhost" + "/" + "eh_garden", "root", "");
			System.out.println("funciona");
			st = conexion.createStatement();
			
	

			final int INSERTAR_ARBOL = 1;
			final int ELIMINAR_ARBOL = 2;
			final int MODIFICAR_ARBOL = 3;
			final int VISUALIZAR_ARBOLES = 4;
			final int SALIR = 0;

			Scanner scan = new Scanner(System.in);
			int opcion_menu;

			do {
				System.out.println("------MENU-------");
				System.out.println(INSERTAR_ARBOL + ". insertar arbol");
				System.out.println(ELIMINAR_ARBOL + ". eliminar arbol");
				System.out.println(MODIFICAR_ARBOL + ". modificar arbol");
				System.out.println(VISUALIZAR_ARBOLES + ". visualizar arboles");
				System.out.println(SALIR + ". Salir");
				System.out.println("Elije una de las opciones");
				opcion_menu = Integer.parseInt(scan.nextLine());

				switch (opcion_menu) {
				case INSERTAR_ARBOL:
					System.out.println("Has elegido insertar arbol");

					Arbol arbol = new Arbol();

					System.out.println("Introduce el nombre común");
					String nombreComunIntro = teclado.nextLine();

					System.out.println("Introduce el nombre cientifico");
					String nombreCientificoIntro = teclado.nextLine();

					System.out.println("Introduce el habitat");
					String habitatIntro = teclado.nextLine();

					System.out.println("Introduce la altura");
					int alturaIntro = Integer.parseInt(teclado.nextLine());

					System.out.println("Introduce el origen");
					String origenIntro = teclado.nextLine();
					
					arbol.setNombreComun(nombreComunIntro);
					arbol.setNombreCientifico(nombreCientificoIntro);
					arbol.setHabitat(habitatIntro);
					arbol.setAltura(alturaIntro);
					arbol.setOrigen(origenIntro);
					/*INSERT INTO `arboles` (`id`, `nombre_comun`, `nombre_cientifico`, `habitat`, `altura`, `origen`) VALUES (NULL, '767676', 'turfu', 'ryr5', '5', 'gdsrhd');*/
					
					PreparedStatement pst = conexion.prepareStatement("INSERT INTO arboles (nombre_comun, nombre_cientifico, habitat, altura, origen) VALUES (?, ?, ?, ?, ?);");
					
					pst.setString(1, arbol.getNombreComun());
					pst.setString(2, arbol.getNombreCientifico());
					pst.setString(3, arbol.getHabitat());
					pst.setInt(4, arbol.getAltura());
					pst.setString(5, arbol.getOrigen());
					pst.execute();
					
					/*String sentenciaInsert = "INSERT INTO arboles (nombre_comun, nombre_cientifico, habitat, altura, origen) VALUES ('"+ nombreComunIntro +"',' "+nombreCientificoIntro +"','"+ habitatIntro+"'," + alturaIntro + ",'" + origenIntro + "');";
				st.execute(sentenciaInsert);*/
					break;
				case ELIMINAR_ARBOL:
					System.out.println("Has elegido eliminar arbol");
					System.out.println("Introduce la id del arbol que quieres eliminar");
					String id_eliminar = teclado.nextLine();
					String sentenciaBorro = "DELETE FROM arboles WHERE `arboles`.`id` = " + id_eliminar;
					st.execute(sentenciaBorro);
					break;
				case MODIFICAR_ARBOL:
					System.out.println("Has elegido modificar arbol");
					
					Arbol arbol2 = new Arbol();
					
					System.out.println("Introduce la ID del arbol a modificar");
					String id_modificar = teclado.nextLine();
					
					System.out.println("Introduce el nuevo nombre comun");
					String nombreComunIntro2 = teclado.nextLine();
					
					System.out.println("Introduce el nuevo nombre cientifico");
					String nombreCientificoIntro2 = teclado.nextLine();
					
					System.out.println("Introduce el nuevo habitat");
					String habitatIntro2 = teclado.nextLine();
					
					System.out.println("Introduce la nueva altura");
					int alturaIntro2 = Integer.parseInt(teclado.nextLine());
					
					System.out.println("Introduce el nuevo origen");
					String origenIntro2 = teclado.nextLine();
					
					arbol2.setNombreComun(nombreComunIntro2);
					arbol2.setNombreCientifico(nombreCientificoIntro2);
					arbol2.setHabitat(habitatIntro2);
					arbol2.setAltura(alturaIntro2);
					arbol2.setOrigen(origenIntro2);
					
					
					PreparedStatement pst2 = conexion.prepareStatement("UPDATE arboles SET nombre_comun = ?, nombre_cientifico = ?, habitat = ?, altura = ?, origen = ? WHERE id = ?");
					
					pst2.setString(1, arbol2.getNombreComun());
					pst2.setString(2, arbol2.getNombreCientifico());
					pst2.setString(3, arbol2.getHabitat());
					pst2.setInt(4, arbol2.getAltura());
					pst2.setString(5, arbol2.getOrigen());
					pst2.setString(6, id_modificar);
					pst2.executeUpdate();
					
					
					/*String sentenciaModificar = "UPDATE arboles SET nombre_comun = '" + nombreComunIntro2 + "', nombre_cientifico = '" + nombreCientificoIntro2 + "', habitat = '" + habitatIntro2 + "', altura = " + alturaIntro2 + ", origen = '" + origenIntro2 + "' WHERE id = " + id_modificar;
					
					st.executeUpdate(sentenciaModificar);*/
					break;
				case VISUALIZAR_ARBOLES:
					System.out.println("Has elegido visualizar arboles");
					ResultSet rSet = st.executeQuery("SELECT * FROM arboles");
					while (rSet.next()) {
						Arbol arbol3 = new Arbol();
						arbol3.setId(rSet.getInt("id"));
						arbol3.setNombreComun(rSet.getString("nombre_comun"));
						arbol3.setNombreCientifico(rSet.getString("nombre_cientifico"));
						arbol3.setHabitat(rSet.getString("habitat"));
						arbol3.setAltura(rSet.getInt("altura"));
						arbol3.setOrigen(rSet.getString("origen"));
						
						arboles.add(arbol3);
					}
					
					for (Arbol arbol3 : arboles) {
						System.out.println(arbol3.getId() + "," + arbol3.getNombreComun() + "," + arbol3.getNombreCientifico() + "," + arbol3.getHabitat() + "," + arbol3.getAltura() + "," + arbol3.getOrigen());
					}
					break;
				case SALIR:
					System.out.println("ADIOS");
					break;
				default:
					System.out.println("Opcion incorrecta!");
				}

			} while (opcion_menu != SALIR);
			scan.close();

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("no funciona");

		}
	}
}
