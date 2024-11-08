/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PropiedadesArchivoForm extends JFrame {

  public PropiedadesArchivoForm() {
    // Configuración de la ventana principal
    setTitle("Propiedades");
    setSize(350, 500);
    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    //setLocationRelativeTo(null);
    Point mouse = MouseInfo.getPointerInfo().getLocation();
    setLocation(mouse);

    // Inicializar el modelo, solo se puede editar la fila seleccionada
    

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(2, 2));
    
    JLabel nombreLabel = new JLabel("Nombre:");
    JTextField nombre = new JTextField("test.txt");
    nombre.setEditable(false);
    nombre.setFocusable(false);
    
    JLabel extensionLabel = new JLabel("Extensión:");
    
    panel.add(nombreLabel);
    panel.add(nombre);
    panel.add(extensionLabel);
    
    /*
    botonVolver = new JButton("Volver");
    botonCrearDirectorio = new JButton("Crear directorio");
    panel.add(botonVolver);
    panel.add(botonCrearDirectorio);
    */
    //JLabel tituloVentana = new JLabel("Propiedades");
    //panel.add(tituloVentana);
    
    
    
    add(panel, BorderLayout.NORTH);

    
    
    setVisible(true);
  }
}

