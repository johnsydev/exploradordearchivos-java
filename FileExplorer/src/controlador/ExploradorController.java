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
import java.util.ArrayList;
import javax.swing.JComboBox;
import vista.ExploradorForm;
import vista.PropiedadesArchivoForm;


/**
 * Controlador principal del explorador de archivos.
 *
 * <p>Maneja la interacción entre la vista y el modelo del explorador,
 * procesando eventos de usuario y actualizando la interfaz gráfica
 * según corresponda.
 *
 * @author johns
 */
public class ExploradorController {

  private Explorador modelo;
  private ExploradorForm vista;
  private long lastClickTime = 0;
  private String archivoCopiado;
  private boolean estabaEnUnidad = false;
  
  private boolean esCortado;
  
  /**
  * Crea un nuevo controlador del explorador.
  *
  * @param pModelo modelo del explorador
  * @param pVista vista del explorador
  * @throws IOException si hay error al acceder al sistema de archivos
  */
  public ExploradorController(Explorador pModelo, ExploradorForm pVista) throws IOException {
    modelo = pModelo;
    vista = pVista;
    agregarListeners();
    actualizar();

  }
  /**
 * Configura todos los listeners para manejar eventos de la interfaz gráfica.
 *
 * <p>Este método establece los manejadores de eventos para:
 * <ul>
 *   <li>Eventos de clic y doble clic en la tabla de archivos
 *   <li>Navegación entre directorios
 *   <li>Operaciones de archivo (copiar, cortar, pegar)
 *   <li>Creación de directorios
 *   <li>Ordenamiento de elementos
 *   <li>Menú contextual y sus opciones
 * </ul>
 *
 * <p>Los eventos principales incluyen:
 * <ul>
 *   <li>Clic derecho: Muestra el menú contextual con opciones
 *   <li>Doble clic: Entra a un directorio o abre un archivo
 *   <li>Clic simple: Selecciona un elemento
 * </ul>
 *
 * <p>Las operaciones soportadas a través de los listeners incluyen:
 * <ul>
 *   <li>Navegación entre directorios (entrar/salir)
 *   <li>Creación de nuevos directorios
 *   <li>Eliminación de archivos y directorios
 *   <li>Renombrar elementos
 *   <li>Copiar y pegar elementos
 *   <li>Ver propiedades de elementos
 * </ul>
 *
 * <p>Cada operación incluye validaciones y manejo de errores apropiados,
 * mostrando mensajes al usuario cuando sea necesario.
 */
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
            boolean estado = modelo.entrarDirectorio(tablaExplorador.getValueAt(filaSeleccionada, 0).toString());
            if (estado) {
              if (estabaEnUnidad) {
                vista.getVolver().setVisible(true);
                vista.getBotonCrearDirectrio().setVisible(true);
                vista.getOpcionEliminarPopup().setVisible(true);
                vista.getOpcionRenombrarPopup().setVisible(true);
                vista.getOpcionCopiarPopup().setVisible(true);
                vista.getOpcionCortarPopup().setVisible(true);
                vista.getOpcionPegarPopup().setVisible(true);
                vista.getOpcionPropiedades().setVisible(true);
                vista.getOpcionPropiedadesUnidad().setVisible(false);
                if (archivoCopiado != null) {
                  vista.setBotonPegar(true);
                }
                estabaEnUnidad = false;
              }
              actualizar();
            } else {
              vista.mostrarMensajeError("Error de acceso", "No es posible acceder a esta carpeta.");
            }
          } else {
            // Es un solo clic, solo selecciona la fila
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
        boolean estado = modelo.salirDirectorio();
        if (!estado) {
          ArrayList<UnidadLogica> unidades = UnidadLogica.getUnidades();
          if (unidades != null && !unidades.isEmpty()) {
            
            ArrayList<Elemento> listaElementos = new ArrayList<Elemento>();
            for (UnidadLogica unidad : unidades) {
              listaElementos.add((Elemento) unidad);
            }
            estabaEnUnidad = true;
            vista.getVolver().setVisible(false);
            vista.getBotonPegar().setVisible(false);
            vista.getBotonCrearDirectrio().setVisible(false);
            vista.getOpcionEliminarPopup().setVisible(false);
            vista.getOpcionRenombrarPopup().setVisible(false);
            vista.getOpcionCopiarPopup().setVisible(false);
            vista.getOpcionCortarPopup().setVisible(false);
            vista.getOpcionPegarPopup().setVisible(false);
            vista.getOpcionPropiedades().setVisible(false);
            vista.getOpcionPropiedadesUnidad().setVisible(true);
            vista.actualizarTabla(listaElementos);
          }
        }
        else {
          actualizar();
        }
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
    
    JComboBox comboboxOrdenamiento = vista.getComboboxOrdenamiento();
    JComboBox comboboxModoOrden = vista.getComboboxModoOrden();
    
    
    comboboxOrdenamiento.addActionListener(e -> {
      modelo.setCriterioOrden((String) comboboxOrdenamiento.getSelectedItem());
      modelo.setModoOrden((String) comboboxModoOrden.getSelectedItem());
      actualizar();
    });
    comboboxModoOrden.addActionListener(comboboxOrdenamiento.getActionListeners()[0]);

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
          vista.setBotonPegar(true);
        } catch(Exception exc) {}
      }
    });

    vista.getOpcionCortarPopup().addActionListener(e -> {
      int filaSeleccionada = tablaExplorador.getSelectedRow();
      if (filaSeleccionada >= 0) {
        try {
          esCortado = true;
          String rutaElemento = tablaExplorador.getValueAt(filaSeleccionada, 0).toString();
          Elemento elem = new Elemento(modelo.getRuta() + "\\" + rutaElemento);    
          if (elem.esArchivo()) {
            archivoCopiado = rutaElemento;
            modelo.copiarArchivo(rutaElemento);
          } else {
            archivoCopiado = rutaElemento;
            modelo.copiarDirectorio(rutaElemento);
          }
          vista.setBotonPegar(true);
        } catch(Exception exc) {}
      }
    });

    // Acción de 'Pegar archivo' desde el menú contextual
    vista.getOpcionPegarPopup().addActionListener(e -> {
      if (archivoCopiado != null) {
        try {
          boolean estado = modelo.pegar(esCortado);
          if (!estado) {
            vista.mostrarMensajeError("No se puede pegar", "No es posible pegar en la ruta especificada.");
          }
          esCortado = false;
          archivoCopiado = null;
          vista.setBotonPegar(false);
          actualizar();
        } catch(Exception exc) {
        }
      } else {
        JOptionPane.showMessageDialog(null, "No hay archivo copiado para pegar.", "Error", JOptionPane.ERROR_MESSAGE);
      }
    });
    
    vista.getBotonPegar().addActionListener(vista.getOpcionPegarPopup().getActionListeners()[0]);

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
      int filaSeleccionada = tablaExplorador.getSelectedRow();
      if (filaSeleccionada >= 0) {
        try {
          UnidadLogica unidadLogica = new UnidadLogica(tablaExplorador.getValueAt(filaSeleccionada, 0).toString());
          PropiedadesUnidadLogicaController propiedades
                  = new PropiedadesUnidadLogicaController(unidadLogica);
          
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null,
                  "No se pudo obtener las propiedades de la unidad",
                  "Error",
                  JOptionPane.ERROR_MESSAGE);
        }
      }
    });
  }

  public void actualizar() {
    modelo.actualizar();
    vista.actualizarTabla(modelo.getListaElementos());
  }
}
