package logicadenegocios;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JOptionPane;

public class Explorador {

  private static final Explorador instance = new Explorador();
  private String ruta = ".\\";
  private File archivoCopiado; // Variable temporal para almacenar el archivo copiado
  private Directorio directorioActual;

  private Explorador() {
  }

  public static Explorador getInstance() {
    return instance;
  }

  public String[] getListaArchivos() {
    File directorio = new File(ruta);
    return directorio.list();
  }

  public void entrarDirectorio(String src) {
    if (new File(ruta + src).isFile()) {
      try {
        Runtime.getRuntime().exec("cmd /c start \"\" \"" + ruta + src);
        return;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    ruta += src + "\\";
    directorioActual = new Directorio(ruta);
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
    ruta = nuevaRuta;
    directorioActual = new Directorio(ruta);
  }
  
  public boolean crearDirectorio(String pNombre) {
    File carpeta = new File(ruta + pNombre);
    if (!carpeta.exists()) {
      return carpeta.mkdirs();
    }
    return false;
  }

  public void eliminarArchivoInterfaz(String nombre) {
    eliminarArchivo(ruta + nombre);
  }
  
  public void eliminarArchivo(String pRuta) {
    File archivo = new File(pRuta);
    archivo.delete();
  }

  // Método para copiar un archivo
  public void copiarArchivo(String nombreArchivo) {
    File archivo = new File(ruta + nombreArchivo);
    if (archivo.exists()) {
      archivoCopiado = archivo;
      JOptionPane.showMessageDialog(null, "Archivo copiado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null, "El archivo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
  
  // Método para pegar un archivo copiado en el directorio actual
  public void pegarArchivo(boolean esCortado) {
    if (archivoCopiado == null) {
      JOptionPane.showMessageDialog(null, "No hay ningún archivo copiado para pegar.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    File archivoPegado = new File(ruta + archivoCopiado.getName());

    // Si ya existe un archivo con el mismo nombre en el directorio actual
    if (archivoPegado.exists()) {
      // Proponer un nuevo nombre o confirmar reemplazo
      int opcion = JOptionPane.showConfirmDialog(null, 
              "Ya existe un archivo con el mismo nombre. ¿Deseas reemplazarlo?", 
              "Archivo existente", JOptionPane.YES_NO_OPTION);

      if (opcion == JOptionPane.NO_OPTION) {
        archivoPegado = new File(ruta + obtenerNombreDisponible(archivoPegado));
      }
    }

    try {
      System.out.println("ARCHIVO CORTADO " + archivoCopiado.getCanonicalPath());
      Files.copy(archivoCopiado.toPath(), archivoPegado.toPath(), StandardCopyOption.REPLACE_EXISTING);
      if (esCortado) {
        eliminarArchivo(archivoCopiado.getCanonicalPath());
      }
      JOptionPane.showMessageDialog(null, "Archivo pegado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Error al pegar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
    }
  }

  // Método auxiliar para obtener un nombre de archivo disponible (archivo(1).txt, archivo(2).txt, etc.)
  private String obtenerNombreDisponible(File archivo) {
    String nombreOriginal = archivo.getName();
    String nombreSinExtension = nombreOriginal.contains(".") 
            ? nombreOriginal.substring(0, nombreOriginal.lastIndexOf(".")) 
            : nombreOriginal;
    String extension = nombreOriginal.contains(".") 
            ? nombreOriginal.substring(nombreOriginal.lastIndexOf(".")) 
            : "";

    int contador = 1;
    File archivoNuevo = archivo;
    while (archivoNuevo.exists()) {
      archivoNuevo = new File(ruta + nombreSinExtension + "(" + contador + ")" + extension);
      contador++;
    }
    return archivoNuevo.getName();
  }
}
