package com.uniesp.backflix.demo.repository;


import com.uniesp.backflix.demo.model.MinhaLista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinhaListaRepository extends JpaRepository<MinhaLista, Long> {
}
