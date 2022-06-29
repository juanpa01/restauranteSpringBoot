package com.ceiba.factura.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.factura.comando.ComandoActualizaFacturar;
import com.ceiba.factura.comando.ComandoSolicitudFacturar;
import com.ceiba.factura.comando.manejador.ManejadorFacturar;
import com.ceiba.factura.comando.manejador.ManejadorActualizaFacturar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/factura")
@Tag(name = "Controlador comando factura")
public class ComandoControladorFactura {

    private final ManejadorFacturar manejadorFacturar;
    private final ManejadorActualizaFacturar manejadorActualizaFacturar;

    public ComandoControladorFactura(ManejadorFacturar manejadorFacturar, ManejadorActualizaFacturar manejadorActualizaFacturar) {
        this.manejadorFacturar = manejadorFacturar;
        this.manejadorActualizaFacturar = manejadorActualizaFacturar;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Facturar", description = "Metodo utilizado para crear una nueva factura")
    public ComandoRespuesta<Long> facturar(@RequestBody ComandoSolicitudFacturar comandoSolicitudFacturar) {
        return this.manejadorFacturar.ejecutar(comandoSolicitudFacturar);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id-factura}")
    @Operation(summary = "Facturar", description = "Metodo utilizado para actualizar una factura")
    public void facturar(@PathVariable("id-factura") Long idFactura, @RequestBody ComandoActualizaFacturar comandoActualizaFacturar) {
        comandoActualizaFacturar.setIdFactura(idFactura);
        this.manejadorActualizaFacturar.ejecutar(comandoActualizaFacturar);
    }
}
