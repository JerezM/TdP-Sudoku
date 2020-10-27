package main.model.timer;

/**
 * TiimerModel
 * @author Martin Jerez.
 */
public interface TimerModel {

    /**
     * Le indica al timer que empiece a funcionar.
     */
    public void iniciarTimer();
    
    /**
     * Le indica al timer que se detenga.
     * @return String indicando el tiempo transcurrido.
     */
    public String detenerTimer();
}