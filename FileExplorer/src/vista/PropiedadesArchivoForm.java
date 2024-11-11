/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class PropiedadesArchivoForm extends JFrame {

  JTextField nombre;
  JTextField extension;
  JTextField ubicacion;
  JTextField tamano;
  JTextField fechaCreacion;
  JTextField fechaModificacion;
  JTextField ultimoAcceso;

  public final double TAMANO_COLUMNA_1 = 0.2;
  public final double TAMANO_COLUMNA_2 = 1.2;
  private final JTextField tipo;

  public PropiedadesArchivoForm() {
    // Configuración de la ventana principal
    setTitle("Propiedades");
    setSize(370, 540);
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

    Border sangria = BorderFactory.createEmptyBorder(0, 10, 0, 0); // Aumenté la sangría a 6 para mayor separación

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio antes de la fila 0

// Fila 0: "Nombre"
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = TAMANO_COLUMNA_1;
    JLabel lblNombre = new JLabel("Nombre:");
    lblNombre.setBorder(sangria);
    panel.add(lblNombre, gbc);

    nombre = new JTextField("");
    nombre.setEditable(false);
    nombre.setFocusable(false);
    nombre.setBackground(Color.WHITE);
    nombre.setBorder(BorderFactory.createEmptyBorder());    
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(nombre, gbc);

// Espacio
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio adicional después de la línea debajo de "Nombre"

// Línea separadora entre "Nombre" y "Tipo de Archivo"
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    JLabel line1 = new JLabel();
    line1.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
    panel.add(line1, gbc);

// Espacio
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas

// Fila 1: "Tipo de Archivo"
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    gbc.weightx = TAMANO_COLUMNA_1;
    JLabel lblTipo = new JLabel("Tipo de Archivo:");
    lblTipo.setBorder(sangria);
    panel.add(lblTipo, gbc);

    tipo = new JTextField("");
    tipo.setEditable(false);
    tipo.setFocusable(false);
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(tipo, gbc);

// Espacio 
    gbc.gridx = 0;
    gbc.gridy = 6;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas

// Fila 2: "Extensión"
    gbc.gridx = 0;
    gbc.gridy = 7;
    gbc.gridwidth = 1;
    gbc.weightx = TAMANO_COLUMNA_1;
    JLabel lblExtension = new JLabel("Extensión:");
    lblExtension.setBorder(sangria);
    panel.add(lblExtension, gbc);

    extension = new JTextField("");
    extension.setEditable(false);
    extension.setFocusable(false);
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(extension, gbc);

// Espacio 
    gbc.gridx = 0;
    gbc.gridy = 8;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas

// Fila 3: "Ubicación"
    gbc.gridx = 0;
    gbc.gridy = 9;
    gbc.gridwidth = 1;
    gbc.weightx = TAMANO_COLUMNA_1;
    JLabel lblUbicacion = new JLabel("Ubicación:");
    lblUbicacion.setBorder(sangria);
    panel.add(lblUbicacion, gbc);

    ubicacion = new JTextField("");
    ubicacion.setEditable(false);
    ubicacion.setFocusable(false);
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(ubicacion, gbc);

// Espacio
    gbc.gridx = 0;
    gbc.gridy = 10;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas

// Fila 4: "Tamaño"
    gbc.gridx = 0;
    gbc.gridy = 11;
    gbc.gridwidth = 1;
    gbc.weightx = TAMANO_COLUMNA_1;
    JLabel lblTamano = new JLabel("Tamaño:");
    lblTamano.setBorder(sangria);
    panel.add(lblTamano, gbc);

    tamano = new JTextField("");
    tamano.setEditable(false);
    tamano.setFocusable(false);
    tamano.setBackground(Color.WHITE);
    tamano.setBorder(BorderFactory.createEmptyBorder());    
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(tamano, gbc);

// Espacio 
    gbc.gridx = 0;
    gbc.gridy = 12;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio antes de la línea

// Línea separadora
    gbc.gridx = 0;
    gbc.gridy = 13;
    gbc.gridwidth = 2;
    JLabel line2 = new JLabel();
    line2.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY)); // Línea gris claro
    panel.add(line2, gbc);

// Espacio 
    gbc.gridx = 0;
    gbc.gridy = 14;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas

// Fila 5: "Fecha de Creación"
    gbc.gridx = 0;
    gbc.gridy = 15;
    gbc.gridwidth = 1;
    gbc.weightx = TAMANO_COLUMNA_1;
    JLabel lblFechaCreacion = new JLabel("Creado:");
    lblFechaCreacion.setBorder(sangria);
    panel.add(lblFechaCreacion, gbc);

    fechaCreacion = new JTextField("");
    fechaCreacion.setEditable(false);
    fechaCreacion.setFocusable(false);
    fechaCreacion.setBackground(Color.WHITE);
    fechaCreacion.setBorder(BorderFactory.createEmptyBorder());
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(fechaCreacion, gbc);

// Espacio 
    gbc.gridx = 0;
    gbc.gridy = 16;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas

    add(panel, BorderLayout.NORTH);
    setVisible(true);
    
// Fila 6: "Fecha de Modificacion"
    gbc.gridx = 0;
    gbc.gridy = 17;
    gbc.gridwidth = 1;
    gbc.weightx = TAMANO_COLUMNA_1;
    JLabel lblFechaModificacion = new JLabel("Modificado:");
    lblFechaModificacion.setBorder(sangria);
    panel.add(lblFechaModificacion, gbc);

    fechaModificacion = new JTextField("");
    fechaModificacion.setEditable(false);
    fechaModificacion.setFocusable(false);
    fechaModificacion.setFocusable(false);
    fechaModificacion.setBackground(Color.WHITE);
    fechaModificacion.setBorder(BorderFactory.createEmptyBorder());
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(fechaModificacion, gbc);
    
    // Espacio 
    gbc.gridx = 0;
    gbc.gridy = 18;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas

    add(panel, BorderLayout.NORTH);
    setVisible(true);
    
  // Fila 7: "Ultimo Acceso"
    gbc.gridx = 0;
    gbc.gridy = 19;
    gbc.gridwidth = 1;
    gbc.weightx = TAMANO_COLUMNA_1;
    JLabel lblUltimoAcceso = new JLabel("Ultimo Acceso:");
    lblUltimoAcceso.setBorder(sangria);
    panel.add(lblUltimoAcceso, gbc);

    ultimoAcceso = new JTextField("");
    ultimoAcceso.setEditable(false);
    ultimoAcceso.setFocusable(false);
    ultimoAcceso.setFocusable(false);
    ultimoAcceso.setBackground(Color.WHITE);
    ultimoAcceso.setBorder(BorderFactory.createEmptyBorder());
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(ultimoAcceso, gbc);
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
    fechaCreacion.setText(pFechaCreacion);
  }

  public void setUltimaModificacion(String pFechaModificacion) {
    fechaModificacion.setText(pFechaModificacion);
  }

  public void setUltimoAcceso(String pUltimoAcceso) {
    ultimoAcceso.setText(pUltimoAcceso);
  }
}
