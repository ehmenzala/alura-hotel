/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import be.Reservas;
import dao.HuespedesDao;
import dao.ReservasDao;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author shmen
 */
public class TEST {

    public static void main(String[] args) {
        HuespedesDao hdao = new HuespedesDao();
        System.out.println(hdao.buscarHuespedes(""));
        
        /*ReservasDao rdao = new ReservasDao();
        Calendar calendarioLocal = Calendar.getInstance();
        java.util.Date fechaActual = calendarioLocal.getTime();
        long fechaMilisegundos = fechaActual.getTime();
        Date fecha = new Date(fechaMilisegundos);
        
        Reservas re =  new Reservas(fecha, fecha, "2500", "Tarjeta de credito");
        
        String idReserva = rdao.insertarReservas(re);
        int idReservaINT =  Integer.parseInt(idReserva);
        
        System.out.println(idReservaINT);*/
        
        
    }

}
