/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import be.Reservas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author shmen
 */
public class ReservasDao {

    public ReservasDao() {
    }

    //GET - MOSTRAR RESERVAS
    public ArrayList<Reservas> mostrarReservas() {
        ArrayList<Reservas> ArrReservas = new ArrayList<>();

        Connection c = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM RESERVAS";

        try {
            c = new bd().getOracle();
            pst = c.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Reservas res = new Reservas(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getString(5));

                ArrReservas.add(res);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                    pst = null;
                }
                if (c != null) {
                    c.close();
                    c = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ArrReservas;
    }

    //POST - INSERTAR O GUARDAR RESERVAS
    public String insertarReservas(Reservas res) {
        int r = 0;

        String CURRVAL = null;
        Connection c = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String sql = "INSERT INTO RESERVAS (fech_entrada, fech_salida, valor, formaPago) "
                + "VALUES (?,?,?,?)";

        try {
            c = new bd().getOracle();
            pst = c.prepareStatement(sql);

            pst.setDate(1, res.getFech_entrada());
            pst.setDate(2, res.getFech_salida());
            pst.setString(3, res.getValor());
            pst.setString(4, res.getFormaPago());

            r = pst.executeUpdate();

            pst = c.prepareStatement("select SQ_RESERVAS.currval from DUAL");

            rs = pst.executeQuery();

            while (rs.next()) {
                CURRVAL = rs.getString("CURRVAL");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (pst != null) {
                    pst.close();
                    pst = null;
                }
                if (c != null) {
                    c.close();
                    c = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return CURRVAL;
    }

    public void actualizarReserva(Reservas p) {
        int r = 0;
        Connection c = null;
        PreparedStatement pst = null;

        String sql = "UPDATE RESERVAS SET FECH_ENTRADA= TO_DATE('"+p.getFech_entrada()+"','YYYY-MM-DD'),FECH_SALIDA= TO_DATE('"+p.getFech_salida()+"','YYYY-MM-DD')"
                + ",VALOR='"+p.getValor()+"',FORMAPAGO='"+p.getFormaPago()+"'"+ " WHERE ID='"+p.getId()+"'";

        try {
            c = new bd().getOracle();
            pst = c.prepareStatement(sql);
            r = pst.executeUpdate();
            
            if(r>0){
                JOptionPane.showMessageDialog(null, "Actualización realizada");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                    pst = null;
                }
                if (c != null) {
                    c.close();
                    c = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void eliminarReserva(Reservas p) {
        int r = 0;
        Connection c = null;
        PreparedStatement pst = null;

        String sql = "DELETE FROM RESERVAS WHERE ID='"+p.getId()+"'";

        try {
            c = new bd().getOracle();
            pst = c.prepareStatement(sql);
            r = pst.executeUpdate();

            if(r>0){
                JOptionPane.showMessageDialog(null, "Eliminación exitosa");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                    pst = null;
                }
                if (c != null) {
                    c.close();
                    c = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public ArrayList<Reservas> buscarReservas(String pb) {
        ArrayList<Reservas> ArrReservas = new ArrayList<>();

        Connection c = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM RESERVAS WHERE (ID) LIKE ('%" + pb + "%') OR (FECH_ENTRADA) LIKE ('%" + pb + "%') OR (FECH_SALIDA) LIKE ('%" + pb + "%') OR"
                + " (VALOR) LIKE ('%" + pb + "%') OR UPPER(FORMAPAGO) LIKE UPPER ('%" + pb + "%')";

        try {
            c = new bd().getOracle();
            pst = c.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Reservas res = new Reservas(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getString(5));

                ArrReservas.add(res);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                    pst = null;
                }
                if (c != null) {
                    c.close();
                    c = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ArrReservas;
    }

}
