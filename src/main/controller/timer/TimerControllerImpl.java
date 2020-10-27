package main.controller.timer;

import javax.swing.ImageIcon;

import main.view.timer.TimerView;
import main.view.timer.TimerViewImpl;

public class TimerControllerImpl implements TimerController {
    
    private static TimerControllerImpl instance;
    
    private TimerView timerView;

    private TimerControllerImpl() {
        this.timerView = TimerViewImpl.getInstance();
    }

    public static TimerControllerImpl getInstance() {
        if (instance == null) {
            instance = new TimerControllerImpl();
        }

        return instance;
    }

    @Override
    public void notificarCambiosSprites(ImageIcon[] sprites) {
        this.timerView.notificarCambiosSprites(sprites);
    }
    
}
