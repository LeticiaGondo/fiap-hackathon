package br.com.fiap.hackathon.tea.api.mapper;

import br.com.fiap.hackathon.tea.api.dto.EncaminhamentoRequest;
import br.com.fiap.hackathon.tea.domain.Cid;
import br.com.fiap.hackathon.tea.domain.Cpf;
import br.com.fiap.hackathon.tea.domain.Encaminhamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface EncaminhamentoMapper {


    @Mapping(target = "paciente.cpf", source = "paciente.cpf", qualifiedByName = "cpfPaciente")
    @Mapping(target = "cid", source = "cid", qualifiedByName = "cid")
    Encaminhamento toDomain(EncaminhamentoRequest request);

    @Named("cpfPaciente")
    default Cpf cpfPaciente(String numero) {
        return Cpf.of(numero);
    }

    @Named("cid")
    default Cid cid(String codigo) {
        return Cid.of(codigo);
    }
}
