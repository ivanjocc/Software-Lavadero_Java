import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Register {
    private JPanel mainPanel;
    private JButton ingresarBtn;
    private JButton listaBtn;
    private JButton cerrarBtn;
    private JRadioButton pequenoRb;
    private JRadioButton medianoRb;
    private JRadioButton grandeRb;
    private JRadioButton sencilloRb;
    private JRadioButton completoRb;
    private JRadioButton siRb;
    private JRadioButton noRb;
    private JTextField siTxt;
    private JRadioButton otroRb;
    private JTextField placaTxt;
    private JTextField propietarioTxt;
    private JTextField telefonoTxt;
    private JTextField fechaTxt;
    private JTextField valorTxt;
    private JButton enviarBtn;
    private JPanel btnPanel;
    private JPanel infoPanel;
    private JPanel detallesPanel;
    private JPanel carroPanel;
    private JPanel tamanoPanel;
    private JPanel lavadoPanel;
    private JPanel adicionalPanel;

    public Register() {
//        Listener para enviar la info a la base de datos
        enviarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    enviarDatos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al enviar datos: " + ex.getMessage());
                }
            }
        });

//        Listener para cerrar al dar click al boton "Cerrar"
        cerrarBtn.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            if (frame != null) {
                frame.dispose();
            }
        });

//        Grupo de radiobutton, para que solo pueda seleccionar uno
        ButtonGroup grupoTamano = new ButtonGroup();
        grupoTamano.add(pequenoRb);
        grupoTamano.add(medianoRb);
        grupoTamano.add(grandeRb);
        grupoTamano.add(otroRb);

        ButtonGroup grupoLavado = new ButtonGroup();
        grupoLavado.add(sencilloRb);
        grupoLavado.add(completoRb);

        ButtonGroup grupoAdicional = new ButtonGroup();
        grupoAdicional.add(siRb);
        grupoAdicional.add(noRb);

        pequenoRb.setSelected(true);
        sencilloRb.setSelected(true);
        noRb.setSelected(true);

//        Fecha de hoy
        fechaTxt.setText(LocalDate.now().toString());
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void enviarDatos() throws SQLException {
        DatabaseHelper db = new DatabaseHelper();
        Connection conexion = db.conectarConDB();

        String placa = placaTxt.getText();
        String propietario = propietarioTxt.getText();
        int telefono = Integer.parseInt(telefonoTxt.getText());
        Date fecha = Date.valueOf(fechaTxt.getText());
        int valor = Integer.parseInt(valorTxt.getText());

        String tamano;
        if (pequenoRb.isSelected()) {
            tamano = "pequeño";
        } else if (medianoRb.isSelected()) {
            tamano = "mediano";
        } else if (grandeRb.isSelected()) {
            tamano = "grande";
        } else {
            tamano = "otro";
        }

        String tipoLavado = sencilloRb.isSelected() ? "sencillo" : "completo";

        String adicional;
        if (siRb.isSelected()) {
            adicional = siTxt.getText();
        } else {
            adicional = "no";
        }

        if (conexion != null) {
            insertarEnBaseDeDatos(conexion, placa, propietario, telefono, fecha, valor, tamano, tipoLavado, adicional);
        }
    }

    private void insertarEnBaseDeDatos(Connection conexion, String placa, String propietario, int telefono, Date fecha, int valor, String tamano, String tipoLavado, String adicional) throws SQLException {
        String sql = "INSERT INTO info (placa, propietario, telefono, fecha, valor, tamano, tipo_lavado, adicional) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, placa);
            statement.setString(2, propietario);
            statement.setInt(3, telefono);
            statement.setDate(4, fecha);
            statement.setInt(5, valor);
            statement.setString(6, tamano);
            statement.setString(7, tipoLavado);
            statement.setString(8, adicional);

            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(null, "Datos insertados correctamente.");
                resetFormulario();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    // Log o manejo del error de cierre
                }
            }
        }
    }

    private void resetFormulario() {
        placaTxt.setText("");
        propietarioTxt.setText("");
        telefonoTxt.setText("");
        fechaTxt.setText("");
        valorTxt.setText("");

        pequenoRb.setSelected(true);
        sencilloRb.setSelected(true);
        noRb.setSelected(true);

        siTxt.setText("");
    }
}
