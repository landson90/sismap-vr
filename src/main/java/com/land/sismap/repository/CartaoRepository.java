package com.land.sismap.repository;


import com.land.sismap.entity.CartaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CartaoRepository extends JpaRepository<CartaoEntity, Long> {


    @Query("select c from CartaoEntity c where c.numeroCartao = :numeroCartao")
    List<CartaoEntity> buscarCartaoPorNumero(String numeroCartao);
    Optional<CartaoEntity> findByNumeroCartao(String numeroCartao);


    @Query("select c from CartaoEntity c where c.numeroCartao = :numeroCartao")
    CartaoEntity filtroPorNumeroCartao(String numeroCartao);

}
