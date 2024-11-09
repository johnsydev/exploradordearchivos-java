/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicadenegocios;

import java.io.File;

/**
 *
 * @author johns
 */
public class Elemento {

  protected File file;
  protected String nombre;
  protected String ruta;
  
  public Elemento(String pRuta) {
    file = new File(pRuta);
    nombre = file.getName();
    ruta = pRuta;
  }
  
  public boolean esArchivo() {
    return file.isFile();
  }
  
  public String getNombre() {
    return nombre;
  }
  
  public File getFile() {
    return file;
  }
  
  public boolean existe() {
    return file.exists();
  }
  
  public String getRuta() {
    return ruta;
  }
  
  public String getRutaCompleta() {
    try {
      return file.getCanonicalPath();
    } catch(Exception e) {
      return ruta;
    }
  }
  
  public long getTamano() {
    return file.length();
  }
  
  public String getTamanoTexto() {
    long tamano = getTamano();
    String unidad = "B";
    for(int i = 1; i<5; i++) {
      if(tamano/1000 > 0) {
        tamano = tamano/1000;
        switch(unidad) {
          case "B":
            unidad = "KB";
            break;
          case "KB":
            unidad = "MB";
            break;
           case "MB":
            unidad = "GB";
            break;
        }
      } else {
        break;
      }
    }
    return tamano + " " + unidad;
  }
}
