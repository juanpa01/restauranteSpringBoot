package com.ceiba.factura.modelo.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.ceiba.factura.modelo.entidad.ProductoFacturar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CrearFacturaDTO {
    private List<ProductoFacturar> productosFacturar;
    private Boolean aplicaPropina;
    private LocalDateTime fechaCreacion;
}
