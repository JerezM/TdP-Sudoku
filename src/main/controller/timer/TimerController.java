package main.controller.timer;

import javax.swing.ImageIcon;

public interface TimerController {
    
    /**
     * Le notifica al timerView de la nueva actualizacion ocurrida en los sprites.
     * @param sprites Arreglo de sprites que se notificaran al timerView.
     */
    public void notificarCambiosSprites(ImageIcon[] sprites);
}
