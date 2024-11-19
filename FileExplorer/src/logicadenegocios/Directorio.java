/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadenegocios;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 *
 * @author johns
 */
public class Directorio extends Elemento {

  private ArrayList<Archivo> listaArchivos;
  private ArrayList<Directorio> listaDirectorios;
  private ArrayList<Elemento> listaElementos;
  
  private String rutaPegandoInicial;
  
  public enum ORDENAR_POR {NOMBRE, TAMANO};
  
  public Directorio(String pRuta) {
    super(pRuta);
    listaArchivos = new ArrayList<Archivo>();
    listaDirectorios = new ArrayList<Directorio>();
    listaElementos = new ArrayList<Elemento>();
    cargarDatos(false);
  }

  public Directorio(String pRuta, boolean generarRamas, boolean generarTodas) {
    super(pRuta);
    listaArchivos = new ArrayList<Archivo>();
    listaDirectorios = new ArrayList<Directorio>();
    listaElementos = new ArrayList<Elemento>();
    if (generarRamas) {
      cargarDatos(generarTodas);
    }
  }
  
  public void ordenar(ORDENAR_POR criterio) {
    if (criterio.equals(ORDENAR_POR.NOMBRE)) {
      listaElementos.clear();
      cargarDatos(false);
    }
  }

  private void cargarDatos(boolean pGenerarTodo) {
    ArrayList<Elemento> archivosTemporal = new ArrayList<Elemento>();
    for (File file : file.listFiles()) {
      String rutaArchivo = ruta + "\\" + file.getName();
      
      Elemento elemento = new Elemento(rutaArchivo);
      
      if (elemento.esArchivo()) {
        listaArchivos.add(new Archivo(rutaArchivo));
        archivosTemporal.add(elemento);
      } else {
        listaDirectorios.add(new Directorio(rutaArchivo, pGenerarTodo, pGenerarTodo));
        listaElementos.add(elemento);
      }
    }
    listaElementos.addAll(archivosTemporal);
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
  
  
  public void pegar(Directorio pDirectorioOrigen) {
    try {
      rutaPegandoInicial = ruta + "\\" + pDirectorioOrigen.getNombre() + "\\";
      String rutaPegando = rutaPegandoInicial;
      Directorio dir = new Directorio(rutaPegandoInicial, false, false);
      dir.getFile().mkdirs();
      for (Directorio directorio : pDirectorioOrigen.getDirectorios()) {
        //Directorio subDirectorio = new Directorio(ruta + "\\" + directorio.getNombre(), false, false);
        Directorio subDirectorio = new Directorio(rutaPegandoInicial + "\\" + directorio.getNombre(), false, false);
        subDirectorio.pegar(directorio);
      }
      for (Archivo archivo : pDirectorioOrigen.getArchivos()) {
        archivo.pegar(rutaPegando);
      }
    } catch(Exception exc) {
      exc.printStackTrace();
    }
  }
}
