package com.ceiba.factura.adaptador.repositorio;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.factura.puerto.repositorio.RepositorioProductoFacturar;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Repository
public class RepositorioFacturaH2 implements RepositorioFactura {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoFactura mapeoFactura;
    private final RepositorioProductoFacturar repositorioProductoFacturar;

    @SqlStatement(namespace = "factura", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "factura", value = "obtenerporid")
    private static String sqlObtenerPorId;

    @SqlStatement(namespace = "factura", value = "actualizar")
    private static String sqlActualizar;

    public RepositorioFacturaH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoFactura mapeoFactura, RepositorioProductoFacturar repositorioProductoFacturar) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoFactura = mapeoFactura;
        this.repositorioProductoFacturar = repositorioProductoFacturar;
    }

    @Override
    public Long guardar(Factura factura) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("valor_total", factura.getValorTotal());
        paramSource.addValue("valor_imp_iva", factura.getValorImpIva());
        paramSource.addValue("valor_imp_consumo", factura.getValorImpConsumo());
        paramSource.addValue("fecha_creacion", factura.getFechaCreación());
        paramSource.addValue("aplica_propina", factura.getAplicaPropina());
        paramSource.addValue("valor_propina", factura.getValorPropina());
        Long idFacturaGuardada = this.customNamedParameterJdbcTemplate.crear(paramSource, sqlCrear);
        repositorioProductoFacturar.guardarPorFactura(factura, idFacturaGuardada);
        return idFacturaGuardada;
    }

    @Override
    public Factura obtener(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() -> this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlObtenerPorId, paramSource, mapeoFactura));
    }

    @Override
    public void actualizar(Factura factura) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", factura.getId());
        paramSource.addValue("valor_total", factura.getValorTotal());
        paramSource.addValue("valor_imp_iva", factura.getValorImpIva());
        paramSource.addValue("valor_imp_consumo", factura.getValorImpConsumo());
        paramSource.addValue("aplica_propina", factura.getAplicaPropina());
        paramSource.addValue("valor_propina", factura.getValorPropina());
        paramSource.addValue("fecha_actualizacion", factura.getFechaActualizacion());

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizar, paramSource);
        this.repositorioProductoFacturar.eliminarPorFactuta(factura.getId());
        this.repositorioProductoFacturar.guardarPorFactura(factura, factura.getId());
    }
    
}
