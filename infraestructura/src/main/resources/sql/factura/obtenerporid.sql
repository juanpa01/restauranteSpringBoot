select  id, valor_total, valor_imp_iva, valor_imp_consumo, aplica_propina, valor_propina
from factura
where id = :id