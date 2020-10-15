package main.model.entidadesGraficas;

import javax.swing.ImageIcon;

import main.model.CeldaModel;

public class EntidadGraficaStateCelda0 implements EntidadGraficaState {
    private ImageIcon spriteIcon;
	private String[] imagenes;
	private CeldaModel celdaModel;
	
	public EntidadGraficaStateCelda0() {
		this.spriteIcon = new ImageIcon();
		this.imagenes = new String[]{"resources/sprites/0-cell/cell-0.png", "resources/sprites/0-cell/cell-1.png", "resources/sprites/0-cell/cell-2.png"};
	}

	@Override
	public void actualizarValor(int valorNuevo) {
		//Switch dependiendo el valorNuevo
		switch (valorNuevo) {
			case 0:
				this.actualizarSprite(0);
				break;
			case 1:
				//celdaModel.cambiarEntidadGrafica( new EntidadGraficaStateCelda1() );
				break;
			case 2:
				//celdaModel.cambiarEntidadGrafica( new EntidadGraficaStateCelda2() );
				break;
			case 3:
				//celdaModel.cambiarEntidadGrafica( new EntidadGraficaStateCelda3() );
				break;
			case 4:
				//celdaModel.cambiarEntidadGrafica( new EntidadGraficaStateCelda4() );
				break;
			case 5:
				//celdaModel.cambiarEntidadGrafica( new EntidadGraficaStateCelda5() );
				break;
			case 6:
				//celdaModel.cambiarEntidadGrafica( new EntidadGraficaStateCelda6() );
				break;
			case 7:
				//celdaModel.cambiarEntidadGrafica( new EntidadGraficaStateCelda7() );
				break;
			case 8:
				//celdaModel.cambiarEntidadGrafica( new EntidadGraficaStateCelda8() );
				break;
			case 9:
				//celdaModel.cambiarEntidadGrafica( new EntidadGraficaStateCelda9() );
				break;
		}
	}

	@Override
	public void actualizarSprite(int estadoNuevo) {
		switch (estadoNuevo) {
			case 2:
				estadoNuevo = 1;
				break;
			case 3:
				estadoNuevo = 2;
				break;
		
		}

		ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[estadoNuevo]));
		this.spriteIcon.setImage(imageIcon.getImage());
	}

	@Override
	public ImageIcon getSpriteIcon() {
		return this.spriteIcon;
	}

	public void setCeldaModel(CeldaModel celdaModel) {
		this.celdaModel = celdaModel;
	}
}
