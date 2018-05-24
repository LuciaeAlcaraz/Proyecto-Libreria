package com.example.model;

public class Usuarios {

	private int id;
	private String nombre;
	private String contrasenia;
	private String url_imagen;
	
	public int getId() {
		return id;
	}
	
	public Usuarios(int i, String n, String c, String u){
		this.id = i;
		this.nombre = n;
		this.contrasenia = c;
		this.url_imagen = u;
	}

	
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
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
