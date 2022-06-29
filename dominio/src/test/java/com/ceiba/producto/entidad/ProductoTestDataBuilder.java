package com.ceiba.producto.entidad;

import java.math.BigDecimal;

public class ProductoTestDataBuilder {

    private Long id;
    private BigDecimal valor;
    private String nombre;

    public ProductoTestDataBuilder conProductoPorDefecto(){
        this.id = 5l;
        this.nombre = "DESGRANADO";
        this.valor = BigDecimal.valueOf(23000);
        return this;
    }

    public ProductoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ProductoTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ProductoTestDataBuilder conValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public Producto reconstruir() {
        return Producto.reconstruir(id, nombre, valor);
    }
}
