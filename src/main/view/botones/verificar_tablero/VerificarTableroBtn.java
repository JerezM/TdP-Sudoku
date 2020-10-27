package main.view.botones.verificar_tablero;

import javax.swing.JButton;

import main.view.tablero.TableroViewImpl;
import main.view.tablero.TableroViewVerificar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VerificarTableroBtn extends JButton implements ActionListener {
    private TableroViewVerificar tablero;

    public VerificarTableroBtn() {
        this.setText("Verificar Tablero");
        this.setBounds(345, 64, 160, 23);

        this.addActionListener(this);

        tablero = TableroViewImpl.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tablero.verificarTablero();
    }
    
}
