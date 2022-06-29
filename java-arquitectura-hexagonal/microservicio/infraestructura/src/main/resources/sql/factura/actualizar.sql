 update factura
 set valor_total = :valor_total, valor_imp_iva = :valor_imp_iva,
    valor_imp_consumo = :valor_imp_consumo, aplica_propina = :aplica_propina,
    valor_propina = :valor_propina, fecha_actualizacion = :fecha_actualizacion
 where id = :id