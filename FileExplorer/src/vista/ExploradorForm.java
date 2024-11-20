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
import javax.swing.table.JTableHeader;
import logicadenegocios.Elemento;

public class ExploradorForm extends JFrame {

  private final JTable tablaExplorador;
  private final JButton botonVolver;
  private final JButton botonCrearDirectorio;
  private final JComboBox comboboxOrdenamiento;
  private final JButton botonPegar;
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
    model = new DefaultTableModel(new Object[]{"Nombre", "Fecha creación", "Tipo", "Tamaño"}, 30) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return row == filaSeleccionada;
      }
    };

    tablaExplorador = new JTable(model);
    JTableHeader header = tablaExplorador.getTableHeader();
    header.setBackground(new Color(143, 143, 143)); // Cambia el fondo del encabezado
    header.setForeground(Color.WHITE); // Cambia el color del texto del encabezado
    header.setFont(new Font("Arial", Font.PLAIN, 12)); // Cambia la fuente y el tamaño
    header.setPreferredSize(new Dimension(header.getWidth(), 30));
    
    tablaExplorador.setFont(new Font("Arial", Font.PLAIN, 16));
    tablaExplorador.setRowHeight(40);
    tablaExplorador.getTableHeader().setReorderingAllowed(false);
    tablaExplorador.getColumnModel().getColumn(0).setPreferredWidth(1400);
    tablaExplorador.getColumnModel().getColumn(1).setPreferredWidth(200);
    tablaExplorador.getColumnModel().getColumn(2).setPreferredWidth(100);
    
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
                    tablaExplorador.repaint(tablaExplorador.getCellRect(oldHoverRow, 1, true));
                    tablaExplorador.repaint(tablaExplorador.getCellRect(oldHoverRow, 2, true));
                    tablaExplorador.repaint(tablaExplorador.getCellRect(oldHoverRow, 3, true));
                }
                if (hoverRow != -1) {
                    tablaExplorador.repaint(tablaExplorador.getCellRect(hoverRow, 0, true));
                    tablaExplorador.repaint(tablaExplorador.getCellRect(hoverRow, 1, true));
                    tablaExplorador.repaint(tablaExplorador.getCellRect(hoverRow, 2, true));
                    tablaExplorador.repaint(tablaExplorador.getCellRect(hoverRow, 3, true));
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
                tablaExplorador.repaint(tablaExplorador.getCellRect(oldHoverRow, 1, true));
                tablaExplorador.repaint(tablaExplorador.getCellRect(oldHoverRow, 2, true));
                tablaExplorador.repaint(tablaExplorador.getCellRect(oldHoverRow, 3, true));
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
    
    ImageIcon iconoCrearDirectorio = new ImageIcon("./src/imagenes/crear_directorio.png");
    botonCrearDirectorio.setFocusable(false);
    botonCrearDirectorio.setBackground(new Color(117, 131, 184)); // Fondo
    botonCrearDirectorio.setForeground(new Color(255, 255, 255)); // Texto
    botonCrearDirectorio.setIcon(iconoCrearDirectorio);
    
    ImageIcon iconoVolver = new ImageIcon("./src/imagenes/volver.png");
    botonVolver.setBackground(new Color(237, 103, 110)); // Fondo
    botonVolver.setForeground(new Color(255, 255, 255)); // Texto
    botonVolver.setIcon(iconoVolver);
    
    ImageIcon iconoPegar = new ImageIcon("./src/imagenes/pegar.png");
    botonPegar = new JButton("Pegar");
    botonPegar.setBackground(new Color(117, 158, 184)); // Fondo
    botonPegar.setForeground(new Color(255, 255, 255)); // Texto
    botonPegar.setIcon(iconoPegar);

    panel.add(botonVolver);
    panel.add(botonCrearDirectorio);
    
    comboboxOrdenamiento =new JComboBox<String>();
    comboboxOrdenamiento.setBounds(10,10,80,20);
    panel.add(comboboxOrdenamiento);
    comboboxOrdenamiento.addItem("Nombre");
    comboboxOrdenamiento.addItem("Tamaño");
    comboboxOrdenamiento.addActionListener(e -> {
            String seleccion = (String) comboboxOrdenamiento.getSelectedItem();
            System.out.println("Seleccionado: " + seleccion);
        });
    comboboxOrdenamiento.setRenderer(new DefaultListCellRenderer() {
        @Override
        public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            // Mostrar "Ordenar por:" solo cuando no se está desplegando
            String display = (index == -1) ? "Ordenar por: " + value : value.toString();
            return super.getListCellRendererComponent(list, display, index, isSelected, cellHasFocus);
        }
    });
    
    panel.add(comboboxOrdenamiento);
    panel.add(botonPegar);
    botonPegar.setVisible(false);
    
    panel.setBackground(new Color(223, 243, 228)); // Color de ARRIBA
    add(panel, BorderLayout.NORTH);
    
    ImageIcon iconoEliminar = new ImageIcon("./src/imagenes/eliminar.png");
    ImageIcon iconoRenombrar = new ImageIcon("./src/imagenes/renombrar.png");
    ImageIcon iconoCopiar = new ImageIcon("./src/imagenes/copiar.png");
    ImageIcon iconoCortar = new ImageIcon("./src/imagenes/cortar.png");
    ImageIcon iconoPropiedades = new ImageIcon("./src/imagenes/propiedades.png");
    ImageIcon iconoPropiedadesUnidad = new ImageIcon("./src/imagenes/propiedades_unidad.png");


    menuOpciones = new JPopupMenu();
    opcionEliminarPopup = new JMenuItem("Eliminar");
    opcionEliminarPopup.setIcon(iconoEliminar);
    
    opcionRenombrarPopup = new JMenuItem("Cambiar nombre");
    opcionRenombrarPopup.setIcon(iconoRenombrar);
    
    opcionCopiarPopup = new JMenuItem("Copiar");
    opcionCopiarPopup.setIcon(iconoCopiar);
    
    opcionPegarPopup = new JMenuItem("Pegar");
    opcionPegarPopup.setIcon(iconoPegar);
    
    opcionCortarPopup = new JMenuItem("Cortar");
    opcionCortarPopup.setIcon(iconoCortar);
    
    opcionPropiedades = new JMenuItem("Propiedades");
    opcionPropiedades.setIcon(iconoPropiedades);
    
    opcionPropiedadesUnidad = new JMenuItem("Propiedades de la unidad");
    opcionPropiedadesUnidad.setIcon(iconoPropiedadesUnidad);
    opcionPropiedadesUnidad.setVisible(false);

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
  
  public JButton getBotonPegar() {
    return botonPegar;
  }
  
  public void setBotonPegar(boolean pEstado) {
    botonPegar.setVisible(pEstado);
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
    for (int i = 0; i < model.getRowCount(); i++) {
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
    model.setRowCount(elementos.size());
    limpiarTabla();
    
    
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
    tablaExplorador.clearSelection();
    int indice = 0;
    for (Elemento elemento : elementos) {
      tablaExplorador.setValueAt(elemento.getNombre(), indice, 0);
      tablaExplorador.setValueAt(elemento.getFechaCreacion(), indice, 1);
      tablaExplorador.setValueAt(elemento.getTipo(), indice, 2);
      if (elemento.esArchivo()) {
        tablaExplorador.setValueAt(elemento.getTamanoSimpleTexto(), indice, 3);
      } else {
        tablaExplorador.setValueAt("", indice, 3);
      }
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
  
  public void mostrarMensajeError(String titulo, String texto) {
    JOptionPane.showMessageDialog(this, texto, titulo, JOptionPane.ERROR_MESSAGE);
  }
          
  private void clearHover() {
    if (hoverRow != -1) {
        int oldHoverRow = hoverRow;
        hoverRow = -1;
        tablaExplorador.repaint(tablaExplorador.getCellRect(oldHoverRow, 0, true));
    }
  }
}
