package com.saulop.atividade4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResumoActivity extends AppCompatActivity {

    private TextView textViewResumoPedido;
    private Button buttonNovoPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);

        textViewResumoPedido = findViewById(R.id.textViewResumoPedido);
        buttonNovoPedido = findViewById(R.id.buttonNovoPedido);

        String nomeCliente = getIntent().getStringExtra("NOME_CLIENTE");
        String lancheSelecionado = getIntent().getStringExtra("LANCHE_SELECIONADO");

        if (nomeCliente != null && lancheSelecionado != null) {
            textViewResumoPedido.setText(String.format(
                    getString(R.string.resumo_pedido),
                    nomeCliente,
                    lancheSelecionado
            ));
        }

        buttonNovoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResumoActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}