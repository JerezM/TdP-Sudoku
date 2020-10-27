package main.model.timer;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import main.model.timer.entidad_grafica.EntidadGraficaTimer;
import main.model.timer.entidad_grafica.EntidadGraficaTimerImpl;

public class TimerModelImpl extends TimerTask implements TimerModel {
    
    private static TimerModelImpl instance;

    //private TimerController controller;
    private EntidadGraficaTimer entidadGrafica;

    private Timer timer;

    private int hh, hhDecimal, hhUnidad;// hh:horas
    private int mm, mmDecimal, mmUnidad;// mm:minutos
    private int ss, ssDecimal, ssUnidad;// ss:segundos
    private int tiempoTranscurrido, minutosTranscurridos;

    private ImageIcon[] sprites;

    private TimerModelImpl() {
        entidadGrafica = new EntidadGraficaTimerImpl();

        timer = new Timer();

        hh = mm = ss = tiempoTranscurrido = minutosTranscurridos = 0;
    }

    public static TimerModelImpl getInstance() {
        if (instance == null) {
            instance = new TimerModelImpl();
        }

        return instance;
    }

    @Override
    public void iniciarTimer() {
        timer.schedule(this, 0, 1000);
    }

    @Override
    public String pararTimer() {
        timer.cancel();

        return this.toString();
    }

    @Override
    public String toString() {
        String tiempoTotal = new String("Tiempo total: "+hh+":"+mm+":"+ss);

        return tiempoTotal;
    }

    @Override
    public void run() {
        tiempoTranscurrido++;

        hh = tiempoTranscurrido / 3600;
        minutosTranscurridos = tiempoTranscurrido % 3600;
        mm = minutosTranscurridos / 60;
        ss = minutosTranscurridos % 60;

        hhDecimal = hh / 10;
        hhUnidad = hh % 10;
        mmDecimal = mm / 10;
        mmUnidad = mm % 10;
        ssDecimal = ss / 10;
        ssUnidad = ss % 10;

        sprites = entidadGrafica.getSpriteIcons(hhDecimal, hhUnidad, mmDecimal, mmUnidad, ssDecimal, ssUnidad);
        //controller.notificarCambioSprites(sprites);
    }
    
}
