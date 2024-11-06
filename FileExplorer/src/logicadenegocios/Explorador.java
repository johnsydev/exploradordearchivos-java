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
import javax.swing.JOptionPane;

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
      try {
        //definiendo la ruta en la propiedad file
        Runtime.getRuntime().exec("cmd /c start \"\" \"" + ruta + src);
        return;

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    ruta += src + "\\";
  }

  public void renombrarArchivo(String oldName, String newName) {
    File archivoViejo = new File(ruta + oldName);
    File archivoNuevo = new File(ruta + newName);

    if (archivoViejo.exists() && !archivoNuevo.exists()) {
      boolean renombrado = archivoViejo.renameTo(archivoNuevo);
      if (renombrado) {
        JOptionPane.showMessageDialog(null, "Archivo renombrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(null, "Error al renombrar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
      }
    } else {
      JOptionPane.showMessageDialog(null, "El archivo no existe o ya existe un archivo con el nuevo nombre.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
  }

  public void salirDirectorio() {
    String[] rutaArray = ruta.split("\\\\");
    String nuevaRuta = "";
    int cont = 1;
    for (String varRuta : rutaArray) {
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
