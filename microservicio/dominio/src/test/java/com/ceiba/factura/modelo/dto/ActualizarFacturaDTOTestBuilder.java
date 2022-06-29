package com.ceiba.factura.modelo.dto;

import java.util.ArrayList;
import java.util.List;

import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.modelo.entidad.ProductoFacturar;

public class ActualizarFacturaDTOTestBuilder {
    private Factura factura;
    private List<ProductoFacturar> productosFacturar;
    private Boolean aplicaPropina;

    public ActualizarFacturaDTOTestBuilder() {
        this.productosFacturar = new ArrayList<>();
    }

    public ActualizarFacturaDTOTestBuilder conFactura(Factura factura) {
        this.factura = factura;
        return this;
    }

    public ActualizarFacturaDTOTestBuilder conProductoFacturar(ProductoFacturar productosFacturar) {
        this.productosFacturar.add(productosFacturar);
        return this;
    }

    public ActualizarFacturaDTOTestBuilder conProductosFacturar(List<ProductoFacturar> productosFacturar) {
        this.productosFacturar = productosFacturar;
        return this;
    }

    public ActualizarFacturaDTOTestBuilder conAplicarPropina(Boolean aplicaPropina) {
        this.aplicaPropina = aplicaPropina;
        return this;
    }

    public ActualizarFacturaDTO build() {
        return new ActualizarFacturaDTO(factura, productosFacturar, aplicaPropina);
    }
    

    
}
