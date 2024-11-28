/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadenegocios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Representa un archivo en el sistema de archivos.
 * Extiende la clase Elemento para heredar funcionalidades básicas de elementos del sistema de archivos,
 * agregando operaciones específicas para el manejo de archivos como obtener su extensión,
 * tipo, eliminar y copiar.
 *
 * @author johns
 * @version 1.0
 * @see logicadenegocios.Elemento
 */
public class Archivo extends Elemento {

  /**
     * Constructor que inicializa un nuevo archivo con la ruta especificada.
     *
     * @param pRuta Ruta completa del archivo en el sistema
     */
  public Archivo(String pRuta) {
    super(pRuta);
  }

  public String getExtension() {
    int indiceExt = nombre.lastIndexOf(".");
    if (indiceExt >= 0) {
      return "." +nombre.substring(indiceExt + 1);
    }
    return "";
  }

  public boolean eliminar() {
    return file.delete();
  }

  public void pegar(String pRutaDestino) {
    try {
      Archivo archivoPegado = new Archivo(pRutaDestino + this.getNombre());
      Files.copy(this.getFile().toPath(), archivoPegado.getFile().toPath(), StandardCopyOption.REPLACE_EXISTING);
    } catch (Exception exc) {
    }
  }
}
