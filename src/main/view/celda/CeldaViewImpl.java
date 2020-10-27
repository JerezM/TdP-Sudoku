package main.view.celda;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Image;

import main.service.EstadosPosiblesCeldas;
import main.view.tablero.TableroViewCelda;
import main.view.tablero.TableroViewImpl;

@SuppressWarnings("serial")
public class CeldaViewImpl extends JLabel implements CeldaView {
    private ImageIcon sprite;
    private int posX, posY;
    private TableroViewCelda tablero;
    private boolean estaSeleccionada;

    public CeldaViewImpl(boolean esInicial, int posX, int posY, ImageIcon sprite) {
        this.sprite = sprite;
        this.setIcon(this.sprite);
        if(!esInicial) {
            this.addMouseListener(new CeldaViewListener());
        }

        
        this.posX = posX;
        this.posY = posY;
        tablero = TableroViewImpl.getInstance();
        estaSeleccionada = false;

        this.setSize(49, 49);//Size de las celdas
        
        this.pintarSprite();
        this.setVisible(true);
    }

    @Override
    public void notificarCambio(ImageIcon sprite) {
        this.sprite = sprite;
        this.setIcon(this.sprite);
        this.pintarSprite();
    }

    @Override
    public void actualizarSprite() {
        int estado;

        if(estaSeleccionada) {
            estado = EstadosPosiblesCeldas.CELDA_NO_SELECCIONADA.getEstado();
        }
        else {
            estado = EstadosPosiblesCeldas.CELDA_SELECCIONADA.getEstado();
        }
        estaSeleccionada = (!estaSeleccionada);

        tablero.actualizarSprite(estado, posX, posY);
    }

    @Override
    public void deseleccionar() {
        estaSeleccionada = false;
    }

    /**
     * Pinta el sprite actual de la celda en si mismo.
     */
    protected void pintarSprite() {
        Image image = sprite.getImage();

		if (image != null) {  
			Image newimg = image.getScaledInstance(this.getWidth(), this.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			sprite.setImage(newimg);
			this.repaint();
		}
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

}
