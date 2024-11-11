/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicadenegocios;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;

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

  public String getRuta() {
    return ruta;
  }

  public String getRutaCompleta() {
    try {
      return file.getCanonicalPath();
    } catch (Exception e) {
      return ruta;
    }
  }

  public long getTamano() {
    return file.length();
  }

  public String getTamanoTexto() {
    long tamano = getTamano();
    long tamano1 = getTamano();
    String unidad = "B";
    for (int i = 1; i < 5; i++) {
      if (tamano / 1000 > 0) {
        tamano = tamano / 1000;
        switch (unidad) {
          case "B":
            unidad = "KB";
            break;
          case "KB":
            unidad = "MB";
            break;
          case "MB":
            unidad = "GB";
            break;
        }
      } else {
        break;
      }
    }
    return tamano + " " + unidad + " (" + tamano1 + " bytes)";
  }

  public String getFechaCreacion() {
    try {
      Path path = file.toPath();
      BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
      Date fechaCreacion = new Date(attr.creationTime().toMillis());
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      return sdf.format(fechaCreacion);
    } catch (Exception e) {
      return "Fecha no disponible";
    }
  }

  // Obtener la última modificación
  public String getUltimaModificacion() {
    try {
      Path path = file.toPath();
      BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
      Date ultimaModificacion = new Date(attr.lastModifiedTime().toMillis());
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      return sdf.format(ultimaModificacion);
    } catch (Exception e) {
      return "Fecha no disponible";
    }
  }

  // Obtener el último acceso
  public String getUltimoAcceso() {
    try {
      Path path = file.toPath();
      BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
      Date ultimoAcceso = new Date(attr.lastAccessTime().toMillis());
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      return sdf.format(ultimoAcceso);
    } catch (Exception e) {
      return "Fecha no disponible";
    }
  }
}
