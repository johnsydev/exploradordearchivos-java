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
    panel.add(botonVolver);
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
    tablaExplorador.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {
      @Override
      public void editingStopped(ChangeEvent e) {
        String nuevoNombre = (String) tablaExplorador.getValueAt(filaSeleccionada, 0);
        File archivo = new File(nuevoNombre); // Verifica si el archivo ya existe

        if (archivo.exists()) {
          JOptionPane.showMessageDialog(ExploradorForm.this,
                  "El archivo ya existe o el nombre no es válido.",
                  "Error", JOptionPane.ERROR_MESSAGE);
        } else {
          // Lógica para renombrar el archivo
          renombrarArchivo(nuevoNombre);
        }
        deshabilitarEdicion();
      }

      @Override
      public void editingCanceled(ChangeEvent e) {
        deshabilitarEdicion();
      }
    });

    setVisible(true);
  }

  private void renombrarArchivo(String nuevoNombre) {
    // Lógica para renombrar el archivo aquí
    // Este es solo un ejemplo de cómo manejar la lógica de renombrar el archivo
    File archivo = new File((String) tablaExplorador.getValueAt(filaSeleccionada, 0));
    File archivoRenombrado = new File(nuevoNombre);

    if (archivo.renameTo(archivoRenombrado)) {
      JOptionPane.showMessageDialog(this, "El archivo ha sido renombrado con éxito.");
      // Actualizar la tabla con el nuevo nombre
      tablaExplorador.setValueAt(nuevoNombre, filaSeleccionada, 0);
    } else {
      JOptionPane.showMessageDialog(this, "No se pudo renombrar el archivo.",
              "Error", JOptionPane.ERROR_MESSAGE);
    }
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

  public JMenuItem getOpcionCopiarPopup() {
    return opcionCopiarPopup;
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
