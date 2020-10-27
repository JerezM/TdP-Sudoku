package main.model.celda.factories;

import main.model.celda.CeldaModel;
import main.model.celda.CeldaModelImpl;
import main.model.celda.entidad_grafica.EntidadGraficaCelda;
import main.model.celda.entidad_grafica.EntidadGraficaPokemon;

public class CeldaPokemonFactory implements CeldaModelFactory {

    @Override
    public CeldaModel createCeldaModel(int posX, int posY, int valorCelda) {
        EntidadGraficaCelda entidadGrafica = new EntidadGraficaPokemon(valorCelda);
        CeldaModel celdaModel = new CeldaModelImpl(valorCelda, entidadGrafica, posX, posY);

        return celdaModel;
    }
    
}
