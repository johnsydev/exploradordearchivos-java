package vista;

import controlador.ExploradorController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;

public class ExploradorForm extends JFrame {

  //private final JTextField display;
  private final JTable tablaExplorador;
  private final JButton botonVolver;
  private final JPopupMenu menuOpciones;
  private final JMenuItem opcionEliminarPopup;
  private final JMenuItem opcionRenombrarPopup;
  private final DefaultTableModel model;
  

  public ExploradorForm() {
    // Configuración de la ventana principal
    setTitle("Explorador de archivos");
    setSize(1500, 900);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    /*
    display = new JTextField();
    display.setEditable(false);
    display.setFont(new Font("Arial", Font.PLAIN, 24));
    
        // Panel de display
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        add(display, BorderLayout.NORTH);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 5, 5));
        
        // Crear botones y agregarlos al panel
        String[] botones = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                            "+", "-", "*", "/", "^", "Neg", "(", ")", "Borrar", 
                            "CE", "%", "Calcular", "Cambiar Base", "Salir"};
        
        for (String texto : botones) {
            JButton boton = new JButton(texto);
            boton.setFont(new Font("Arial", Font.PLAIN, 18));
            boton.setActionCommand(texto); // Establece el comando de acción
            buttonPanel.add(boton);
        }

        add(buttonPanel, BorderLayout.CENTER);
     */
    
    model = new DefaultTableModel(30, 1) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    tablaExplorador = new JTable(model);
    tablaExplorador.setFont(new Font("Arial", Font.PLAIN, 16));
    tablaExplorador.setRowHeight(40);
    tablaExplorador.setValueAt("Nombre", 0, 0);
    add(tablaExplorador);
    
    JPanel panel = new JPanel();
    botonVolver = new JButton("Volver");
    panel.add(botonVolver);
    add(panel, BorderLayout.NORTH);
    
    JScrollPane tableScrollPane = new JScrollPane(tablaExplorador);
    add(tableScrollPane, BorderLayout.CENTER);
    //tablaExplorador.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    
    menuOpciones = new JPopupMenu();
    opcionEliminarPopup = new JMenuItem("Eliminar");
    opcionRenombrarPopup = new JMenuItem("Cambiar nombre");

    // Agregar las opciones al menú emergente
    menuOpciones.add(opcionEliminarPopup);
    menuOpciones.add(opcionRenombrarPopup);
      
    
    setVisible(true);
  }
    
  public JTable getTabla() {
    return tablaExplorador;
  }
  
  public DefaultTableModel getTablaModel() {
    return model;
  }
  
  public JButton getVolver() {
    return botonVolver;
  }
  
  public JPopupMenu getMenuOpcionesArchivo() {
    return menuOpciones;
  }
  
  public JMenuItem getOpcionEliminarPopup() {
    return opcionEliminarPopup;
  }
  
  public JMenuItem getOpcionRenombrarPopup() {
    return opcionRenombrarPopup;
  }
  
  public void limpiarTabla() {
    for (int i = 1; i<30; i++) {
      tablaExplorador.setValueAt("", i, 0);
    }
  }
  
  public void actualizarTabla(String[] nombres) {
    limpiarTabla();
    int indice = 1;
    for (String nombre : nombres) {
      tablaExplorador.setValueAt(nombre, indice, 0);
      indice++;
    }
  }
  
  /*
  // Método para obtener el display
  public JTextField getDisplay() {
    return display;
  }

  // Método para obtener los botones del panel
  public Component[] getBotones() {
    JPanel buttonPanel = (JPanel) getContentPane().getComponent(1);
    return buttonPanel.getComponents();
  }

  // Métodos para manipular el display
  public void actualizarDisplay(String texto) {
    display.setText(texto);
  }

  public void salir() {
    System.exit(0); // Cierra la aplicación
  }

  public String getDisplayText() {
    return display.getText();
  }

  public void mensajeEmergente(String mensaje, String titulo) {
    JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
  }
*/
}
