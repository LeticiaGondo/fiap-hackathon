package br.com.fiap.hackathon.tea.application.useCase;

import br.com.fiap.hackathon.tea.application.model.ResultadoValidacao;
import br.com.fiap.hackathon.tea.application.port.CfmGateway;
import br.com.fiap.hackathon.tea.application.port.EncaminhamentoRepository;
import br.com.fiap.hackathon.tea.domain.*;
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
    void deveRetornarPendenciaQuandoProtocoloJaExiste() {
        Encaminhamento encaminhamento = buildEncaminhamento("ABC-123", "SP", "123456");
        when(repository.existePorProtocolo("ABC-123")).thenReturn(true);

        ResultadoValidacao resultado = useCase.execute(encaminhamento);

        assertTrue(resultado.possuiPendencias());
        assertTrue(resultado.listarPendencias().contains("Encaminhamento com protocolo ABC-123 já existe"));
        verify(repository, never()).salvar(any());
    }

    @Test
    void deveRetornarPendenciaQuandoCrmNaoExiste() {
        Encaminhamento encaminhamento = buildEncaminhamento("ABC-123", "SP", "123456");
        when(repository.existePorProtocolo("ABC-123")).thenReturn(false);
        when(cfmGateway.existeCrm("SP", "123456")).thenReturn(false);

        ResultadoValidacao resultado = useCase.execute(encaminhamento);

        assertTrue(resultado.possuiPendencias());
        assertTrue(resultado.listarPendencias().contains(ValidarEncaminhamentoUseCase.ERRO_CRM_INVALIDO));
        verify(repository, never()).salvar(any());
    }

    @Test
    void deveAcumularPendenciasSemFalharRapido() {
        Encaminhamento encaminhamento = new Encaminhamento(
                "",
                Cid.of("F90.0"),
                new Medico("", "", ""),
                new Paciente(Cpf.of("123"), ""),
                "",
                ""
        );

        ResultadoValidacao resultado = useCase.execute(encaminhamento);

        assertTrue(resultado.possuiPendencias());
        assertTrue(resultado.listarPendencias().size() > 5);
        verify(repository, never()).salvar(any());
        verify(cfmGateway, never()).existeCrm(any(), any());
    }

    @Test
    void deveSalvarQuandoNaoHouverPendencias() {
        Encaminhamento encaminhamento = buildEncaminhamento("ABC-123", "SP", "123456");
        when(repository.existePorProtocolo("ABC-123")).thenReturn(false);
        when(cfmGateway.existeCrm("SP", "123456")).thenReturn(true);

        ResultadoValidacao resultado = useCase.execute(encaminhamento);

        assertFalse(resultado.possuiPendencias());
    }

    private Encaminhamento buildEncaminhamento(String protocolo, String crmUf, String crmNumero) {
        Medico medico = new Medico("Dra. Maria", crmUf, crmNumero);
        Paciente paciente = new Paciente(Cpf.of("529.982.247-25"), "Joao");
        Cid cid = Cid.of("F84.0");
        return new Encaminhamento(protocolo, cid, medico, paciente, "NEURO", "Triagem");
    }
}
