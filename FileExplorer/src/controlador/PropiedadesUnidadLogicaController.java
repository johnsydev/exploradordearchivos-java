/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import logicadenegocios.UnidadLogica;
import vista.PropiedadesUnidadLogicaForm;

/**
 * Controlador que gestiona la interacción entre el modelo de UnidadLogica y su vista de propiedades.
 * Implementa el patrón MVC para manejar la visualización de las propiedades y estadísticas
 * de una unidad lógica del sistema (como discos duros, unidades USB, etc.).
 *
 * @author Keyne
 * @version 1.0
 * @see logicadenegocios.UnidadLogica
 * @see vista.PropiedadesUnidadLogicaForm
 */

public class PropiedadesUnidadLogicaController {
    private UnidadLogica modelo;
    private PropiedadesUnidadLogicaForm vista;
    
    /**
     * Constructor que inicializa el controlador con una unidad lógica específica.
     * Crea la vista asociada y actualiza la información mostrada.
     *
     * @param pUnidad La unidad lógica cuyas propiedades se mostrarán
     */
    public PropiedadesUnidadLogicaController(UnidadLogica pUnidad) {
        modelo = pUnidad;
        vista = new PropiedadesUnidadLogicaForm();
        actualizar();
    }
    
    /**
     * Actualiza la vista con la información actual del modelo.
     * Sincroniza todos los campos de la vista con los valores correspondientes de la unidad lógica,
     * incluyendo:
     * - Nombre de la unidad
     * - Tipo de unidad
     * - Sistema de archivos
     * - Porcentaje de espacio usado
     * - Espacio total disponible
     * - Espacio usado
     * - Espacio libre
     */
    public void actualizar() {
        vista.setNombre(modelo.getNombre());
        vista.setTipo(modelo.getTipo());
        vista.setSistemaDeArchivos(modelo.getSistemaArchivos());
        vista.setProgresoUsado(modelo.getNumTotal(), modelo.getNumUsado());
        vista.setEspacioTotal(modelo.getEspacioTotal());
        vista.setEspacioUsado(modelo.getEspacioUsado());
        vista.setEspacioLibre(modelo.getEspacioLibre());
    }
    
}