package controlador;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import logicadenegocios.Explorador;
import vista.ExploradorForm;

public class ExploradorController {
  private Explorador modelo;
  private ExploradorForm vista;
  private long lastClickTime = 0;

  public ExploradorController(Explorador pModelo, ExploradorForm pVista) {
    modelo = pModelo;
    vista = pVista;
    agregarListeners();
    actualizar();
  }

  public void agregarListeners() {
    JTable tablaExplorador = vista.getTabla();

    // Evento del mouse en la tabla de archivos
    tablaExplorador.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int filaSeleccionada = tablaExplorador.rowAtPoint(e.getPoint());
        
        if (e.getButton() == MouseEvent.BUTTON3) { // Clic derecho (botón 3)
          int row = tablaExplorador.rowAtPoint(e.getPoint());
          if (row >= 0) {
            tablaExplorador.setRowSelectionInterval(row, row); // Selecciona la fila
            vista.getMenuOpcionesArchivo().show(e.getComponent(), e.getX(), e.getY()); // Muestra el menú contextual
          }
          return; // No sigue ejecutando el código debajo
        }

        // Si es un clic izquierdo (por ejemplo, para seleccionar o ingresar)
        if (filaSeleccionada >= 0) {
          long currentTime = System.currentTimeMillis();
          long timeDifference = currentTime - lastClickTime;
          
          if (timeDifference <= 500) { // Si el tiempo entre clics es menor a 500 ms, es un doble clic
            // Ejecuta la acción para ingresar a la carpeta (entrar al directorio)
            System.out.println("Doble clic: " + tablaExplorador.getValueAt(filaSeleccionada, 0));
            modelo.entrarDirectorio(tablaExplorador.getValueAt(filaSeleccionada, 0).toString());
            actualizar();
          } else {
            // Es un solo clic, solo selecciona la fila
            System.out.println("Clic único: " + tablaExplorador.getValueAt(filaSeleccionada, 0));
            tablaExplorador.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
          }
          
          lastClickTime = currentTime; // Actualiza el tiempo del último clic
        }
      }
    });

    // Botón de volver atrás
    JButton botonVolver = vista.getVolver();
    botonVolver.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        modelo.salirDirectorio();
        actualizar();
      }
    });

    // Acción de 'Eliminar archivo' desde el menú contextual
    vista.getOpcionEliminarPopup().addActionListener(e -> {
      int filaSeleccionada = tablaExplorador.getSelectedRow();
      if (filaSeleccionada >= 0) {
        modelo.eliminarArchivo(tablaExplorador.getValueAt(filaSeleccionada, 0).toString());
        actualizar();
      }
    });

    vista.getOpcionRenombrarPopup().addActionListener(e -> {
      int filaSeleccionada = vista.getTabla().getSelectedRow();
      if (filaSeleccionada >= 0) {
        // Llama a la función para habilitar la edición en la fila seleccionada
        vista.habilitarEdicion(filaSeleccionada);

        // Añade un listener para detectar cuando la edición termine
        vista.getTabla().getCellEditor().addCellEditorListener(new CellEditorListener() {
          @Override
          public void editingStopped(ChangeEvent e) {
            String nuevoNombre = vista.getTabla().getValueAt(filaSeleccionada, 0).toString();
            String viejoNombre = modelo.getListaArchivos()[filaSeleccionada];

            // Llama al modelo para renombrar el archivo
            modelo.renombrarArchivo(viejoNombre, nuevoNombre);
            vista.actualizarTabla(modelo.getListaArchivos()); // Actualiza la vista con los nuevos nombres
            vista.deshabilitarEdicion(); // Deshabilita la edición una vez terminada
          }

          @Override
          public void editingCanceled(ChangeEvent e) {
            // Si la edición fue cancelada
            vista.deshabilitarEdicion();
          }
        });
      }
    });
  }

  public void actualizar() {
    vista.actualizarTabla(modelo.getListaArchivos());
  }
}
