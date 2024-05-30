package local.test.enr.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import local.test.enr.dtos.AccountDto;
import local.test.enr.dtos.AmountDto;
import local.test.enr.dtos.TransactionResponseDto;
import local.test.enr.models.CuentaBancariaModel;
import local.test.enr.models.TipoTransaccion;
import local.test.enr.models.TransaccionModel;
import local.test.enr.repositories.AccountRepository;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public AmountDto balance(Long id){
        CuentaBancariaModel account = accountRepository.findById(id).get();
        AmountDto amountDto = new AmountDto();
        amountDto.setAmount(account.getSaldo());
        return amountDto;
    }

    public AccountDto create(AccountDto accountDto){
        CuentaBancariaModel account = new CuentaBancariaModel();
        account.setFechaCreacion(LocalDateTime.now());
        account.setSaldo(BigDecimal.ZERO);
        account.setTitular(accountDto.getTitular());
        accountRepository.save(account);
        return accountDto;
    }

    public TransactionResponseDto deposit(Long id, BigDecimal amount){
        TransactionResponseDto response = new TransactionResponseDto();
        try{
            CuentaBancariaModel account = (accountRepository.findById(id)).get();
            account.setSaldo(account.getSaldo().add(amount));
            TransaccionModel transaccion = new TransaccionModel();
            transaccion.setFecha(LocalDateTime.now());
            transaccion.setMonto(amount);
            transaccion.setTipoTransaccion(TipoTransaccion.DEPOSITO);
            account.addTransaccion(transaccion);
            accountRepository.save(account);
            response.setSuccess(true);
        }catch (Exception err){
            System.out.println(err.getMessage());
            response.setSuccess(false);
            response.setError("Ocurrio un error en la transacción");
        }finally{
            return response;
        }
    }
    public TransactionResponseDto withdraw(Long id, BigDecimal amount){
        TransactionResponseDto response = new TransactionResponseDto();
        try{
        CuentaBancariaModel account = (accountRepository.findById(id)).get();
        if (account.getSaldo().compareTo(amount) >= 0) {
            account.setSaldo(account.getSaldo().subtract(amount));
            TransaccionModel transaccion = new TransaccionModel();
            transaccion.setFecha(LocalDateTime.now());
            transaccion.setMonto(amount);
            transaccion.setTipoTransaccion(TipoTransaccion.RETIRO);
            account.addTransaccion(transaccion);
            accountRepository.save(account);
            response.setSuccess(true);
        }else{
            response.setSuccess(false);
            response.setError("Fondos insuficientes");
        }
        }catch (Exception err){
            System.out.println(err.getMessage());
            response.setSuccess(false);
            response.setError("Ocurrio un error en la transacción");
        }finally{
            return response;
        }
    }

    

}
