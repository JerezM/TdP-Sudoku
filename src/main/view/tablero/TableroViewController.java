package main.view.tablero;

import java.util.List;

import javax.swing.ImageIcon;

import main.controller.tablero.TableroControllerView;
import main.service.entry.Entry;

public interface TableroViewController {
    
    /**
     * Se inicializa el tablero y se muestra al mismo visualmente.
     * @param celdas Colleccion de coordenadas de celdas del tablero, con sus respectivos sprites a inicializar.
     *  Vease: List< esInicial,< < posX, posY>, sprite > >.
     */
    public void inicializarTablero(List<Entry<Boolean, Entry<Entry<Integer, Integer>, ImageIcon>>> celdas);

    /**
     * Notifica de los cambios en los estados a las celdas parametrizadas.
     * @param celdas Colleccion de coordenadas de celdas en las cuales se notificaron cambios, con sus respectivos sprites.
     * Vease: List<< posX, posY>, sprite>.
     */
    public void notificarCambios(List<Entry<Entry<Integer, Integer>, ImageIcon>> celdas);

    /**
     * Si el tablero actual posee un estado completo y correcto entonces el sudoku se da por finalizado,
     *  caso contrario, indica los errores visualmente para que el usuario sea capaz de corregirlos.
     * @param resultado Indica el resultado del tablero.
     * @param celdas  Colleccion de coordenadas de celdas en las cuales se notificaron cambios, con sus respectivos sprites.
     *  Vease: List<< posX, posY>, sprite>.
     */
    public void notificarVerificacionTablero(boolean resultado, List<Entry<Entry<Integer, Integer>, ImageIcon>> celdas);
    
    /**
     * Establece el controller con el que se comunicara la view.
     * @param controller El controller que utilizara la view.
     */
    public void setController(TableroControllerView controller);
}
