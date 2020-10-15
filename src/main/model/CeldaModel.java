package main.model;

import javax.swing.ImageIcon;

import main.model.entidadesGraficas.EntidadGraficaState;

public class CeldaModel {
	private Integer valor;
	private EntidadGraficaState entidadGrafica;
	
	public CeldaModel(Integer valorInicial, EntidadGraficaState entidadGraficaInicial) {
		this.valor = valorInicial;
		this.entidadGrafica = entidadGraficaInicial;
		this.entidadGrafica.setCeldaModel(this);
	}
	
	public void actualizarValor(int valorNuevo){
		valor = valorNuevo;
		entidadGrafica.actualizarValor(valorNuevo);
	}

    public void actualizarSprite(int estadoNuevo){
		entidadGrafica.actualizarSprite(estadoNuevo);
	}

	public ImageIcon getSpriteIcon(){
		return entidadGrafica.getSpriteIcon();
	}

	public void cambiarEntidadGrafica(EntidadGraficaState entidadGrafica) {
		this.entidadGrafica = entidadGrafica;
	}

	public Integer getValor() {
		return this.valor;
	}
 
}
