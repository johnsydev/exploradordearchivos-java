package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import java.io.File;

public class ExploradorForm extends JFrame {

  private final JTable tablaExplorador;
  private final JButton botonVolver;
  private final JButton botonCrearDirectorio;
  private final JPopupMenu menuOpciones;
  private final JMenuItem opcionEliminarPopup;
  private final JMenuItem opcionRenombrarPopup;
  private final JMenuItem opcionCopiarPopup;
  private final JMenuItem opcionPegarPopup;
  private final JMenuItem opcionCortarPopup;
  private final DefaultTableModel model;

  private int filaSeleccionada = -1;

  public ExploradorForm() {
    // Configuración de la ventana principal
    setTitle("Explorador de archivos");
    setSize(1500, 900);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // Inicializar el modelo, solo se puede editar la fila seleccionada
    model = new DefaultTableModel(30, 1) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return row == filaSeleccionada;
      }
    };

    tablaExplorador = new JTable(model);
    tablaExplorador.setFont(new Font("Arial", Font.PLAIN, 16));
    tablaExplorador.setRowHeight(40);
    tablaExplorador.setValueAt("Nombre", 0, 0);

    JScrollPane tableScrollPane = new JScrollPane(tablaExplorador);
    add(tableScrollPane, BorderLayout.CENTER);

    JPanel panel = new JPanel();
    botonVolver = new JButton("Volver");
    botonCrearDirectorio = new JButton("Crear directorio");
    panel.add(botonVolver);
    panel.add(botonCrearDirectorio);
    add(panel, BorderLayout.NORTH);

    menuOpciones = new JPopupMenu();
    opcionEliminarPopup = new JMenuItem("Eliminar");
    opcionRenombrarPopup = new JMenuItem("Cambiar nombre");
    opcionCopiarPopup = new JMenuItem("Copiar");
    opcionPegarPopup = new JMenuItem("Pegar");
    opcionCortarPopup = new JMenuItem("Cortar");

    menuOpciones.add(opcionEliminarPopup);
    menuOpciones.add(opcionRenombrarPopup);
    menuOpciones.add(opcionCopiarPopup);
    menuOpciones.add(opcionPegarPopup);
    menuOpciones.add(opcionCortarPopup);
    
    setVisible(true);
  }

  // Métodos de acceso a componentes de la vista
  public JTable getTabla() {
    return tablaExplorador;
  }

  public DefaultTableModel getTablaModel() {
    return model;
  }

  public JButton getVolver() {
    return botonVolver;
  }
  
  public JButton getBotonCrearDirectrio() {
    return botonCrearDirectorio;
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

  // Método para limpiar la tabla
  public void limpiarTabla() {
    for (int i = 1; i < 30; i++) {
      tablaExplorador.setValueAt("", i, 0);
    }
  }

  public JMenuItem getOpcionCopiarPopup() {
    return opcionCopiarPopup;
  }
  
  public JMenuItem getOpcionCortarPopup() {
    return opcionCortarPopup;
  }

  public JMenuItem getOpcionPegarPopup() {

    return opcionPegarPopup;
  }

  // Método para actualizar la tabla con nuevos nombres
  public void actualizarTabla(String[] nombres) {
    limpiarTabla();
    int indice = 1;
    for (String nombre : nombres) {
      tablaExplorador.setValueAt(nombre, indice, 0);
      indice++;
    }
  }

  // Método para habilitar la edición de una fila específica
  public void habilitarEdicion(int fila) {
    filaSeleccionada = fila;
    tablaExplorador.editCellAt(fila, 0);
    Component editor = tablaExplorador.getEditorComponent();
    if (editor != null) {
      editor.requestFocus(); // Da foco al editor de la celda
    }
  }

  // Método para deshabilitar la edición
  public void deshabilitarEdicion() {
    if (tablaExplorador.isEditing()) {
      tablaExplorador.getCellEditor().stopCellEditing();
    }
    filaSeleccionada = -1;
  }
}
