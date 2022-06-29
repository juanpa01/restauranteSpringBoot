package com.ceiba.factura.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoSolicitudFacturar {
    private List<ComandoProductoFacturar> comandoProductosFacturar;
    private Boolean aplicaPropina;
    private LocalDateTime fechaCreacion;
}
