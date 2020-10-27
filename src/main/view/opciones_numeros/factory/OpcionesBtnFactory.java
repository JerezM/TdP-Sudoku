package main.view.opciones_numeros.factory;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public interface OpcionesBtnFactory {
    
    /**
     * Crea un JButton que represente contiene las specificaciones parametrizadas.
     * @param width ancho del btn.
     * @param height alto del btn.
     * @param imgBtn imagen visual del btn.
     * @return el btn generado con las caracteristicas parametrizadas.
     */
    public JButton createOptionBtn(int width, int height, ImageIcon imgBtn);
}
