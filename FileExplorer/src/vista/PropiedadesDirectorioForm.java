package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
* Formulario que muestra y permite editar las propiedades de un directorio.
* Implementa una interfaz gráfica detallada con información como nombre, ubicación,
* tamaño, contenido, fechas y atributos del directorio.
*
* @author johns
* @version 1.0
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
  private final JButton btnAceptar;
  public JCheckBox cbSoloLectura;
  public JCheckBox cbOculto;
  private boolean oculto;
  private boolean lectura;
  public final double TAMANO_COLUMNA_1 = 0.2;
  public final double TAMANO_COLUMNA_2 = 1.2;

  /**
    * Constructor que inicializa y configura la ventana de propiedades.
    * Crea una interfaz organizada con GridBagLayout que muestra:
    * - Información básica (nombre)
    * - Ubicación y tamaño
    * - Contenido (cantidad de archivos y directorios)
    * - Fechas (creación, modificación, último acceso)
    * - Atributos modificables (solo lectura, oculto)
    * La ventana aparece en la posición actual del cursor.
    */
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

    cbSoloLectura = new JCheckBox("Solo Lectura(solo para archivos de la carpeta)");
    cbSoloLectura.setBackground(Color.WHITE); // Fondo blanco
    cbSoloLectura.setBorder(BorderFactory.createEmptyBorder(10, 93, 10, 20)); // Espaciado interno
    cbSoloLectura.setForeground(Color.BLACK);
    cbSoloLectura.setFont(new Font("Dialog", Font.PLAIN, 11));
    cbSoloLectura.setSelected(lectura);
    gbc.gridx = 0; // Alineado debajo de "Atributos"
    gbc.gridy = 26;
    gbc.gridwidth = 2; // Opcional para ocupar todo el ancho de las columnas
    gbc.weightx = 0;
    panel.add(cbSoloLectura, gbc);

// Checkbox "Oculto"
    cbOculto = new JCheckBox("Oculto");
    cbOculto.setBackground(Color.WHITE); // Fondo blanco
    cbOculto.setBorder(BorderFactory.createEmptyBorder(10, 93, 10, 20)); // Espaciado interno
    cbOculto.setSelected(oculto);
    gbc.gridx = 0; // Alineado debajo del anterior checkbox
    gbc.gridy = 27;
    gbc.gridwidth = 2; // Opcional para ocupar todo el ancho de las columnas
    gbc.weightx = 0;
    panel.add(cbOculto, gbc);

    // Espacio 
    gbc.gridx = 0;
    gbc.gridy = 28;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas

    // Espacio 
    gbc.gridx = 0;
    gbc.gridy = 29;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas
    
    // Espacio 
    gbc.gridx = 0;
    gbc.gridy = 30;
    gbc.gridwidth = 2;
    panel.add(new JLabel(" "), gbc); // Espacio entre filas

    //Boton Aceptar
    btnAceptar = new JButton("Aceptar");

// Posicionar el botón en el grid
    gbc.gridy = 31; // Nueva fila después de los checkboxes
    gbc.gridwidth = 0; // El botón ocupa todo el ancho
    gbc.insets = new Insets(5, 130, 70, 5); // Opcional: Agregar márgenes
    gbc.gridx = 1;
    gbc.weightx = TAMANO_COLUMNA_2;
    gbc.anchor = GridBagConstraints.CENTER;
    panel.add(btnAceptar, gbc);

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
    contenido.setText(pDirectorios + dirText + pArchivos + archText);
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
  
  
  public boolean isSoloLectura() {
    return lectura;
  }

  public boolean isOculto() {
    return oculto;
  }

  public void setLectura(boolean lectura) {
    this.lectura = lectura;
    cbSoloLectura.setSelected(lectura);
  }

  public void setOculto(boolean oculto) {
    this.oculto = oculto;
    cbOculto.setSelected(oculto);
  }

  public JButton getBotonAceptar() {
    return btnAceptar;
  }
}
