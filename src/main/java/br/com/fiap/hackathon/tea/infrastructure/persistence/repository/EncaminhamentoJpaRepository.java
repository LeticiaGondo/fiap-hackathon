package br.com.fiap.hackathon.tea.infrastructure.persistence.repository;

import br.com.fiap.hackathon.tea.infrastructure.persistence.entity.EncaminhamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EncaminhamentoJpaRepository extends JpaRepository<EncaminhamentoEntity, String> {

    boolean existsByProtocolo(String protocolo);
    Optional<EncaminhamentoEntity> findByProtocolo(String protocolo);
}