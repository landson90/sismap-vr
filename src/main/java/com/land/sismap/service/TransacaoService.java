package com.land.sismap.service;


import com.land.sismap.dto.TransacaoRequestDto;
import com.land.sismap.entity.CartaoEntity;
import com.land.sismap.excecao.CartaoInexistenteException;
import com.land.sismap.excecao.SaldoInsuficienteException;
import com.land.sismap.excecao.SenhaInvalidaException;
import com.land.sismap.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransacaoService {

    private CartaoRepository cartaoRepository;
    private static String RESPOSTA_TRANSACAO_OK = "OK";
    @Autowired
    public TransacaoService(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    public String compra(TransacaoRequestDto requestDto) {

        CartaoEntity cartao = cartaoRepository.findByNumeroCartao(requestDto.getNumeroCartao())
                .orElseThrow(CartaoInexistenteException::new);

        validarTransacao(requestDto, cartao);

        Double novoSaldo = cartao.getSaldo() - requestDto.getValor();
        cartao.setSaldo(novoSaldo);
        cartaoRepository.save(cartao);

        return  RESPOSTA_TRANSACAO_OK;
    }

    private void validarTransacao(TransacaoRequestDto requestDto, CartaoEntity cartao) {
        if (!senhaValida(requestDto.getSenhaCartao(), cartao)) {
            throw new SenhaInvalidaException();
        }
        if (!saldoSuficiente(requestDto.getValor(), cartao)) {
            throw new SaldoInsuficienteException();
        }
    }

    private boolean saldoSuficiente(Double valor, CartaoEntity cartao) {
        return cartao.getSaldo() >= valor;
    }

    private boolean senhaValida(String senha, CartaoEntity cartao) {
        return cartao.getSenha().equals(senha);
    }
}
