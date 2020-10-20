package main.model;

import javax.swing.ImageIcon;

import main.model.entidadesGraficas.EntidadGrafica;

public class CeldaModelImpl implements CeldaModel {


	private int valor;
	private EntidadGrafica entidadGrafica;
	private int posX, posY;
	private TableroModel tablero;
	
	public CeldaModelImpl(Integer valorInicial, EntidadGrafica entidadGraficaInicial, int posX, int posY) {
		this.valor = valorInicial;
		this.entidadGrafica = entidadGraficaInicial;
		this.posX = posX;
		this.posY = posY;
		tablero = TableroModelImpl.getInstance();
	}
	
	@Override
	public void actualizarValor(int valorNuevo){
		valor = valorNuevo;
		entidadGrafica.actualizarValor(valorNuevo);
		this.notificarCambioATablero();
	}

	@Override
    public void actualizarSprite(int estadoNuevo){
		entidadGrafica.actualizarSprite(estadoNuevo);
		this.notificarCambioATablero();
	}

	@Override
	public void fuisteSeleccionada() {
		int seleccionadaIndirectamente = 1;
		entidadGrafica.actualizarSprite(seleccionadaIndirectamente);
	}

	@Override
	public void notificarCambioATablero() {
		tablero.notificarCambioEnCelda(posX, posY);
	}

	@Override
	public ImageIcon getSpriteIcon(){
		return entidadGrafica.getSpriteIcon();
	}

	@Override
	public int getValor() {
		return this.valor;
	}
 
}
