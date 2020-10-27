package main.view.opciones_numeros;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class OpcionesNumerosListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnPresionado = (JButton) e.getSource();
        OpcionesNumeros panelOpciones = (OpcionesNumeros) btnPresionado.getParent();

        panelOpciones.notificarBtnPresionado(btnPresionado);
    }
    
}
