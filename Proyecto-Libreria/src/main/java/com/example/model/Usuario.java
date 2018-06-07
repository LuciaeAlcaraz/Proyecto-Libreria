package com.example.model;

public class Usuario {

	private int id;
	private String nombre;
	private String contrasenia;
	private String url_imagen;
	

	
	public Usuario(int i, String n, String c, String u){
		this.id = i;
		this.nombre = n;
		this.contrasenia = c;
		this.url_imagen = u;
	}

	
	public int getId_usuario( ) {
		return this.id;
	}
	public void setId_usuario(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getNombre(String nombre) {
		return this.nombre = nombre; 	
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getUrl_imagen() {
		return url_imagen;
	}

	public void setUrl_imagen(String url_imagen) {
		this.url_imagen = url_imagen;
	}
	
}
