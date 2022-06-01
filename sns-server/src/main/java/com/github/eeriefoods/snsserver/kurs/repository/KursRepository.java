package com.github.eeriefoods.snsserver.kurs.repository;

import com.github.eeriefoods.snsserver.kurs.domain.Kurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KursRepository extends JpaRepository<Kurs, String> {
}
