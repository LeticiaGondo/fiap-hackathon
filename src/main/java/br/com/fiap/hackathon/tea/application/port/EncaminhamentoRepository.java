package br.com.fiap.hackathon.tea.application.port;

import br.com.fiap.hackathon.tea.domain.Encaminhamento;

public interface EncaminhamentoRepository {
    void salvar(Encaminhamento encaminhamento);
    boolean existePorProtocolo(String protocolo);
}