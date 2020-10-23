package main.model.tablero;

import main.controller.tablero.TableroControllerModel;
import main.model.celda.factories.CeldaModelFactory;

public interface TableroModel {
    
    /**
     * Establece el controller a utilizar.
     * @param controller Controller que usara el TableroModel.
     */
    public void setController(TableroControllerModel controller);

    /**
     * Establece el celdaFactory a utilizar.
     * @param controller Controller que usara el TableroModel.
     */
    public void setCeldaFactory(CeldaModelFactory factory);
}
