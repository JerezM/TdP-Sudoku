package main.view.timer;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class TimerViewImpl extends JPanel implements TimerView {

    private static TimerViewImpl instance;

    private JLabel[] labels;

    private TimerViewImpl() {

        this.setBounds(545, 128, 80, 17);
        this.setLayout( new GridLayout(1, 8) );
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackline);

        labels = new JLabel[8];

        for (int i = 0; i < labels.length; i++) {

            labels[i] = new JLabel();
            labels[i].setSize(10, 17);
            labels[i].setVisible(true);

            this.add( labels[i] );
        }
    }

    public static TimerViewImpl getInstance() {
        if (instance == null) {
            instance = new TimerViewImpl();
        }

        return instance;
    }
    
    @Override
    public void notificarCambiosSprites(ImageIcon[] sprites) {

        for (int i = 0; i < sprites.length; i++) {

            JLabel labelActual = labels[i];
            this.setSpriteToLabel(labelActual, sprites[i] );
            
        }

        this.repaint();
    }

    /**
     * Setea al label el sprite parametrizado.
     * @param label JLabel a actualizar.
     * @param sprite Nueva sprite que va a tener el label.
     */
    protected void setSpriteToLabel(JLabel label, ImageIcon sprite) {

        Image image = sprite.getImage();
		if (image != null) {  
			image = image.getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH);
            sprite.setImage(image);
            label.setIcon(sprite);
        }

    }
    
}
