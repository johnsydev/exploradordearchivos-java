/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicadenegocios;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* Clase base que representa un elemento del sistema de archivos (archivo o directorio).
* Proporciona funcionalidad común para manejar atributos y operaciones básicas de archivos
* y directorios como nombres, rutas, fechas, tamaños y permisos.
*
* @author johns
* @version 1.0
*/
public class Elemento {

  protected File file;
  protected String nombre;
  protected String ruta;

  /**
    * Constructor que inicializa un elemento con la ruta especificada.
    *
    * @param pRuta Ruta completa del elemento en el sistema de archivos
    */
  public Elemento(String pRuta) {
    file = new File(pRuta);
    nombre = file.getName();
    ruta = pRuta;
  }

  /**
    * Verifica si el elemento es un archivo.
    *
    * @return true si es archivo, false si es directorio
    */
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

  /**
    * Obtiene la ruta canónica completa del elemento.
    *
    * @return Ruta canónica completa, o ruta normal si no se puede obtener la canónica
    */
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

  /**
    * Calcula y formatea el tamaño del elemento con la unidad apropiada (B, KB, MB, GB).
    *
    * @return Tamaño formateado con su unidad (ej: "15 MB")
    */
  public String getTamanoSimpleTexto() {
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
    return tamano + " " + unidad;
  }

  /**
    * Obtiene el tamaño completo del elemento incluyendo bytes.
    *
    * @return Tamaño formateado con unidad y bytes (ej: "15 MB (15728640 bytes)")
    */
  public String getTamanoTexto() {
    return getTamanoSimpleTexto() + " (" + getTamano() + " bytes)";
  }

  /**
    * Obtiene la fecha de creación del elemento.
    *
    * @return Fecha formateada como "dd/MM/yyyy HH:mm:ss" o "Fecha no disponible" si hay error
    */
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

   /**
    * Obtiene la fecha de última modificación del elemento.
    *
    * @return Fecha formateada como "dd/MM/yyyy HH:mm:ss" o "Fecha no disponible" si hay error
    */
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

  /**
    * Obtiene la fecha del último acceso al elemento.
    *
    * @return Fecha formateada como "dd/MM/yyyy HH:mm:ss" o "Fecha no disponible" si hay error
    */
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

  public String getTipo() {
    if (!esArchivo()) {
      return "Carpeta";
    } else {
      int indiceExt = nombre.lastIndexOf(".");
      if (indiceExt >= 0) {
        return "Archivo " + nombre.substring(indiceExt + 1).toUpperCase();
      }
    }
    return "";
  }
  
  public String getTipoSimple() {
    return getTipo();
  }

  public boolean getEsArchivoOculto() {
    return file.isHidden();
  }

  public boolean getEsSoloLectura() {
    return !file.canWrite();
  }

   /**
    * Establece el atributo oculto del elemento.
    *
    * @param oculto true para ocultar el elemento, false para hacerlo visible
    */
  public void setEsArchivoOculto(boolean oculto) {
    if (oculto) {
      try {
        String comando = "attrib +h " + file.getAbsolutePath();
        Runtime.getRuntime().exec(comando);
      } catch (IOException e) {
      }
    } else {
      try {

        String comando = "attrib -h " + file.getAbsolutePath();
        Runtime.getRuntime().exec(comando);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

   /**
    * Establece el atributo de solo lectura para archivos.
    *
    * @param soloLectura true para hacer el archivo de solo lectura, false para permitir escritura
    */
  public void setEsSoloLectura(boolean soloLectura) {
    if (soloLectura) {
      file.setWritable(false);  // Evita la escritura
    } else {
      // Permitir la escritura en el archivo
      file.setWritable(true);
    }
  }

  /**
    * Establece el atributo de solo lectura para directorios.
    * Permite listar el contenido pero evita modificaciones.
    *
    * @param soloLectura true para hacer el directorio de solo lectura, false para permitir modificaciones
    */
  public void setEsSoloLecturaD(boolean soloLectura) {
    if (soloLectura) {
      file.setWritable(false); // Evita modificar su contenido
      file.setReadable(true);  // Permite listar el contenido
    } else {
      // Permitir la escritura en el archivo
      file.setWritable(true);
      file.setReadable(true); // Habilita la lectura
    }
  }
}
