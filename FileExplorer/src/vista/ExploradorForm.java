package vista;
import controlador.ExploradorController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import javax.swing.*;
import java.awt.*;

public class ExploradorForm extends JFrame {
    private final JTextField display;
    private final JTable tablaExplorador;

    public ExploradorForm() {
        // Configuración de la ventana principal
        setTitle("Explorador de archivos");
        setSize(1500, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        /*
        // Panel de display
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        add(display, BorderLayout.NORTH);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 5, 5));
        
        // Crear botones y agregarlos al panel
        String[] botones = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                            "+", "-", "*", "/", "^", "Neg", "(", ")", "Borrar", 
                            "CE", "%", "Calcular", "Cambiar Base", "Salir"};
        
        for (String texto : botones) {
            JButton boton = new JButton(texto);
            boton.setFont(new Font("Arial", Font.PLAIN, 18));
            boton.setActionCommand(texto); // Establece el comando de acción
            buttonPanel.add(boton);
        }

        add(buttonPanel, BorderLayout.CENTER);
        */
        
        tablaExplorador = new JTable(10,1);
        tablaExplorador.setFont(new Font("Arial", Font.PLAIN, 16));
        tablaExplorador.setRowHeight(40);
        tablaExplorador.setValueAt("Nombre", 0, 0);
        add(tablaExplorador);
        
        
        setVisible(true);
    }
    
    public void actualizarTabla(String[] nombres) {
      int indice = 1;
      for(String nombre : nombres) {
        tablaExplorador.setValueAt(nombre, indice, 0);
        indice++;
      }
    }

    // Método para obtener el display
    public JTextField getDisplay() {
        return display;
    }

    // Método para obtener los botones del panel
    public Component[] getBotones() {
        JPanel buttonPanel = (JPanel) getContentPane().getComponent(1);
        return buttonPanel.getComponents();
    }

    // Métodos para manipular el display
    public void actualizarDisplay(String texto) {
        display.setText(texto);
    }
    
    public void salir() {
        System.exit(0); // Cierra la aplicación
    }

    public String getDisplayText() {
        return display.getText();
    }
    
    public void mensajeEmergente(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }    
}