/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PropiedadesArchivoForm extends JFrame {

  public PropiedadesArchivoForm() {
    // Configuración de la ventana principal
    setTitle("Propiedades");
    setSize(350, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // Inicializar el modelo, solo se puede editar la fila seleccionada
    

    JPanel panel = new JPanel();
    /*
    botonVolver = new JButton("Volver");
    botonCrearDirectorio = new JButton("Crear directorio");
    panel.add(botonVolver);
    panel.add(botonCrearDirectorio);
    */
    JLabel label1 = new JLabel("test");
    panel.add(label1);
    
    
    add(panel, BorderLayout.NORTH);

    
    
    setVisible(true);
  }
}

