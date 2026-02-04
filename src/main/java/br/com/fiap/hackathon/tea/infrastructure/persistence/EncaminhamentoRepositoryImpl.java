package br.com.fiap.hackathon.tea.infrastructure.persistence;

import br.com.fiap.hackathon.tea.application.validacao.repository.EncaminhamentoRepository;
import br.com.fiap.hackathon.tea.domain.encaminhamento.Encaminhamento;
import br.com.fiap.hackathon.tea.domain.encaminhamento.Medico;
import br.com.fiap.hackathon.tea.domain.encaminhamento.Paciente;
import br.com.fiap.hackathon.tea.infrastructure.persistence.entity.EncaminhamentoEntity;
import br.com.fiap.hackathon.tea.infrastructure.persistence.repository.EncaminhamentoJpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class EncaminhamentoRepositoryImpl implements EncaminhamentoRepository {

    private final EncaminhamentoJpaRepository jpaRepository;

    public EncaminhamentoRepositoryImpl(EncaminhamentoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void salvar(Encaminhamento encaminhamento) {
        EncaminhamentoEntity entity = new EncaminhamentoEntity();

        entity.setProtocolo(encaminhamento.getProtocolo());
        entity.setCid(encaminhamento.getCid());

        // sistêmico: data atual
        entity.setDataEncaminhamento(LocalDate.now());


        Medico medico = encaminhamento.getMedico();
        if (medico != null) {
            entity.setMedicoNome(medico.getNome());
            entity.setMedicoCrm(medico.getCrmUf() + "-" + medico.getCrmNumero());
        }

        Paciente paciente = encaminhamento.getPaciente();
        if (paciente != null) {
            entity.setPacienteNome(paciente.getNome());
            entity.setPacienteCpf(paciente.getCpf().getNumero());
        }

        entity.setEspecialidade("NAO_INFORMADA");
        entity.setMotivoSolicitacao("NAO_INFORMADO");

        jpaRepository.save(entity);
    }

    @Override
    public boolean existePorProtocolo(String protocolo) {
        return jpaRepository.existsByProtocolo(protocolo);
    }

}