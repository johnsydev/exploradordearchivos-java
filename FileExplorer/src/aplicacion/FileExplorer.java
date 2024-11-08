/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aplicacion;

import controlador.ExploradorController;
import logicadenegocios.Explorador;
import vista.ExploradorForm;
import vista.PropiedadesArchivoForm;

/**
 *
 * @author johns
 */
public class FileExplorer {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here
    Explorador modelo = Explorador.getInstance();
    ExploradorForm vista = new ExploradorForm();
    ExploradorController controlador = new ExploradorController(modelo, vista);
    Explorador.getInstance().getListaArchivos();
    //PropiedadesArchivoForm prop = new PropiedadesArchivoForm();
  }
  
}
