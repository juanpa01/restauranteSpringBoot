package com.ceiba.factura.modelo.entidad;

import org.junit.jupiter.api.Test;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.producto.entidad.ProductoTestDataBuilder;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;

public class ProductoFacturarTest {
    
    @Test
    public void deberiaCrearProductoFacturarCorrectamente() {
        ProductoFacturar productoFacturar = new ProductoFacturarTestDataBuilder()
            .conCantidad(2)
            .conProducto(new ProductoTestDataBuilder()
                .conProductoPorDefecto()
                .reconstruir())
            .build();

        Assertions.assertEquals("DESGRANADO", productoFacturar.getProducto().getNombre());
    }

    @Test
    public void deberiaCrearProductofacturarCorrectamenteCAlcularValorSinImpuesto() {
        ProductoFacturar productoFacturar = new ProductoFacturarTestDataBuilder()
            .conCantidad(3)
            .conProducto(new ProductoTestDataBuilder()
                .conProductoPorDefecto()
                .reconstruir())
            .build();
        
        BigDecimal valorSinImp = productoFacturar.calcularValorSinImpuestos(); 

        Assertions.assertEquals(69000, valorSinImp.longValue());
    }

    @Test
    public void deberiaReconstruirCorrectamente() {
        ProductoFacturar productoFacturar = new ProductoFacturarTestDataBuilder()
            .conId(1l)
            .conProducto(new  ProductoTestDataBuilder()
                .conProductoPorDefecto()
                .reconstruir())
            .conCantidad(11)
            .reconstruir();
        
        Assertions.assertEquals(11, productoFacturar.getCantidad());
    }

    @Test
    public void deberiaLanzarErrorContruirProductoFacturarFaltaProducto() {
        BasePrueba.assertThrows(() -> new ProductoFacturarTestDataBuilder()
            .conCantidad(3)
            .conProducto(null)
            .build(), ExcepcionValorObligatorio.class, "Producto es requerido");
    }
}
