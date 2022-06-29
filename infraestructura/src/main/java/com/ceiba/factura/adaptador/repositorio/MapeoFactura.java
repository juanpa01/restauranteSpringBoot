package com.ceiba.factura.adaptador.repositorio;

import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.puerto.repositorio.RepositorioProductoFacturar;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoFactura implements RowMapper<Factura>, MapperResult {

    private final RepositorioProductoFacturar repositorioProductoFacturar;

    public MapeoFactura(RepositorioProductoFacturar repositorioProductoFacturar) {
        this.repositorioProductoFacturar = repositorioProductoFacturar;
    }

    @Override
    public Factura mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var valorImpIva = resultSet.getBigDecimal("valor_imp_iva");
        var valorImpConsumo = resultSet.getBigDecimal("valor_imp_consumo");
        var valorTotal = resultSet.getBigDecimal("valor_total");
        var valorPropina = resultSet.getBigDecimal("valor_propina");
        var aplicaPropina = resultSet.getBoolean("aplica_propina");
        
        return Factura.reconstruir(id, repositorioProductoFacturar.obtenerPorFactura(id), valorImpIva, valorImpConsumo, valorPropina, valorTotal, aplicaPropina);
     }

}
