package com.ceiba.factura.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoActualizaFacturar {
    private Long idFactura;
    private List<ComandoProductoFacturar> comandoProductosFacturar;
    private Boolean aplicaPropina;    
}
