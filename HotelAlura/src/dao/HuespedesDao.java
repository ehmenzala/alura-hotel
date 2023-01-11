/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import be.Huespedes;
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
public class HuespedesDao {

    public HuespedesDao() {
    }

    //GET - MOSTRAR HUESPEDES
    public ArrayList<Huespedes> mostrarHuespedes() {
        ArrayList<Huespedes> ArrHuespedes = new ArrayList<>();

        Connection c = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM HUESPEDES";

        try {
            c = new bd().getOracle();
            pst = c.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Huespedes hue = new Huespedes(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7));

                ArrHuespedes.add(hue);
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
        return ArrHuespedes;
    }
    
    //POST - INSERTAR O GUARDAR HUESPEDES
    public void insertarHuespedes(Huespedes hue) {
        int r = 0;
        
        Connection c = null;
        PreparedStatement pst = null;

        String sql = "INSERT INTO HUESPEDES (nombre, apellido, fech_nacimiento, nacionalidad, telefono, idReserva) "
                + "VALUES (?,?,?,?,?,?)";

        try {
            c = new bd().getOracle();
            pst = c.prepareStatement(sql);

            pst.setString(1, hue.getNombre());
            pst.setString(2, hue.getApellido());
            pst.setDate(3, hue.getFech_nacimiento());
            pst.setString(4, hue.getNacionalidad());
            pst.setInt(5, hue.getTelefono());
            pst.setInt(6, hue.getIdReserva());

            r = pst.executeUpdate();

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
    
    public String mostrarValorSiguienteReserva() {

        String LAST_NUMBER = null;
        
        Connection c = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String sql = "select LAST_NUMBER from user_sequences where sequence_name = 'SQ_RESERVAS'";
        //String sql = "SELECT SQ_RESERVAS.CURRVAL FROM DUAL;";
        
        try {
            c = new bd().getOracle();
            
            pst = c.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                LAST_NUMBER = rs.getString("LAST_NUMBER");
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
        return LAST_NUMBER;
    }
    
    public void actualizarHuesped(Huespedes p) {
        int r = 0;
        Connection c = null;
        PreparedStatement pst = null;

        String sql = "UPDATE HUESPEDES SET NOMBRE = '"+ p.getNombre() +"', APELLIDO = '"+ p.getApellido() +"', FECH_NACIMIENTO = TO_DATE('"+p.getFech_nacimiento()+"','YYYY-MM-DD'),"
                + " NACIONALIDAD = '"+ p.getNacionalidad() +"', TELEFONO = '"+p.getTelefono()+"' WHERE ID='"+p.getId()+"'";
        
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
    
    public void eliminarHuesped(Huespedes p) {
        int r = 0;
        Connection c = null;
        PreparedStatement pst = null;

        String sql = "DELETE FROM HUESPEDES WHERE ID='"+p.getId()+"'";

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
    
    public ArrayList<Huespedes> buscarHuespedes(String pb) {
        ArrayList<Huespedes> ArrHuespedes = new ArrayList<>();

        Connection c = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String sql = "SELECT ID, NOMBRE, APELLIDO, FECH_NACIMIENTO, NACIONALIDAD, TELEFONO, IDRESERVA FROM HUESPEDES WHERE (ID) LIKE ('%" + pb + "%') OR UPPER(NOMBRE) LIKE UPPER('%" + pb + "%') OR UPPER(APELLIDO) LIKE UPPER('%" + pb + "%') OR (FECH_NACIMIENTO) LIKE ('%" + pb + "%')"
                + " OR UPPER(NACIONALIDAD) LIKE UPPER('%" + pb + "%') OR (TELEFONO) LIKE ('%" + pb + "%') OR (IDRESERVA) LIKE ('%" + pb + "%')";

        try {
            c = new bd().getOracle();
            pst = c.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Huespedes hue = new Huespedes(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7));

                ArrHuespedes.add(hue);
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
        return ArrHuespedes;
    }

}
