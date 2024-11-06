/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JTable;
import logicadenegocios.Explorador;
import vista.ExploradorForm;

/**
 *
 * @author johns
 */
public class ExploradorController {
  private Explorador modelo;
  private ExploradorForm vista;
    
  public ExploradorController(Explorador pModelo, ExploradorForm pVista) {
    modelo = pModelo;
    vista = pVista;
    agregarListeners();
    actualizar();
  }
  
  public void agregarListeners() {
    JTable tablaExplorador = vista.getTabla();
    //tablaExplorador.add
    
    // Eventis del mouse en la tabla de archivos
    tablaExplorador.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int filaSeleccionada = tablaExplorador.rowAtPoint(e.getPoint());
        
        if (e.getButton() == 3) {//e.isPopupTrigger()) {
            int row = tablaExplorador.rowAtPoint(e.getPoint());
            if (row >= 0) {
                tablaExplorador.setRowSelectionInterval(row, row);
                vista.getMenuOpcionesArchivo().show(e.getComponent(), e.getX(), e.getY());
            }
            return;
        }
        
        if (filaSeleccionada >= 0) {
            // Ejecuta la acción para la fila seleccionada
            System.out.println(tablaExplorador.getValueAt(filaSeleccionada, 0));
            System.out.println("Fila seleccionada: " + filaSeleccionada);
            // Aquí puedes añadir el código de acción para cada fila
            modelo.entrarDirectorio(tablaExplorador.getValueAt(filaSeleccionada, 0).toString());
            actualizar();
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
    
    // Agregar acciones para cada opción del menú
    vista.getOpcionEliminarPopup().addActionListener(e -> {
        int filaSeleccionada = tablaExplorador.getSelectedRow();
        if (filaSeleccionada >= 0) {
            modelo.eliminarArchivo(tablaExplorador.getValueAt(filaSeleccionada, 0).toString());
            actualizar();
        }
    });

    vista.getOpcionRenombrarPopup().addActionListener(e -> {
        int filaSeleccionada = tablaExplorador.getSelectedRow();
        vista.getTablaModel().isCellEditable(filaSeleccionada, 0); 
        
        tablaExplorador.editCellAt(filaSeleccionada, 0);
        
        Component editor = tablaExplorador.getEditorComponent();
        if (editor != null) {
            editor.requestFocus();
        }
    });
  }
  
  public void actualizar() {
    vista.actualizarTabla(modelo.getListaArchivos());
  }
}
