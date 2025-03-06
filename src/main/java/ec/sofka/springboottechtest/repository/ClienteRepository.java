package ec.sofka.springboottechtest.repository;

import ec.sofka.springboottechtest.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByClienteId(String clienteId);

    Void deleteByClienteId(String clienteId);

}
