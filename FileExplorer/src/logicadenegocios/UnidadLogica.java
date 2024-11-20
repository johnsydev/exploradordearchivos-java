/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadenegocios;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Keyne
 */
public class UnidadLogica extends Elemento {

  public UnidadLogica(String pRuta) {
    super(pRuta);
  }
  
  public static ArrayList<UnidadLogica> getUnidades() {
    ArrayList<UnidadLogica> unidades = new ArrayList<UnidadLogica>();
    for (File unidad : File.listRoots()) {
      unidades.add(new UnidadLogica(unidad.getPath()));
    }
    return unidades;
  }

  public String getNombre() {
    try {
      // Para Windows, la ruta inicial (como "C:\") puede ser el nombre
      String absolutePath = file.getAbsolutePath();
      if (absolutePath.length() > 2 && absolutePath.charAt(1) == ':') {
        return absolutePath.substring(0, 2); // Devuelve "C:", "D:", etc.
      } else {
        return absolutePath; // Para otros sistemas, devolver la ruta base
      }
    } catch (Exception e) {
      return "Error al obtener el nombre";
    }
  }

  public String getTipo() {
    try {
      java.nio.file.FileStore fileStore = java.nio.file.Files.getFileStore(file.toPath());

      // Intentar identificar el tipo de unidad lógica
      if (fileStore.type().equalsIgnoreCase("NTFS") || fileStore.type().equalsIgnoreCase("FAT32")) {
        return "Disco Local";
      } else if (fileStore.type().equalsIgnoreCase("exFAT") || fileStore.type().toLowerCase().contains("removable")) {
        return "Unidad Extraíble";
      } else if (fileStore.type().toLowerCase().contains("network")) {
        return "Unidad de Red";
      } else {
        return "Tipo Desconocido";
      }
    } catch (IOException e) {
      return "Error al determinar el tipo";
    }
  }

  public String getSistemaArchivos() {
    try {
      java.nio.file.FileStore fileStore = java.nio.file.Files.getFileStore(file.toPath());
      return fileStore.type(); // Ejemplo: NTFS, FAT32, etc.
    } catch (IOException e) {
      return "Desconocido";
    }
  }

  public String getEspacioTotal() {
    return getTamanoTexto(file.getTotalSpace());
  }

  public String getEspacioLibre() {
    return getTamanoTexto(file.getFreeSpace());
  }

  public String getEspacioUsado() {
    return getTamanoTexto(file.getTotalSpace() - file.getFreeSpace());
  }

  public long getNumUsado() {
    return (file.getTotalSpace() - file.getFreeSpace());
  }

  public long getNumTotal() {
    return file.getTotalSpace();
  }

  private String getTamanoTexto(long tamano) {
    String unidad = "B";
    long tamanoOriginal = tamano;
    long tamanoProcesado = tamano;

    for (int i = 1; i < 5; i++) {
      if (tamanoProcesado / 1024 > 0) {
        tamanoProcesado = tamanoProcesado / 1024;
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
          case "GB":
            unidad = "TB";
            break;
        }
      } else {
        break;
      }
    }
    return String.format("%,d bytes        %.0f %s ", tamanoOriginal, (double) tamanoProcesado, unidad);
  }
}
