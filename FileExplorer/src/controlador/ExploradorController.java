/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import logicadenegocios.Explorador;
import vista.ExploradorForm;

/**
 *
 * @author johns
 */
public class ExploradorController {
  private Explorador modelo;
  private ExploradorForm vista;
    
  public ExploradorController(Explorador pModelo, ExploradorForm pVista) {
    modelo = pModelo;
    vista = pVista;
    actualizar();
  }
  
  public void actualizar() {
    vista.actualizarTabla(modelo.getListaArchivos());
  }
}
