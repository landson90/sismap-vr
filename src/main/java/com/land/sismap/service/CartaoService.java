package com.land.sismap.service;


import com.land.sismap.config.ObjectNotFoundException;
import com.land.sismap.dto.CartaoRequestDto;
import com.land.sismap.dto.CartaoResponseDto;
import com.land.sismap.dto.DadosCartaoResponseDto;
import com.land.sismap.entity.CartaoEntity;
import com.land.sismap.excecao.CartaoJaExisteException;
import com.land.sismap.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartaoService {

    private final CartaoRepository cartaoRepository;

    @Autowired
    public CartaoService(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    public CartaoResponseDto salvar(CartaoRequestDto requestDto) {
        List<CartaoEntity> cartaoExiste = cartaoRepository.buscarCartaoPorNumero(requestDto.getNumeroCartao());
        if (!cartaoExiste.isEmpty()) {
            throw new CartaoJaExisteException("O cartão já existe");
        }

        CartaoEntity entity = cartaoRepository.save(getCartaoEntity(requestDto));
        return getCartaoResponseDto(entity);
    }

    public DadosCartaoResponseDto getCartao(String numeroCartao) {
        Optional<CartaoEntity> entity = cartaoRepository.findByNumeroCartao(numeroCartao);
        return entity.map(cartao -> new DadosCartaoResponseDto(cartao.getSaldo()))
                .orElseThrow(() -> new ObjectNotFoundException("Cartão não encontrado"));
    }

    private CartaoEntity getCartaoEntity(CartaoRequestDto requestDto) {
        return CartaoEntity.builder()
                .numeroCartao(requestDto.getNumeroCartao())
                .saldo(500.0)
                .senha(requestDto.getSenha())
                .build();
    }

    private CartaoResponseDto getCartaoResponseDto(CartaoEntity entity) {
        return CartaoResponseDto.builder()
                .numeroCartao(entity.getNumeroCartao())
                .senha(entity.getSenha())
                .build();
    }
}
