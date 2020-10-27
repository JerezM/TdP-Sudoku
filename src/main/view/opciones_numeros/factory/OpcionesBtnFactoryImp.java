package main.view.opciones_numeros.factory;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import main.view.opciones_numeros.OpcionesNumerosListener;

public class OpcionesBtnFactoryImp implements OpcionesBtnFactory {

    @Override
    public JButton createOptionBtn(int width, int height, ImageIcon imgBtn) {
        JButton btn = new JButton();

        btn.addActionListener(new OpcionesNumerosListener());
        btn.setSize(width, height);

        Image image = imgBtn.getImage();
		if (image != null) {  
			image = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
            imgBtn.setImage(image);
            btn.setIcon(imgBtn);
        }

        return btn;
    }
    
}
