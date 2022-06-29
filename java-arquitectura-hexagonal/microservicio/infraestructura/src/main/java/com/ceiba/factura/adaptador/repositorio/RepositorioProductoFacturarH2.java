package com.ceiba.factura.adaptador.repositorio;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.modelo.entidad.ProductoFacturar;
import com.ceiba.factura.puerto.repositorio.RepositorioProductoFacturar;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Repository
public class RepositorioProductoFacturarH2 implements RepositorioProductoFacturar{

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoProductoFacturar mapeoProductoFacturar;

    @SqlStatement(namespace = "productofacturar", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "productofacturar", value = "obtenerporfactura")
    private static String sqlObtenerPorFactura;

    @SqlStatement(namespace = "productofacturar", value = "eliminar")
    private static String sqlEliminar;

    public RepositorioProductoFacturarH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoProductoFacturar mapeoProductoFacturar) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoProductoFacturar = mapeoProductoFacturar;
    }

    @Override
    public void guardarPorFactura(Factura factura, Long idFactura) {
        factura.getProductosFacturar().stream().forEach(productoFacturar -> this.guardar(productoFacturar, idFactura));
    }

    @Override
    public List<ProductoFacturar> obtenerPorFactura(Long idFactura) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_factura", idFactura);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerPorFactura, paramSource, mapeoProductoFacturar );
    }

    @Override
    public void eliminarPorFactuta(Long idFactura) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_factura", idFactura);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }

    private void guardar(ProductoFacturar productoFacturar, Long idFactura) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_factura", idFactura);
        paramSource.addValue("id_producto", productoFacturar.getProducto().getId());
        paramSource.addValue("cantidad", productoFacturar.getCantidad());
        this.customNamedParameterJdbcTemplate.crear(paramSource, sqlCrear);
    }
}
