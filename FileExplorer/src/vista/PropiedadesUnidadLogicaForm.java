/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

/**
 *
 * @author Keyne
 */
public class PropiedadesUnidadLogicaForm extends JFrame {
    
    private final JTextField nombre;
    private final JTextField espacioTotal;
    private final JTextField espacioUsado;
    private final JTextField espacioLibre;
    private final JProgressBar barraEspacio;
    
    public final double TAMANO_COLUMNA_1 = 0.3;
    public final double TAMANO_COLUMNA_2 = 1.5;
    
    public PropiedadesUnidadLogicaForm() {
        setTitle("Propiedades de Unidad");
        setSize(350, 600);
        setLayout(new BorderLayout());
        setResizable(false);
        
        Point mouse = MouseInfo.getPointerInfo().getLocation();
        setLocation(mouse);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Nombre de la unidad
        JLabel nombreLabel = new JLabel("Unidad:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = TAMANO_COLUMNA_1;
        panel.add(nombreLabel, gbc);
        
        nombre = new JTextField("");
        nombre.setEditable(false);
        nombre.setFocusable(false);
        gbc.gridx = 1;
        gbc.weightx = TAMANO_COLUMNA_2;
        panel.add(nombre, gbc);
        
        // Espacio total
        JLabel espacioTotalLabel = new JLabel("Espacio total:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = TAMANO_COLUMNA_1;
        panel.add(espacioTotalLabel, gbc);
        
        espacioTotal = new JTextField("");
        espacioTotal.setEditable(false);
        espacioTotal.setFocusable(false);
        gbc.gridx = 1;
        gbc.weightx = TAMANO_COLUMNA_2;
        panel.add(espacioTotal, gbc);
        
        // Espacio usado
        JLabel espacioUsadoLabel = new JLabel("Espacio usado:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = TAMANO_COLUMNA_1;
        panel.add(espacioUsadoLabel, gbc);
        
        espacioUsado = new JTextField("");
        espacioUsado.setEditable(false);
        espacioUsado.setFocusable(false);
        gbc.gridx = 1;
        gbc.weightx = TAMANO_COLUMNA_2;
        panel.add(espacioUsado, gbc);
        
        // Espacio libre
        JLabel espacioLibreLabel = new JLabel("Espacio libre:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = TAMANO_COLUMNA_1;
        panel.add(espacioLibreLabel, gbc);
        
        espacioLibre = new JTextField("");
        espacioLibre.setEditable(false);
        espacioLibre.setFocusable(false);
        gbc.gridx = 1;
        gbc.weightx = TAMANO_COLUMNA_2;
        panel.add(espacioLibre, gbc);
        
        // Barra de progreso
        JLabel progresoLabel = new JLabel("Uso de disco:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = TAMANO_COLUMNA_1;
        panel.add(progresoLabel, gbc);
        
        barraEspacio = new JProgressBar(0, 100);
        barraEspacio.setStringPainted(true);
        gbc.gridx = 1;
        gbc.weightx = TAMANO_COLUMNA_2;
        panel.add(barraEspacio, gbc);
        
        add(panel, BorderLayout.NORTH);
        setVisible(true);
    }
    
    public void setNombre(String pNombre) {
        nombre.setText(pNombre);
    }
    
    public void setEspacioTotal(String pEspacioTotal) {
        espacioTotal.setText(pEspacioTotal);
    }
    
    public void setEspacioUsado(String pEspacioUsado) {
        espacioUsado.setText(pEspacioUsado);
    }
    
    public void setEspacioLibre(String pEspacioLibre) {
        espacioLibre.setText(pEspacioLibre);
    }
    
    public void setProgresoUsado(int porcentaje) {
        barraEspacio.setValue(porcentaje);
    }
}
