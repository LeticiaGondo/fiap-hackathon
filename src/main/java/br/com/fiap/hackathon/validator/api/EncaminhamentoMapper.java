package br.com.fiap.hackathon.validator.api;

import br.com.fiap.hackathon.validator.api.dto.EncaminhamentoRequest;
import br.com.fiap.hackathon.validator.domain.Cpf;
import br.com.fiap.hackathon.validator.domain.entity.Encaminhamento;
import br.com.fiap.hackathon.validator.domain.exception.CpfInvalidoException;
import br.com.fiap.hackathon.validator.domain.exception.CpfObrigatorioException;
import br.com.fiap.hackathon.validator.domain.exception.DomainValidationException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface EncaminhamentoMapper {


    @Mapping(target = "medico.crm.uf", source = "medico.crmUf")
    @Mapping(target = "medico.crm.numero", source = "medico.crmNumero")
    @Mapping(target = "paciente.cpf", source = "paciente.cpf", qualifiedByName = "cpfPaciente")
    Encaminhamento toDomain(EncaminhamentoRequest request);

    @Named("cpfPaciente")
    default Cpf cpfPaciente(String numero) {
        try {
            return new Cpf(numero);
        } catch (CpfObrigatorioException e) {
            throw new DomainValidationException("CPF do paciente é obrigatório");
        } catch (CpfInvalidoException e) {
            throw new DomainValidationException("Cpf do paciente é inválido");
        }
    }

}
