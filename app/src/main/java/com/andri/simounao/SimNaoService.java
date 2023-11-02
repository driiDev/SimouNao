package com.andri.simounao;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SimNaoService {

    @GET("api")
    Call<RespostaSimouNao>obterResposta();
}
