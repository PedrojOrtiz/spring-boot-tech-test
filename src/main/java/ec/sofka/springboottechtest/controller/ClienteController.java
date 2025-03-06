package ec.sofka.springboottechtest.controller;

import ec.sofka.springboottechtest.domain.Cliente;
import ec.sofka.springboottechtest.dto.ClienteCuentaDTO;
import ec.sofka.springboottechtest.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.save(cliente));
    }

    @PostMapping("/cuenta")
    public ResponseEntity<Cliente> createCliente(@RequestBody ClienteCuentaDTO clienteCuentaDTO) {
        return ResponseEntity.ok(clienteService.saveClienteCuenta(clienteCuentaDTO));
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> getClienteByClienteId(@PathVariable String clienteId) {
        return ResponseEntity.ok(clienteService.readByClienteId(clienteId));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteService.readAll());
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable String clienteId, @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.updateByClienteId(clienteId, cliente));
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String clienteId) {
        clienteService.deleteByClienteId(clienteId);
        return ResponseEntity.noContent().build();
    }


}
