package main.model.tablero;

import main.model.celda.factories.CeldaModelFactory;

public interface TableroModel {

    /**
     * Establece el celdaFactory a utilizar.
     * @param factory Factory que usara el TableroModel.
     */
    public void setCeldaFactory(CeldaModelFactory factory);
    
}
