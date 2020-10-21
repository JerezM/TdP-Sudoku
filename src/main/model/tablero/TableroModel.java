package main.model.tablero;

import main.controller.TableroControllerModel;
import main.model.factories.CeldaModelFactory;

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
