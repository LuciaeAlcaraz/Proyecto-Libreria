package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Libros;

@Controller
public class LibrosController {

	@Autowired
	private Environment env;

	@GetMapping("/bienvenido")
	public String bienvenido() {
		return "saludo";
	}

	@GetMapping("/nuevoLibro")
	public String nuevoLibro() {
		return "ingresarLibro";
	}

	@PostMapping("/insertar-nuevoLibro")
	public String insertarUsuario(Model template, @RequestParam String titulo, @RequestParam String autor,
			@RequestParam String editorial, @RequestParam int anio, @RequestParam String sinopsis,
			@RequestParam String urlImagen, @RequestParam String genero) throws SQLException {
		if (titulo.length() == 0 || autor.length() == 0 || editorial.length() == 0) {
			template.addAttribute("mensaje", "Algunos campos necesarios no han sido completados");
			template.addAttribute("urlImagen", urlImagen);
			template.addAttribute("titulo", titulo);
			template.addAttribute("autor", autor);
			template.addAttribute("editorial", editorial);
			template.addAttribute("sinopsis", sinopsis);

			return "ingresarLibro";

		} else {
			Connection connection;
			connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"),
					env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));
			PreparedStatement consulta = connection.prepareStatement(
					"INSERT INTO libros(titulo, autor_a, editorial, anio, sinopsis, url_fotolibro, genero) VALUES(?, ?, ?, ?, ?, ?, ?);");
			consulta.setString(1, titulo);
			consulta.setString(2, autor);
			consulta.setString(3, editorial);
			consulta.setInt(4, anio);
			consulta.setString(5, sinopsis);
			consulta.setString(6, urlImagen);
			consulta.setString(7, genero);

			consulta.executeUpdate();

			connection.close();
		}
		return "redirect:/listadoDeLibros";
	}

	@GetMapping("/editarLibro/{id}")
	public String editar(Model template, @PathVariable int id) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"),
				env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM libros WHERE id_libros = ?;");

		consulta.setInt(1, id);

		ResultSet resultado = consulta.executeQuery();

		if (resultado.next()) {
			String urlImagen = resultado.getString("url_fotoLibro");
			String titulo = resultado.getString("titulo");
			String autor = resultado.getString("autor_a");
			String editorial = resultado.getString("editorial");
			int anio = resultado.getInt("anio");
			String sinopsis = resultado.getString("sinopsis");
			String genero = resultado.getString("genero");

			template.addAttribute("urlImagen", urlImagen);
			template.addAttribute("titulo", titulo);
			template.addAttribute("autor_a", autor);
			template.addAttribute("editorial", editorial);
			template.addAttribute("anio", anio);
			template.addAttribute("sinopsis", sinopsis);
			template.addAttribute("genero", genero);
		}

		return "editarLibro";
	}

	@PostMapping("/modificarLibro/{id}")
	public String editarUsuario(Model template, @PathVariable int id, @RequestParam String titulo,
			@RequestParam String autor, @RequestParam String editorial, @RequestParam int anio,
			@RequestParam String sinopsis, @RequestParam String urlImagen, @RequestParam String genero)
			throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"),
				env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement(
				"UPDATE libros SET titulo = ?, autor_a = ?, editorial = ?, anio = ?, sinopsis = ?, url_fotolibro = ?, genero = ? WHERE id_libros = ?;");

		consulta.setString(1, titulo);
		consulta.setString(2, autor);
		consulta.setString(3, editorial);
		consulta.setInt(4, anio);
		consulta.setString(5, sinopsis);
		consulta.setString(6, urlImagen);
		consulta.setString(7, genero);
		consulta.setInt(8, id);

		template.addAttribute("titulo", titulo);
		template.addAttribute("autor_a", autor);
		template.addAttribute("editorial", editorial);
		template.addAttribute("anio", anio);
		template.addAttribute("sinopsis", sinopsis);
		template.addAttribute("urlImagen", urlImagen);
		template.addAttribute("genero", genero);
		template.addAttribute("id", id);
		consulta.executeUpdate();

		return "redirect:/detalleLibro/" + id;
	}

	@GetMapping("/detalleLibro/{id}")
	public String detalle(Model template, @PathVariable int id) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"),
				env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM libros WHERE id_libros = ?;");

		consulta.setInt(1, id);

		ResultSet resultado = consulta.executeQuery();

		if (resultado.next()) {
			String urlImagen = resultado.getString("url_fotolibro");
			String titulo = resultado.getString("titulo");
			String autor = resultado.getString("autor_a");
			String editorial = resultado.getString("editorial");
			int anio = resultado.getInt("anio");
			String sinopsis = resultado.getString("sinopsis");
			String genero = resultado.getString("genero");

			template.addAttribute("urlImagen", urlImagen);
			template.addAttribute("titulo", titulo);
			template.addAttribute("autor_a", autor);
			template.addAttribute("editorial", editorial);
			template.addAttribute("anio", anio);
			template.addAttribute("sinopsis", sinopsis);
			template.addAttribute("genero", genero);
		}

		return "detalleLibro";
	}

	@GetMapping("/eliminarLibro/{id}")
	public String eliminar(@PathVariable int id) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"),
				env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement("DELETE FROM libros WHERE id_libros = ?;");
		consulta.setInt(1, id);

		consulta.executeUpdate();

		connection.close();
		return "redirect:/listadoDeLibros";
	}

	@GetMapping("/listadoDeLibros")
	public String listado(Model template) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"),
				env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM libros;");

		ResultSet resultado = consulta.executeQuery();

		ArrayList<Libros> listadoLibros = new ArrayList<Libros>();

		while (resultado.next()) {
			int id = resultado.getInt("id_libros");
			String urlImagen = resultado.getString("url_fotolibro");
			String titulo = resultado.getString("titulo");
			String autor = resultado.getString("autor_a");
			String editorial = resultado.getString("editorial");
			int anio = resultado.getInt("anio");
			String genero = resultado.getString("genero");

			Libros x = new Libros(id, titulo, autor, editorial, anio, urlImagen, genero);
			listadoLibros.add(x);
		}

		template.addAttribute("listadoLibros", listadoLibros);

		return "listadoLibros";
	}

	@GetMapping("/busqueda")
	public String busqueda() {
		return "listadoLibros";
	}

	@PostMapping("/resultado-busqueda/{busqueda}")
	public String resulBusqueda(Model template, @PathVariable String busqueda, @RequestParam String tituloBuscado,
			@RequestParam String autorBuscado) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"),
				env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));

		if (tituloBuscado.length() == 0) {
			busqueda = autorBuscado;
			template.addAttribute("autorBuscado", autorBuscado);

			PreparedStatement consulta = connection.prepareStatement("SELECT * FROM libros WHERE autor_a LIKE ?");
			consulta.setString(1, "%" + busqueda + "%");
			ResultSet resultado = consulta.executeQuery();
			ArrayList<Libros> listadoLibros = new ArrayList<Libros>();

			while (resultado.next()) {
				int id = resultado.getInt("id_libros");
				String urlImagen = resultado.getString("url_fotolibro");
				String titulo = resultado.getString("titulo");
				String autor = resultado.getString("autor_a");
				String editorial = resultado.getString("editorial");
				int anio = resultado.getInt("anio");
				String genero = resultado.getString("genero");

				Libros x = new Libros(id, titulo, autor, editorial, anio, urlImagen, genero);
				listadoLibros.add(x);
				template.addAttribute("listadoLibros", listadoLibros);
			}
			return "listadoLibros";
		} else {

			busqueda = tituloBuscado;
			template.addAttribute("tituloBuscado", tituloBuscado);

			PreparedStatement consulta = connection.prepareStatement("SELECT * FROM libros WHERE titulo LIKE ?");
			consulta.setString(1, "%" + busqueda + "%");
			ResultSet resultado = consulta.executeQuery();
			ArrayList<Libros> listadoLibros = new ArrayList<Libros>();

			while (resultado.next()) {
				int id = resultado.getInt("id_libros");
				String urlImagen = resultado.getString("url_fotolibro");
				String titulo = resultado.getString("titulo");
				String autor = resultado.getString("autor_a");
				String editorial = resultado.getString("editorial");
				int anio = resultado.getInt("anio");
				String genero = resultado.getString("genero");

				Libros x = new Libros(id, titulo, autor, editorial, anio, urlImagen, genero);
				listadoLibros.add(x);
				template.addAttribute("listadoLibros", listadoLibros);
			}
		}
		
		return "listadoLibros";
}
}