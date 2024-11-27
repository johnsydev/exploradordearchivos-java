/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aplicacion;

import controlador.ExploradorController;
import java.io.IOException;
import logicadenegocios.Explorador;
import vista.ExploradorForm;
import vista.PropiedadesArchivoForm;

/**
 * Clase principal que inicia la aplicación del explorador de archivos.
 *
 * <p>Esta clase inicializa los componentes principales de la aplicación siguiendo
 * el patrón MVC (Modelo-Vista-Controlador), creando las instancias necesarias del
 * explorador, la interfaz gráfica y el controlador.
 *
 * @author johns
 */
public class FileExplorer {

   /**
     * Punto de entrada principal de la aplicación.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     * @throws IOException si ocurre un error al acceder al sistema de archivos
     */
  public static void main(String[] args) throws IOException {
    // TODO code application logic here
    Explorador modelo = Explorador.getInstance();
    ExploradorForm vista = new ExploradorForm();
    ExploradorController controlador = new ExploradorController(modelo, vista);
    Explorador.getInstance().getListaElementos();
    //PropiedadesArchivoForm prop = new PropiedadesArchivoForm();
  }
  
}
