package local.test.enr.models;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="transacciones")
public class TransaccionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_bancaria_id")
    private CuentaBancariaModel cuentaBancaria;
    private BigDecimal monto;
    private LocalDateTime fecha;
    
    @Enumerated(EnumType.STRING)
    private TipoTransaccion tipo;
    
    public BigDecimal getMonto() {
        return monto;
    }
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public TipoTransaccion getTipoTransaccion() {
        return tipo;
    }
    public void setTipoTransaccion(TipoTransaccion tipo) {
        this.tipo = tipo;
    }
    public CuentaBancariaModel getCuentaBancaria() {
        return cuentaBancaria;
    }
    public void setCuentaBancaria(CuentaBancariaModel cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }
    
}
