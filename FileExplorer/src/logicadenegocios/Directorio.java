/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadenegocios;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author johns
 */
public class Directorio extends Elemento {

  private ArrayList<Archivo> listaArchivos;
  private ArrayList<Directorio> listaDirectorios;
  private ArrayList<Elemento> listaElementos;

  public Directorio(String pRuta) {
    super(pRuta);
    listaArchivos = new ArrayList<Archivo>();
    listaDirectorios = new ArrayList<Directorio>();
    listaElementos = new ArrayList<Elemento>();
    cargarDatos();
  }

  private void cargarDatos() {
    for (File file : file.listFiles()) {
      String rutaArchivo = ruta + "\\" + file.getName();
      
      Elemento elemento = new Elemento(rutaArchivo);
      
      if (elemento.esArchivo()) {
        listaArchivos.add(new Archivo(rutaArchivo));
      } else {
        listaDirectorios.add(new Directorio(rutaArchivo));
      }

      listaElementos.add(elemento);
    }
  }
  
  public ArrayList<Archivo> getArchivos() {
    return listaArchivos;
  }
  
  public ArrayList<Directorio> getDirectorios() {
    return listaDirectorios;
  }
  
  public ArrayList<Elemento> get() {
    return listaElementos;
  }

  public String[] list() {
    return file.list();
  }

  public File getDir() {
    return file;
  }
  
  public boolean eliminar() {
    try {
      for(Directorio dir : listaDirectorios) {
        dir.eliminar();
      }
      for(Archivo arch : listaArchivos) {
        arch.eliminar();
      }
      file.delete();
    } catch(Exception e) {
      return false;
    }
    return true;
  }
  
  public ArrayList<Elemento> getElementos() {
    return listaElementos;
  }
}
