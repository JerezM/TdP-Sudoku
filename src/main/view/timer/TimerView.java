package main.view.timer;

import javax.swing.ImageIcon;

public interface TimerView {
    
    /**
     * Recibe un arreglo de imageIcon los cuales va a utilizar para actualizar sus componentes.
     * @param sprites arreglo de imageIcons con los nuevos sprites a colocar.
     */
    public void notificarCambiosSprites(ImageIcon[] sprites);
}
