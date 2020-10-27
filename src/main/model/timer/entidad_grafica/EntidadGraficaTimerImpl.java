package main.model.timer.entidad_grafica;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class EntidadGraficaTimerImpl implements EntidadGraficaTimer {
    
    private Map<Integer, ImageIcon> map;
    private ImageIcon dosPuntos;
    private ImageIcon[] sprites;

    public EntidadGraficaTimerImpl() {
        map = new HashMap<Integer, ImageIcon>();

        dosPuntos = new ImageIcon(this.getClass().getResource("/resources/sprites/timer/dos-puntos.png"));
        dosPuntos.setImage( dosPuntos.getImage());

        sprites = new ImageIcon[8];
        sprites[2] = dosPuntos;
        sprites[5] = dosPuntos;

        this.inicializarMap();
    }

    @Override
    public ImageIcon[] getSpriteIcons(int h1, int h2, int m1, int m2, int s1, int s2) {

        sprites[0] = map.get(h1);
        sprites[1] = map.get(h2);

        sprites[3] = map.get(m1);
        sprites[4] = map.get(m2);

        sprites[6] = map.get(s1);
        sprites[7] = map.get(s2);


        return sprites;
    }
    
    /**
     * Se encarga de inicializar los sprites y luego cargar los mismos en el mapeo.
     */
    protected void inicializarMap() {

        ImageIcon img0 = new ImageIcon(this.getClass().getResource("/resources/sprites/timer/number-0.png"));
        img0.setImage( img0.getImage());

        ImageIcon img1 = new ImageIcon(this.getClass().getResource("/resources/sprites/timer/number-1.png"));
        img1.setImage( img1.getImage());

        ImageIcon img2 = new ImageIcon(this.getClass().getResource("/resources/sprites/timer/number-2.png"));
        img2.setImage( img2.getImage());

        ImageIcon img3 = new ImageIcon(this.getClass().getResource("/resources/sprites/timer/number-3.png"));
        img3.setImage( img3.getImage());

        ImageIcon img4 = new ImageIcon(this.getClass().getResource("/resources/sprites/timer/number-4.png"));
        img4.setImage( img4.getImage());

        ImageIcon img5 = new ImageIcon(this.getClass().getResource("/resources/sprites/timer/number-5.png"));
        img5.setImage( img5.getImage());

        ImageIcon img6 = new ImageIcon(this.getClass().getResource("/resources/sprites/timer/number-6.png"));
        img6.setImage( img6.getImage());

        ImageIcon img7 = new ImageIcon(this.getClass().getResource("/resources/sprites/timer/number-7.png"));
        img7.setImage( img7.getImage());

        ImageIcon img8 = new ImageIcon(this.getClass().getResource("/resources/sprites/timer/number-8.png"));
        img8.setImage( img8.getImage());

        ImageIcon img9 = new ImageIcon(this.getClass().getResource("/resources/sprites/timer/number-9.png"));
        img9.setImage( img9.getImage());

        map.put(0, img0);
        map.put(1, img1);
        map.put(2, img2);
        map.put(3, img3);
        map.put(4, img4);
        map.put(5, img5);
        map.put(6, img6);
        map.put(7, img7);
        map.put(8, img8);
        map.put(9, img9);
    }
}
