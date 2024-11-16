package vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GraficoCircular extends JPanel {

  private final double espacioTotal;
  private final double espacioUsado;

  public GraficoCircular(double espacioTotal, double espacioUsado) {
    this.espacioTotal = espacioTotal;
    this.espacioUsado = espacioUsado;

    setBackground(Color.WHITE);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    double usadoAngulo = (espacioUsado / espacioTotal) * 360;
    double libreAngulo = 360 - usadoAngulo;

    g2d.setColor(new Color(40, 184, 235));
    g2d.fillArc(80, 20, 200, 200, 0, (int) usadoAngulo);  // Ajustado para mover el gráfico hacia arriba

    g2d.setColor(new Color(200, 200, 200));
    g2d.fillArc(80, 20, 200, 200, (int) usadoAngulo, (int) libreAngulo);  // Ajustado para mover el gráfico hacia arriba

    // Dibujar el círculo blanco en el centro
    g2d.setColor(Color.WHITE);
    g2d.fillArc(120, 60, 120, 120, 0, 360);  // El círculo blanco en el centro
  }
}
