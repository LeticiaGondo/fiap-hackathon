package br.com.fiap.hackathon.validator.domain.entity;

import br.com.fiap.hackathon.validator.domain.exception.ValidacaoDominioException;

public class Encaminhamento {

    private String protocolo;
    private String cid;
    private Medico medico;
    private Paciente paciente;

    public Encaminhamento(String protocolo, String cid, Medico medico, Paciente paciente) {
        if (protocolo == null || protocolo.isBlank()) {
            throw new ValidacaoDominioException("Protocolo é obrigatório");
        }

        if (cid == null || cid.isBlank()) {
            throw new ValidacaoDominioException("CID é obrigatório");
        }

        this.protocolo = protocolo;
        this.cid = cid;
        this.medico = medico;
        this.paciente = paciente;
    }
}
