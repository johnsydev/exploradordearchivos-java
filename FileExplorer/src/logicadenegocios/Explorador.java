package logicadenegocios;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Explorador {

  private static final Explorador instance = new Explorador();
  private String ruta = ".\\";
  private Archivo archivoCopiado; // Variable temporal para almacenar el archivo copiado
  private Directorio directorioActual = new Directorio(ruta);

  private Explorador() {
  }

  public static Explorador getInstance() {
    return instance;
  }
  
  public Elemento getElemento(String pNombre) {
    for (Elemento elem : directorioActual.get()) {
      if (elem.getNombre().equals(pNombre)) {
        return elem;
      }
    }
    return null;
  }
  
  public void actualizar() {
    directorioActual = new Directorio(ruta);
  }
  
  public ArrayList<Elemento> getListaArchivos() {
    return directorioActual.get();
  }
  
  public ArrayList<String> getListaNombres() {
    ArrayList<String> nombres = new ArrayList<String>();
    for (Elemento elem : directorioActual.get()) {
      nombres.add(elem.getNombre());
    }
    return nombres;
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
    Elemento elem = new Elemento(pRuta);
    if (elem.esArchivo()) {
      Archivo a = new Archivo(pRuta);
      a.eliminar();
    } else {
      Directorio d = new Directorio(pRuta);
      d.eliminar();
    }
  }

  // Método para copiar un archivo
  public void copiarArchivo(String nombreArchivo) {
    Elemento elem = new Elemento(ruta + nombreArchivo);
    if (elem.existe()) {
      if (elem.esArchivo()) {
        archivoCopiado = new Archivo(ruta + nombreArchivo);
        JOptionPane.showMessageDialog(null, "Archivo copiado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(null, "El archivo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
  
  // Método para pegar un archivo copiado en el directorio actual
  public void pegarArchivo(boolean esCortado) {
    if (archivoCopiado == null) {
      JOptionPane.showMessageDialog(null, "No hay ningún archivo copiado para pegar.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    Archivo archivoPegado = new Archivo(ruta + archivoCopiado.getNombre());

    // Si ya existe un archivo con el mismo nombre en el directorio actual
    if (archivoPegado.existe()) {
      // Proponer un nuevo nombre o confirmar reemplazo
      int opcion = JOptionPane.showConfirmDialog(null, 
              "Ya existe un archivo con el mismo nombre. ¿Deseas reemplazarlo?", 
              "Archivo existente", JOptionPane.YES_NO_OPTION);

      if (opcion == JOptionPane.NO_OPTION) {
        archivoPegado = new Archivo(ruta + obtenerNombreDisponible(archivoPegado));
      }
    }

    try {
      System.out.println("ARCHIVO CORTADO " + archivoCopiado.getFile().getCanonicalPath());
      Files.copy(archivoCopiado.getFile().toPath(), archivoPegado.getFile().toPath(), StandardCopyOption.REPLACE_EXISTING);
      if (esCortado) {
        eliminarArchivo(archivoCopiado.getFile().getCanonicalPath());
      }
      JOptionPane.showMessageDialog(null, "Archivo pegado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Error al pegar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
    }
  }

  // Método auxiliar para obtener un nombre de archivo disponible (archivo(1).txt, archivo(2).txt, etc.)
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
