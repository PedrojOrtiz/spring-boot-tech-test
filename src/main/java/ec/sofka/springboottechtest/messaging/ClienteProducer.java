package ec.sofka.springboottechtest.messaging;

import ec.sofka.springboottechtest.dto.ClienteCuentaDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteProducer {

    private final Logger log = LoggerFactory.getLogger(ClienteProducer.class);

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE = "clienteExchange";
    private static final String ROUTING_KEY = "cliente.created";

    public void enviarEventoClienteCreado(ClienteCuentaDTO clienteCuenta) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, clienteCuenta);
        log.info("\uD83D\uDCE4 Evento enviado: Cliente creado con ID {}", clienteCuenta);
    }

}
