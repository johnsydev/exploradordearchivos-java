/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadenegocios;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 *
 * @author johnsy
 */
public class Explorador {
  private static final Explorador instance = new Explorador();
  //private String ruta = "C:\\";
  private String ruta = ".\\";
  
  private Explorador() {
    
  }
  
  public static Explorador getInstance() {
    return instance;
  }
  
  public String[] getListaArchivos() {
    File directorio = new File(ruta);
    String[] lista = directorio.list();
    return lista;
  }
  
  public void entrarDirectorio(String src) {
    ruta += src + "\\";
  } 
  
  public void salirDirectorio() {
    String[] rutaArray = ruta.split("\\\\");
    String nuevaRuta = "";
    int cont = 1;
    for(String varRuta : rutaArray) {
      if (cont != rutaArray.length) {
        nuevaRuta += varRuta + "\\";
      }
      cont++;
    }
    System.out.println(ruta);
    ruta = nuevaRuta;
  }
}
