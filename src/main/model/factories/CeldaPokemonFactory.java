package main.model.factories;

import main.model.CeldaModel;
import main.model.CeldaModelImpl;
import main.model.entidadesGraficas.EntidadGrafica;
import main.model.entidadesGraficas.EntidadGraficaPokemon;

public class CeldaPokemonFactory implements CeldaModelFactory {

    @Override
    public CeldaModel createCeldaModel(int posX, int posY, int valorCelda) {
        EntidadGrafica entidadGrafica = new EntidadGraficaPokemon(valorCelda);
        CeldaModel celdaModel = new CeldaModelImpl(valorCelda, entidadGrafica, posX, posY);

        return celdaModel;
    }
    
}
