package com.toyoda.sura.service;

import com.toyoda.sura.dto.PedidoRequestDTO;
import com.toyoda.sura.dto.PedidoResponseDTO;
import com.toyoda.sura.entity.Cliente;
import com.toyoda.sura.entity.Pedido;
import com.toyoda.sura.entity.Produto;
import com.toyoda.sura.exception.ResourceNotFoundException;
import com.toyoda.sura.repository.ClienteRepository;
import com.toyoda.sura.repository.PedidoItemRepository;
import com.toyoda.sura.repository.PedidoRepository;
import com.toyoda.sura.repository.ProdutoRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureWebMvc
public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private PedidoItemRepository pedidoItemRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    public void createPedidoTest() {
        String emailUser = "user@test.com";
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setEmail(emailUser);

        Pedido pedido = new Pedido();
        pedido.setSessao(RandomStringUtils.randomAlphabetic(5));
        pedido.setCliente(cliente);

        Long produtoId = 1L;
        PedidoRequestDTO item1 = new PedidoRequestDTO(produtoId, 1);

        Produto produto = new Produto();
        produto.setId(produtoId);
        produto.setProduto("Iphone");
        produto.setPreco(5000.0);

        when(clienteRepository.findByEmail(emailUser)).thenReturn(cliente);
        when(pedidoRepository.save(pedido)).thenReturn(pedido);
        when(produtoRepository.findById(produtoId)).thenReturn(Optional.of(produto));

        PedidoResponseDTO pedidoResponseDTO = pedidoService.createPedido(emailUser, Arrays.asList(item1));
        Assertions.assertEquals(pedidoResponseDTO.getStatus(), "Andamento");

    }

    @Test
    public void createPedidoClienteNotFoundTest() {
        String emailUser = "user@test.com";

        when(clienteRepository.findByEmail(emailUser)).thenReturn(null);
        PedidoResponseDTO pedidoResponseDTO = pedidoService.createPedido(emailUser, Arrays.asList());

        Assertions.assertEquals(pedidoResponseDTO.getId(), 0L);
        Assertions.assertEquals(pedidoResponseDTO.getStatus(), "Error");
    }

    @Test
    public void getPedidoIdTest() {
        Long pedidoId = 1L;

        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);

        when(pedidoRepository.findById(pedidoId)).thenReturn(Optional.of(pedido));

        PedidoResponseDTO pedidoRecuperado = pedidoService.getPedidoId(pedidoId);

        Assertions.assertEquals(pedidoRecuperado.getStatus(), "Andamento");
        Assertions.assertEquals(pedidoRecuperado.getId(), pedidoId);
    }

    @Test
    public void getPedidoNotFoundByIdTest() {
        Long pedidoId = 1L;

        when(pedidoRepository.findById(pedidoId)).thenThrow(ResourceNotFoundException.class);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> pedidoService.getPedidoId(pedidoId));
    }
}