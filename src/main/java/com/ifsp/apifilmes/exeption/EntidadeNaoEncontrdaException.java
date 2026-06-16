package com.ifsp.apifilmes.exeption;

public class EntidadeNaoEncontrdaException extends RuntimeException {

    private String mensagem;
    private String erro;

    public EntidadeNaoEncontrdaException(String mensagem, String erro) {
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
