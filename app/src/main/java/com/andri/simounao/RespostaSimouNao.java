package com.andri.simounao;


import com.google.gson.annotations.SerializedName;

public class RespostaSimouNao {

    @SerializedName("answer")
    private String resposta;

    public String getResposta() {
        return resposta;
    }
}
