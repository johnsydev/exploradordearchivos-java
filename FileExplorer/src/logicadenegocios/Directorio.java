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
  
  private void pegarRecursivo(String pRuta, Directorio origen, String oldRuta) {
    Directorio dir = new Directorio(pRuta, false, false);
    dir.getFile().mkdirs();

    for (Archivo archivo : origen.getArchivos()) {
      archivo.pegar(pRuta);
    }

    Directorio dirOrigen = new Directorio(oldRuta, true, true);
    for (Directorio directorioOrigen : dirOrigen.getDirectorios()) {
      String nuevaRuta = pRuta + "\\" + directorioOrigen.getNombre() + "\\";
      dir.pegarRecursivo(nuevaRuta, directorioOrigen, oldRuta + "\\" + directorioOrigen.getNombre() + "\\");
    }
  }
  
  public void pegar(Directorio pDirectorioOrigen) {
    try {
      rutaPegandoInicial = ruta + "\\" + pDirectorioOrigen.getNombre() + "\\";
      Directorio dir = new Directorio(rutaPegandoInicial, false, false);
      dir.getFile().mkdirs();
      dir.pegarRecursivo(rutaPegandoInicial, pDirectorioOrigen, pDirectorioOrigen.getRuta());  
    } catch(Exception exc) {
      exc.printStackTrace();
    }
  }
  
  public int[] getCantidadElementos() {
    int[] cantidades = new int[2];
    int archivos = 0;
    int directoios = 0;
    
    Directorio dir = new Directorio(ruta, true, true);
    archivos += dir.getArchivos().size();
    for (Directorio subdir : dir.getDirectorios()) {
      directoios++;
      int[] subelementos = subdir.getCantidadElementos();
      archivos += subelementos[0];
      directoios += subelementos[1];
    }
    cantidades[0] = archivos;
    cantidades[1] = directoios;
    return cantidades;
  }
}
