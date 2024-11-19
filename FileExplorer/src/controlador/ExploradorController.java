package controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import logicadenegocios.Archivo;
import logicadenegocios.Directorio;
import logicadenegocios.Elemento;
import logicadenegocios.Explorador;
import logicadenegocios.UnidadLogica;
import java.io.File;
import java.io.IOException;
import vista.ExploradorForm;
import vista.PropiedadesArchivoForm;

public class ExploradorController {

  private Explorador modelo;
  private ExploradorForm vista;
  private long lastClickTime = 0;
  private String archivoCopiado;
  
  private boolean esCortado;

  public ExploradorController(Explorador pModelo, ExploradorForm pVista) throws IOException {
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
            boolean estado = modelo.entrarDirectorio(tablaExplorador.getValueAt(filaSeleccionada, 0).toString());
            if (estado) {
              actualizar();
            } else {
              vista.mostrarMensajeError("Error de acceso", "No es posible acceder a esta carpeta.");
            }
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

    JButton botonCrearDirectorio = vista.getBotonCrearDirectrio();
    botonCrearDirectorio.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String input = JOptionPane.showInputDialog(null, "Ingresa nombre carpeta: ", "Nueva carpeta", JOptionPane.PLAIN_MESSAGE);
        if (input != null && !input.trim().equals("")) {
          if (input.length() > 64) {
            JOptionPane.showMessageDialog(null, "El nombre de la carpeta no puede tener más de 64 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
          }

          // Validación de caracteres especiales (solo letras, números, guiones y guiones bajos)
          if (!input.matches("[a-zA-Z0-9_-]+")) {
            JOptionPane.showMessageDialog(null, "El nombre de la carpeta contiene caracteres no permitidos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
          }

          modelo.crearDirectorio(input);
          actualizar();
        }
      }
    });

    // Acción de 'Eliminar archivo' desde el menú contextual
    vista.getOpcionEliminarPopup().addActionListener(e -> {
      int filaSeleccionada = tablaExplorador.getSelectedRow();
      if (filaSeleccionada >= 0) {
        modelo.eliminarElementoInterfaz(tablaExplorador.getValueAt(filaSeleccionada, 0).toString());
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
            vista.getTabla().getCellEditor().removeCellEditorListener(this);

            String viejoNombre = modelo.getListaElementos().get(filaSeleccionada).getNombre();
            String nuevoNombre = vista.getTabla().getCellEditor().getCellEditorValue().toString();

            System.out.println(viejoNombre);
            System.out.println(nuevoNombre);
            // Llama al modelo para renombrar el archivo

            modelo.renombrarArchivo(viejoNombre, nuevoNombre);
            //vista.getTabla().getCellEditor().stopCellEditing();

            vista.deshabilitarEdicion(); // Deshabilita la edición una vez terminada
            modelo.actualizar();
            vista.actualizarTabla(modelo.getListaElementos()); // Actualiza la vista con los nuevos nombres

          }

          @Override
          public void editingCanceled(ChangeEvent e) {
            // Si la edición fue cancelada
            vista.deshabilitarEdicion();
          }
        });
      }
    });

    vista.getOpcionCopiarPopup().addActionListener(e -> {
      int filaSeleccionada = tablaExplorador.getSelectedRow();
      if (filaSeleccionada >= 0) {
        try {
          esCortado = false;
          String rutaElemento = tablaExplorador.getValueAt(filaSeleccionada, 0).toString();

          Elemento elem = new Elemento(modelo.getRuta() + "\\" + rutaElemento);
          if (elem.esArchivo()) {
            archivoCopiado = rutaElemento;
            modelo.copiarArchivo(rutaElemento);
          } else {
            archivoCopiado = rutaElemento;
            modelo.copiarDirectorio(rutaElemento);
          }
        } catch(Exception exc) {}
      }
    });

    vista.getOpcionCortarPopup().addActionListener(e -> {
      int filaSeleccionada = tablaExplorador.getSelectedRow();
      if (filaSeleccionada >= 0) {
        try {
          esCortado = true;
          archivoCopiado = tablaExplorador.getValueAt(filaSeleccionada, 0).toString();
          modelo.copiarArchivo(archivoCopiado);
        } catch(Exception exc) {}
      }
    });

    // Acción de 'Pegar archivo' desde el menú contextual
    vista.getOpcionPegarPopup().addActionListener(e -> {
      if (archivoCopiado != null) {
        try {
          modelo.pegar(esCortado);
          esCortado = false;
          actualizar();
        } catch(Exception exc) {
        }
      } else {
        JOptionPane.showMessageDialog(null, "No hay archivo copiado para pegar.", "Error", JOptionPane.ERROR_MESSAGE);
      }
    });

    vista.getOpcionPropiedades().addActionListener(e -> {
      int filaSeleccionada = tablaExplorador.getSelectedRow();
      if (filaSeleccionada >= 0) {
        String nombreArch = tablaExplorador.getValueAt(filaSeleccionada, 0).toString();
        Elemento elem = modelo.getElemento(nombreArch);
        if (elem.esArchivo()) {
          Archivo archivo = new Archivo(elem.getRuta());
          PropiedadesArchivoController propiedades = new PropiedadesArchivoController(archivo);
        } else {
          Directorio directorio = new Directorio(elem.getRuta());
          PropiedadesDirectorioController propiedades = new PropiedadesDirectorioController(directorio);
        }
      }
    });

    vista.getOpcionPropiedadesUnidad().addActionListener(e -> {

      try {
        // Obtener la primera unidad de disco (normalmente C:)r la primera unidad de disco (normalmente C:)
        File[] unidades = File.listRoots();
        if (unidades != null && unidades.length > 0) {
          UnidadLogica unidadLogica = new UnidadLogica(unidades[0].getPath());
          PropiedadesUnidadLogicaController propiedades
                  = new PropiedadesUnidadLogicaController(unidadLogica);
        }
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(null,
                "No se pudo obtener las propiedades de la unidad",
                "Error",
                JOptionPane.ERROR_MESSAGE);
      }

    });

  }

  public void actualizar() {
    modelo.actualizar();
    vista.actualizarTabla(modelo.getListaElementos());
  }
}
