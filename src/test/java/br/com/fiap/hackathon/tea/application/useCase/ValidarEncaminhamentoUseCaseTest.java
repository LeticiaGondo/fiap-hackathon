package br.com.fiap.hackathon.tea.application.useCase;

import br.com.fiap.hackathon.tea.application.port.CfmGateway;
import br.com.fiap.hackathon.tea.application.port.EncaminhamentoRepository;
import br.com.fiap.hackathon.tea.domain.*;
import br.com.fiap.hackathon.tea.domain.exception.ValidacaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ValidarEncaminhamentoUseCaseTest {


    private EncaminhamentoRepository repository;
    private CfmGateway cfmGateway;
    private ValidarEncaminhamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(EncaminhamentoRepository.class);
        cfmGateway = mock(CfmGateway.class);
        useCase = new ValidarEncaminhamentoUseCase(cfmGateway, repository);
    }

    @Test
    void deveRecusarQuandoProtocoloJaExiste() {
        Encaminhamento encaminhamento = buildEncaminhamento("ABC-123", "SP", "123456");
        when(repository.existePorProtocolo("ABC-123")).thenReturn(true);

        assertThrows(ValidacaoException.class, () -> useCase.execute(encaminhamento));

        verify(cfmGateway, never()).existeCrm(any(), any());
        verify(repository, never()).salvar(any());
    }

    @Test
    void deveRecusarQuandoCrmNaoExiste() {
        Encaminhamento encaminhamento = buildEncaminhamento("ABC-123", "SP", "123456");
        when(repository.existePorProtocolo("ABC-123")).thenReturn(false);
        when(cfmGateway.existeCrm("SP", "123456")).thenReturn(false);

        assertThrows(ValidacaoException.class, () -> useCase.execute(encaminhamento));

        verify(repository, never()).salvar(any());
    }

    @Test
    void deveSalvarQuandoCrmExiste() {
        Encaminhamento encaminhamento = buildEncaminhamento("ABC-123", "SP", "123456");
        when(repository.existePorProtocolo("ABC-123")).thenReturn(false);
        when(cfmGateway.existeCrm("SP", "123456")).thenReturn(true);
        useCase.execute(encaminhamento);

        verify(repository).salvar(encaminhamento);
    }

    private Encaminhamento buildEncaminhamento(String protocolo, String crmUf, String crmNumero) {
        Medico medico = new Medico("Dra. Maria", crmUf, crmNumero);
        Paciente paciente = new Paciente(new Cpf("529.982.247-25"), "Joao");
        Cid cid = new Cid("F84.0");
        return new Encaminhamento(protocolo, cid, medico, paciente, "NEURO", "Triagem");
    }
}
