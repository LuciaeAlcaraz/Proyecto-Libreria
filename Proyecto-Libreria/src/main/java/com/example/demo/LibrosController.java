package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Comentarios;
import com.example.model.Libro;
import com.example.model.Usuario;

@Controller
public class LibrosController {
	
	
	@Autowired
	private Environment env;

	@Autowired
	private UsuarioHelper UsuarioHelper;
	
	@GetMapping("/")
	public String paginaPrincipal() {
		return "estructuraPrincipal";
	}

	@GetMapping("/nuevoLibro")
	public String nuevoLibro(HttpSession session) throws SQLException {
		Usuario logueado = UsuarioHelper.usuarioLogueado(session);
		
		if (logueado == null) {
			return "redirect:/login";
		}
		return "ingresarLibro";
	}

	@PostMapping("/insertar-nuevoLibro")
	public String insertarUsuario( Model template, @RequestParam String titulo, @RequestParam String autor,
			@RequestParam String editorial, @RequestParam int anio, @RequestParam String sinopsis,
			@RequestParam String urlImagen, @RequestParam String genero) throws SQLException {
		
		if (titulo.length() == 0 || autor.length() == 0) {
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
		if(editorial.length() != 0){
			
			consulta.setString(1, titulo);
			consulta.setString(2, autor);
			consulta.setString(3, editorial);
			consulta.setInt(4, anio);
			consulta.setString(5, sinopsis);
			consulta.setString(6, urlImagen);
			consulta.setString(7, genero);
			
		}else {
			editorial = "-";
			consulta.setString(1, titulo);
			consulta.setString(2, autor);
			consulta.setString(3, editorial);
			consulta.setInt(4, anio);
			consulta.setString(5, sinopsis);
			consulta.setString(6, urlImagen);
			consulta.setString(7, genero);
		}
			consulta.executeUpdate();

			connection.close();
		}
		return "redirect:/listadoDeLibros";
	}

	@GetMapping("/editarLibro/{id}")
	public String editar(HttpSession session, Model template, @PathVariable int id) throws SQLException {
			Usuario logueado = UsuarioHelper.usuarioLogueado(session);
			
			if (logueado == null) {
				return "redirect:/login";
			}else {
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"),
				env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM libros WHERE id_libro = ?;");

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
		
		connection.close();

		return "editarLibro";
	}
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
				"UPDATE libros SET titulo = ?, autor_a = ?, editorial = ?, anio = ?, sinopsis = ?, url_fotolibro = ?, genero = ? WHERE id_libro = ?;");

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

		connection.close();
		
		return "redirect:/detalleLibro/" + id;
	}

	@GetMapping("/detalleLibro/{id}")
	public String detalle(Model template, @PathVariable int id) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"),
				env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement(
				"SELECT * FROM libros WHERE id_libro = ?;");

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
		PreparedStatement consulta2 = connection.prepareStatement("SELECT * FROM comentarios WHERE id_libro = ?;");
		
		consulta2.setInt(1, id);
		ResultSet resultado2 = consulta2.executeQuery();
		
		ArrayList<Comentarios> listadoComentarios = new ArrayList<Comentarios>();

		while (resultado2.next()) {
			int id_comentario = resultado2.getInt("id_comentario");
			int id_libro = resultado2.getInt("id_libro");
			int id_usuario = resultado2.getInt("id_usuario");
			String comentario = resultado2.getString("comentario");

			Comentarios c = new Comentarios(id_comentario, id_libro , id_usuario, comentario);
			listadoComentarios.add(c);
		}
		
		template.addAttribute("listadoComentarios", listadoComentarios);
		
		connection.close();
		
		return "detalleLibro";
	}
	@GetMapping("/procesarComentario/{id}")
	public String procesarComentario(HttpSession session, Model template , @PathVariable int id,
			 @RequestParam String comentario) throws SQLException {
		
		Usuario logueado = UsuarioHelper.usuarioLogueado(session);
		if (logueado == null) {
			return "redirect:/login";
		}else {
		
		int id_usuario = logueado.getId_usuario();
		template.addAttribute("id_u", id_usuario);
		
		Connection connection;
			connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"),
					env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));
			
			PreparedStatement consulta = connection.prepareStatement("INSERT INTO comentarios (id_libro, id_usuario, comentario) VALUES ( ? , ?, ? );");
			consulta.setInt(1, id);
			consulta.setInt(2, id_usuario);				
			consulta.setString(3, comentario);
			
			template.addAttribute("comentario", comentario);
			
			consulta.executeUpdate();

			connection.close();
			return "redirect:/detalleLibro/" + id;
	}
 }
	@GetMapping("/eliminarLibro/{id}")
	public String eliminar(HttpSession session , @PathVariable int id) throws SQLException {
		
		Usuario logueado = UsuarioHelper.usuarioLogueado(session);
		
		if (logueado == null) {
			return "redirect:/login";
		}else {
		
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"),
				env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement("DELETE FROM libros WHERE id_libro = ?;");
		consulta.setInt(1, id);

		consulta.executeUpdate();

		connection.close();
		return "redirect:/listadoDeLibros";
	}
 }
	@GetMapping("/listadoDeLibros")
	public String listado(Model template) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"),
				env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM libros;");

		ResultSet resultado = consulta.executeQuery();

		ArrayList<Libro> listadoLibros = new ArrayList<Libro>();

		while (resultado.next()) {
			int id = resultado.getInt("id_libro");
			String urlImagen = resultado.getString("url_fotolibro");
			String titulo = resultado.getString("titulo");
			String autor = resultado.getString("autor_a");
			String editorial = resultado.getString("editorial");
			int anio = resultado.getInt("anio");
			String genero = resultado.getString("genero");

			Libro x = new Libro(id, titulo, autor, editorial, anio, urlImagen, genero);
			listadoLibros.add(x);
		}

		template.addAttribute("listadoLibros", listadoLibros);

		connection.close();
		
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
			ArrayList<Libro> listadoLibros = new ArrayList<Libro>();

			while (resultado.next()) {
				int id = resultado.getInt("id_libro");
				String urlImagen = resultado.getString("url_fotolibro");
				String titulo = resultado.getString("titulo");
				String autor = resultado.getString("autor_a");
				String editorial = resultado.getString("editorial");
				int anio = resultado.getInt("anio");
				String genero = resultado.getString("genero");

				Libro x = new Libro(id, titulo, autor, editorial, anio, urlImagen, genero);
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
			ArrayList<Libro> listadoLibros = new ArrayList<Libro>();

			while (resultado.next()) {
				int id = resultado.getInt("id_libro");
				String urlImagen = resultado.getString("url_fotolibro");
				String titulo = resultado.getString("titulo");
				String autor = resultado.getString("autor_a");
				String editorial = resultado.getString("editorial");
				int anio = resultado.getInt("anio");
				String genero = resultado.getString("genero");

				Libro x = new Libro(id, titulo, autor, editorial, anio, urlImagen, genero);
				listadoLibros.add(x);
				template.addAttribute("listadoLibros", listadoLibros);
			}
		}
		connection.close();
		
		return "listadoLibros";
	}
	
	@GetMapping("/agregarFavorito/{id}")
	public String agregarFavorito(HttpSession session, Model template , @PathVariable int id) throws SQLException {
		
		Usuario logueado = UsuarioHelper.usuarioLogueado(session);
		
		if (logueado == null) {
			return "redirect:/login";
		}else {
		
		int id_usuario = logueado.getId_usuario();
		template.addAttribute("id_u", id_usuario);
		
		Connection connection;
			connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"),
					env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));
			
			PreparedStatement consulta = connection.prepareStatement("INSERT INTO favoritos (id_libro, id_usuario) VALUES ( ?, ? );");
			consulta.setInt(1, id);
			consulta.setInt(2, id_usuario);				

			consulta.executeUpdate();

			connection.close();
			return "redirect:/listadoDeFavoritos/" + id_usuario;
	}
 }		
	@GetMapping("/listadoDeFavoritos/{id_usuario}")
	public String listadoFavoritos(HttpSession session, Model template) throws SQLException {
		
		Usuario logueado = UsuarioHelper.usuarioLogueado(session);
		
		if (logueado == null) {
		
			return "redirect:/login";
		}else {
			
			int id_usuario = logueado.getId_usuario();
			template.addAttribute("id_u", id_usuario);
		Connection connection;
			connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"),
					env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));
			
			PreparedStatement consulta = connection.prepareStatement(
					"SELECT * FROM favoritos, libros WHERE favoritos.id_usuario = ? AND favoritos.id_libro = libros.id_libro");
						
			consulta.setInt(1, id_usuario);
			
			ResultSet resultado = consulta.executeQuery();
			ArrayList<Libro> listadoLibros = new ArrayList<Libro>();

			while (resultado.next()) {
				int id = resultado.getInt("id_libro");
				String urlImagen = resultado.getString("url_fotolibro");
				String titulo = resultado.getString("titulo");
				String autor = resultado.getString("autor_a");
				String editorial = resultado.getString("editorial");
				int anio = resultado.getInt("anio");
				String genero = resultado.getString("genero");

				Libro x = new Libro(id, titulo, autor, editorial, anio, urlImagen, genero);
				listadoLibros.add(x);
			}

			template.addAttribute("listadoLibros", listadoLibros);
			
			connection.close();
		
			return "listadoFavoritos";
	}
 }
}