package com.mintic.tiendafront.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = Producto.TABLE_NAME)
public class Producto {
	public static final String TABLE_NAME = "producto";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int idProveedor;
	private double ivaCompra;
	private String nombre;
	private double precioCompra;
	private double precioVenta;
	public Producto() {
		
	}
	public Producto(int id, int idProveedor, double ivaCompra, String nombre, double precioCompra, double precioVenta) {
		
		this.id = id;
		this.idProveedor = idProveedor;
		this.ivaCompra = ivaCompra;
		this.nombre = nombre;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	public double getIvaCompra() {
		return ivaCompra;
	}
	public void setIvaCompra(double ivaCompra) {
		this.ivaCompra = ivaCompra;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}
	public double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public static String getTableName() {
		return TABLE_NAME;
	}
	@Override
	public String toString() {
		return "Producto [id=" + id + ", idProveedor=" + idProveedor + ", ivaCompra=" + ivaCompra + ", nombre=" + nombre
				+ ", precioCompra=" + precioCompra + ", precioVenta=" + precioVenta + "]";
	}
	
	

}