package com.toyoda.sura.service;

import com.toyoda.sura.dto.PedidoRequestDTO;
import com.toyoda.sura.dto.PedidoResponseDTO;
import com.toyoda.sura.entity.Cliente;
import com.toyoda.sura.entity.Pedido;
import com.toyoda.sura.entity.PedidoItem;
import com.toyoda.sura.entity.Produto;
import com.toyoda.sura.exception.ResourceNotFoundException;
import com.toyoda.sura.repository.ClienteRepository;
import com.toyoda.sura.repository.PedidoItemRepository;
import com.toyoda.sura.repository.PedidoRepository;
import com.toyoda.sura.repository.ProdutoRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final ClienteRepository clienteRepository;

    private final PedidoItemRepository pedidoItemRepository;

    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository,
                         PedidoItemRepository pedidoItemRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.pedidoItemRepository = pedidoItemRepository;
        this.produtoRepository = produtoRepository;
    }

    public PedidoResponseDTO getPedidoId(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return new PedidoResponseDTO(pedido.getId(), pedido.getStatus());
    }

    @Transactional
    public PedidoResponseDTO createPedido(List<PedidoRequestDTO> pedidoList) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.getPrincipal() != null) {
            Cliente cliente = clienteRepository.findByEmail(authentication.getPrincipal().toString());
            Pedido pedido = new Pedido();
            pedido.setSessao(RandomStringUtils.randomAlphabetic(5));
            pedido.setStatus("Andamento");
            pedido.setCliente(cliente);
            Pedido pedidoSalvo = pedidoRepository.save(pedido);

            pedidoList.forEach(pedidoRequestDTO -> {
                PedidoItem pedidoItem = new PedidoItem();
                Produto produto = produtoRepository.findById(pedidoRequestDTO.getIdproduto()).orElseThrow(ResourceNotFoundException::new);

                pedidoItem.setNomeProduto(produto.getProduto());
                pedidoItem.setPedido(pedidoSalvo);
                pedidoItem.setQuantidade(pedidoRequestDTO.getQuantidade());
                pedidoItem.setValor(produto.getPreco());
                pedidoItem.setProduto(produto);

                BigDecimal preco = new BigDecimal(produto.getPreco());
                BigDecimal subtotal = preco.multiply(new BigDecimal(pedidoRequestDTO.getQuantidade()));
                pedidoItem.setSubtotal(subtotal.doubleValue());

                pedidoItemRepository.save(pedidoItem);
            });

            return new PedidoResponseDTO(pedidoSalvo.getId(), pedido.getStatus());
        }

        return new PedidoResponseDTO(0L, "Error");
    }
}
