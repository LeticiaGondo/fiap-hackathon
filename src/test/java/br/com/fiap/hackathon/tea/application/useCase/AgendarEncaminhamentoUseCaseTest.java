package br.com.fiap.hackathon.tea.application.useCase;

import br.com.fiap.hackathon.tea.application.exception.EncaminhamentoNaoEncontradoException;
import br.com.fiap.hackathon.tea.application.model.AgendamentoResponse;
import br.com.fiap.hackathon.tea.application.model.UnidadeAgendamento;
import br.com.fiap.hackathon.tea.application.port.AgendamentoGateway;
import br.com.fiap.hackathon.tea.application.port.EncaminhamentoRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AgendarEncaminhamentoUseCaseTest {

    @Test
    void deveRetornarErroQuandoProtocoloNaoExiste() {
        EncaminhamentoRepository repository = mock(EncaminhamentoRepository.class);
        AgendamentoGateway gateway = mock(AgendamentoGateway.class);
        AgendarEncaminhamentoUseCase useCase = new AgendarEncaminhamentoUseCase(repository, gateway);

        when(repository.existePorProtocolo("PROTO-1")).thenReturn(false);

        assertThatThrownBy(() -> useCase.execute("PROTO-1"))
                .isInstanceOf(EncaminhamentoNaoEncontradoException.class)
                .hasMessageContaining("PROTO-1");
    }

    @Test
    void deveAgendarQuandoDadosValidos() {
        EncaminhamentoRepository repository = mock(EncaminhamentoRepository.class);
        AgendamentoGateway gateway = mock(AgendamentoGateway.class);
        AgendarEncaminhamentoUseCase useCase = new AgendarEncaminhamentoUseCase(repository, gateway);

        AgendamentoResponse response = new AgendamentoResponse(
                "PROTO-3",
                "AGENDADO",
                LocalDateTime.parse("2026-02-10T08:00:00"),
                new UnidadeAgendamento("UBS-001", "UBS Central", "Rua A, 10"),
                "NEUROLOGIA"
        );

        when(repository.existePorProtocolo("PROTO-3")).thenReturn(true);
        when(repository.buscaEspecialidaePorProtocolo("PROTO-3")).thenReturn("NEUROLOGIA");
        when(gateway.agendar("PROTO-3", "NEUROLOGIA")).thenReturn(response);

        AgendamentoResponse resultado = useCase.execute("PROTO-3");

        assertThat(resultado).isEqualTo(response);
        verify(gateway).agendar("PROTO-3", "NEUROLOGIA");
    }
}
