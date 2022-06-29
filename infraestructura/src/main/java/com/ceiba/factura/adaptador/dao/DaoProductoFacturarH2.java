package com.ceiba.factura.adaptador.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ceiba.factura.modelo.dto.ResumenProductoFacturarDTO;
import com.ceiba.factura.puerto.dao.DaoProductoFacturar;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Repository
public class DaoProductoFacturarH2 implements DaoProductoFacturar{

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoProductoFacturarRespuesta mapeoProductoFacturarRespuesta;

    @SqlStatement(namespace = "productofacturar", value = "obtenerresumen")
    private static String sqlObtener;

    public DaoProductoFacturarH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoProductoFacturarRespuesta mapeoProductoFacturarRespuesta) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoProductoFacturarRespuesta = mapeoProductoFacturarRespuesta;
    }

    @Override
    public List<ResumenProductoFacturarDTO> obtenerResumenDePrdocutosFacturar(Long idFactura) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_factura", idFactura);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtener, paramSource, mapeoProductoFacturarRespuesta);
    }
    
}
