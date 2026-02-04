package br.com.fiap.hackathon.tea.application.validacao.repository;

import br.com.fiap.hackathon.tea.domain.encaminhamento.Encaminhamento;

public interface EncaminhamentoRepository {
    void salvar(Encaminhamento encaminhamento);
    boolean existePorProtocolo(String protocolo);
}