package com.saulop.atividade3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CadastroActivity extends AppCompatActivity {

    private TextInputLayout textInputLayoutNome;
    private TextInputEditText editTextNome;
    private Button buttonContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        textInputLayoutNome = findViewById(R.id.textInputLayoutNome);
        editTextNome = findViewById(R.id.editTextNome);
        buttonContinuar = findViewById(R.id.buttonContinuar);

        buttonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editTextNome.getText().toString().trim();

                if (TextUtils.isEmpty(nome)) {
                    textInputLayoutNome.setError("Por favor, insira seu nome");
                    return;
                }

                Intent intent = new Intent(CadastroActivity.this, ConfirmacaoActivity.class);
                intent.putExtra("NOME_CLIENTE", nome);
                startActivity(intent);
            }
        });
    }
}