package main.model.celda;

import javax.swing.ImageIcon;

import main.service.EstadosPosiblesCeldas;
import main.model.celda.entidadesGraficas.EntidadGrafica;
import main.model.tablero.TableroModelCelda;
import main.model.tablero.TableroModelImpl;

public class CeldaModelImpl implements CeldaModel {
	
	private int valor;
	private EntidadGrafica entidadGrafica;
	private int posX, posY;
	private TableroModelCelda tablero;
	
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
		int estado = EstadosPosiblesCeldas.CELDA_NO_SELECCIONADA.getEstado();

		entidadGrafica.actualizarValor(valorNuevo);
		this.notificarCambioATablero(estado);
	}

	@Override
    public void actualizarSprite(int estadoNuevo){
		entidadGrafica.actualizarSprite(estadoNuevo);
		this.notificarCambioATablero(estadoNuevo);
	}

	@Override
	public void actualizarSpriteSeleccionada(int estado) {
		entidadGrafica.actualizarSprite(estado);
	}

	/**
     * Notifica a TableroModel de un cambio en el estado interno de la clase.
     */
	protected void notificarCambioATablero(int estado) {
		tablero.notificarCambioEnCelda(posX, posY, estado);
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
