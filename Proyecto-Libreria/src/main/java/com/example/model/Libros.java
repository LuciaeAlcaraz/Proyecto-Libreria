package com.example.model;

public class Libros {

		private int id;
		private String titulo;
		private String autor;
		private String editorial;
		private int anio;
		private String url_fotolibro;
		
		public Libros(int i, String t, String a, String e, int anio, String u){
			this.id = i;
			this.titulo = t;
			this.autor = a;
			this.editorial = e;
			this.anio = anio;
			this.url_fotolibro = u;
		}
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		public String getTitulo(){
			return this.titulo;
		}
		
		public void setTitulo(String nuevoTitulo){
			this.titulo = nuevoTitulo;
		}

		public String getAutor() {
			return autor;
		}

		public void setAutor(String autor) {
			this.autor = autor;
		}

		public String getEditorial() {
			return editorial;
		}

		public void setEditorial(String editorial) {
			this.editorial = editorial;
		}

		public int getAnio() {
			return anio;
		}

		public void setAnio(int anio) {
			this.anio = anio;
		}

		public String getUrl_fotolibro() {
			return url_fotolibro;
		}

		public void setUrl_fotolibro(String url_fotolibro) {
			this.url_fotolibro = url_fotolibro;
		}
		
	}


