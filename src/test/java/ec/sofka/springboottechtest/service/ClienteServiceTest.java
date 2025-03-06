package ec.sofka.springboottechtest.service;

import ec.sofka.springboottechtest.domain.Cliente;
import ec.sofka.springboottechtest.dto.ClienteCuentaDTO;
import ec.sofka.springboottechtest.messaging.ClienteProducer;
import ec.sofka.springboottechtest.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteProducer clienteProducer;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = Cliente.builder()
                .nombre("Mock Client")
                .genero("Masculino")
                .edad(30)
                .identificacion("123456789")
                .direccion("Calle 123")
                .telefono("0999999999")
                .clienteId("CLI123")
                .contrasena("pass123")
                .estado(true)
                .build();
    }

    @Test
    void testCreateCliente() {
        doNothing().when(clienteProducer).enviarEventoClienteCreado(new ClienteCuentaDTO());
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        Cliente clienteCreado = clienteService.save(cliente);
        assertNotNull(clienteCreado);
        assertEquals(cliente.getNombre(), clienteCreado.getNombre());
        verify(clienteRepository, times(1)).save(cliente);
    }

}