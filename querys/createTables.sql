
table medicamento

       nu_id_medicamento   NUMBER(20)                 NOT NULL,
       TX_DESCRIPCION VARCHAR2(2000 BYTE)
       pvp    NUMBER(12,2)                NOT NULL

table BENEFICIARIO
   nu_id_BENEFICIARIO   NUMBER(20)                 NOT NULL,
   TX_EMAIL VARCHAR2(2000 BYTE)
   TX_CEDULA  NUMBER(8)      
   TX_CEDULA_FAMILIAR  NUMBER(8)      

table encuesta_medicamento
                nu_id_medicamento   NUMBER(20)                 NOT NULL,      
                nu_id_BENEFICIARIO   NUMBER(20)                 NOT NULL,
                FE_REG_PAGO       DATE                        NOT NULL
                ST_recibir_medicamento VARCHAR2(2 BYTE)          NOT NULL

