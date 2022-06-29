package com.ceiba.factura.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionDiaFacturar;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.factura.modelo.dto.CrearFacturaDTO;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
public class Factura implements Serializable {

    public static final double DESCUENTO_FACTURA_SUPERA_VALOR = 0.05;
    public static final double DESCUENTO_DIA_JUEVES = 0.05;
    public static final double VALOR_DESCUENTO = 100000;
    public static final Double VALOR_IMPUESTO_IVA = 0.19;
    public static final Double VALOR_IMPUESTO_CONSUMO = 0.08;
    public static final Double VALOR_PROPINA = 0.10;
    public static final List<String> DIAS_NO_LABORALES = new ArrayList<>(
        List.of("MONDAY", "TUESDAY", "WEDNESDAY")
    );

    private Long id;
    private List<ProductoFacturar> productosFacturar;
    private BigDecimal valorImpIva;
    private BigDecimal valorImpConsumo;
    private BigDecimal valorTotal;
    private BigDecimal valorPropina;
    private Boolean aplicaPropina;
    private LocalDateTime fechaCreación;
    private LocalDateTime fechaActualizacion;

    public Factura(Long id, List<ProductoFacturar> productosFacturar, BigDecimal valorImpIva,
    BigDecimal valorImpConsumo, BigDecimal valorPropina,BigDecimal valorTotal, Boolean aplicaPropina) {
        this.id = id;
        this.productosFacturar = new ArrayList<>(productosFacturar);
        this.valorImpIva = valorImpIva;
        this.valorImpConsumo = valorImpConsumo;
        this.valorTotal = valorTotal;
        this.valorPropina = valorPropina;
        this.aplicaPropina = aplicaPropina;
    }

    public Factura(List<ProductoFacturar> productosFacturar, Boolean aplicaPropina, LocalDateTime fechaCreacion) {
        this.fechaCreación = fechaCreacion;
        this.validarDiaFacturar();
        this.productosFacturar = new ArrayList<>(productosFacturar);
        this.aplicaPropina = aplicaPropina;
        this.valorTotal = calcularValorTotal(productosFacturar);
    }

    public void actualizar (List<ProductoFacturar> productosFacturar) {
        ValidadorArgumento.validarNoVacio(productosFacturar, "Deben de existir al menos un producto para facturar");
        this.productosFacturar = new ArrayList<>(productosFacturar);
        this.valorTotal = calcularValorTotal(productosFacturar);
        this.fechaActualizacion = LocalDateTime.now();
    }

    public BigDecimal calcularValorTotal(List<ProductoFacturar> productosFacturar) {
        BigDecimal subtotal = this.calcularValorSubTotal(productosFacturar);
        
        subtotal = subtotal.subtract(this.calcularDescuento(subtotal));

        this.valorImpIva = this.calcularValorTotalImpIva(subtotal);
        this.valorImpConsumo = this.calcularValorTotalImpConsumo(subtotal);

        if(this.aplicaPropina) {
            this.valorPropina = subtotal.multiply(BigDecimal.valueOf(VALOR_PROPINA));
        } else {
            this.valorPropina = new BigDecimal(0);
        }

        return  subtotal.add(this.valorImpIva).add(this.valorImpConsumo).add(this.valorPropina);
    }

    public BigDecimal calcularValorSubTotal(List<ProductoFacturar> productosFacturar) {
        return productosFacturar.stream()
            .map(ProductoFacturar::calcularValorSinImpuestos)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    } 

    public BigDecimal calcularDescuento(BigDecimal subtotal) {
        Calendar fechaActual = Calendar.getInstance();
        BigDecimal descuentoDia  = new BigDecimal(0);
        BigDecimal descuentoValor  = new BigDecimal(0);

        if(fechaActual.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            descuentoDia = subtotal.multiply(BigDecimal.valueOf(DESCUENTO_DIA_JUEVES));
        }
        if(subtotal.doubleValue() > VALOR_DESCUENTO) {
            descuentoValor = subtotal.multiply(BigDecimal.valueOf(DESCUENTO_FACTURA_SUPERA_VALOR));
        }

        return descuentoDia.add(descuentoValor);
    }

    public BigDecimal calcularValorTotalImpIva(BigDecimal subtotal) {
        return subtotal.multiply(BigDecimal.valueOf(VALOR_IMPUESTO_IVA));
    }

    public BigDecimal calcularValorTotalImpConsumo(BigDecimal subtotal) {
        return subtotal.multiply(BigDecimal.valueOf(VALOR_IMPUESTO_CONSUMO));
    }

    private void validarDiaFacturar() {
        Optional<String> esDiaNoLaboral = DIAS_NO_LABORALES.stream()
            .filter(x -> x.equals(this.fechaCreación.getDayOfWeek().toString()))
            .findFirst();
            
        if(esDiaNoLaboral.isPresent()) {
                throw new ExcepcionDiaFacturar("Día no valido para generar facturas");
        } 
    }

    public static Factura crear(CrearFacturaDTO crearFacturaDTO) {
        ValidadorArgumento.validarObligatorio(crearFacturaDTO.getAplicaPropina(), "El campo aplica propina es obligatorio");
        ValidadorArgumento.validarObligatorio(crearFacturaDTO.getFechaCreacion(), "El campo fecha creación propina es obligatorio");
        ValidadorArgumento.validarNoVacio(crearFacturaDTO.getProductosFacturar(), "Deben de existir al menos un producto para facturar");
        return new Factura(crearFacturaDTO.getProductosFacturar(), crearFacturaDTO.getAplicaPropina(), crearFacturaDTO.getFechaCreacion());
    }

    public static Factura reconstruir(Long id, List<ProductoFacturar> productosFacturar, BigDecimal valorImpIva,
        BigDecimal valorImpConsumo, BigDecimal valorPropina,BigDecimal valorTotal, Boolean aplicaPropina) {
        ValidadorArgumento.validarObligatorio(id, "El id es requerido");
        ValidadorArgumento.validarNoVacio(productosFacturar, "Deben de existir al menos un producto para facturar");
        if (valorTotal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ExcepcionValorInvalido("El total no puede ser menor a cero");
        }
        return new Factura(id, productosFacturar, valorImpIva, valorImpConsumo, valorTotal, valorPropina, aplicaPropina);
    }
}

