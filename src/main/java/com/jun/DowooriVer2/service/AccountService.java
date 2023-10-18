package com.jun.DowooriVer2.service;

import com.jun.DowooriVer2.domain.Account;
import com.jun.DowooriVer2.springDataJpa.AccountRepository;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional
@Slf4j
@Validated
public class AccountService {

    private AccountRepository accountRepository;
    private Account account;

    public void saveAccount(@Valid Account account) {
        this.account = account;
        accountRepository.save(account);
        log.info("Account saved");
    }
}
