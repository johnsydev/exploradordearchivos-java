/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import logicadenegocios.Directorio;
import vista.PropiedadesDirectorioForm;

/**
 * Controlador que gestiona la interacción entre el modelo de Directorio y su vista de propiedades.
 * Implementa el patrón MVC para manejar la visualización y modificación de las propiedades
 * de un directorio en el sistema de archivos.
 *
 * @author johns
 * @version 1.0
 * @see logicadenegocios.Directorio
 * @see vista.PropiedadesDirectorioForm
 */
public class PropiedadesDirectorioController {

  private Directorio modelo;
  private PropiedadesDirectorioForm vista;

  /**
     * Constructor que inicializa el controlador con un directorio específico.
     * Crea la vista asociada, configura los listeners y actualiza la información mostrada.
     *
     * @param pDirectorio El directorio cuyas propiedades se mostrarán y podrán modificarse
     */
  public PropiedadesDirectorioController(Directorio pDirectorio) {
    modelo = pDirectorio;
    vista = new PropiedadesDirectorioForm();
    agregarListeners();
    actualizar();
  }

  private int getNumeroArchivos() {
    return modelo.getArchivos().size();
  }

  private int getNumeroDirectorios() {
    return modelo.getDirectorios().size();
  }

  public void actualizar() {
    vista.setNombre(modelo.getNombre());
    vista.setUbicacion(modelo.getRutaCompleta());
    vista.setTamano(modelo.getTamanoTexto());
    int[] numElementos = modelo.getCantidadElementos();
    vista.setContenido(numElementos[1], numElementos[0]);
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

        boolean lectura = vista.cbSoloLectura.isSelected();
        boolean oculto = vista.cbOculto.isSelected();

        modelo.setEsSoloLecturaD(lectura);
        modelo.setEsArchivoOculto(oculto);

        JOptionPane.showMessageDialog(null, "Configuración actualizada correctamente.");
        actualizar(); // Actualiza la vista
        
        vista.dispose();
      }
    });
  }
}
