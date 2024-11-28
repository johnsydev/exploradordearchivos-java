package logicadenegocios;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

/**
* Gestiona la navegación, manipulación y visualización de archivos y directorios
* en el sistema de archivos.
*
* @author  
* @version 1.0
* @see logicadenegocios.Archivo
* @see logicadenegocios.Directorio
* @see logicadenegocios.Elemento
*/
public class Explorador {

  private static final Explorador instance = new Explorador();
  private String ruta = "C:\\";
  private Archivo archivoCopiado; // Variable temporal para almacenar el archivo copiado
  private Directorio directorioCopiado;
  private Directorio directorioActual = new Directorio(ruta);
  private Directorio.ORDENAR_POR criterioOrden = Directorio.ORDENAR_POR.NOMBRE;
  private boolean ordenAscendente = true;

  private Explorador() {
  }

  /**
    * Obtiene la instancia única del explorador.
    *
    * @return Instancia del explorador
    */
  public static Explorador getInstance() {
    return instance;
  }

  /**
    * Busca un elemento por nombre en el directorio actual.
    *
    * @param pNombre Nombre del elemento a buscar
    * @return El elemento encontrado o null si no existe
    */
  public Elemento getElemento(String pNombre) {
    for (Elemento elem : directorioActual.get()) {
      if (elem.getNombre().equals(pNombre)) {
        return elem;
      }
    }
    return null;
  }

  /**
    * Actualiza el directorio actual y reordena sus elementos.
    */
  public void actualizar() {
    directorioActual = new Directorio(ruta);
    ordenar();
  }
  
  /**
    * Establece el criterio de ordenamiento.
    *
    * @param pCriterio "Nombre" o "Tamaño"
    */
  public void setCriterioOrden(String pCriterio) {
    switch(pCriterio) {
      case "Nombre":
        criterioOrden = Directorio.ORDENAR_POR.NOMBRE;
        break;
      case "Tamaño":
        criterioOrden = Directorio.ORDENAR_POR.TAMANO;
        break;
      case "Tipo":
        criterioOrden = Directorio.ORDENAR_POR.TIPO;
        break;
    }
  }
  
  public void setModoOrden(String pModo) {
    ordenAscendente = false;
    if (pModo.equals("Ascendente")) {
      ordenAscendente = true;
    }
  }
  
  public void ordenar() {
    directorioActual.ordenar(criterioOrden, ordenAscendente);
  }

  public ArrayList<Elemento> getListaElementos() {
    return directorioActual.get();
  }
  
  public String getRuta() {
    return ruta;
  }

  public ArrayList<String> getListaNombres() {
    ArrayList<String> nombres = new ArrayList<String>();
    for (Elemento elem : directorioActual.get()) {
      nombres.add(elem.getNombre());
    }
    return nombres;
  }
  /**
    * Navega a un directorio o abre un archivo.
    * Si es un archivo, lo abre con la aplicación predeterminada.
    * Si es un directorio, actualiza la ruta actual.
    *
    * @param src Nombre del elemento a abrir/navegar
    * @return true si la operación fue exitosa, false en caso contrario
    */
  public boolean entrarDirectorio(String src) {
    if (new File(ruta + src).isFile()) {
      try {
        Runtime.getRuntime().exec("cmd /c start \"\" \"" + ruta + src);
        return true;
      } catch (IOException e) {
        return false;
      }
    }
    String rutaAntigua = ruta;
    ruta += src + "\\";
    try {
      directorioActual = new Directorio(ruta);
    } catch (Exception e) {
      ruta = rutaAntigua;
      return false;
    }
    return true;
  }

  public void renombrarArchivo(String oldName, String newName) {
    File archivoViejo = new File(ruta + oldName);
    File archivoNuevo = new File(ruta + newName);

    if (archivoViejo.exists() && !archivoNuevo.exists()) {
      archivoViejo.renameTo(archivoNuevo);
    }
  }

  public boolean salirDirectorio() {
    String[] rutaArray = ruta.split("\\\\");
    String nuevaRuta = "";
    int cont = 1;
    for (String varRuta : rutaArray) {
      if (cont != rutaArray.length) {
        nuevaRuta += varRuta + "\\";
      }
      cont++;
    }
    ruta = nuevaRuta;
    if (ruta.equals("")) {
      return false;
    }
    directorioActual = new Directorio(ruta);
    return true;
  }
  
  

  /**
    * Crea un nuevo directorio en la ruta actual.
    * Valida que el nombre cumpla con las restricciones:
    * - Máximo 64 caracteres
    * - Solo letras, números, guiones y guiones bajos
    *
    * @param pNombre Nombre del nuevo directorio
    * @return true si se creó exitosamente, false en caso contrario
    */
  public boolean crearDirectorio(String pNombre) {
    if (pNombre.length() > 64) {
      return false;
    }
    if (!pNombre.matches("[a-zA-Z0-9_-]+")) {
      return false;
    }
    File carpeta = new File(ruta + pNombre);
    if (!carpeta.exists()) {
      return carpeta.mkdirs();
    }
    return false;
  }

  /**
 * Elimina un elemento (archivo o directorio) desde la interfaz del explorador.
 * Este método sirve como wrapper para eliminarElemento, agregando la ruta actual
 * al nombre del elemento antes de eliminarlo.
 *
 * @param nombre Nombre del elemento a eliminar (sin la ruta)
 * @see #eliminarElemento(String)
 */
  public void eliminarElementoInterfaz(String nombre) {
    eliminarElemento(ruta + nombre);
  }

  /**
 * Elimina un elemento del sistema de archivos.
 * Determina si el elemento es un archivo o directorio y aplica el método
 * de eliminación apropiado. Para directorios, realiza una eliminación recursiva
 * de todo su contenido.
 *
 * @param pRuta Ruta completa del elemento a eliminar
 */
  public void eliminarElemento(String pRuta) {
    Elemento elem = new Elemento(pRuta);
    if (elem.esArchivo()) {
      Archivo a = new Archivo(pRuta);
      a.eliminar();
    } else {
      Directorio d = new Directorio(pRuta, true, true);
      boolean estado = d.eliminar();
    }
  }

  /**
    * Copia un archivo para operaciones posteriores de pegado.
    *
    * @param nombreArchivo Nombre del archivo a copiar
    */
  public void copiarArchivo(String nombreArchivo) {
    Elemento elem = new Elemento(ruta + nombreArchivo);
    if (elem.existe() && elem.esArchivo()) {
      archivoCopiado = new Archivo(ruta + nombreArchivo);
      directorioCopiado = null;
    }
  }
  
  public void copiarDirectorio(String pNombreDirectorio) {
    Elemento elem = new Elemento(ruta + pNombreDirectorio);
    if (elem.existe() && !elem.esArchivo()) {
      directorioCopiado = new Directorio(ruta + pNombreDirectorio, true, true);
      archivoCopiado = null;
    }
  }

  /**
    * Pega el elemento previamente copiado (archivo o directorio) en la ubicación actual.
    * Si ya existe un elemento con el mismo nombre, genera un nombre alternativo.
    *
    * @param esCortado true si se debe eliminar el original después de pegar, false para mantenerlo.
    * @return true si se pegó correctamente.
    */
  public boolean pegar(boolean esCortado) {
    if (archivoCopiado != null && directorioCopiado == null) {
      Archivo archivoPegado = new Archivo(ruta + archivoCopiado.getNombre());

      // Si ya existe un archivo con el mismo nombre en el directorio actual
      if (archivoPegado.existe()) {
        // Aquí puedes manejar el conflicto de nombres de otra forma, por ejemplo, solo renombrando
        archivoPegado = new Archivo(ruta + obtenerNombreDisponible(archivoPegado));
      }

      try {
        Files.copy(archivoCopiado.getFile().toPath(), archivoPegado.getFile().toPath(), StandardCopyOption.REPLACE_EXISTING);
        if (esCortado) {
          eliminarElemento(archivoCopiado.getFile().getCanonicalPath());
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else if (archivoCopiado == null && directorioCopiado != null) {
      try {
        boolean estado = directorioActual.pegar(directorioCopiado);
        if (esCortado && estado) {
          directorioCopiado.eliminar();
        }
        return estado;
      } catch(Exception exc) {
        exc.printStackTrace();
      }
    } else {
      //no hay nada copiado
    }
    return true;
  }

   /**
    * Genera un nombre de archivo disponible cuando ya existe uno con el mismo nombre.
    * Agrega un número entre paréntesis antes de la extensión (ej: archivo(1).txt).
    *
    * @param archivo Archivo base para generar el nuevo nombre
    * @return Nombre disponible para el archivo
    */
  private String obtenerNombreDisponible(Archivo archivo) {
    String nombreOriginal = archivo.getNombre();
    String nombreSinExtension = nombreOriginal.contains(".")
            ? nombreOriginal.substring(0, nombreOriginal.lastIndexOf("."))
            : nombreOriginal;
    String extension = nombreOriginal.contains(".")
            ? nombreOriginal.substring(nombreOriginal.lastIndexOf("."))
            : "";

    int contador = 1;
    Archivo archivoNuevo = archivo;
    while (archivoNuevo.existe()) {
      archivoNuevo = new Archivo(ruta + nombreSinExtension + "(" + contador + ")" + extension);
      contador++;
    }
    return archivoNuevo.getNombre();
  }
}
