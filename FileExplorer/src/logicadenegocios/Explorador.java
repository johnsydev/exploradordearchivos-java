/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadenegocios;

import java.io.File;
import java.io.IOException;
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
    
    if (new File(ruta + src).isFile()) {
      try{ 
        //definiendo la ruta en la propiedad file
        Runtime.getRuntime().exec("cmd /c start \"\" \""+ruta+src);
        return;

        } catch(IOException e){
           e.printStackTrace();
        }
      }
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
  
  public void eliminarArchivo(String nombre) {
    File archivo = new File(ruta + nombre);
    archivo.delete();
  }
}
