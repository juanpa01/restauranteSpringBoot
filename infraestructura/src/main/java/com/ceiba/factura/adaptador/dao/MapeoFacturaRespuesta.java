package com.ceiba.factura.adaptador.dao;

import com.ceiba.factura.modelo.dto.ResumenFacturaDTO;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoFacturaRespuesta implements RowMapper<ResumenFacturaDTO>, MapperResult {
    private final DaoProductoFacturarH2 daoProductoFacturarH2;

    public MapeoFacturaRespuesta(DaoProductoFacturarH2 daoProductoFacturarH2) {
        this.daoProductoFacturarH2 = daoProductoFacturarH2;
    }

    @Override
    public ResumenFacturaDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var valorTotal = resultSet.getBigDecimal("valor_total");
        var valorImpIva = resultSet.getBigDecimal("valor_imp_iva");
        var valorImpConsumo = resultSet.getBigDecimal("valor_imp_consumo");
        var valorPropina = resultSet.getBigDecimal("valor_propina");
        var fechaCreacion = resultSet.getTimestamp("fecha_creacion").toLocalDateTime();

        return ResumenFacturaDTO.builder()
            .id(id)
            .productosFacturar(this.daoProductoFacturarH2.obtenerResumenDePrdocutosFacturar(id))
            .valorImpConsumo(valorImpConsumo)
            .valorImpIva(valorImpIva)
            .valorPropina(valorPropina)
            .valorTotal(valorTotal)
            .fechaCreacion(fechaCreacion)
            .build();
    }

}
