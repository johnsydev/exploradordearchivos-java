/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

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
    tablaExplorador.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int filaSeleccionada = tablaExplorador.rowAtPoint(e.getPoint());
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
    
    JButton botonVolver = vista.getVolver();
    botonVolver.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        modelo.salirDirectorio();
        actualizar();
      }
    });
  }
  
  public void actualizar() {
    vista.actualizarTabla(modelo.getListaArchivos());
  }
}
