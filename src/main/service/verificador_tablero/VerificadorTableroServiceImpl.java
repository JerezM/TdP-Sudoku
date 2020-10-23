package main.service.verificador_tablero;

import java.util.HashMap;
import java.util.List;
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
        
       // paneles.put(new EntryImpl(), value)
    }

    private void setNumerosRepetidos() {
        for (int num = 1; num <= 9; num++) {
            numerosRepetidos.put(num, false);
        }
    }

	@Override
	public Entry<Boolean, List<Entry<Integer, Integer>>> verificarTablero(int[][] matrizActual, int[][] matrizInicial) {
        boolean esCorrecto = verificarFilas(matrizActual, matrizInicial);

        if(esCorrecto){
            esCorrecto = verificarColumnas(matrizActual, matrizInicial);

            if(esCorrecto) {
                esCorrecto = verificarPaneles(matrizActual, matrizInicial);
            }
        }

        return null;
	}

	@Override
	public boolean verificarFilas(int[][] matrizActual, int[][] matrizInicial) {
        boolean esCorrecto = true;
        
		return false;
	}

	@Override
	public boolean verificarColumnas(int[][] matrizActual, int[][] matrizInicial) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verificarPaneles(int[][] matrizActual, int[][] matrizInicial) {
		// TODO Auto-generated method stub
		return false;
	}


    
}
