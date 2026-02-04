package br.com.fiap.hackathon.validator.api;

import br.com.fiap.hackathon.validator.api.dto.EncaminhamentoRequest;
import br.com.fiap.hackathon.validator.domain.Cpf;
import br.com.fiap.hackathon.validator.domain.Encaminhamento;
import br.com.fiap.hackathon.validator.domain.exception.CpfInvalidoException;
import br.com.fiap.hackathon.validator.domain.exception.CpfObrigatorioException;
import br.com.fiap.hackathon.validator.domain.exception.ValidacaoException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface EncaminhamentoMapper {


    @Mapping(target = "paciente.cpf", source = "paciente.cpf", qualifiedByName = "cpfPaciente")
    Encaminhamento toDomain(EncaminhamentoRequest request);

    @Named("cpfPaciente")
    default Cpf cpfPaciente(String numero) {
        try {
            return new Cpf(numero);
        } catch (CpfObrigatorioException e) {
            throw new ValidacaoException("CPF do paciente é obrigatório");
        } catch (CpfInvalidoException e) {
            throw new ValidacaoException("Cpf do paciente é inválido");
        }
    }

}
