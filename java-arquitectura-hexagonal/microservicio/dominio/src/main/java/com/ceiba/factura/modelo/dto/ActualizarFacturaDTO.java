package com.ceiba.factura.modelo.dto;

import java.util.List;

import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.modelo.entidad.ProductoFacturar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ActualizarFacturaDTO {
    private Factura factura;
    private List<ProductoFacturar> productosFacturar;
    private Boolean aplicaPropina;
}
