package main.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class VerificadorTableroServiceImpl implements VerificadorTablero {

    private Map<Integer, Boolean> numerosRepetidos;

    /**
     * Se dividio el tablero en 9 paneles los cuales se los indica por las coordenas que poseen los mismos.
     * Vease: 
     *          P00 | P01 | P02
     *          ----+-----+----
     *          P10 | P11 | P12
     *          ----+-----+----
     *          P20 | P21 | P22
     * 
     * Por lo que este mapeo funcionario de la siguiente manera:
     * Map< CoordenadasPanel, Entry<CoordenadasInicioFinPanelEnX, CoordenadasInicioFinPanelEnY>>
     * obs: Considerar que las coordenadas del tablero se cuentan de 0 a 8.
     */
    private Map<Entry<Integer, Integer>, Entry<Entry<Integer, Integer>, Entry<Integer, Integer>>> paneles;

    public VerificadorTableroServiceImpl() {
        numerosRepetidos = new HashMap<Integer, Boolean>();
        paneles = new HashMap<Entry<Integer, Integer>, Entry<Entry<Integer, Integer>, Entry<Integer, Integer>>>();

        this.setNumerosRepetidos();
        
        paneles.put(new EntryImpl(), value)
    }

    private void setNumerosRepetidos() {
        for (int num = 1; num <= 9; num++) {
            numerosRepetidos.put(num, false);
        }
    }

	@Override
	public boolean verificarTablero(int[][] matriz) {
        boolean esCorrecto = verificarFilas(matriz);

        if(esCorrecto){
            esCorrecto = verificarColumnas(matriz);

            if(esCorrecto) {
                esCorrecto = verificarPaneles(matriz);
            }
        }

        return esCorrecto;
	}

	@Override
	public boolean verificarFilas(int[][] matriz) {
        boolean esCorrecto = true;
        
		return false;
	}

	@Override
	public boolean verificarColumnas(int[][] matriz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verificarPaneles(int[][] matriz) {
		// TODO Auto-generated method stub
		return false;
	}


    
}
