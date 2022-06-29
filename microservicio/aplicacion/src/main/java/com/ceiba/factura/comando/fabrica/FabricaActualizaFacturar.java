package com.ceiba.factura.comando.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.factura.comando.ComandoActualizaFacturar;
import com.ceiba.factura.modelo.dto.ActualizarFacturaDTO;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;

@Component
public class FabricaActualizaFacturar {

    private final FabricaObtenerProducto fabricaObtenerProducto;
    private final RepositorioFactura repositorioFactura;

    public FabricaActualizaFacturar(FabricaObtenerProducto fabricaObtenerProducto, RepositorioFactura repositorioFactura) {
        this.fabricaObtenerProducto = fabricaObtenerProducto;
        this.repositorioFactura = repositorioFactura;
    }

    public ActualizarFacturaDTO crear(ComandoActualizaFacturar comandoActualizaFacturar) {
        return new ActualizarFacturaDTO(
            this.repositorioFactura.obtener(comandoActualizaFacturar.getIdFactura()),
            this.fabricaObtenerProducto.crear(comandoActualizaFacturar.getComandoProductosFacturar()),
            comandoActualizaFacturar.getAplicaPropina()
        );
    }


    
}
