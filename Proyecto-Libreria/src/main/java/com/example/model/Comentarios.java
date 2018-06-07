package com.example.model;

public class Comentarios {

	private int id_comentario;
	private int id_libro;
	private int id_usuario;
	private String comentario;
	
	public int getId_comentario() {
		return id_comentario;
	}
	
	public Comentarios(int ic, int il, int iu, String c){
		this.id_comentario = ic;
		this.id_libro = il;
		this.id_usuario = iu;
		this.comentario = c;
	}

	public int getId_libro() {
		return id_libro;
	}

	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
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
