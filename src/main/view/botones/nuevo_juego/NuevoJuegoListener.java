package main.view.botones.nuevo_juego;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NuevoJuegoListener implements ActionListener {
    private NuevoJuevoBtn nuevoJuegoBtn;

    @Override
    public void actionPerformed(ActionEvent e) {
        nuevoJuegoBtn = (NuevoJuevoBtn) e.getSource();
        
        nuevoJuegoBtn.abrirFileChooser();
    }
    
}
