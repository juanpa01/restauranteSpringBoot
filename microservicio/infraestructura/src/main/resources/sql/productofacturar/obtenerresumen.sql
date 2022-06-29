select pf.cantidad, p.nombre as nombre_producto, p.valor as valor_unitario
from producto_facturar as pf
inner join producto  as p on pf.id_producto = p.id
where pf.id_factura = :id_factura
