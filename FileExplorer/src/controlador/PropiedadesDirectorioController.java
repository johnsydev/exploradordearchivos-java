/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import logicadenegocios.Directorio;
import vista.PropiedadesDirectorioForm;

/**
 *
 * @author johns
 */
public class PropiedadesDirectorioController {
  private Directorio modelo;
  private PropiedadesDirectorioForm vista;
  
  public PropiedadesDirectorioController(Directorio pDirectorio) {
    modelo = pDirectorio;
    vista = new PropiedadesDirectorioForm();
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
  }
}
