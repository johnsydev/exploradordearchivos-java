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
}
