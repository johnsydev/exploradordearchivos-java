/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PropiedadesArchivoForm extends JFrame {
  
  JTextField nombre;
  JTextField extension;
  JTextField ubicacion;
  JTextField tamano;
  
  public final double TAMANO_COLUMNA_1 = 0.3;
  public final double TAMANO_COLUMNA_2 = 1.5;

  public PropiedadesArchivoForm() {
    // Configuración de la ventana principal
    setTitle("Propiedades");
    setSize(350, 600);
    
    setLayout(new BorderLayout());
    setResizable(false);
    
    Point mouse = MouseInfo.getPointerInfo().getLocation();
    setLocation(mouse);

    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    // Ajustamos el grid a 3 filas y 2 columnas
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // Columna 0 (títulos)
    JLabel nombreLabel = new JLabel("Nombre:");
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = TAMANO_COLUMNA_1;  // Controlamos el ancho de la primera columna
    panel.add(nombreLabel, gbc);

    nombre = new JTextField("");
    nombre.setEditable(false);
    nombre.setFocusable(false);
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;  // La segunda columna ocupa más espacio
    panel.add(nombre, gbc);

    JLabel extensionLabel = new JLabel("Extensión:");
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = TAMANO_COLUMNA_1;
    panel.add(extensionLabel, gbc);

    extension = new JTextField("");
    extension.setEditable(false);
    extension.setFocusable(false);
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(extension, gbc);

    JLabel ubicacionLabel = new JLabel("Ubicación:");
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = TAMANO_COLUMNA_1;
    panel.add(ubicacionLabel, gbc);

    ubicacion = new JTextField("");
    ubicacion.setEditable(false);
    ubicacion.setFocusable(false);
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(ubicacion, gbc);
    add(panel, BorderLayout.NORTH);
    
    JLabel tamanoLabel = new JLabel("Tamaño:");
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weightx = TAMANO_COLUMNA_1;
    panel.add(tamanoLabel, gbc);

    tamano = new JTextField("");
    tamano.setEditable(false);
    tamano.setFocusable(false);
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(tamano, gbc);
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
  
  public void setTamano(String pTamano) {
    tamano.setText(pTamano);
  }
}

