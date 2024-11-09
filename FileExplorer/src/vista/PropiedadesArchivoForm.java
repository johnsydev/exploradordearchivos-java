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
  
  JTextField nombre;
  JLabel extension;
  JLabel ubicacion;

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
    panel.setLayout(new GridLayout(3, 2));
    
    JLabel nombreLabel = new JLabel("Nombre:");
    nombre = new JTextField("");
    nombre.setEditable(false);
    nombre.setFocusable(false);
    
    JLabel extensionLabel = new JLabel("Extensión:");
    extension = new JLabel("");
    
    JLabel ubicacionLabel = new JLabel("Ubicación:");
    ubicacion = new JLabel("");
    
    panel.add(nombreLabel);
    panel.add(nombre);
    panel.add(extensionLabel);
    panel.add(extension);
    panel.add(ubicacionLabel);
    panel.add(ubicacion);
    
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
  
  public void setNombre(String pNombre) {
    nombre.setText(pNombre);
  }
  
  public void setExtension(String pExtension) {
    extension.setText(pExtension);
  }
  
  public void setUbicacion(String pUbicacion) {
    ubicacion.setText(pUbicacion);
  }
}

