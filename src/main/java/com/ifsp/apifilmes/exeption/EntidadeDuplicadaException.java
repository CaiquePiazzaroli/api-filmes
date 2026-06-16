package com.ifsp.apifilmes.exeption;

public class EntidadeDuplicadaException extends RuntimeException{
    private String mensagem;
    private String erro;

    public EntidadeDuplicadaException(String mensagem, String erro) {
        this.mensagem = mensagem;
        this.erro = erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getErro() {
        return erro;
    }
}
