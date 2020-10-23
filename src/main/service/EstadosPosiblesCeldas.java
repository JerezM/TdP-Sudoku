package main.service;

public enum EstadosPosiblesCeldas {
    CELDA_NO_SELECCIONADA(0),
    CELDA_SELECCIONADA_INDIRECTAMENTE(1),
    CELDA_SELECCIONADA(2),
    CELDA_EN_ERROR(3);

    private int estado;

    private EstadosPosiblesCeldas(int estado){
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }
}
