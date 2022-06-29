package com.ceiba.factura.adaptador.dao;

import com.ceiba.factura.modelo.dto.ResumenFacturaDTO;
import com.ceiba.factura.puerto.dao.DaoFactura;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoFacturaH2 implements DaoFactura {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoFacturaRespuesta mapeoFacturaResumen;

    @SqlStatement(namespace = "factura", value = "obtenerfacturas")
    private static String sqlObtener;

    public DaoFacturaH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoFacturaRespuesta mapeoFacturaResumen) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoFacturaResumen = mapeoFacturaResumen;
    }

    @Override
    public List<ResumenFacturaDTO> obtenerResumenDeFacturas() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtener, mapeoFacturaResumen);
    }
}
