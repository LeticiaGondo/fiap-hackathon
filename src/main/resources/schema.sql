CREATE TABLE encaminhamento (
    protocolo           VARCHAR(50) PRIMARY KEY,

    data_encaminhamento DATE         NOT NULL,
    especialidade       VARCHAR(100) NOT NULL,
    cid                 VARCHAR(10)  NOT NULL,
    motivo_solicitacao  VARCHAR(255) NOT NULL,

    medico_nome         VARCHAR(150) NOT NULL,
    medico_crm          VARCHAR(30)  NOT NULL,

    paciente_nome       VARCHAR(150) NOT NULL,
    paciente_cpf        VARCHAR(14)  NOT NULL
);