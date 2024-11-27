package vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
* Componente gráfico que muestra un gráfico circular tipo "donut" para visualizar
* el espacio usado vs. total de una unidad de almacenamiento.
* Extiende JPanel para proporcionar capacidades de dibujo personalizado.
* 
* @author 
* @version 1.0
* @see javax.swing.JPanel
*/
public class GraficoCircular extends JPanel {

  private double espacioTotal;
  private double espacioUsado;

  /**
    * Constructor que inicializa el gráfico con los valores de espacio inicial.
    * Configura el fondo blanco del panel.
    *
    * @param espacioTotal Capacidad total de la unidad
    * @param espacioUsado Espacio utilizado de la unidad
    */
  public GraficoCircular(double espacioTotal, double espacioUsado) {
    this.espacioTotal = espacioTotal;
    this.espacioUsado = espacioUsado;

    setBackground(Color.WHITE);
  }

  public void setDatos(double espacioTotal, double espacioUsado) {
    this.espacioTotal = espacioTotal;
    this.espacioUsado = espacioUsado;
    repaint();
  }

  /**
    * Dibuja el gráfico circular tipo "donut" con los datos actuales.
    * El gráfico muestra:
    * - Un arco azul para el espacio usado (Color(40, 184, 235))
    * - Un arco gris para el espacio libre (Color(200, 200, 200))
    * - Un círculo blanco central para crear el efecto "donut"
    * 
    * El gráfico comienza desde la izquierda (180 grados) y avanza en sentido horario.
    *
    * @param g Contexto gráfico proporcionado por el sistema
    */
   @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    // Cambiar el ángulo inicial a 180 grados para comenzar desde la izquierda
    double usadoAngulo = (espacioUsado / espacioTotal) * 360;
    double libreAngulo = 360 - usadoAngulo;

    // Espacio usado - Desde el ángulo de 180 hasta el ángulo calculado
    g2d.setColor(new Color(40, 184, 235));  // Color para el espacio usado
    g2d.fillArc(80, 20, 200, 200, 180, (int) -usadoAngulo);  // Comenzar desde 180 grados hacia 90 grados (horario)

    // Espacio libre - Después del ángulo usado, se dibuja el espacio libre
    g2d.setColor(new Color(200, 200, 200));  // Color para el espacio libre
    g2d.fillArc(80, 20, 200, 200, 180 - (int) usadoAngulo, (int) -libreAngulo);  // Completar el gráfico

    // Dibujar el círculo blanco en el centro
    g2d.setColor(Color.WHITE);
    g2d.fillArc(120, 60, 120, 120, 0, 360);  // El círculo blanco en el centro
  }

}
