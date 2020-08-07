package com.toyoda.sura.resource;

import com.toyoda.sura.dto.PedidoRequestDTO;
import com.toyoda.sura.dto.PedidoResponseDTO;
import com.toyoda.sura.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PedidoResource {

    private final PedidoService pedidoService;

    public PedidoResource(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/pedido")
    @ApiOperation(value = "Cria Pedido", response = String.class, notes = "Cria Pedido")
    public ResponseEntity<PedidoResponseDTO> createPedido(@RequestBody List<PedidoRequestDTO> pedidosRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getPrincipal().toString();
        final PedidoResponseDTO pedido = pedidoService.createPedido(email, pedidosRequestDTO);
        URI uri = uriComponentsBuilder.path("/pedido/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(pedido);
    }

    @GetMapping("/pedido/{id}")
    @ApiOperation(value = "Retorna dados do Pedido pelo Id", response = String.class, notes = "Retorna dados do Pedido pelo Id")
    public ResponseEntity<PedidoResponseDTO> getPedido(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.getPedidoId(id));
    }
}
