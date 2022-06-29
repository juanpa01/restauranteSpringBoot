package com.ceiba.factura.servicio;

import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.factura.modelo.dto.ActualizarFacturaDTO;
import com.ceiba.factura.modelo.dto.CrearFacturaDTO;
import com.ceiba.factura.modelo.entidad.Factura;

public class ServicioFacturar {

    private final RepositorioFactura repositorioFactura;

    public ServicioFacturar(RepositorioFactura repositorioFactura) {
        this.repositorioFactura = repositorioFactura;
    }

    public long guardarFactura(CrearFacturaDTO crearFacturaDTO) {
        Factura factura = Factura.crear(crearFacturaDTO);
        return repositorioFactura.guardar(factura);
    }

    public void actualizarFactura(ActualizarFacturaDTO actualizarFacturaDTO) {
        ValidadorArgumento.validarObligatorio(actualizarFacturaDTO.getFactura(), "No se econtró la factura consultada");
        Factura factura = actualizarFacturaDTO.getFactura();
        factura.setAplicaPropina(actualizarFacturaDTO.getAplicaPropina());
        factura.actualizar(actualizarFacturaDTO.getProductosFacturar());
        this.repositorioFactura.actualizar(factura);
    }
}
  