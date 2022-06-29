package com.ceiba.factura.comando.fabrica;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ceiba.factura.comando.ComandoProductoFacturar;
import com.ceiba.factura.modelo.entidad.ProductoFacturar;
import com.ceiba.producto.puerto.RepositorioProducto;

@Component
public class FabricaObtenerProducto {
    private final RepositorioProducto repositorioProducto;

    public FabricaObtenerProducto(RepositorioProducto repositorioProducto) {
        this.repositorioProducto = repositorioProducto;
    }
    
    public List<ProductoFacturar> crear(List<ComandoProductoFacturar> comandoProductosFacturar) {
        return comandoProductosFacturar.stream().map(comandoProducto ->
                            ProductoFacturar.crear(
                                comandoProducto.getCantidad(),
                                repositorioProducto.obtener(comandoProducto.getIdProducto()))
                            )
                            .collect(Collectors.toList());
    }
}
