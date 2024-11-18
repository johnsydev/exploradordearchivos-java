package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author johns
 */
public class PropiedadesDirectorioForm extends JFrame {

  private final JTextField nombre;
  private final JTextField ubicacion;
  private final JTextField tamano;
  private final JLabel contenido;
  private final JTextField fechaCreacion;
  private final JTextField fechaModificacion;
  private final JTextField ultimoAcceso;
  private final JTextField atributos;

  public final double TAMANO_COLUMNA_1 = 0.2;
  public final double TAMANO_COLUMNA_2 = 1.2;

  public PropiedadesDirectorioForm() {
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
    
// Fila: "Contiene"
    gbc.gridx = 0;
    gbc.gridy = 13;
    gbc.gridwidth = 1;
    gbc.weightx = TAMANO_COLUMNA_1;
    JLabel labelContenido = new JLabel("Contiene:");
    labelContenido.setBorder(sangria);
    panel.add(labelContenido, gbc);

    contenido = new JLabel("");
    contenido.setFont(contenido.getFont().deriveFont(Font.PLAIN));
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(contenido, gbc);

// Espacio 
    gbc.gridx = 0;
    gbc.gridy = 14;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio antes de la línea

// Línea separadora
    gbc.gridx = 0;
    gbc.gridy = 15;
    gbc.gridwidth = 2;
    JLabel line2 = new JLabel();
    line2.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY)); // Línea gris claro
    panel.add(line2, gbc);

// Espacio 
    gbc.gridx = 0;
    gbc.gridy = 16;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas

// Fila 5: "Fecha de Creación"
    gbc.gridx = 0;
    gbc.gridy = 17;
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
    gbc.gridy = 18;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas

// Fila 6: "Fecha de Modificacion"
    gbc.gridx = 0;
    gbc.gridy = 19;
    gbc.gridwidth = 1;
    gbc.weightx = TAMANO_COLUMNA_1;
    JLabel lblFechaModificacion = new JLabel("Modificado:");
    lblFechaModificacion.setBorder(sangria);
    panel.add(lblFechaModificacion, gbc);

    fechaModificacion = new JTextField("");
    fechaModificacion.setEditable(false);
    fechaModificacion.setFocusable(false);
    fechaModificacion.setBackground(Color.WHITE);
    fechaModificacion.setBorder(BorderFactory.createEmptyBorder());
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(fechaModificacion, gbc);

    // Espacio 
    gbc.gridx = 0;
    gbc.gridy = 20;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas

    // Fila 7: "Ultimo Acceso"
    gbc.gridx = 0;
    gbc.gridy = 21;
    gbc.gridwidth = 1;
    gbc.weightx = TAMANO_COLUMNA_1;
    JLabel lblUltimoAcceso = new JLabel("Ultimo Acceso:");
    lblUltimoAcceso.setBorder(sangria);
    panel.add(lblUltimoAcceso, gbc);

    ultimoAcceso = new JTextField("");
    ultimoAcceso.setEditable(false);
    ultimoAcceso.setFocusable(false);
    ultimoAcceso.setBackground(Color.WHITE);
    ultimoAcceso.setBorder(BorderFactory.createEmptyBorder());
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(ultimoAcceso, gbc);

// Espacio 
    gbc.gridx = 0;
    gbc.gridy = 22;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio antes de la línea

// Línea separadora
    gbc.gridx = 0;
    gbc.gridy = 23;
    gbc.gridwidth = 2;
    JLabel line3 = new JLabel();
    line3.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY)); // Línea gris claro
    panel.add(line3, gbc);

// Espacio 
    gbc.gridx = 0;
    gbc.gridy = 24;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas

    // Fila 8: "Atributos"
    gbc.gridx = 0;
    gbc.gridy = 25;
    gbc.gridwidth = 1;
    gbc.weightx = TAMANO_COLUMNA_1;
    JLabel lbAtributos = new JLabel("Atributos");
    lbAtributos.setBorder(sangria);
    panel.add(lbAtributos, gbc);

    atributos = new JTextField("");
    atributos.setEditable(false);
    atributos.setFocusable(false);
    atributos.setBackground(Color.WHITE);
    atributos.setBorder(BorderFactory.createEmptyBorder());
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    panel.add(atributos, gbc);

    add(panel, BorderLayout.NORTH);
    setVisible(true);
  }

  public void setNombre(String pNombre) {
    nombre.setText(pNombre);
    setTitle("Propiedades de " + pNombre);
  }

  public void setUbicacion(String pUbicacion) {
    ubicacion.setText(pUbicacion);
  }

  public void setTamano(String pTamano) {
    tamano.setText(pTamano);
  }
  
  public void setContenido(int pDirectorios, int pArchivos) {
    String dirText = " directorios, ";
    if (pDirectorios == 1) {
      dirText = " directorio, ";
    }
    String archText = " archivos.";
    if (pArchivos == 1) {
      archText = " archivo.";
    } 
    contenido.setText(pDirectorios + dirText+ pArchivos + archText);
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
