package main.controller;

import java.util.List;

import main.model.CeldaModel;

public interface TableroControllerModel {

    /**
     * Notifica al TableroView de los cambios en los estados de las celdas parametrizadas.
     * @param celdas Colleccion de celdas en las cuales se notificaron cambios.
     */
    public void notificarCambios(List<CeldaModel> celdas);
    
}
