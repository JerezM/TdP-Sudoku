package main.controller.tablero;

import java.util.List;

import javax.swing.ImageIcon;

import main.service.entry.Entry;

public interface TableroControllerModel {

    /**
     * Notifica al TableroView de que se inicializo correctamente el tablero y muestra al mismo visualmente.
     * @param celdas Colleccion de coordenadas de celdas en las cuales se notificaron cambios, con sus respectivos sprites.
     *  Vease: List< esInicial,< < posX, posY>, sprite > >.
     */
    public void notificarInicioTablero(List<Entry<Boolean, Entry<Entry<Integer, Integer>, ImageIcon>>> celdas);

    /**
     * Notifica al TableroView de los cambios en los estados de las celdas parametrizadas.
     * @param celdas Colleccion de coordenadas de celdas en las cuales se notificaron cambios, con sus respectivos sprites.
     * Vease: List<< posX, posY>, sprite>.
     */
    public void notificarCambios(List<Entry<Entry<Integer, Integer>, ImageIcon>> celdas);

    /**
     * Notifica al TableroView de si el tablero actual posee un estado completo y correcto, o si contienen errores en caso contrario.
     * @param resultado Entrada donde se indicar el resultado del tablero.
     * @param celdas  Colleccion de coordenadas de celdas en las cuales se notificaron cambios, con sus respectivos sprites.
     *  Vease: List<< posX, posY>, sprite>.
     */
    public void notificarVerificacionTablero(boolean resultado, List<Entry<Entry<Integer, Integer>, ImageIcon>> celdas);
    
}
