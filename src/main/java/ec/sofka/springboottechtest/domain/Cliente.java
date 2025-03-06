package ec.sofka.springboottechtest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "cliente")
public class Cliente extends Persona {

    @NotNull(message = "clienteId no puede ser nulo")
    @Column(unique = true, nullable = false)
    private String clienteId;

    private String contrasena;

    private Boolean estado;

}
