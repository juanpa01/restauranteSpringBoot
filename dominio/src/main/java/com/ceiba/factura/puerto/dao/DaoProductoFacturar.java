package com.ceiba.factura.puerto.dao;

import java.util.List;

import com.ceiba.factura.modelo.dto.ResumenProductoFacturarDTO;

public interface DaoProductoFacturar {
    List<ResumenProductoFacturarDTO> obtenerResumenDePrdocutosFacturar(Long idFactura);
}
