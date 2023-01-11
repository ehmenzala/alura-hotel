/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import be.Huespedes;
import be.Reservas;
import com.sun.java.accessibility.util.AWTEventMonitor;
import dao.HuespedesDao;
import dao.ReservasDao;
import dto.AlmacenDto;
import dto.BooleanDto;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHuespedes;
    private JTable tbReservas;
    private DefaultTableModel modelo;
    private DefaultTableModel modeloH;
    private JLabel labelAtras;
    private JLabel labelExit;
    int xMouse, yMouse;

    ReservasDao resDao = new ReservasDao();
    HuespedesDao hueDao = new HuespedesDao();

    //RESERVAS
    int id = 0;
    Date fechEnt = null;
    Date fechSal = null;
    String valRev = null;
    String formPed = null;

    //HUESPEDES
    int idH = 0;
    String nombreH = null;
    String apellidoH = null;
    Date fechNacH = null;
    String nacionalidadH = null;
    int telefonoH = 0;
    int idReservaH = 0;

    int tabListener = 0;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Busqueda frame = new Busqueda();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Busqueda() {

        setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
        lblNewLabel_4.setForeground(new Color(12, 138, 199));
        lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblNewLabel_4.setBounds(331, 62, 280, 42);
        contentPane.add(lblNewLabel_4);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);

        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), tbReservas, null);
        modelo = (DefaultTableModel) tbReservas.getModel();
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Fecha Check In");
        modelo.addColumn("Fecha Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pago");

        tbHuespedes = new JTable();
        tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), tbHuespedes, null);
        modeloH = (DefaultTableModel) tbHuespedes.getModel();
        modeloH.addColumn("Numero de Huesped");
        modeloH.addColumn("Nombre");
        modeloH.addColumn("Apellido");
        modeloH.addColumn("Fecha de Nacimiento");
        modeloH.addColumn("Nacionalidad");
        modeloH.addColumn("Telefono");
        modeloH.addColumn("Numero de Reserva");

        //INDEX INICIAL
        tabListener = panel.getSelectedIndex();
        System.out.println(tabListener);

        panel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //INDEX CAMBIANTE
                tabListener = panel.getSelectedIndex();
                if (tabListener == 0) {
                    System.out.println(tabListener);
                } else {
                    System.out.println(tabListener);
                }
            }
        });

        iniciarControlesReservasYHuespedes();

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);

            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel btnbuscar = new JPanel();

        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel btnEditar = new JPanel();
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        //BOTON EDITAR
        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (tabListener == 0) {
                    boolean edicion = true;
                    System.out.println("Editar RESERVA");

                    System.out.println(id + " - " + fechEnt + " - " + fechSal
                            + " - " + valRev + " - " + formPed);

                    AlmacenDto.removeData();
                    AlmacenDto.addNewData(id, fechEnt, fechSal, valRev, formPed);
                    BooleanDto.removeData();
                    BooleanDto.addNewData(edicion);

                    ReservasView reservas = new ReservasView();
                    reservas.setVisible(true);
                    dispose();
                } else {
                    //aca va lo de HUESPEDES
                    boolean edicion = true;
                    System.out.println("Editar HUESPED");

                    System.out.println(idH + " - " + nombreH + " - " + apellidoH
                            + " - " + fechNacH + " - " + nacionalidadH + " - "
                            + telefonoH + " - " + idReservaH);

                    AlmacenDto.removeData();
                    AlmacenDto.addNewData(idH, nombreH, apellidoH, fechNacH, nacionalidadH, telefonoH, idReservaH);
                    BooleanDto.removeData();
                    BooleanDto.addNewData(edicion);

                    RegistroHuesped huespedes = new RegistroHuesped();
                    huespedes.setVisible(true);
                    dispose();
                }

            }
        });

        JPanel btnEliminar = new JPanel();
        btnEliminar.setLayout(null);
        btnEliminar.setBackground(new Color(12, 138, 199));
        btnEliminar.setBounds(767, 508, 122, 35);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEliminar);

        JLabel lblEliminar = new JLabel("ELIMINAR");
        lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEliminar.setForeground(Color.WHITE);
        lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEliminar.setBounds(0, 0, 122, 35);
        btnEliminar.add(lblEliminar);
        setResizable(false);

        //BOTON ELIMINAR
        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (tabListener == 0) {
                    //ACA VA LO DE RESERVAS
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar al reserva?");

                    if (opcion == 0) {
                        Reservas res = new Reservas(id, fechEnt, fechSal, valRev, formPed);
                        resDao.eliminarReserva(res);
                        modelo.removeRow(tbReservas.getSelectedRow());
                        
                        iniciarControlesReservasYHuespedes();
                    } else {

                    }
                } else {
                    //ACA VA LO DE HUESPEDES
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar al huésped?");

                    if (opcion == 0) {
                        Huespedes hue = new Huespedes(idH, nombreH, apellidoH, fechNacH, nacionalidadH, telefonoH, idReservaH);
                        hueDao.eliminarHuesped(hue);
                        modeloH.removeRow(tbHuespedes.getSelectedRow());
                        
                        iniciarControlesReservasYHuespedes();
                    } else {

                    }
                }

            }
        });

        tbReservas.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {

                if (!event.getValueIsAdjusting() && (tbReservas.getSelectedRow() >= 0)) {
                    int filaSeleccionada = tbReservas.getSelectedRow();
                    //Reservas reservaL = (Reservas) tbReservas.getValueAt(filaSeleccionada, 0);

                    id = (int) tbReservas.getValueAt(filaSeleccionada, 0);
                    fechEnt = (Date) tbReservas.getValueAt(filaSeleccionada, 1);
                    fechSal = (Date) tbReservas.getValueAt(filaSeleccionada, 2);
                    valRev = (String) tbReservas.getValueAt(filaSeleccionada, 3);
                    formPed = (String) tbReservas.getValueAt(filaSeleccionada, 4);

                }
            }
        }
        );

        tbHuespedes.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {

                if (!event.getValueIsAdjusting() && (tbHuespedes.getSelectedRow() >= 0)) {
                    int filaSeleccionada = tbHuespedes.getSelectedRow();
                    //Reservas reservaL = (Reservas) tbReservas.getValueAt(filaSeleccionada, 0);

                    idH = (int) tbHuespedes.getValueAt(filaSeleccionada, 0);
                    nombreH = (String) tbHuespedes.getValueAt(filaSeleccionada, 1);
                    apellidoH = (String) tbHuespedes.getValueAt(filaSeleccionada, 2);
                    fechNacH = (Date) tbHuespedes.getValueAt(filaSeleccionada, 3);
                    nacionalidadH = (String) tbHuespedes.getValueAt(filaSeleccionada, 4);
                    telefonoH = (int) tbHuespedes.getValueAt(filaSeleccionada, 5);
                    idReservaH = (int) tbHuespedes.getValueAt(filaSeleccionada, 6);

                }
            }
        }
        );

        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tabListener == 0) {
                    limpiarTablaReservas();
                    
                    System.out.println("Buscando Reservas");

                    int Ncol = tbReservas.getColumnCount();
                    
                    String buscarR = txtBuscar.getText();

                    System.out.println(Ncol);
                    //LISTAR RESERVAS
                    ArrayList<Reservas> listReservas = resDao.buscarReservas(buscarR);

                    modelo.setNumRows(listReservas.size());

                    for (int i = 0; i < listReservas.size(); i++) {
                        Reservas reservas = listReservas.get(i);

                        modelo.setValueAt(reservas.getId(), i, 0);
                        modelo.setValueAt(reservas.getFech_entrada(), i, 1);
                        modelo.setValueAt(reservas.getFech_salida(), i, 2);
                        modelo.setValueAt(reservas.getValor(), i, 3);
                        modelo.setValueAt(reservas.getFormaPago(), i, 4);
                    }

                    tbReservas.setModel(modelo);
                    
                } else {
                    limpiarTablaHuespedes();

                    System.out.println("Buscando Huespedes");

                    int Ncol2 = tbHuespedes.getColumnCount();

                    String buscarH = txtBuscar.getText();

                    System.out.println(Ncol2);
                    //LISTAR HUESPEDES
                    ArrayList<Huespedes> listHuespedesB = hueDao.buscarHuespedes(buscarH);

                    modeloH.setNumRows(listHuespedesB.size());

                    for (int i = 0; i < listHuespedesB.size(); i++) {
                        Huespedes huespedes = listHuespedesB.get(i);

                        modeloH.setValueAt(huespedes.getId(), i, 0);
                        modeloH.setValueAt(huespedes.getNombre(), i, 1);
                        modeloH.setValueAt(huespedes.getApellido(), i, 2);
                        modeloH.setValueAt(huespedes.getFech_nacimiento(), i, 3);
                        modeloH.setValueAt(huespedes.getNacionalidad(), i, 4);
                        modeloH.setValueAt(huespedes.getTelefono(), i, 5);
                        modeloH.setValueAt(huespedes.getIdReserva(), i, 6);
                    }

                    tbHuespedes.setModel(modeloH);
                }
            }
        });

    }

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

    private void iniciarControlesReservasYHuespedes() {
        limpiarTabla();

        int Ncol = tbReservas.getColumnCount();
        int Ncol2 = tbHuespedes.getColumnCount();

        System.out.println(Ncol);
        System.out.println(Ncol2);
        //LISTAR RESERVAS
        ArrayList<Reservas> listReservas = resDao.mostrarReservas();
        ArrayList<Huespedes> listHuespedes = hueDao.mostrarHuespedes();

        modelo.setNumRows(listReservas.size());
        modeloH.setNumRows(listHuespedes.size());

        for (int i = 0; i < listReservas.size(); i++) {
            //for (int j = 0; j < Ncol; j++) {
            Reservas reservas = listReservas.get(i);
            //dtmInven.setValueAt(productos, i, j);
            modelo.setValueAt(reservas.getId(), i, 0);
            modelo.setValueAt(reservas.getFech_entrada(), i, 1);
            modelo.setValueAt(reservas.getFech_salida(), i, 2);
            modelo.setValueAt(reservas.getValor(), i, 3);
            modelo.setValueAt(reservas.getFormaPago(), i, 4);
            //}
        }

        for (int i = 0; i < listHuespedes.size(); i++) {
            //for (int j = 0; j < Ncol; j++) {
            Huespedes huespedes = listHuespedes.get(i);
            //dtmInven.setValueAt(productos, i, j);
            modeloH.setValueAt(huespedes.getId(), i, 0);
            modeloH.setValueAt(huespedes.getNombre(), i, 1);
            modeloH.setValueAt(huespedes.getApellido(), i, 2);
            modeloH.setValueAt(huespedes.getFech_nacimiento(), i, 3);
            modeloH.setValueAt(huespedes.getNacionalidad(), i, 4);
            modeloH.setValueAt(huespedes.getTelefono(), i, 5);
            modeloH.setValueAt(huespedes.getIdReserva(), i, 6);
            //}
        }

        tbReservas.setModel(modelo);
        tbHuespedes.setModel(modeloH);

    }

    private void limpiarTabla() {
        int nFilas = modelo.getRowCount();//nro. de filas RESERVAS
        int nFilas2 = modeloH.getRowCount(); //nro. de filas HUESPEDES

        if (nFilas > 0) {
            for (int i = nFilas - 1; i >= 0; i--) {
                modelo.removeRow(i);
            }
        }

        if (nFilas2 > 0) {
            for (int i = nFilas2 - 1; i >= 0; i--) {
                modeloH.removeRow(i);
            }
        }

    }
    
    private void limpiarTablaReservas(){
        int nFilas = modelo.getRowCount();//nro. de filas RESERVAS

        if (nFilas > 0) {
            for (int i = nFilas - 1; i >= 0; i--) {
                modelo.removeRow(i);
            }
        }

    }
    
    private void limpiarTablaHuespedes(){
        int nFilas2 = modeloH.getRowCount(); //nro. de filas HUESPEDES

        if (nFilas2 > 0) {
            for (int i = nFilas2 - 1; i >= 0; i--) {
                modeloH.removeRow(i);
            }
        }
    }

}
