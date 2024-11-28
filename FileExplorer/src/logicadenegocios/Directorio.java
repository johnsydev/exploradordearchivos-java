/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadenegocios;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
* Representa un directorio en el sistema de archivos, gestionando su contenido y operaciones.
* Extiende la clase Elemento para proporcionar funcionalidades específicas de directorios como
* listar contenido, ordenar elementos, copiar recursivamente y gestionar la estructura jerárquica.
*
* @author johns
* @version 1.0
* @see logicadenegocios.Elemento
*/
public class Directorio extends Elemento {

  private ArrayList<Archivo> listaArchivos;
  private ArrayList<Directorio> listaDirectorios;
  private ArrayList<Elemento> listaElementos;

  private String rutaPegandoInicial;

  public enum ORDENAR_POR {
    NOMBRE, TAMANO, TIPO
  };
  
   /**
    * Constructor básico que inicializa un directorio con la ruta especificada.
    *
    * @param pRuta Ruta completa del directorio
    */
  public Directorio(String pRuta) {
    super(pRuta);
    listaArchivos = new ArrayList<Archivo>();
    listaDirectorios = new ArrayList<Directorio>();
    listaElementos = new ArrayList<Elemento>();
    cargarDatos(false);
  }

  /**
    * Constructor que permite especificar si se deben cargar los subdirectorios.
    *
    * @param pRuta Ruta completa del directorio
    * @param generarRamas Si se deben cargar los subdirectorios
    * @param generarTodas Si se debe cargar toda la estructura jerárquica
    */
  public Directorio(String pRuta, boolean generarRamas, boolean generarTodas) {
    super(pRuta);
    listaArchivos = new ArrayList<Archivo>();
    listaDirectorios = new ArrayList<Directorio>();
    listaElementos = new ArrayList<Elemento>();
    if (generarRamas) {
      cargarDatos(generarTodas);
    }
  }

  /**
    * Ordena los elementos del directorio según el criterio especificado.
    *
    * @param criterio Criterio de ordenamiento (NOMBRE o TAMANO)
    * @param esAscedente true para orden ascendente, false para descendente
    */
  public void ordenar(ORDENAR_POR criterio, boolean esAscedente) {
    if (criterio.equals(ORDENAR_POR.NOMBRE)) {
      listaElementos.clear();
      listaDirectorios.clear();
      listaArchivos.clear();
      cargarDatos(false);
      if (!esAscedente) {
        Collections.reverse(listaElementos);
      }
    } else if (criterio.equals(ORDENAR_POR.TAMANO)) {
      ArrayList<Elemento> listaNueva = new ArrayList<Elemento>();
      if (esAscedente) {
        listaDirectorios.sort(Comparator.comparingLong(Elemento::getTamano));
        listaArchivos.sort(Comparator.comparingLong(Elemento::getTamano));
      } else {
        listaDirectorios.sort(Comparator.comparingLong(Elemento::getTamano).reversed());
        listaArchivos.sort(Comparator.comparingLong(Elemento::getTamano).reversed());
      }
      listaNueva.addAll(listaDirectorios);
      listaNueva.addAll(listaArchivos);
      listaElementos.clear();
      listaElementos.addAll(listaNueva);
    } else if (criterio.equals(ORDENAR_POR.TIPO)) {
      ArrayList<Elemento> listaNueva = new ArrayList<Elemento>();
      if (esAscedente) {
          listaDirectorios.sort(Comparator.comparing(Elemento::getTipoSimple));
          listaArchivos.sort(Comparator.comparing(Elemento::getTipoSimple));
      } else {
          listaDirectorios.sort(Comparator.comparing(Elemento::getTipoSimple).reversed());
          listaArchivos.sort(Comparator.comparing(Elemento::getTipoSimple).reversed());
      }
      listaNueva.addAll(listaDirectorios);
      listaNueva.addAll(listaArchivos);
      listaElementos.clear();
      listaElementos.addAll(listaNueva);
    }
  }

  /**
    * Carga los archivos y subdirectorios contenidos en este directorio.
    *
    * @param pGenerarTodo Si se debe generar toda la estructura jerárquica
    */
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

  /**
    * Elimina recursivamente el directorio y todo su contenido.
    *
    * @return true si la eliminación fue exitosa, false en caso contrario
    */
  public boolean eliminar() {
    try {
      for (Directorio dir : listaDirectorios) {
        dir.eliminar();
      }
      for (Archivo arch : listaArchivos) {
        arch.eliminar();
      }
      file.delete();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public ArrayList<Elemento> getElementos() {
    return listaElementos;
  }

   /**
    * Copia recursivamente un subdirectorio y su contenido.
    *
    * @param pRuta Ruta destino donde se copiará
    * @param origen Directorio origen a copiar
    * @param oldRuta Ruta original del directorio
    */
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

  /**
    * Copia un directorio completo a la ubicación actual.
    *
    * @param pDirectorioOrigen Directorio que se copiará
    */
  public boolean pegar(Directorio pDirectorioOrigen) {
    try {
      rutaPegandoInicial = ruta + "\\" + pDirectorioOrigen.getNombre() + "\\";
      Directorio dir = new Directorio(rutaPegandoInicial, false, false);
      if (this.file.getCanonicalPath().equals(pDirectorioOrigen.getFile().getCanonicalPath())) {
        return false;
      }
      dir.getFile().mkdirs();
      dir.pegarRecursivo(rutaPegandoInicial, pDirectorioOrigen, pDirectorioOrigen.getRuta());
    } catch (Exception exc) {
      exc.printStackTrace();
      return false;
    }
    return true;
  }

   /**
    * Calcula la cantidad total de archivos y subdirectorios contenidos.
    *
    * @return Array con [cantidad de archivos, cantidad de directorios]
    */
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
