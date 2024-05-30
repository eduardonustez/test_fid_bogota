package local.test.enr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import local.test.enr.dtos.AccountDto;
import local.test.enr.dtos.AmountDto;
import local.test.enr.dtos.TransactionResponseDto;
import local.test.enr.services.AccountService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(this.accountService.create(accountDto));
    } 
    @PostMapping(path = "/{id}/deposit")
    public ResponseEntity<?> deposit(@PathVariable("id") Long id, 
        @Valid @RequestBody AmountDto amountDto) {

        TransactionResponseDto resul = this.accountService.deposit(id,amountDto.getAmount());
        return ResponseEntity.ok(resul);
    } 

    @PostMapping(path = "/{id}/withdraw")
    public ResponseEntity<?> withdraw(@PathVariable("id") Long id, 
        @Valid @RequestBody AmountDto amountDto) {
            return ResponseEntity.ok(this.accountService.withdraw(id,amountDto.getAmount()));
    } 

    @GetMapping(path = "/{id}/balance")
    public AmountDto balance(@PathVariable("id") Long id) {
        return this.accountService.balance(id);
    }
   
}
