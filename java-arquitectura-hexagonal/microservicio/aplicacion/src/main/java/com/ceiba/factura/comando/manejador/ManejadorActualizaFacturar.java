package com.ceiba.factura.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.factura.comando.ComandoActualizaFacturar;
import com.ceiba.factura.comando.fabrica.FabricaActualizaFacturar;
import com.ceiba.factura.servicio.ServicioFacturar;
import com.ceiba.manejador.ManejadorComando;

@Component
public class ManejadorActualizaFacturar implements ManejadorComando<ComandoActualizaFacturar>{

    private final FabricaActualizaFacturar fabricaActualizaFacturar;
    private final ServicioFacturar servicioFacturar;

    public ManejadorActualizaFacturar(FabricaActualizaFacturar fabricaActualizaFacturar, ServicioFacturar servicioFacturar) {
        this.fabricaActualizaFacturar = fabricaActualizaFacturar;
        this.servicioFacturar = servicioFacturar;
    }

    @Override
    public void ejecutar(ComandoActualizaFacturar comandoActualizaFacturar) {
        this.servicioFacturar.actualizarFactura(fabricaActualizaFacturar.crear(comandoActualizaFacturar));
    }
    
}
