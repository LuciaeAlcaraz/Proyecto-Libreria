package com.example.model;

public class Comentarios {

	private int id_comentario;
	private int id_libros;
	private int id_usuario;
	private String comentario;
	
	public int getId_comentario() {
		return id_comentario;
	}
	
	public Comentarios(int ic, int il, int iu, String c){
		this.id_comentario = ic;
		this.id_libros = il;
		this.id_usuario = iu;
		this.comentario = c;
	}

	public int getId_libros() {
		return id_libros;
	}

	public void setId_libros(int id_libros) {
		this.id_libros = id_libros;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public void setId_comentario(int id_comentario) {
		this.id_comentario = id_comentario;
	}

	
	
}
