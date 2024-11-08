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
public class Directorio {

  private File file;
  private String nombre;
  private String ruta;
  private ArrayList<Archivo> listaArchivos;
  private ArrayList<Directorio> listaDirectorios;

  public Directorio(String pRuta) {
    file = new File(pRuta);
    ruta = pRuta;
    listaArchivos = new ArrayList<Archivo>();
    listaDirectorios = new ArrayList<Directorio>();
    
    cargarDatos();
  }

  private void cargarDatos() {
    for (File file : file.listFiles()) {
      String rutaArchivo = ruta + "\\" + file.getName();
      if (file.isFile()) {
        listaArchivos.add(new Archivo(rutaArchivo));
      } else {
        listaDirectorios.add(new Directorio(rutaArchivo));
      }
    }
  }
  
  private ArrayList<Archivo> getArchivos() {
    return listaArchivos;
  }
  
  private ArrayList<Directorio> getDirectorios() {
    return listaDirectorios;
  }

  public String[] list() {
    return file.list();
  }

  public File getDir() {
    return file;
  }
}
