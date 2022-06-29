package com.ceiba.factura.modelo.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ceiba.factura.modelo.entidad.ProductoFacturar;

public class CrearFacturaDTOTestBuilder {
    private List<ProductoFacturar> productosFacturar;
    private Boolean aplicaPropina;
    private LocalDateTime fechaCreacion;

    public CrearFacturaDTOTestBuilder() {
        this.productosFacturar = new ArrayList<>();
    }

    public CrearFacturaDTOTestBuilder conProductoFacturar(ProductoFacturar productoFacturar) {
        this.productosFacturar.add(productoFacturar);
        return this;
    }

    public CrearFacturaDTOTestBuilder conProductosFacturar(List<ProductoFacturar> productosFacturar) {
        this.productosFacturar = productosFacturar;
        return this;
    }

    public CrearFacturaDTOTestBuilder conAplicarPropina(Boolean aplicaPropina) {
        this.aplicaPropina = aplicaPropina;
        return this;
    }

    public CrearFacturaDTOTestBuilder conFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public CrearFacturaDTO build() {
        return new CrearFacturaDTO(productosFacturar, aplicaPropina, fechaCreacion);
    }

    
}
 