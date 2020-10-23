package main.service.verificador_tablero;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.service.entry.Entry;
import main.service.entry.EntryImpl;


public class VerificadorTableroServiceImpl implements VerificadorTablero {

    /**
     * Map< valor, Entry< aparece, List< Entry< posX, posY>>
     * obs: 1 <= valor <= 9;
     *      aparece: indica si dicho valor aparece en el tablero
     *      En caso de aparecer = true, la lista poseera todas las coordenadas donde aparezca dicho valor.
     */
    private Map<Integer, Entry<Boolean, List<Entry<Integer, Integer>>>> numerosRepetidos;

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
     * Map< CoordenadasPanel, Entry< CoordenadasInicioFinPanelEnX, CoordenadasInicioFinPanelEnY>>
     * obs: Considerar que las coordenadas del tablero se cuentan de 0 a 8.
     */
    private Map<Entry<Integer, Integer>, Entry<Entry<Integer, Integer>, Entry<Integer, Integer>>> paneles;

    public VerificadorTableroServiceImpl() {
        numerosRepetidos = new HashMap<Integer, Entry<Boolean, List<Entry<Integer, Integer>>>>();
        paneles = new HashMap<Entry<Integer, Integer>, Entry<Entry<Integer, Integer>, Entry<Integer, Integer>>>();

        this.setNumerosRepetidos();
        this.inicializarPaneles();
       
    }

	@Override
	public Entry<Boolean, List<Entry<Integer, Integer>>> verificarTablero(int[][] matrizActual, int[][] matrizInicial) {
        Entry<Boolean, List<Entry<Integer, Integer>>> erroresFila = verificarFilas(matrizActual, matrizInicial);
        Entry<Boolean, List<Entry<Integer, Integer>>> erroresCol = verificarColumnas(matrizActual, matrizInicial);
        Entry<Boolean, List<Entry<Integer, Integer>>> erroresPanel = verificarPaneles(matrizActual, matrizInicial);

        Set<Entry<Integer, Integer>> setCoordenadasErrores = new HashSet<Entry<Integer, Integer>>();

        setCoordenadasErrores.addAll( erroresFila.getValue() );
        setCoordenadasErrores.addAll( erroresCol.getValue() );
        setCoordenadasErrores.addAll( erroresPanel.getValue() );

        List<Entry<Integer, Integer>> listCoordenadasErrores = new LinkedList<Entry<Integer, Integer>>(setCoordenadasErrores);

        boolean hayErroresFila = erroresFila.getKey();
        boolean hayErroresCol = erroresCol.getKey();
        boolean hayErroresPanel = erroresPanel.getKey();

        boolean resultado = (hayErroresFila || hayErroresCol || hayErroresPanel);

        return new EntryImpl<Boolean, List<Entry<Integer, Integer>>>(resultado, listCoordenadasErrores);
	}

	@Override
	public Entry<Boolean, List<Entry<Integer, Integer>>> verificarFilas(int[][] matrizActual, int[][] matrizInicial) {
        Entry<Boolean, List<Entry<Integer, Integer>>> erroresFila = null;
        boolean resultado = true;
        List<Entry<Integer, Integer>> listCoordenadasErrores = null;

        Set<Entry<Integer, Integer>> setErrores = new HashSet<Entry<Integer, Integer>>();

        for (int posY = 0; posY < matrizActual.length; posY++) {
            for (int posX = 0; posX < matrizActual[0].length; posX++) {
                int valorCelda = matrizInicial[posY][posX];

                if (valorCelda != 0) {//Si no es una celda del estado inicial

                    if (valorCelda == 0) {//La celda esta vacia -> Error
                        setErrores.add( new EntryImpl<Integer, Integer>(posX, posY) );
                        resultado = false;
                    }
                    else {
                        Entry<Boolean, List<Entry<Integer, Integer>>> entradaActual = numerosRepetidos.get(valorCelda);
                        boolean resultadoActual = entradaActual.getKey();
                        List<Entry<Integer, Integer>> listActual = entradaActual.getValue();
                        Entry<Integer, Integer> coordenadasActuales = new EntryImpl<Integer, Integer>(posX, posY);

                        if (resultadoActual && listActual.size() == 1) resultado = true;//Se repiten valores en la fila -> Error

                        if (!resultadoActual) resultadoActual = true;
                        listActual.add(coordenadasActuales);

                        entradaActual.setKey(resultadoActual);
                        entradaActual.setValue(listActual);

                        numerosRepetidos.put(valorCelda, entradaActual);
                    }

                }

            }
            setErrores.addAll( this.buscarErrores() );
        }

        listCoordenadasErrores = new LinkedList<Entry<Integer, Integer>>(setErrores);
        erroresFila = new EntryImpl<Boolean, List<Entry<Integer, Integer>>>(resultado, listCoordenadasErrores);

		return erroresFila;
	}

	@Override
	public Entry<Boolean, List<Entry<Integer, Integer>>> verificarColumnas(int[][] matrizActual, int[][] matrizInicial) {
        Entry<Boolean, List<Entry<Integer, Integer>>> erroresCol = null;
        boolean resultado = true;
        List<Entry<Integer, Integer>> listCoordenadasErrores = null;

        Set<Entry<Integer, Integer>> setErrores = new HashSet<Entry<Integer, Integer>>();

        for (int posX = 0; posX < matrizActual[0].length; posX++) {
            for (int posY = 0; posY < matrizActual.length; posY++) {
                int valorCelda = matrizInicial[posY][posX];

                if (valorCelda != 0) {//Si no es una celda del estado inicial

                    if (valorCelda == 0) {//La celda esta vacia -> Error
                        setErrores.add( new EntryImpl<Integer, Integer>(posX, posY) );
                        resultado = false;
                    }
                    else {
                        Entry<Boolean, List<Entry<Integer, Integer>>> entradaActual = numerosRepetidos.get(valorCelda);
                        boolean resultadoActual = entradaActual.getKey();
                        List<Entry<Integer, Integer>> listActual = entradaActual.getValue();
                        Entry<Integer, Integer> coordenadasActuales = new EntryImpl<Integer, Integer>(posX, posY);

                        if (resultadoActual && listActual.size() == 1) resultado = true;//Se repiten valores en la columna -> Error

                        if (!resultadoActual) resultadoActual = true;
                        listActual.add(coordenadasActuales);

                        entradaActual.setKey(resultadoActual);
                        entradaActual.setValue(listActual);

                        numerosRepetidos.put(valorCelda, entradaActual);
                    }

                }

            }
            setErrores.addAll( this.buscarErrores() );
        }

        listCoordenadasErrores = new LinkedList<Entry<Integer, Integer>>(setErrores);
        erroresCol = new EntryImpl<Boolean, List<Entry<Integer, Integer>>>(resultado, listCoordenadasErrores);

		return erroresCol;
	}

	@Override
	public Entry<Boolean, List<Entry<Integer, Integer>>> verificarPaneles(int[][] matrizActual, int[][] matrizInicial) {
		Entry<Boolean, List<Entry<Integer, Integer>>> erroresPaneles = null;
        boolean resultado = true;
        List<Entry<Integer, Integer>> listCoordenadasErrores = null;

        Set<Entry<Integer, Integer>> setErrores = new HashSet<Entry<Integer, Integer>>();

        return erroresPaneles;
	}

    /**
     * Busca los errores que se encuentras actualmente en el mapeo numerosRepetidos, y almacena las coordenadas de los mismos.
     * @return Lista que contiene las coordenadas con los errores.
     */
    private List<Entry<Integer, Integer>> buscarErrores() {
        List<Entry<Integer, Integer>> errores = null;
        Set<Entry<Integer, Integer>> set = new HashSet<Entry<Integer, Integer>>();

        for(Entry<Boolean, List<Entry<Integer, Integer>>> entry : numerosRepetidos.values() ) {
            boolean aparece = entry.getKey();
            List<Entry<Integer, Integer>> listCoordenadas = entry.getValue();

            if (aparece && listCoordenadas.size() > 1) {
                set.addAll(listCoordenadas);
            }
        }

        errores = new LinkedList<Entry<Integer, Integer>>(set);
        this.setNumerosRepetidos();

        return errores;
    }

    /**
     * Se encarga de inicializar/resetear el atributo numerosRepetidos.
     */
    private void setNumerosRepetidos() {
        for (int num = 1; num <= 9; num++) {
            List<Entry<Integer, Integer>> apariciones = new LinkedList<Entry<Integer, Integer>>();
            Entry<Boolean, List<Entry<Integer, Integer>>> entry = new EntryImpl<Boolean, List<Entry<Integer, Integer>>>(false, apariciones);
            numerosRepetidos.put(num, entry);
        }
    }

    /**
     * Se encarga de inicializar el atributo paneles.
     */
    private void inicializarPaneles() {
        Entry<Integer, Integer> coordenadasPanel00 = new EntryImpl<Integer, Integer>(0, 0);
        Entry<Integer, Integer> coordenadasEnXPanel00 = new EntryImpl<Integer, Integer>(0, 2);
        Entry<Integer, Integer> coordenadasEnYPanel00 = new EntryImpl<Integer, Integer>(0, 2);
        paneles.put(coordenadasPanel00, new EntryImpl<Entry<Integer, Integer>, Entry<Integer, Integer>>(coordenadasEnXPanel00, coordenadasEnYPanel00));
        
        Entry<Integer, Integer> coordenadasPanel01 = new EntryImpl<Integer, Integer>(0, 1);
        Entry<Integer, Integer> coordenadasEnXPanel01 = new EntryImpl<Integer, Integer>(3, 5);
        Entry<Integer, Integer> coordenadasEnYPanel01 = new EntryImpl<Integer, Integer>(0, 2);
        paneles.put(coordenadasPanel01, new EntryImpl<Entry<Integer, Integer>, Entry<Integer, Integer>>(coordenadasEnXPanel01, coordenadasEnYPanel01));
        
        Entry<Integer, Integer> coordenadasPanel02 = new EntryImpl<Integer, Integer>(0, 2);
        Entry<Integer, Integer> coordenadasEnXPanel02 = new EntryImpl<Integer, Integer>(6, 8);
        Entry<Integer, Integer> coordenadasEnYPanel02 = new EntryImpl<Integer, Integer>(0, 2);
        paneles.put(coordenadasPanel02, new EntryImpl<Entry<Integer, Integer>, Entry<Integer, Integer>>(coordenadasEnXPanel02, coordenadasEnYPanel02));

        Entry<Integer, Integer> coordenadasPanel10 = new EntryImpl<Integer, Integer>(1, 0);
        Entry<Integer, Integer> coordenadasEnXPanel10 = new EntryImpl<Integer, Integer>(0, 2);
        Entry<Integer, Integer> coordenadasEnYPanel10 = new EntryImpl<Integer, Integer>(3, 5);
        paneles.put(coordenadasPanel10, new EntryImpl<Entry<Integer, Integer>, Entry<Integer, Integer>>(coordenadasEnXPanel10, coordenadasEnYPanel10));

        Entry<Integer, Integer> coordenadasPanel11 = new EntryImpl<Integer, Integer>(1, 1);
        Entry<Integer, Integer> coordenadasEnXPanel11 = new EntryImpl<Integer, Integer>(3, 5);
        Entry<Integer, Integer> coordenadasEnYPanel11 = new EntryImpl<Integer, Integer>(3, 5);
        paneles.put(coordenadasPanel11, new EntryImpl<Entry<Integer, Integer>, Entry<Integer, Integer>>(coordenadasEnXPanel11, coordenadasEnYPanel11));

        Entry<Integer, Integer> coordenadasPanel12 = new EntryImpl<Integer, Integer>(1, 2);
        Entry<Integer, Integer> coordenadasEnXPanel12 = new EntryImpl<Integer, Integer>(6, 8);
        Entry<Integer, Integer> coordenadasEnYPanel12 = new EntryImpl<Integer, Integer>(3, 5);
        paneles.put(coordenadasPanel12, new EntryImpl<Entry<Integer, Integer>, Entry<Integer, Integer>>(coordenadasEnXPanel12, coordenadasEnYPanel12));

        Entry<Integer, Integer> coordenadasPanel20 = new EntryImpl<Integer, Integer>(2, 0);
        Entry<Integer, Integer> coordenadasEnXPanel20 = new EntryImpl<Integer, Integer>(0, 2);
        Entry<Integer, Integer> coordenadasEnYPanel20 = new EntryImpl<Integer, Integer>(6, 8);
        paneles.put(coordenadasPanel20, new EntryImpl<Entry<Integer, Integer>, Entry<Integer, Integer>>(coordenadasEnXPanel20, coordenadasEnYPanel20));

        Entry<Integer, Integer> coordenadasPanel21 = new EntryImpl<Integer, Integer>(2, 1);
        Entry<Integer, Integer> coordenadasEnXPanel21 = new EntryImpl<Integer, Integer>(3, 5);
        Entry<Integer, Integer> coordenadasEnYPanel21 = new EntryImpl<Integer, Integer>(6, 8);
        paneles.put(coordenadasPanel21, new EntryImpl<Entry<Integer, Integer>, Entry<Integer, Integer>>(coordenadasEnXPanel21, coordenadasEnYPanel21));

        Entry<Integer, Integer> coordenadasPanel22 = new EntryImpl<Integer, Integer>(2, 2);
        Entry<Integer, Integer> coordenadasEnXPanel22 = new EntryImpl<Integer, Integer>(6, 8);
        Entry<Integer, Integer> coordenadasEnYPanel22 = new EntryImpl<Integer, Integer>(6, 8);
        paneles.put(coordenadasPanel22, new EntryImpl<Entry<Integer, Integer>, Entry<Integer, Integer>>(coordenadasEnXPanel22, coordenadasEnYPanel22));
    }
    
}
