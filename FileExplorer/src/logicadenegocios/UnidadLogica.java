/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadenegocios;

import java.io.File;

/**
 *
 * @author Keyne
 */
public class UnidadLogica extends Elemento {
    
    public UnidadLogica(String pRuta) {
        super(pRuta);
    }
    
    public String getEspacioTotal() {
        return getTamanoTexto(file.getTotalSpace());
    }
    
    public String getEspacioLibre() {
        return getTamanoTexto(file.getFreeSpace());
    }
    
    public String getEspacioUsado() {
        return getTamanoTexto(file.getTotalSpace() - file.getFreeSpace());
    }
    
    public int getPorcentajeUsado() {
        long total = file.getTotalSpace();
        long libre = file.getFreeSpace();
        long usado = total - libre;
        return (int)((usado * 100.0) / total);
    }
    
    private String getTamanoTexto(long tamano) {
        String unidad = "B";
        double tamanoProcesado = tamano;
        
        for(int i = 1; i < 5; i++) {
            if(tamanoProcesado/1000 > 0) {
                tamanoProcesado = tamanoProcesado/1000;
                switch(unidad) {
                    case "B":
                        unidad = "KB";
                        break;
                    case "KB":
                        unidad = "MB";
                        break;
                    case "MB":
                        unidad = "GB";
                        break;
                }
            } else {
                break;
            }
        }
        return String.format("%.2f %s", tamanoProcesado, unidad);
    }
}