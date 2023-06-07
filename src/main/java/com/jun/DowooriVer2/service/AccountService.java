package com.jun.DowooriVer2.service;

import com.jun.DowooriVer2.domain.Account;
import com.jun.DowooriVer2.springDataJpa.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AccountService {

    private AccountRepository accountRepository;

    public void saveAccount(Account account) {
        accountRepository.save(account);
        log.info("Account saved");
    }
}
