package com.ceiba.factura.modelo.entidad;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ceiba.factura.modelo.dto.CrearFacturaDTOTestBuilder;
import com.ceiba.producto.entidad.ProductoTestDataBuilder;

public class FacturaTestDataBuilder {
    private Long id;
    private List<ProductoFacturar> productosFacturar;
    private BigDecimal valorImpIva;
    private BigDecimal valorImpConsumo;
    private BigDecimal valorTotal;
    private BigDecimal valorPropina;
    private Boolean aplicaPropina;
    private LocalDateTime fechaCreacion;

    public FacturaTestDataBuilder() {
        productosFacturar = new ArrayList<>();
    }

    public FacturaTestDataBuilder conFacturaPorDefecto() {
        this.id = 199l;
        var producto = new ProductoTestDataBuilder().conProductoPorDefecto().reconstruir();
        this.conProducto(new ProductoFacturarTestDataBuilder().conCantidad(5).conProducto(producto).conId(2l).reconstruir());
        this.valorTotal = BigDecimal.valueOf(36000);
        return this;
    }

    public FacturaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public FacturaTestDataBuilder conProducto(ProductoFacturar productoFacturar) {
        this.productosFacturar.add(productoFacturar);
        return this;
    }

    public FacturaTestDataBuilder conProductos(List<ProductoFacturar> productosFacturar) {
        this.productosFacturar = productosFacturar;
        return this;
    }

    public FacturaTestDataBuilder conValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
        return this;
    }

    public FacturaTestDataBuilder conValorImpIva(BigDecimal valorImpIva) {
        this.valorImpIva = valorImpIva;
        return this;
    }

    public FacturaTestDataBuilder conValorImpConsumo(BigDecimal valorImpConsumo) {
        this.valorImpConsumo = valorImpConsumo;
        return this;
    }

    public FacturaTestDataBuilder conValorPropina(BigDecimal valorPropina) {
        this.valorPropina = valorPropina;
        return this;
    }

    public FacturaTestDataBuilder conAplicaPropina(Boolean aplicaPropina) {
        this.aplicaPropina = aplicaPropina;
        return this;
    }

    public FacturaTestDataBuilder conFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public Factura build() {
        return Factura.crear(new CrearFacturaDTOTestBuilder()
                .conProductosFacturar(productosFacturar)
                .conAplicarPropina(aplicaPropina)
                .conFechaCreacion(fechaCreacion)
                .build());
    }

    public Factura reconstruir() {
        return Factura.reconstruir(id, productosFacturar, valorImpIva, valorImpConsumo, valorPropina, valorTotal, aplicaPropina );
    }

    
}
