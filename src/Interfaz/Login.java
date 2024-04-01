package Interfaz;

import ConexionSql.ConexionMySQL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static ConexionSql.ConexionMySQL.ConectarBD;

public class Login extends JFrame {
    private JTextField Login_usuario;
    private JLabel Textusuario;
    private JLabel Textcontraseña;
    private JPasswordField passwordField1;
    private JPanel Panel2;
    private JButton Login_iniciar;
    private JButton Registrarse;

    //Conexion
    Connection con = ConexionMySQL.ConectarBD();
//Botones
    public void Login() {


        setSize(400,400);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Login");
        setContentPane(Panel2);

        Login_iniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String Nombre = Login_usuario.getText();
                String Contraseña = passwordField1.getText();

                if (!Nombre.isEmpty() || !Contraseña.isEmpty()){

                    try {
                        PreparedStatement ps = con.prepareStatement("SELECT * FROM `datos de registro` WHERE `Nombre`= \"" + Nombre + "\" AND `Contraseña`= \"" + Contraseña + "\"; ");

                        ResultSet rs=ps.executeQuery();

                        if (rs.next()){
                            dispose();
                            JFrame fram = new Registros_00();
                            ((Registros_00) fram).Registros_00();


                        }else {
                            JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos");
                        }

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(Login.this,"fallo","Intentelo de nuevo",JOptionPane.ERROR_MESSAGE);
                    }


            } else { JOptionPane.showMessageDialog(null,"Favor rellenar los campos");
                }

            }

            }
        );
        Registrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Registro res = new Registro();
                res.Registro();
            }
        });
    }
}





