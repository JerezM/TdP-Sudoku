package main;

import java.awt.EventQueue;

import main.view.GUI;

public class Sudoku {
        
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					GUI frame = GUI.getInstance();
                    frame.setVisible(true);
                    
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
