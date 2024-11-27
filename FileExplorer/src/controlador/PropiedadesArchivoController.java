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
 * Controlador que maneja la lógica entre el modelo de Archivo y la vista de PropiedadesArchivoForm.
 * Esta clase implementa el patrón MVC para gestionar la visualización y modificación de las 
 * propiedades de un archivo en el sistema.
 *
 * @author johns
 * @version 1.0
 * @see logicadenegocios.Archivo
 * @see vista.PropiedadesArchivoForm
 */
public class PropiedadesArchivoController {

  private Archivo modelo;
  private PropiedadesArchivoForm vista;

  /**
   * Constructor que inicializa el controlador con un archivo específico.
   * Crea la vista asociada, configura los listeners y actualiza la información mostrada.
   *
   * @param pArchivo El archivo cuyas propiedades se mostrarán y podrán modificarse
   */
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
    vista.setTipo(modelo.getTipo());
  }
  
  /**
  * Configura los listeners de eventos para los componentes de la vista.
  * Específicamente, maneja el evento de clic del botón Aceptar para actualizar
  * los atributos de solo lectura y oculto del archivo.
  * 
  * Cuando se hace clic en el botón Aceptar:
  * - Actualiza los atributos del archivo según los valores de los checkboxes
  * - Muestra un mensaje de confirmación
  * - Actualiza la vista
  * - Cierra la ventana de propiedades
  */
  public void agregarListeners() {
    JButton btnAceptar = vista.getBotonAceptar();

    btnAceptar.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {

        boolean lectura = vista.cbSoloLectura.isSelected();
        boolean oculto = vista.cbOculto.isSelected();

        modelo.setEsSoloLectura(lectura);
        modelo.setEsArchivoOculto(oculto);

        JOptionPane.showMessageDialog(null, "Configuración actualizada correctamente.");
        actualizar(); // Actualiza la vista
        
        vista.dispose();
      }
    });
  }
}
