package com.land.sismap.controller;


import com.land.sismap.dto.CartaoRequestDto;
import com.land.sismap.dto.CartaoResponseDto;
import com.land.sismap.dto.DadosCartaoResponseDto;
import com.land.sismap.excecao.CartaoJaExisteException;
import com.land.sismap.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {


    private CartaoService cartaoService;

    @Autowired
    public CartaoController(CartaoService cartaoService ) {
        this.cartaoService = cartaoService;
    }

    @PostMapping
    public ResponseEntity<CartaoResponseDto> criarCartao(@RequestBody CartaoRequestDto requestDto) {


        try {
            CartaoResponseDto cartaoResponse = cartaoService.salvar(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(cartaoResponse);
        } catch (CartaoJaExisteException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    CartaoResponseDto.builder()
                            .senha(requestDto.getSenha())
                            .numeroCartao(requestDto.getNumeroCartao())
                            .build()
            );
        }
    }

    @GetMapping("{numeroCartao}")
    public ResponseEntity<DadosCartaoResponseDto> getCartao(@PathVariable String numeroCartao) {
        try {
            DadosCartaoResponseDto dadosCartao = cartaoService.getCartao(numeroCartao);
            return ResponseEntity.ok(dadosCartao);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
