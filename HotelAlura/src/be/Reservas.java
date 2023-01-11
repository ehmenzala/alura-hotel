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
public class Reservas {
    
    private int id;
    private Date fech_entrada;
    private Date fech_salida;
    private String valor;
    private String formaPago;
    private boolean editar;

    public Reservas() {
    }

    public Reservas(int id, Date fech_entrada, Date fech_salida, String valor, String formaPago) {
        this.id = id;
        this.fech_entrada = fech_entrada;
        this.fech_salida = fech_salida;
        this.valor = valor;
        this.formaPago = formaPago;
    }
    
    public Reservas(Date fech_entrada, Date fech_salida, String valor, String formaPago) {
        this.fech_entrada = fech_entrada;
        this.fech_salida = fech_salida;
        this.valor = valor;
        this.formaPago = formaPago;
    }

    public Reservas(boolean editar, int id, Date fech_entrada, Date fech_salida, String valor, String formaPago) {
        this.editar = editar;
        this.id = id;
        this.fech_entrada = fech_entrada;
        this.fech_salida = fech_salida;
        this.valor = valor;
        this.formaPago = formaPago;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFech_entrada() {
        return fech_entrada;
    }

    public void setFech_entrada(Date fech_entrada) {
        this.fech_entrada = fech_entrada;
    }

    public Date getFech_salida() {
        return fech_salida;
    }

    public void setFech_salida(Date fech_salida) {
        this.fech_salida = fech_salida;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public boolean isEditar() {
        return editar;
    }

    @Override
    public String toString() {
        return "Reservas{" + "id=" + id + ", fech_entrada=" + fech_entrada + ", fech_salida=" + fech_salida + ", valor=" + valor + ", formaPago=" + formaPago + '}';
    }

    
    
    
    
}
