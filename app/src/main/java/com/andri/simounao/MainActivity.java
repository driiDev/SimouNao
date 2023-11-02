package com.andri.simounao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText perguntaEditText;
    private Button decideButton;
    private TextView resultadoTextView;
    private Retrofit retrofit;
    private SimNaoService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        perguntaEditText = findViewById(R.id.perguntaEditText);
        decideButton = findViewById(R.id.decideButton);
        resultadoTextView = findViewById(R.id.resultadoTextView);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://yesno.wtf/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(SimNaoService.class);

        decideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decideParaMim();
            }
        });
    }

    private void decideParaMim() {
        // Se tiver algum resultado, limpa
        resultadoTextView.setText("");

        Call<RespostaSimouNao> chamada = apiService.obterResposta();
        chamada.enqueue(new Callback<RespostaSimouNao>() {
            @Override
            public void onResponse(Call<RespostaSimouNao> call, Response<RespostaSimouNao> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String resposta = response.body().getResposta();
                    resultadoTextView.setText(resposta.toUpperCase());
                } else {
                    resultadoTextView.setText("Erro ao obter resposta.");
                }
            }

            @Override
            public void onFailure(Call<RespostaSimouNao > call, Throwable t) {
                resultadoTextView.setText("Erro ao obter resposta.");
            }
        });
    }
}