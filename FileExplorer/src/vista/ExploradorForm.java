package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.ArrayList;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import logicadenegocios.Elemento;

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
  private final JMenuItem opcionPropiedades;
  private final JMenuItem opcionPropiedadesUnidad;
  private final DefaultTableModel model;
  private int hoverRow = -1;

  private int filaSeleccionada = -1;

  public ExploradorForm() {
    // Configuración de la ventana principal
    setTitle("Explorador de archivos");
    setSize(1500, 900);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    getContentPane().setBackground(new Color(173, 216, 230));

    // Inicializar el modelo, solo se puede editar la fila seleccionada
    model = new DefaultTableModel(new Object[]{"Nombre", "Fecha", "Tipo", "Tamaño"}, 30) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return row == filaSeleccionada;
      }
    };

    tablaExplorador = new JTable(model);
    tablaExplorador.getTableHeader().setBackground(new Color(143, 143, 143)); // Cambia el fondo del encabezado
    tablaExplorador.getTableHeader().setForeground(Color.WHITE); // Cambia el color del texto del encabezado
    tablaExplorador.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 12)); // Cambia la fuente y el tamaño
    
    tablaExplorador.setFont(new Font("Arial", Font.PLAIN, 16));
    tablaExplorador.setRowHeight(40);
    tablaExplorador.getTableHeader().setReorderingAllowed(false);
    tablaExplorador.getColumnModel().getColumn(0).setPreferredWidth(1800);
    tablaExplorador.getColumnModel().getColumn(1).setPreferredWidth(150);
    
    // Variable de clase para guardar la fila de "hover"

// Variable de clase para guardar la fila de "hover"

    tablaExplorador.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Cambiar el fondo si la celda está seleccionada
            if (isSelected) {
                c.setBackground(new Color(230, 227, 255)); // Fondo seleccionado
            } else if (row == hoverRow) {
                c.setBackground(new Color(220, 220, 255)); // Fondo "hover"
            } else {
                c.setBackground(new Color(244, 243, 253)); // Fondo por defecto
            }

            // Establecer el borde de la celda
            ((JComponent) c).setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 1));

            return c;
        }
    });

    // Listener para actualizar la fila de "hover" y redibujar solo las filas afectadas
    tablaExplorador.addMouseMotionListener(new MouseMotionAdapter() {
        @Override
        public void mouseMoved(MouseEvent e) {
            int row = tablaExplorador.rowAtPoint(e.getPoint());
            if (row != hoverRow) {
                int oldHoverRow = hoverRow;
                hoverRow = row;

                // Solo redibuja las filas que cambiaron
                if (oldHoverRow != -1) {
                    tablaExplorador.repaint(tablaExplorador.getCellRect(oldHoverRow, 0, true));
                }
                if (hoverRow != -1) {
                    tablaExplorador.repaint(tablaExplorador.getCellRect(hoverRow, 0, true));
                }
            }
        }
    });

    // Limpiar el "hover" cuando el mouse sale de la tabla
    tablaExplorador.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseExited(MouseEvent e) {
            if (hoverRow != -1) {
                int oldHoverRow = hoverRow;
                hoverRow = -1;
                tablaExplorador.repaint(tablaExplorador.getCellRect(oldHoverRow, 0, true));
            }
        }
    });

    //tablaExplorador.addMouseWheelListener(e -> clearHover()); // Limpiar cuando se usa la rueda de desplazamiento

    
    tablaExplorador.setShowGrid(true);
    tablaExplorador.setGridColor(new Color(230, 227, 255));
    JScrollPane tableScrollPane = new JScrollPane(tablaExplorador);
    add(tableScrollPane, BorderLayout.CENTER);

    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
    botonVolver = new JButton("Volver");
    botonCrearDirectorio = new JButton("Crear directorio");
    botonVolver.setFocusable(false);
    botonCrearDirectorio.setFocusable(false);

    botonVolver.setBackground(new Color(237, 103, 110)); // Fondo
    botonVolver.setForeground(new Color(255, 255, 255)); // Texto
    botonCrearDirectorio.setBackground(new Color(117, 131, 184)); // Fondo
    botonCrearDirectorio.setForeground(new Color(255, 255, 255)); // Texto

    panel.add(botonVolver);
    panel.add(botonCrearDirectorio);
    panel.setBackground(new Color(223, 243, 228)); // Color de ARRIBA
    add(panel, BorderLayout.NORTH);

    menuOpciones = new JPopupMenu();
    opcionEliminarPopup = new JMenuItem("Eliminar");
    opcionRenombrarPopup = new JMenuItem("Cambiar nombre");
    opcionCopiarPopup = new JMenuItem("Copiar");
    opcionPegarPopup = new JMenuItem("Pegar");
    opcionCortarPopup = new JMenuItem("Cortar");
    opcionPropiedades = new JMenuItem("Propiedades");
    opcionPropiedadesUnidad = new JMenuItem("Propiedades de la unidad");

    menuOpciones.add(opcionEliminarPopup);
    menuOpciones.add(opcionRenombrarPopup);
    menuOpciones.add(opcionCopiarPopup);
    menuOpciones.add(opcionPegarPopup);
    menuOpciones.add(opcionCortarPopup);
    menuOpciones.add(opcionPropiedades);
    menuOpciones.add(opcionPropiedadesUnidad); 

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
    for (int i = 0; i < 30; i++) {
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

  public JMenuItem getOpcionPropiedades() {
    return opcionPropiedades;
  }
  
  public JMenuItem getOpcionPropiedadesUnidad() {  
    return opcionPropiedadesUnidad;
  }

  // Método para actualizar la tabla con nuevos nombres
  public void actualizarTabla(ArrayList<Elemento> elementos) {
    limpiarTabla();
    int indice = 0;
    for (Elemento elemento : elementos) {
      tablaExplorador.setValueAt(elemento.getNombre(), indice, 0);
      indice++;
    }
    
     // PARA IMAGENES
    tablaExplorador.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
      @Override
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
          JLabel label = new JLabel();
          label.setOpaque(true);

          // Determina el fondo basado en selección, "hover" o estado por defecto
          if (isSelected) {
              label.setBackground(new Color(230, 227, 255)); // Fondo seleccionado
          } else if (row == hoverRow) {
              label.setBackground(new Color(220, 220, 255)); // Fondo "hover"
          } else {
              label.setBackground(new Color(244, 243, 253)); // Fondo por defecto
          }

          // Configura texto e ícono
          if (value instanceof String) {
              String nombre = (String) value;
              label.setText(nombre);
              if (row < elementos.size()) {
                
                /*
                String rutaIcono = (elementos.get(row).esArchivo()) ? 
                    "./src/imagenes/archivo.png" : 
                    "./src/imagenes/directorio.png";

                ImageIcon iconoOriginal = new ImageIcon(rutaIcono);
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
                    table.getRowHeight(), // Altura de la fila
                    table.getRowHeight(), // Mantenerlo cuadrado
                    Image.SCALE_SMOOTH // Suavizado para mejor calidad
                );
                */
                
                FileSystemView fsv = FileSystemView.getFileSystemView();
                Icon iconoSistema = fsv.getSystemIcon(elementos.get(row).getFile());
                //label.setIcon(new ImageIcon(imagenEscalada));
                label.setIcon(iconoSistema);
              }
          }

          label.setFont(new Font("Arial", Font.PLAIN, 16));
          label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
          return label;
      }
    });
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
  
  private void clearHover() {
    if (hoverRow != -1) {
        int oldHoverRow = hoverRow;
        hoverRow = -1;
        tablaExplorador.repaint(tablaExplorador.getCellRect(oldHoverRow, 0, true));
    }
  }

}
