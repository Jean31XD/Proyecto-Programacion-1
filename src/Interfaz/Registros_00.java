package Interfaz;

import ConexionSql.ConexionMySQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Registros_00  extends JFrame {
    private JPanel panel1;
    private JTable table1;
    private JButton actualizarButton;
    private JButton button2;
    private JButton cerrarSesionButton;

    //Conexion
    Connection con = ConexionMySQL.ConectarBD();

    //Botones
    public void Registros_00() {
        mostrar();
        setSize(600, 400);
        setContentPane(panel1);
        setLocationRelativeTo(null);
        setVisible(true);

        table1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);


            }
        });
    }
    public Registros_00() {
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar();
            }
        });
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login l = new Login();
                l.Login();

            }
        });
    }

    //Metodos
    public void mostrar(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Apellidos");
        model.addColumn("Numero");
        model.addColumn("Correo");
        table1.setModel(model);
        String[] datos = new String[4];
        try {

            PreparedStatement ps =  con.prepareStatement("SELECT * FROM `datos de registro`");
            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                datos [0] = rs.getString(1);
                datos [1] = rs.getString(2);
                datos [2] = rs.getString(3);
                datos[3] = rs.getString(4);
                model.addRow(datos);
            }
            table1.setModel(model);

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Fallo");
        }

    }
    public void actualizar()  {
      int fila = table1.getSelectedRow();
      String Nombre = table1.getValueAt(fila,0).toString();
        String Apellidos = table1.getValueAt(fila,1).toString();
        String Numero = table1.getValueAt(fila,2).toString();
        String Correo = table1.getValueAt(fila,3).toString();
try {

        PreparedStatement ac =  con.prepareStatement("UPDATE `datos de registro` SET `Nombre`='"+Nombre+"',`Apellidos`='"+Apellidos+"',`Numero`='"+Numero+"',`Correo`='"+Correo+"' WHERE Nombre ='"+Nombre+"'");
        ac.executeUpdate();
        mostrar();
    }catch (SQLException e){ JOptionPane.showMessageDialog(null,"No se logro actualizar los datos");}

    }
    public void eliminar()  {
        int fila = table1.getSelectedRow();
        String Nombre = table1.getValueAt(fila,0).toString();
        try {
            PreparedStatement ac =  con.prepareStatement("DELETE FROM `datos de registro` WHERE `Nombre`='"+Nombre+"'");
            ac.executeUpdate();
            mostrar();
        }catch (SQLException e){ JOptionPane.showMessageDialog(null,"No se logro eliminar datos");}

    }


}
