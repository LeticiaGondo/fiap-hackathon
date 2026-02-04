package br.com.fiap.hackathon.validator.infrastructure.persistence.repository;

import br.com.fiap.hackathon.validator.infrastructure.persistence.entity.EncaminhamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncaminhamentoJpaRepository extends JpaRepository<EncaminhamentoEntity, String> {
}