/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be;

import java.sql.Date;

/**
 *
 * @author shmen
 */
public class Huespedes {
    
    private int id;
    private String nombre;
    private String apellido;
    private Date fech_nacimiento;
    private String nacionalidad;
    private int telefono;
    private int idReserva;

    public Huespedes() {
    }

    public Huespedes(int id, String nombre, String apellido, Date fech_nacimiento, String nacionalidad, int telefono, int idReserva) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fech_nacimiento = fech_nacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.idReserva = idReserva;
    }
    
    public Huespedes(String nombre, String apellido, Date fech_nacimiento, String nacionalidad, int telefono, int idReserva) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fech_nacimiento = fech_nacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.idReserva = idReserva;
    }

    public int getId() {
        return id;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFech_nacimiento() {
        return fech_nacimiento;
    }

    public void setFech_nacimiento(Date fech_nacimiento) {
        this.fech_nacimiento = fech_nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    @Override
    public String toString() {
        return "Huespedes{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fech_nacimiento=" + fech_nacimiento + ", nacionalidad=" + nacionalidad + ", telefono=" + telefono + ", idReserva=" + idReserva + '}';
    }
    
    
    
}
