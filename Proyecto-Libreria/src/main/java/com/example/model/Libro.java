package com.example.model;

public class Libro {

		private int id;
		private String titulo;
		private String autor;
		private String editorial;
		private int anio;
		private String url_fotolibro;
		private String genero;
		
		public Libro(int i, String t, String a, String e, int anio, String u, String g){
			this.id = i;
			this.titulo = t;
			this.autor = a;
			this.editorial = e;
			this.anio = anio;
			this.url_fotolibro = u;
			this.genero = g;
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
		public String getGenero() {
			return genero;
		}

		public void setGenero(String genero) {
			this.genero = genero;
		}
	}