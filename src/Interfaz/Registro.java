package Interfaz;

import ConexionSql.ConexionMySQL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static ConexionSql.ConexionMySQL.ConectarBD;


public class Registro extends JFrame {
    private JPanel panel1;
    private JTextField Nombre_usuario;
    private JLabel TextNombre;
    private JLabel TextRegistro;
    private JLabel Textapellido;
    private JTextField Apellido_usuario;
    private JLabel TextTelefono;
    private JTextField Telefono_usuario;
    private JTextField Correo_usuario;
    private JLabel TextContraseña;
    private JLabel Textconfirmar_contraseña;
    private JButton Registrar_boton;
    private JPasswordField Contraseña_usuario;
    private JPasswordField Contraseña_confirmar_usuario;
    private JLabel TEXTCorreo;
    //Conexion
    Connection con = ConexionMySQL.ConectarBD();
    //Metodo
    void Limpiar(){

        Nombre_usuario.setText("");
        Apellido_usuario.setText("");
        Telefono_usuario.setText("");
        Correo_usuario.setText("");
        Correo_usuario.setText("");
        Contraseña_usuario.setText("");
        Contraseña_confirmar_usuario.setText("");

    }

    //Botones

    public void Registro() {

        setSize(400,600);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Programa de registro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);

        Registrar_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Nombre = Nombre_usuario.getText();
                String Apellido = Apellido_usuario.getText();
                String Numero = Telefono_usuario.getText();
                String Correo = Correo_usuario.getText();
                String Contraseña = Contraseña_usuario.getText();
                String Confirmar_contraseña = Contraseña_confirmar_usuario.getText();

                if (!Contraseña.equals(Confirmar_contraseña)) {
                    JOptionPane.showMessageDialog(null, "Las contraseñas no son iguales", "intentalo de nuevo", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    if (Nombre.isEmpty() || Apellido.isEmpty() || Numero.isEmpty() || Correo.isEmpty() || Contraseña.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Debe ingresar su usuario y contraseña, si no está registrado debe registrarse", "", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            PreparedStatement stmt = con.prepareStatement("INSERT INTO `datos de registro` (`Nombre`, `Apellidos`, `Numero`, `Correo`, `Contraseña`, `Confirmar_contraseña`) VALUES ('" + Nombre + "', '" + Apellido + "', '" + Numero + "', '" + Correo + "', '" + Contraseña + "', '" + Confirmar_contraseña + "');");
                            stmt.executeUpdate();
                            Limpiar();
                            JOptionPane.showMessageDialog(null, "Registro exitoso");

                            Login l = new Login();
                            dispose();
                            l.Login();
                        } catch (SQLException exce) {
                            throw new RuntimeException(exce);
                              }

                    }
                    ;}
            }

        });
    }
    }






