package com.ceiba.factura.comando.fabrica;

import com.ceiba.factura.comando.ComandoSolicitudFacturar;
import com.ceiba.factura.modelo.dto.CrearFacturaDTO;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudFacturar {

    private final FabricaObtenerProducto fabricaObtenerProducto;

    public FabricaSolicitudFacturar(FabricaObtenerProducto fabricaObtenerProducto) {
        this.fabricaObtenerProducto = fabricaObtenerProducto;
    }

    public CrearFacturaDTO crear(ComandoSolicitudFacturar comandoSolicitudFacturar) {
        return new CrearFacturaDTO(this.fabricaObtenerProducto.crear(comandoSolicitudFacturar.getComandoProductosFacturar()),
            comandoSolicitudFacturar.getAplicaPropina(),
            comandoSolicitudFacturar.getFechaCreacion()
        );
    }

    
    
}
