package es.aulamentor;

import java.io.Serializable;

public class Prestamo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String isbn;
	private String autor;
	private String titulo;
	private String socio;
	private String paginas;
	private String fecha;
	
	public Prestamo(){
		
	}
	
	public Prestamo(String isbn, String autor, String titulo, String socio, String paginas, String fecha){
		this.isbn = isbn;
		this.autor = autor;
		this.titulo = titulo;
		this.socio = socio;
		this.paginas = paginas;
		this.fecha = fecha;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSocio() {
		return socio;
	}

	public void setSocio(String socio) {
		this.socio = socio;
	}

	public String getPaginas() {
		return paginas;
	}

	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
