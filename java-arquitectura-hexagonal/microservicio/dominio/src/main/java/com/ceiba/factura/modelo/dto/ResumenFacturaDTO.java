package com.ceiba.factura.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class ResumenFacturaDTO {
    private Long id;
    private List<ResumenProductoFacturarDTO> productosFacturar;
    private BigDecimal valorImpIva;
    private BigDecimal valorImpConsumo;
    private BigDecimal valorTotal;
    private BigDecimal valorPropina;
    private Boolean aplicaPropina;
    private LocalDateTime fechaCreacion;

}
