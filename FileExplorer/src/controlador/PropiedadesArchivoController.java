/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

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
    actualizar();
  }
  
  public void actualizar() {
    vista.setNombre(modelo.getNombre());
    vista.setExtension(modelo.getExtension());
    vista.setUbicacion(modelo.getRutaCompleta());
    vista.setTamano(modelo.getTamanoTexto());
  }
  
}
