package br.com.fiap.hackathon.validator.application.useCase;

import br.com.fiap.hackathon.validator.application.model.ResultadoValidacao;
import br.com.fiap.hackathon.validator.application.repository.EncaminhamentoRepository;
import br.com.fiap.hackathon.validator.domain.Encaminhamento;
import br.com.fiap.hackathon.validator.domain.exception.ValidacaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ValidarEncaminhamentoUseCaseTest {


    private EncaminhamentoRepository repository;
    private ValidarEncaminhamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(EncaminhamentoRepository.class);
        useCase = new ValidarEncaminhamentoUseCase(repository);
    }

    @Test
    void deveLancarValidacaoException_quandoProtocoloJaExistir() {
        Encaminhamento encaminhamento = mock(Encaminhamento.class);
        when(encaminhamento.getProtocolo()).thenReturn("ABC123");
        when(repository.existePorProtocolo("ABC123")).thenReturn(true);

        ValidacaoException ex = assertThrows(
                ValidacaoException.class,
                () -> useCase.execute(encaminhamento)
        );

        assertEquals(
                String.format(ValidarEncaminhamentoUseCase.ERRO_PROTOCOLO_DUPLICADO, "ABC123"),
                ex.getMessage()
        );

        verify(repository).existePorProtocolo("ABC123");
        verify(repository, never()).salvar(any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deveSalvar_quandoEncaminhamentoValido() {
        Encaminhamento encaminhamento = mock(Encaminhamento.class);
        when(encaminhamento.getProtocolo()).thenReturn("XYZ999");
        when(repository.existePorProtocolo("XYZ999")).thenReturn(false);

        ResultadoValidacao resultado = useCase.execute(encaminhamento);

        assertNotNull(resultado);

        verify(repository).existePorProtocolo("XYZ999");
        verify(repository).salvar(encaminhamento);
        verifyNoMoreInteractions(repository);
    }
}

}