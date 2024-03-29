package com.jun.DowooriVer2.springDataJpa;

import com.jun.DowooriVer2.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
