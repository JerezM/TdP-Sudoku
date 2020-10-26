package main.view.botones.nuevo_juego;

import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import main.controller.tablero.TableroControllerImpl;
import main.controller.tablero.TableroControllerView;
import main.exception.SudokuFileException;
import main.view.GUI;

@SuppressWarnings("serial")
public class NuevoJuegoBtn extends JButton implements ActionListener {
    private TableroControllerView controller;

    private static final GUI gui = GUI.getInstance();
    
    public NuevoJuegoBtn() {
        this.setText("Nuevo Juego");
        this.setBounds(345, 30, 160, 23);

        this.addActionListener(this);

        controller = TableroControllerImpl.getInstance();
    }

    /**
     * Abre el fileChooser para que eligas el archivo del tablero y despues le pasa el mismo al controller.
     */
    public void abrirFileChooser() {
        JFileChooser fileChooser = new JFileChooser(".");
        int returnVal = fileChooser.showOpenDialog( gui );
    
        
                
        if( returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String fileName = file.getName();

            if (file != null && fileName.endsWith(".txt")) {
                try{
                    controller.cargarTableroDesdeArchivo(file);

                    this.setEnabled(false);
                }
                catch (SudokuFileException e) {
                    System.out.println(e.getStackTrace());
                    this.mostrarCartel(e.getMessage());
                }
            }
            else {
                this.mostrarCartel("El archivo seleccionado es invalido.");
            }

            
        }
        else {
            System.out.print("El usuario no selecciono ningun archivo");
        }
    }

    protected void mostrarCartel(String msg) {
        JOptionPane.showInternalMessageDialog(null, msg);
        gui.dispatchEvent( new WindowEvent(gui, WindowEvent.WINDOW_CLOSING) );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.abrirFileChooser();
    }
}
