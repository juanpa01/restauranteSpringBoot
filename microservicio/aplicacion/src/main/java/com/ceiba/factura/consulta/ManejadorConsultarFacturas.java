package com.ceiba.factura.consulta;

import com.ceiba.factura.modelo.dto.ResumenFacturaDTO;
import com.ceiba.factura.puerto.dao.DaoFactura;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultarFacturas {

    private final DaoFactura daoFactura;

    public ManejadorConsultarFacturas(DaoFactura daoFactura) {
        this.daoFactura = daoFactura;
    }

    public List<ResumenFacturaDTO> ejecutar(){
        return daoFactura.obtenerResumenDeFacturas();
    }
}
