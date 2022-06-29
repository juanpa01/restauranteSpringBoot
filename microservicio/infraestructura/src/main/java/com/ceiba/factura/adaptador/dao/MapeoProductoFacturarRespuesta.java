package com.ceiba.factura.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ceiba.factura.modelo.dto.ResumenProductoFacturarDTO;
import com.ceiba.infraestructura.jdbc.MapperResult;

@Component
public class MapeoProductoFacturarRespuesta implements RowMapper<ResumenProductoFacturarDTO>, MapperResult{

    @Override
    public ResumenProductoFacturarDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var cantidad = resultSet.getLong("cantidad");
        var nombreProducto = resultSet.getString("nombre_producto");
        var valorUnitario = resultSet.getBigDecimal("valor_unitario");

        return ResumenProductoFacturarDTO.builder()
            .cantidad(cantidad)
            .nombreProducto(nombreProducto)
            .valorUnitario(valorUnitario)
            .build();
    }
    
}
