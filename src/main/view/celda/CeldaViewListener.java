package main.view.celda;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CeldaViewListener extends MouseAdapter {
    private CeldaView celda;

    @Override
    public void mouseClicked(MouseEvent e) {
        celda = (CeldaViewImpl) e.getSource();

        celda.actualizarSprite();
    }
}
