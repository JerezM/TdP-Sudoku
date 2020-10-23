package main.model.celda.factories;

import main.model.celda.CeldaModel;

public interface CeldaModelFactory {

    /**
     * Crea un CeldaModel que represente el valor parametrizado.
     * @param posX posicion en el eje X de la celda en cuestion.
     * @param posY posicion en el eje X de la celda en cuestion.
     * @param valorCelda Valor de la celda.
     * @return El CeldaModel generado.
     */
    public CeldaModel createCeldaModel(int posX, int posY, int valorCelda);
}
