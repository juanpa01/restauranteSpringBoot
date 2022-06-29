package com.ceiba.producto.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ceiba.dominio.ValidadorArgumento;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producto implements Serializable {
    private Long id;
    private String nombre;
    private BigDecimal valor;

    public static Producto reconstruir(Long id, String nombre, BigDecimal valor) {
        ValidadorArgumento.validarObligatorio(id, "El id del producto es requerido");
        ValidadorArgumento.validarObligatorio(nombre, "El nombre del producto es requerido");
        ValidadorArgumento.validarObligatorio(valor, "Valor es requerido para el producto");
        return new Producto(id, nombre, valor);

    }
}
