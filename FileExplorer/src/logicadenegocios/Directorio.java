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
public class Directorio {
  private File file;
  private String nombre;
  private String ruta;
  
  public Directorio(String pRuta) {
    file = new File(pRuta);
    ruta = pRuta;
  }
  
  public String[] list() {
    return file.list();
  }
  
  public File getDir() {
    return file;
  }
}
