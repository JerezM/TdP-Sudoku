package main.model.entidadesGraficas;

import javax.swing.ImageIcon;

import main.model.CeldaModel;

public interface EntidadGraficaState {
    public void actualizarValor(int valorNuevo);
    public void actualizarSprite(int estadoNuevo);
    public ImageIcon getSpriteIcon();
    public void setCeldaModel(CeldaModel celdaModel);
}
