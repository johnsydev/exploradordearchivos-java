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
  
  private Explorador() {
    
  }
  
  public static Explorador getInstance() {
    return instance;
  }
  
  public String[] getListaArchivos() {
    File directorio = new File("./");
    String[] lista = directorio.list();
    for(String name : lista) {
      System.out.println(name);
    }
    System.out.println(directorio.getName());
    return lista;
  }
}
