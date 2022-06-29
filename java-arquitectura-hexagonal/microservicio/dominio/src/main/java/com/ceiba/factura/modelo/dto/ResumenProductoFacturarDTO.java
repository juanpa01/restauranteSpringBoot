package com.ceiba.factura.modelo.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ResumenProductoFacturarDTO {
    private Long cantidad;
    private String nombreProducto;
    private BigDecimal valorUnitario;
}
