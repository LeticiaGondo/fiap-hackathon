package br.com.fiap.hackathon.validator.application.repository;

import br.com.fiap.hackathon.validator.domain.Encaminhamento;

public interface EncaminhamentoRepository {
    void salvar(Encaminhamento encaminhamento);
}