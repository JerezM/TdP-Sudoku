package main.model.celda.entidadesGraficas;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import main.service.EstadosPosiblesCeldas;

public class EntidadGraficaPokemon implements EntidadGrafica {
    private ImageIcon spriteIcon;
	private String[] imagenes;
	private Map<Integer, String[]> spritesMap;
	
	public EntidadGraficaPokemon(int valorCelda) {
		this.inicializarSpritesMap();
		this.imagenes = spritesMap.get(valorCelda);
		
		int estado = EstadosPosiblesCeldas.CELDA_NO_SELECCIONADA.getEstado();
		this.actualizarSprite(estado);
	}

	@Override
	public void actualizarValor(int valor) {
		imagenes = spritesMap.get(valor);
		int estado = EstadosPosiblesCeldas.CELDA_NO_SELECCIONADA.getEstado();
		this.actualizarSprite(estado);
	}

	@Override
	public void actualizarSprite(int estado) {
		ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[estado]));
		this.spriteIcon = imageIcon;
		this.spriteIcon.setImage( this.spriteIcon.getImage());
	}

	@Override
	public ImageIcon getSpriteIcon() {
		return this.spriteIcon;
	}

	/**
	 * Inicializa el atributo spritesMap.
	 */
	protected void inicializarSpritesMap() {
		spritesMap = new HashMap<Integer, String[]>();

		String[] spritesCelda0 =  new String[]{"/resources/sprites/celda-0/celda-0-estado-0.png", "/resources/sprites/celda-0/celda-0-estado-1.png", "/resources/sprites/celda-0/celda-0-estado-2.png", "/resources/sprites/celda-0/celda-0-estado-3.png"};
		String[] spritesCelda1 =  new String[]{"/resources/sprites/celda-1/celda-1-estado-0.png", "/resources/sprites/celda-1/celda-1-estado-1.png", "/resources/sprites/celda-1/celda-1-estado-2.png", "/resources/sprites/celda-1/celda-1-estado-3.png"};
		String[] spritesCelda2 =  new String[]{"/resources/sprites/celda-2/celda-2-estado-0.png", "/resources/sprites/celda-2/celda-2-estado-1.png", "/resources/sprites/celda-2/celda-2-estado-2.png", "/resources/sprites/celda-2/celda-2-estado-3.png"};
		String[] spritesCelda3 =  new String[]{"/resources/sprites/celda-3/celda-3-estado-0.png", "/resources/sprites/celda-3/celda-3-estado-1.png", "/resources/sprites/celda-3/celda-3-estado-2.png", "/resources/sprites/celda-3/celda-3-estado-3.png"};
		String[] spritesCelda4 =  new String[]{"/resources/sprites/celda-4/celda-4-estado-0.png", "/resources/sprites/celda-4/celda-4-estado-1.png", "/resources/sprites/celda-4/celda-4-estado-2.png", "/resources/sprites/celda-4/celda-4-estado-3.png"};
		String[] spritesCelda5 =  new String[]{"/resources/sprites/celda-5/celda-5-estado-0.png", "/resources/sprites/celda-5/celda-5-estado-1.png", "/resources/sprites/celda-5/celda-5-estado-2.png", "/resources/sprites/celda-5/celda-5-estado-3.png"};
		String[] spritesCelda6 =  new String[]{"/resources/sprites/celda-6/celda-6-estado-0.png", "/resources/sprites/celda-6/celda-6-estado-1.png", "/resources/sprites/celda-6/celda-6-estado-2.png", "/resources/sprites/celda-6/celda-6-estado-3.png"};
		String[] spritesCelda7 =  new String[]{"/resources/sprites/celda-7/celda-7-estado-0.png", "/resources/sprites/celda-7/celda-7-estado-1.png", "/resources/sprites/celda-7/celda-7-estado-2.png", "/resources/sprites/celda-7/celda-7-estado-3.png"};
		String[] spritesCelda8 =  new String[]{"/resources/sprites/celda-8/celda-8-estado-0.png", "/resources/sprites/celda-8/celda-8-estado-1.png", "/resources/sprites/celda-8/celda-8-estado-2.png", "/resources/sprites/celda-8/celda-8-estado-3.png"};
		String[] spritesCelda9 =  new String[]{"/resources/sprites/celda-9/celda-9-estado-0.png", "/resources/sprites/celda-9/celda-9-estado-1.png", "/resources/sprites/celda-9/celda-9-estado-2.png", "/resources/sprites/celda-9/celda-9-estado-3.png"};

		spritesMap.put(0, spritesCelda0);
		spritesMap.put(1, spritesCelda1);
		spritesMap.put(2, spritesCelda2);
		spritesMap.put(3, spritesCelda3);
		spritesMap.put(4, spritesCelda4);
		spritesMap.put(5, spritesCelda5);
		spritesMap.put(6, spritesCelda6);
		spritesMap.put(7, spritesCelda7);
		spritesMap.put(8, spritesCelda8);
		spritesMap.put(9, spritesCelda9);
	}

}
