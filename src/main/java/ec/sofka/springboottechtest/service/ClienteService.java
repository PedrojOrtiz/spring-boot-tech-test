package ec.sofka.springboottechtest.service;

import ec.sofka.springboottechtest.domain.Cliente;
import ec.sofka.springboottechtest.dto.ClienteCuentaDTO;
import ec.sofka.springboottechtest.exception.DataNotFound;
import ec.sofka.springboottechtest.messaging.ClienteProducer;
import ec.sofka.springboottechtest.repository.ClienteRepository;
import ec.sofka.springboottechtest.util.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService extends AbstractService<Cliente, ClienteRepository, Long> {

    private Logger log = LoggerFactory.getLogger(ClienteService.class);

    private final ClienteProducer clienteProducer;

    public ClienteService(ClienteRepository repository, ClienteProducer clienteProducer) {
        super(repository, "Cliente");
        this.clienteProducer = clienteProducer;
    }

    @Override
    public Cliente preSave(Cliente cliente) {
        log.info("Preparando para guardar cliente: {}", cliente);
        return super.preSave(cliente);
    }

    @Override
    public Cliente preUpdate(Cliente cliente) {
        log.info("Preparando para actualizar cliente: {}", cliente);
        return super.preSave(cliente);
    }

    @Override
    public Cliente postSave(Cliente cliente) {
        return super.postSave(cliente);
    }

    @Transactional
    public Cliente saveClienteCuenta(ClienteCuentaDTO clienteCuentaDTO) {
        Cliente newCliente = save(Cliente.builder()
                .nombre(clienteCuentaDTO.getCliente().getNombre())
                .genero(clienteCuentaDTO.getCliente().getGenero())
                .edad(clienteCuentaDTO.getCliente().getEdad())
                .identificacion(clienteCuentaDTO.getCliente().getIdentificacion())
                .direccion(clienteCuentaDTO.getCliente().getDireccion())
                .telefono(clienteCuentaDTO.getCliente().getTelefono())
                .clienteId(clienteCuentaDTO.getCliente().getClienteId())
                .contrasena(clienteCuentaDTO.getCliente().getContrasena())
                .estado(clienteCuentaDTO.getEstado())
                .build()
        );
        clienteProducer.enviarEventoClienteCreado(clienteCuentaDTO);
        return newCliente;
    }

    public Cliente readByClienteId(String clienteId) {
        return repository.findByClienteId(clienteId)
                .orElseThrow(() -> new DataNotFound("Cliente con \"clienteId\": " + clienteId + ", no encontrado"));
    }

    public void deleteByClienteId(String clienteId) {
        repository.deleteByClienteId(clienteId);
    }

    public Cliente updateByClienteId(String clienteId, Cliente cliente) {
        Cliente clienteToUpdate = readByClienteId(clienteId);
        clienteToUpdate.setNombre(cliente.getNombre());
        clienteToUpdate.setGenero(cliente.getGenero());
        clienteToUpdate.setEdad(cliente.getEdad());
        clienteToUpdate.setIdentificacion(cliente.getIdentificacion());
        clienteToUpdate.setDireccion(cliente.getDireccion());
        clienteToUpdate.setTelefono(cliente.getTelefono());
        return update(
                clienteToUpdate
                        .setContrasena(cliente.getContrasena())
                        .setEstado(cliente.getEstado())
        );
    }

}
