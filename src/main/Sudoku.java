package main;

import java.awt.EventQueue;

import main.model.celda.factories.CeldaModelFactory;
import main.model.celda.factories.CeldaPokemonFactory;
import main.model.tablero.TableroModel;
import main.model.tablero.TableroModelImpl;
import main.view.GUI;

public class Sudoku {
        
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					CeldaModelFactory factory = new CeldaPokemonFactory();
					TableroModel tableroModel = TableroModelImpl.getInstance();
					tableroModel.setCeldaFactory(factory);
					
					GUI frame = GUI.getInstance();
					frame.setVisible(true);

					
                    
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
