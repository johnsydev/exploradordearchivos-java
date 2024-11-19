/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.Border;
import logicadenegocios.UnidadLogica;

/**
 *
 * @author Keyne
 */
public class PropiedadesUnidadLogicaForm extends JFrame {

  private final JTextField nombre;
  private final JTextField tipo;
  private final JTextField espacioTotal;
  private final JTextField espacioUsado;
  private final JTextField espacioLibre;
  //private final JProgressBar barraEspacio;
  private final JTextField sistemaArchivos;
  private GraficoCircular grafico;

  public final double TAMANO_COLUMNA_1 = 0.4;
  public final double TAMANO_COLUMNA_2 = 1.2  ;

  public PropiedadesUnidadLogicaForm() {
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

    Border sangria = BorderFactory.createEmptyBorder(0, 10, 0, 0);
    Border sangria2 = BorderFactory.createEmptyBorder(0, 22, 0, 0);

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
    JLabel lblTipo = new JLabel("Tipo:");
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
    JLabel lblExtension = new JLabel("Sistema de archivos:");
    lblExtension.setBorder(sangria);
    panel.add(lblExtension, gbc);

    sistemaArchivos = new JTextField("");
    sistemaArchivos.setEditable(false);
    sistemaArchivos.setFocusable(false);
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(sistemaArchivos, gbc);

// Espacio 
    gbc.gridx = 0;
    gbc.gridy = 8;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas

// Línea separadora
    gbc.gridx = 0;
    gbc.gridy = 9;
    gbc.gridwidth = 2;
    JLabel line2 = new JLabel();
    line2.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY)); // Línea gris claro
    panel.add(line2, gbc);

// Espacio 
    gbc.gridx = 0;
    gbc.gridy = 10;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas

// Fila 5: "Fecha de Creación"
    gbc.gridx = 0;
    gbc.gridy = 11;
    gbc.gridwidth = 1;
    gbc.weightx = TAMANO_COLUMNA_1;
    JLabel lblFechaCreacion = new JLabel("Espacio Usado:");
    lblFechaCreacion.setBorder(sangria);
    panel.add(lblFechaCreacion, gbc);

    espacioUsado = new JTextField("");
    espacioUsado.setEditable(false);
    espacioUsado.setFocusable(false);
    espacioUsado.setBackground(Color.WHITE);
    espacioUsado.setBorder(BorderFactory.createEmptyBorder());
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(espacioUsado, gbc);

// Espacio 
    gbc.gridx = 0;
    gbc.gridy = 12;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas

// Fila 6: "Fecha de Modificacion"
    gbc.gridx = 0;
    gbc.gridy = 13;
    gbc.gridwidth = 1;
    gbc.weightx = TAMANO_COLUMNA_1;
    JLabel lblFechaModificacion = new JLabel("Espacio Disponible:");
    lblFechaModificacion.setBorder(sangria);
    panel.add(lblFechaModificacion, gbc);

    espacioLibre = new JTextField("");
    espacioLibre.setEditable(false);
    espacioLibre.setFocusable(false);
    espacioLibre.setBackground(Color.WHITE);
    espacioLibre.setBorder(BorderFactory.createEmptyBorder());
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(espacioLibre, gbc);

    // Espacio 
    gbc.gridx = 0;
    gbc.gridy = 14;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas

// Línea separadora
    gbc.gridx = 0;
    gbc.gridy = 15;
    gbc.gridwidth = 2;
    JLabel line3 = new JLabel();
    line3.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY)); // Línea gris claro
    panel.add(line3, gbc);

// Espacio 
    gbc.gridx = 0;
    gbc.gridy = 16;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas

    // Fila 8: "Atributos"
    gbc.gridx = 0;
    gbc.gridy = 17;
    gbc.gridwidth = 1;
    gbc.weightx = TAMANO_COLUMNA_1;
    JLabel lbAtributos = new JLabel("Capacidad");
    lbAtributos.setBorder(sangria2);
    panel.add(lbAtributos, gbc);

    espacioTotal = new JTextField("");
    espacioTotal.setEditable(false);
    espacioTotal.setFocusable(false);
    espacioTotal.setBackground(Color.WHITE);
    espacioTotal.setBorder(BorderFactory.createEmptyBorder());
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(espacioTotal, gbc);

    add(panel, BorderLayout.NORTH);
    
    grafico = new GraficoCircular(1, 1);
    add(grafico, BorderLayout.CENTER);

    setVisible(true);
  }

  public void setNombre(String pNombre) {
    nombre.setText(pNombre);
  }

  public void setTipo(String pTipo) {
    tipo.setText(pTipo);
  }

  public void setSistemaDeArchivos(String pSistemaDeArchivos) {
    sistemaArchivos.setText(pSistemaDeArchivos);
  }

  public void setEspacioTotal(String pEspacioTotal) {
    espacioTotal.setText(pEspacioTotal);
  }

  public void setEspacioUsado(String pEspacioUsado) {
    espacioUsado.setText(pEspacioUsado);
  }

  public void setEspacioLibre(String pEspacioLibre) {
    espacioLibre.setText(pEspacioLibre);
  }

  public void setProgresoUsado(double pTotal, double pUsado) {
    grafico.setDatos(pTotal, pUsado);
  }
  
}
