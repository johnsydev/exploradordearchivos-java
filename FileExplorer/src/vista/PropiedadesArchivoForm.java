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
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PropiedadesArchivoForm extends JFrame {

  JTextField nombre;
  JTextField extension;
  JTextField ubicacion;
  JTextField tamano;
  JTextField fechaCreacion;

  public final double TAMANO_COLUMNA_1 = 0.3;
  public final double TAMANO_COLUMNA_2 = 1.2;
  private final JTextField tipo;

  public PropiedadesArchivoForm() {
    // Configuración de la ventana principal
    setTitle("Propiedades");
    setSize(350, 600);
    setLayout(new BorderLayout());
    setResizable(false);

    Point mouse = MouseInfo.getPointerInfo().getLocation();
    setLocation(mouse);

    // Cambiar el fondo de la ventana principal
    getContentPane().setBackground(java.awt.Color.WHITE);

    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());

    // Cambiar el fondo del panel
    panel.setBackground(java.awt.Color.WHITE);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // Fila 0: "Nombre"
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = TAMANO_COLUMNA_1;
    panel.add(new JLabel("Nombre:"), gbc);

    nombre = new JTextField("");
    nombre.setEditable(false);
    nombre.setFocusable(false);
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(nombre, gbc);

    // Fila 1: "Tipo de Archivo"
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = TAMANO_COLUMNA_1;
    panel.add(new JLabel("Tipo de Archivo:"), gbc);

    tipo = new JTextField("");
    tipo.setEditable(false);
    tipo.setFocusable(false);
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(tipo, gbc);

    // Espacio entre filas
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = 0;
    gbc.gridwidth = 2;
    gbc.weighty = 1;
    panel.add(Box.createVerticalStrut(20), gbc);  // Espacio entre las filas

    // Fila 2: "Extensión"
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weightx = TAMANO_COLUMNA_1;
    panel.add(new JLabel("Extensión:"), gbc);

    extension = new JTextField("");
    extension.setEditable(false);
    extension.setFocusable(false);
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(extension, gbc);

    // Fila 3: "Ubicación"
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.weightx = TAMANO_COLUMNA_1;
    panel.add(new JLabel("Ubicación:"), gbc);

    ubicacion = new JTextField("");
    ubicacion.setEditable(false);
    ubicacion.setFocusable(false);
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(ubicacion, gbc);

    // Fila 4: "Tamaño"
    gbc.gridx = 0;
    gbc.gridy = 6;
    gbc.weightx = TAMANO_COLUMNA_1;
    panel.add(new JLabel("Tamaño:"), gbc);

    tamano = new JTextField("");
    tamano.setEditable(false);
    tamano.setFocusable(false);
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(tamano, gbc);

    // Espacio entre filas
    gbc.gridx = 0;
    gbc.gridy = 7;
    gbc.weightx = 0;
    gbc.gridwidth = 2;
    gbc.weighty = 1;
    panel.add(Box.createVerticalStrut(20), gbc);  // Espacio entre las filas

    // Fila 5: "Fecha de Creación"
    gbc.gridx = 0;
    gbc.gridy = 8;
    gbc.weightx = TAMANO_COLUMNA_1;
    panel.add(new JLabel("Fecha de Creación:"), gbc);

    fechaCreacion = new JTextField("");
    fechaCreacion.setEditable(false);
    fechaCreacion.setFocusable(false);
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(fechaCreacion, gbc);

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

  public void setFechaCreacion(String pFechaCreacion) {
    tamano.setText(pFechaCreacion);
  }
}
