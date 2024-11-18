/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import logicadenegocios.UnidadLogica;
import vista.PropiedadesUnidadLogicaForm;

/**
 *
 * @author Keyne
 */

public class PropiedadesUnidadLogicaController {
    private UnidadLogica modelo;
    private PropiedadesUnidadLogicaForm vista;
    
    public PropiedadesUnidadLogicaController(UnidadLogica pUnidad) {
        modelo = pUnidad;
        vista = new PropiedadesUnidadLogicaForm();
        actualizar();
    }
    
    public void actualizar() {
        vista.setNombre(modelo.getNombre());
        vista.setEspacioTotal(modelo.getEspacioTotal());
        vista.setEspacioUsado(modelo.getEspacioUsado());
        vista.setEspacioLibre(modelo.getEspacioLibre());
        vista.setProgresoUsado(modelo.getNumTotal(), modelo.getNumUsado());
    }
    
    public static void main(String[] args) {
      new PropiedadesUnidadLogicaController(new UnidadLogica("C:"));
    }
}