package com.saulop.atividade3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmacaoActivity extends AppCompatActivity {

    private TextView textViewBemVindoCliente;
    private Button buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacao);

        textViewBemVindoCliente = findViewById(R.id.textViewBemVindoCliente);
        buttonVoltar = findViewById(R.id.buttonVoltar);

        String nomeCliente = getIntent().getStringExtra("NOME_CLIENTE");

        if (nomeCliente != null && !nomeCliente.isEmpty()) {
            textViewBemVindoCliente.setText(String.format(getString(R.string.bem_vindo_cliente), nomeCliente));
        }

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmacaoActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}