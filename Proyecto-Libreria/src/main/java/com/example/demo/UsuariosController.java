package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Usuario;

@Controller
public class UsuariosController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UsuarioHelper UsuarioHelper;

	@GetMapping("/paginaPrincipal")
	public String paginaPrincipal() {
		return "paginaPrincipal"; 
	}
	
	@GetMapping("/login")
	public String login() {
		return "login"; 
	}
	
	@PostMapping("/procesar-login")
	public String procesarLogin(HttpSession session, Model template, @RequestParam String contrasenia, @RequestParam String nombre)
			throws SQLException {
		boolean sePudo = UsuarioHelper.intentarLoguearse(session, nombre, contrasenia);
		if (sePudo) {
			return "redirect:/paginaPrincipal";
		} else {
			template.addAttribute("mensaje", "La información ingresada no es correcta");
			template.addAttribute("nombreDeUsuario", nombre);
			template.addAttribute("contrasenia", contrasenia);
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logOut(HttpSession session) throws SQLException {
		UsuarioHelper.cerrarSesion(session);
		return "redirect:/login";
	}	

	@GetMapping("/registrarUsuario")
	public String registrarUsuario() {
		return "usuarioRegistro";
	}

	@PostMapping("/nuevoUsuario")
	public String nuevoUsuario(Model template, @RequestParam String nombre, @RequestParam String contrasenia, @RequestParam String urlImagen)
			throws SQLException {

		if (nombre.length() == 0 || contrasenia.length() == 0) {
			template.addAttribute("mensaje", "Alguno de los campos necesarios no han sido completado");
			template.addAttribute("nombreDeUsuario", nombre);

			return "usuarioRegistro";

		} else {
			Connection connection;
			connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));
			PreparedStatement consulta = connection
					.prepareStatement("INSERT INTO usuarios(nombre, contrasenia, url_foto) VALUES(?, ?, ?);");
			consulta.setString(1, nombre);
			consulta.setString(2, contrasenia);
			consulta.setString(3, urlImagen);

			consulta.executeUpdate();

			connection.close();
		}
		return "redirect:/";
	}
	
	@GetMapping("/editarUsuario/{id}")
	public String editar(HttpSession session, Model template, @PathVariable int id) throws SQLException {
 
		Usuario logueado = UsuarioHelper.usuarioLogueado(session);
		if (logueado == null) {
			
			return "redirect:/login";
		}else {
			
			int idUsuario = logueado.getId_usuario();
			template.addAttribute("id", idUsuario);
			
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM usuarios WHERE id_usuario = ?;");

		consulta.setInt(1, idUsuario);

		ResultSet resultado = consulta.executeQuery();

		if (resultado.next()) {
			String urlImagen = resultado.getString("url_foto");
			String nombre = resultado.getString("nombre");
			String contrasenia = resultado.getString("contrasenia");
			
			template.addAttribute("urlImagen", urlImagen);
			template.addAttribute("nombreDeUsuario", nombre);
			template.addAttribute("contrasenia", contrasenia);
			
		}
		
		connection.close();
		
		return "usuarioEditar";
	}
}
	@PostMapping("/modificarUsuario/{id}")
	public String editarUsuario(Model template, @PathVariable int id, @RequestParam String nombre, @RequestParam String contrasenia, @RequestParam String urlImagen) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection
				.prepareStatement("UPDATE usuarios SET nombre = ?, contrasenia = ?, url_foto = ?  WHERE id_usuario = ?;");

		consulta.setString(1, nombre);
		consulta.setString(2, contrasenia);
		consulta.setString(3, urlImagen);
		consulta.setInt(4, id);
		
		template.addAttribute("nombreDeUsuario", nombre);
		template.addAttribute("contrasenia", contrasenia);
		template.addAttribute("urlImagen", urlImagen);
		
		consulta.executeUpdate();

		connection.close();
		
		return "redirect:PerfilUsuario/" + id;
	}

	@GetMapping("/PerfilUsuario/{id}")
	public String detalle(HttpSession session, Model template) throws SQLException {


		Usuario logueado = UsuarioHelper.usuarioLogueado(session);
		if (logueado == null) {
			
			return "redirect:/login";
		}else {
			
			int idUsuario = logueado.getId_usuario();
			template.addAttribute("id", idUsuario);
			
		
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM usuarios WHERE id_usuario = ?;");

		consulta.setInt(1, idUsuario);

		ResultSet resultado = consulta.executeQuery();

		if (resultado.next()) {
			String nombre = resultado.getString("nombre");
			String  contrasenia = resultado.getString("contrasenia");
			String  urlImagen = resultado.getString("url_foto");
			
			template.addAttribute("nombreDeUsuario", nombre);
			template.addAttribute("contrasenia", contrasenia);
			template.addAttribute("urlImagen", urlImagen);
		}

		connection.close();
		
		return "usuarioEnDetalle";
	}
}
	@GetMapping("/eliminarUsuario/{id}")
	public String eliminar(HttpSession session, Model template, @PathVariable int id) throws SQLException {

		Usuario logueado = UsuarioHelper.usuarioLogueado(session);
		if (logueado == null) {
			
			return "redirect:/login";
		}else {
			
			int idUsuario = logueado.getId_usuario();
			template.addAttribute("id", idUsuario);
		
		Connection connection;
		connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));

		PreparedStatement consulta = connection.prepareStatement("DELETE FROM usuarios WHERE id_usuario = ?;");
		consulta.setInt(1, idUsuario);

		consulta.executeUpdate();

		connection.close();
		return "redirect:/usuarioEliminado";
		}
	}
	@GetMapping("/usuarioEliminado")
	public String eliminado() {
		return "usuarioEliminado";
	}
}