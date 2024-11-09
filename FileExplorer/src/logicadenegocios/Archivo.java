/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadenegocios;

/**
 *
 * @author johns
 */
public class Archivo extends Elemento {

  
  public Archivo(String pRuta) {
    super(pRuta);
  }
  
  public String getExtension() {
    int indiceExt = nombre.lastIndexOf(".");
    if (indiceExt >= 0) {
        return nombre.substring(indiceExt + 1);
    }
    return "";
  }
  
  public boolean eliminar() {
    return file.delete();
  }
}
