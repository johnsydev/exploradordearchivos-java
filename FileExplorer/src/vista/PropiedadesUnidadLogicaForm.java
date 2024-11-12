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
  private final JTextField espacioTotal;
  private final JTextField espacioUsado;
  private final JTextField espacioLibre;
  //private final JProgressBar barraEspacio;
  private final JTextField tipo;
  private final JTextField sistemaArchivos;

  public final double TAMANO_COLUMNA_1 = 0.3;
  public final double TAMANO_COLUMNA_2 = 1.5;

  public PropiedadesUnidadLogicaForm() {
    setTitle("Propiedades de Unidad");
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

    // Línea separadora
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

    // Fila 1: "Tipo"
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.weightx = TAMANO_COLUMNA_1;
    JLabel lblTipo = new JLabel("Tipo:");
    lblTipo.setBorder(sangria);
    panel.add(lblTipo, gbc);

    tipo = new JTextField("");
    tipo.setEditable(false);
    tipo.setFocusable(false);
    tipo.setBackground(Color.WHITE);
    tipo.setBorder(BorderFactory.createEmptyBorder());
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(tipo, gbc);

    gbc.gridx = 0;
    gbc.gridy = 6;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas  

    // Fila 2: "Sistema de Archivos"
    gbc.gridx = 0;
    gbc.gridy = 7;
    gbc.weightx = TAMANO_COLUMNA_1;
    JLabel lblSistemaDeArchivos = new JLabel("Sistema de Archivos:");
    lblSistemaDeArchivos.setBorder(sangria);
    panel.add(lblSistemaDeArchivos, gbc);

    sistemaArchivos = new JTextField("");
    sistemaArchivos.setEditable(false);
    sistemaArchivos.setFocusable(false);
    sistemaArchivos.setBackground(Color.WHITE);
    sistemaArchivos.setBorder(BorderFactory.createEmptyBorder());
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(sistemaArchivos, gbc);

    // Espacio
    gbc.gridx = 0;
    gbc.gridy = 8;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio adicional después de la línea debajo de "Nombre"

    // Línea separadora entre "Nombre" y "Tipo de Archivo"
    gbc.gridx = 0;
    gbc.gridy = 9;
    gbc.gridwidth = 2;
    JLabel line2 = new JLabel();
    line2.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
    panel.add(line2, gbc);

    // Espacio
    gbc.gridx = 0;
    gbc.gridy = 10;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas

    // Fila 3: "Espacio Usado"
    gbc.gridx = 0;
    gbc.gridy = 11;
    gbc.weightx = TAMANO_COLUMNA_1;
    JLabel lblEspacioUsado = new JLabel("Espacio Usado:");
    lblEspacioUsado.setBorder(sangria);
    panel.add(lblEspacioUsado, gbc);

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

    // Fila 4: "Espacio Disponible"
    gbc.gridx = 0;
    gbc.gridy = 13;
    gbc.weightx = TAMANO_COLUMNA_1;
    JLabel lblEspacioDisponible = new JLabel("Espacio disponible:");
    lblEspacioDisponible.setBorder(sangria);
    panel.add(lblEspacioDisponible, gbc);

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
    panel.add(new JLabel(" "), gbc); // Espacio adicional después de la línea debajo de "Nombre"

    // Línea separadora
    gbc.gridx = 0;
    gbc.gridy = 15;
    gbc.gridwidth = 2;
    JLabel line3 = new JLabel();
    line3.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
    panel.add(line3, gbc);

    // Espacio
    gbc.gridx = 0;
    gbc.gridy = 16;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas

    // Fila 5: "Capacidad"
    gbc.gridx = 0;
    gbc.gridy = 17;
    gbc.weightx = TAMANO_COLUMNA_1;
    JLabel lblEspacioTotal = new JLabel("Capacidad:");
    lblEspacioTotal.setBorder(sangria);
    panel.add(lblEspacioTotal, gbc);

    espacioTotal = new JTextField("");
    espacioTotal.setEditable(false);
    espacioTotal.setFocusable(false);
    espacioTotal.setBackground(Color.WHITE);
    espacioTotal.setBorder(BorderFactory.createEmptyBorder());
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(espacioTotal, gbc);

    add(panel, BorderLayout.NORTH);
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

  public void setProgresoUsado(int porcentaje) {
    //barraEspacio.setValue(porcentaje);
  }
  
  
  public static void main(String[] args) {
    UnidadLogica unidadC = new UnidadLogica("C:\\");
    System.out.println("Espacio Total: " + unidadC.getEspacioTotal());
    System.out.println("Espacio Libre: " + unidadC.getEspacioLibre());
    System.out.println("Espacio Usado: " + unidadC.getEspacioUsado());
    System.out.println("Porcentaje Usado: " + unidadC.getPorcentajeUsado() + "%");
}
  
}
