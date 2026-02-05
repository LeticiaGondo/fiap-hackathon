package br.com.fiap.hackathon.tea.domain;

import br.com.fiap.hackathon.tea.domain.exception.ValidacaoException;

public class Encaminhamento {

    public static final String ERRO_PROTOCOLO_OBRIGATORIO = "Protocolo é obrigatório";
    public static final String ERRO_ESPECIALIDADE_OBRIGATORIO = "Especialidade é obrigatório";
    public static final String ERRO_MOTIVO_SOLICITACAO_OBRIGATORIO = "Motivo da solicitação é obrigatório";
    public static final String ERRO_MEDICO_OBRIGATORIO = "Dados do medico(a) é obrigatório";
    public static final String ERRO_PACIENTE_OBRIGATORIO = "Dados do paciente é obrigatório";

    private String protocolo;
    private Cid cid;
    private Medico medico;
    private Paciente paciente;
    private String especialidade;
    private String motivoSolicitacao;

    public Encaminhamento(String protocolo, Cid cid, Medico medico, Paciente paciente, String especialidade, String motivoSolicitacao) {
        if (protocolo == null || protocolo.isBlank()) {
            throw new ValidacaoException(ERRO_PROTOCOLO_OBRIGATORIO);
        }

        if (especialidade == null || especialidade.isBlank()) {
            throw new ValidacaoException(ERRO_ESPECIALIDADE_OBRIGATORIO);
        }

        if (motivoSolicitacao == null || motivoSolicitacao.isBlank()) {
            throw new ValidacaoException(ERRO_MOTIVO_SOLICITACAO_OBRIGATORIO);
        }

        if(medico == null){
            throw new ValidacaoException(ERRO_MEDICO_OBRIGATORIO);
        }

        if (paciente == null){
            throw new ValidacaoException(ERRO_PACIENTE_OBRIGATORIO);
        }

        this.protocolo = protocolo;
        this.cid = cid;
        this.medico = medico;
        this.paciente = paciente;
        this.especialidade = especialidade;
        this.motivoSolicitacao = motivoSolicitacao;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public Cid getCid() {
        return cid;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getMotivoSolicitacao() {
        return motivoSolicitacao;
    }
}
