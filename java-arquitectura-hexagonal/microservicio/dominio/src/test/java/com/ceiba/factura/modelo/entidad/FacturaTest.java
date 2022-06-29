package com.ceiba.factura.modelo.entidad;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDiaFacturar;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.factura.modelo.dto.ActualizarFacturaDTOTestBuilder;
import com.ceiba.producto.entidad.ProductoTestDataBuilder;
import com.ceiba.factura.modelo.dto.ActualizarFacturaDTO;

public class FacturaTest {

    @Test
    void deberiaCrearFacturaCorrectaConPropina() {
        LocalDateTime fechaCreacion = LocalDateTime.of(2022, 6, 30, 14, 33);

        ProductoFacturar productoFacturar = new ProductoFacturarTestDataBuilder()
            .conCantidad(2)
            .conProducto(new ProductoTestDataBuilder()
                .conProductoPorDefecto()
                .reconstruir())
            .build();
        
        Factura factura = new FacturaTestDataBuilder()
            .conProducto(productoFacturar)
            .conAplicaPropina(true)
            .conFechaCreacion(fechaCreacion)
            .build();

        Assertions.assertEquals(productoFacturar, factura.getProductosFacturar().get(0));
        Assertions.assertEquals(4600, factura.getValorPropina().longValue());
    }
    
    @Test
    void deberiaCrearFacturaCorrectaSinPropina() {
        LocalDateTime fechaCreacion = LocalDateTime.of(2022, 6, 30, 14, 33);

        ProductoFacturar productoFacturar = new ProductoFacturarTestDataBuilder()
            .conCantidad(4)
            .conProducto(new ProductoTestDataBuilder()
                .conProductoPorDefecto()
                .reconstruir())
            .build();
        
        Factura factura = new FacturaTestDataBuilder()
            .conProducto(productoFacturar)
            .conAplicaPropina(false)
            .conFechaCreacion(fechaCreacion)
            .build();
        
        Assertions.assertEquals(0, factura.getValorPropina().longValue());
        Assertions.assertNotNull(factura.getFechaCreación());
    }

    @Test
    public void deberiaReconstruirCorractamenteFactura() {
        ProductoFacturar productoFacturar = new ProductoFacturarTestDataBuilder()
            .conCantidad(4)
            .conProducto(new ProductoTestDataBuilder()
                .conProductoPorDefecto()
                .reconstruir())
            .build();

        Factura factura = new FacturaTestDataBuilder()
            .conId(5l)
            .conProducto(productoFacturar)
            .conValorImpIva(BigDecimal.valueOf(17480))
            .conValorImpConsumo(BigDecimal.valueOf(7360))
            .conValorPropina(BigDecimal.ZERO)
            .conValorTotal(BigDecimal.valueOf(116840))
            .conAplicaPropina(false)
            .reconstruir();

        Assertions.assertEquals(productoFacturar, factura.getProductosFacturar().get(0));
    }

    @Test
    void deberiaFacutrarCorrectamenteDiaDisonible() {
        LocalDateTime fechaCreacion = LocalDateTime.of(2022, 6, 30, 14, 33);

        ProductoFacturar productoFacturar = new ProductoFacturarTestDataBuilder()
            .conCantidad(2)
            .conProducto(new ProductoTestDataBuilder()
                .conProductoPorDefecto()
                .reconstruir())
            .build();
        
        Factura factura = new FacturaTestDataBuilder()
            .conProducto(productoFacturar)
            .conAplicaPropina(true)
            .conFechaCreacion(fechaCreacion)
            .build();
        
        Assertions.assertEquals(factura.getFechaCreación().getDayOfWeek().toString(), "THURSDAY");
    }

    @Test
    void deberiaLanzarErrorFacturarDiaNoLaboral() {
        LocalDateTime fechaCreacion = LocalDateTime.of(2022, 6, 28, 14, 33);

        ProductoFacturar productoFacturar = new ProductoFacturarTestDataBuilder()
            .conCantidad(2)
            .conProducto(new ProductoTestDataBuilder()
                .conProductoPorDefecto()
                .reconstruir())
            .build();

        BasePrueba.assertThrows(() -> new FacturaTestDataBuilder()
            .conProducto(productoFacturar)
            .conAplicaPropina(true)
            .conFechaCreacion(fechaCreacion)
            .build(), ExcepcionDiaFacturar.class, "Día no valido para generar facturas");
    }

    @Test
    void deberiaLanzarErrorReconstruirFacturaFaltaId() {
        BasePrueba.assertThrows(() -> new FacturaTestDataBuilder()
            .conFacturaPorDefecto()
            .conId(null)
            .reconstruir(), ExcepcionValorObligatorio.class, "El id es requerido");
    }

    @Test
    void deberiaLanzarErrorReconstruirFacturaFaltaProductos() {
        BasePrueba.assertThrows(() -> new FacturaTestDataBuilder()
            .conFacturaPorDefecto()
            .conProductos(new ArrayList<>())
            .reconstruir(), ExcepcionValorObligatorio.class, "Deben de existir al menos un producto para facturar");
    }

    @Test
    void deberiaActualizarFacturaCorrectamente(){
        LocalDateTime fechaCreacion = LocalDateTime.of(2022, 6, 30, 14, 33);

        ProductoFacturar productoFacturarCrear = new ProductoFacturarTestDataBuilder()
            .conCantidad(2)
            .conProducto(new ProductoTestDataBuilder()
                .conProductoPorDefecto()
                .reconstruir())
            .build();
        
        ProductoFacturar productoFacturarActualizar = new ProductoFacturarTestDataBuilder()
            .conCantidad(1)
            .conProducto(new ProductoTestDataBuilder()
                .conId(2l)
                .conNombre("SALCHIPAPA")
                .conValor(BigDecimal.valueOf(10000))
                .reconstruir())
            .build();
        
        ActualizarFacturaDTO actualizarFacturaDTO = new ActualizarFacturaDTOTestBuilder()
            .conProductoFacturar(productoFacturarActualizar)
            .build();
        
        Factura factura = new FacturaTestDataBuilder()
            .conProducto(productoFacturarCrear)
            .conAplicaPropina(true)
            .conFechaCreacion(fechaCreacion)
            .build();
        
        factura.actualizar(actualizarFacturaDTO.getProductosFacturar());

        Assertions.assertEquals(productoFacturarActualizar.getProducto().getNombre(), factura.getProductosFacturar().get(0).getProducto().getNombre());
        Assertions.assertEquals(13700, factura.getValorTotal().longValue());
        
    }
}
