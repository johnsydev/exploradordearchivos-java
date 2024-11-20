/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import logicadenegocios.Archivo;
import vista.PropiedadesArchivoForm;

/**
 *
 * @author johns
 */
public class PropiedadesArchivoController {

  private Archivo modelo;
  private PropiedadesArchivoForm vista;

  public PropiedadesArchivoController(Archivo pArchivo) {
    modelo = pArchivo;
    vista = new PropiedadesArchivoForm();
    agregarListeners();
    actualizar();
  }

  public void actualizar() {
    vista.setNombre(modelo.getNombre());
    vista.setExtension(modelo.getExtension());
    vista.setUbicacion(modelo.getRutaCompleta());
    vista.setTamano(modelo.getTamanoTexto());
    vista.setFechaCreacion(modelo.getFechaCreacion());
    vista.setUltimaModificacion(modelo.getUltimaModificacion());
    vista.setUltimoAcceso(modelo.getUltimoAcceso());
    vista.setLectura(modelo.getEsSoloLectura());
    vista.setOculto(modelo.getEsArchivoOculto());
  }

  public void agregarListeners() {
    JButton btnAceptar = vista.getBotonAceptar();

    btnAceptar.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        // Obtener el estado de los checkboxes
        boolean lectura = vista.cbSoloLectura.isSelected();
        boolean oculto = vista.cbOculto.isSelected();

        // Imprimir en consola para verificar los valores seleccionados
        System.out.println("Estado Solo Lectura: " + lectura);
        System.out.println("Estado Oculto: " + oculto);

        // Llamar a los métodos para cambiar las propiedades del archivo
        modelo.setEsSoloLectura(lectura);  // Cambia el estado del archivo a solo lectura
        modelo.setEsArchivoOculto(oculto); // Cambia el estado del archivo a oculto

        // Opcional: puedes agregar un mensaje de éxito o actualizar la interfaz gráfica si es necesario
        JOptionPane.showMessageDialog(null, "Configuración actualizada correctamente.");
        actualizar(); // Actualiza la vista
      }
    });
  }
}
