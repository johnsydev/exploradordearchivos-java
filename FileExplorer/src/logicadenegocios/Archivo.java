/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadenegocios;

import java.io.File;

/**
 *
 * @author johns
 */
public class Archivo {
  private File file;
  private String nombre;
  private String ruta;
  
  public Archivo(String pRuta) {
    file = new File(pRuta);
    ruta = pRuta;
  }
  
  public String getExtension() {
    int indiceExt = nombre.lastIndexOf(".");
    if (indiceExt >= 0) {
        return nombre.substring(indiceExt + 1);
    }
    return "";
  }
  
  public File getFile() {
    return file;
  }
}
