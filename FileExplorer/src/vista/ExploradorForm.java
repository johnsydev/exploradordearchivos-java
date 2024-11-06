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

  private final JTable tablaExplorador;
  private final JButton botonVolver;
  private final JPopupMenu menuOpciones;
  private final JMenuItem opcionEliminarPopup;
  private final JMenuItem opcionRenombrarPopup;
  private final DefaultTableModel model;

  private int filaSeleccionada = -1;

  public ExploradorForm() {
    // Configuración de la ventana principal
    setTitle("Explorador de archivos");
    setSize(1500, 900);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // Inicializar el modelo sin hacer editable ninguna celda por defecto
    model = new DefaultTableModel(30, 1) {
      @Override
      public boolean isCellEditable(int row, int column) {
        // Permitir la edición solo de la fila seleccionada
        return row == filaSeleccionada;
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

    menuOpciones = new JPopupMenu();
    opcionEliminarPopup = new JMenuItem("Eliminar");
    opcionRenombrarPopup = new JMenuItem("Cambiar nombre");

    // Agregar las opciones al menú emergente
    menuOpciones.add(opcionEliminarPopup);
    menuOpciones.add(opcionRenombrarPopup);

    // Listener para deshabilitar la edición al hacer clic fuera de la celda seleccionada
    tablaExplorador.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        int fila = tablaExplorador.rowAtPoint(e.getPoint());
        if (fila != filaSeleccionada) {
          deshabilitarEdicion(); // Cancela la edición si se hace clic fuera de la celda seleccionada
        }
      }
    });

    // Listener para detectar cuando se finaliza o cancela la edición en la celda
    tablaExplorador.getDefaultEditor(String.class).addCellEditorListener(new javax.swing.event.CellEditorListener() {
      @Override
      public void editingStopped(javax.swing.event.ChangeEvent e) {
        deshabilitarEdicion();
      }

      @Override
      public void editingCanceled(javax.swing.event.ChangeEvent e) {
        deshabilitarEdicion();
      }
    });

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
