package com.ceiba.dominio.excepcion;

public class ExcepcionDiaFacturar extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExcepcionDiaFacturar(String message) {
        super(message);
    }
    
}
