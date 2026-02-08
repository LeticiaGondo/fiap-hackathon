package br.com.fiap.hackathon.tea.domain;

import br.com.fiap.hackathon.tea.domain.exception.ValidacaoException;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> validarPendencias() {
        List<String> pendencias = new ArrayList<>();

        if (protocolo == null || protocolo.isBlank()) {
            pendencias.add(ERRO_PROTOCOLO_OBRIGATORIO);
        }

        if (especialidade == null || especialidade.isBlank()) {
            pendencias.add(ERRO_ESPECIALIDADE_OBRIGATORIO);
        }

        if (motivoSolicitacao == null || motivoSolicitacao.isBlank()) {
            pendencias.add(ERRO_MOTIVO_SOLICITACAO_OBRIGATORIO);
        }

        if (cid == null) {
            pendencias.add(Cid.ERRO_CID_OBRIGATORIO);
        } else {
            pendencias.addAll(cid.validarPendencias());
        }

        if (medico == null) {
            pendencias.add(ERRO_MEDICO_OBRIGATORIO);
        } else {
            pendencias.addAll(medico.validarPendencias());
        }

        if (paciente == null) {
            pendencias.add(ERRO_PACIENTE_OBRIGATORIO);
        } else {
            pendencias.addAll(paciente.validarPendencias());
        }

        return pendencias;
    }
}
