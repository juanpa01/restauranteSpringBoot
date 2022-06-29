package com.ceiba.factura.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.producto.entidad.Producto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductoFacturar implements Serializable{

    private Long id;
    @NonNull
    private Integer cantidad;
    @NonNull
    private Producto producto;

    public static ProductoFacturar crear(Integer cantidad, Producto producto) {
        ValidadorArgumento.validarObligatorio(cantidad, "Cantidad es requerida");
        ValidadorArgumento.validarObligatorio(producto, "Producto es requerido");
        return new ProductoFacturar(cantidad, producto);
    }

    public static ProductoFacturar reconstruir(Long id, Integer cantidad, Producto producto) {
        ValidadorArgumento.validarObligatorio(cantidad, "Cantidad es requerida");
        ValidadorArgumento.validarObligatorio(producto, "Producto es requerido");
        return new ProductoFacturar(id, cantidad, producto);
    }

    public BigDecimal calcularValorSinImpuestos() {
        return producto.getValor()
            .multiply(BigDecimal.valueOf(cantidad));
    }

    
    
}
