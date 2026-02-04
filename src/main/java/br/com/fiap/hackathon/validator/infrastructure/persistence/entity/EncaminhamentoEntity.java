package br.com.fiap.hackathon.validator.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "encaminhamento")
public class EncaminhamentoEntity {

    @Id
    @Column(name = "protocolo")
    private String protocolo;

    @Column(name = "data_encaminhamento")
    private LocalDate dataEncaminhamento;

    @Column(name = "especialidade")
    private String especialidade;

    @Column(name = "cid")
    private String cid;

    @Column(name = "motivo_solicitacao")
    private String motivoSolicitacao;

    @Column(name = "medico_nome")
    private String medicoNome;

    @Column(name = "medico_crm")
    private String medicoCrm;

    @Column(name = "paciente_nome")
    private String pacienteNome;

    @Column(name = "paciente_cpf")
    private String pacienteCpf;


    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public void setDataEncaminhamento(LocalDate dataEncaminhamento) {
        this.dataEncaminhamento = dataEncaminhamento;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setMotivoSolicitacao(String motivoSolicitacao) {
        this.motivoSolicitacao = motivoSolicitacao;
    }

    public void setMedicoNome(String medicoNome) {
        this.medicoNome = medicoNome;
    }

    public void setMedicoCrm(String medicoCrm) {
        this.medicoCrm = medicoCrm;
    }

    public void setPacienteNome(String pacienteNome) {
        this.pacienteNome = pacienteNome;
    }

    public void setPacienteCpf(String pacienteCpf) {
        this.pacienteCpf = pacienteCpf;
    }
}
