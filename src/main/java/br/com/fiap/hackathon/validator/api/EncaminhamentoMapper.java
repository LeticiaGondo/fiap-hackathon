package br.com.fiap.hackathon.validator.api;

import br.com.fiap.hackathon.validator.api.dto.EncaminhamentoRequest;
import br.com.fiap.hackathon.validator.domain.entity.Encaminhamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EncaminhamentoMapper {


    @Mapping(target = "protocolo", source = "protocolo")
    @Mapping(target = "cid", source = "cid")
    @Mapping(target = "medico", source = "medico")
    @Mapping(target = "paciente", source = "paciente")
    Encaminhamento toDomain(EncaminhamentoRequest request);

}
