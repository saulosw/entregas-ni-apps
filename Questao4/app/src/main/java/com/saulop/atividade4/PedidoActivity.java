package com.saulop.atividade4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class PedidoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout textInputLayoutNome;
    private TextInputEditText editTextNome;
    private Button buttonHamburguer, buttonPizza, buttonHotDog, buttonSalada, buttonConfirmarPedido;

    private String lancheSelecionado = "";
    private Button botaoAtual = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        textInputLayoutNome = findViewById(R.id.textInputLayoutNome);
        editTextNome = findViewById(R.id.editTextNome);

        buttonHamburguer = findViewById(R.id.buttonHamburguer);
        buttonPizza = findViewById(R.id.buttonPizza);
        buttonHotDog = findViewById(R.id.buttonHotDog);
        buttonSalada = findViewById(R.id.buttonSalada);
        buttonConfirmarPedido = findViewById(R.id.buttonConfirmarPedido);

        buttonHamburguer.setOnClickListener(this);
        buttonPizza.setOnClickListener(this);
        buttonHotDog.setOnClickListener(this);
        buttonSalada.setOnClickListener(this);
        buttonConfirmarPedido.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonHamburguer) {
            selecionarLanche(buttonHamburguer, getString(R.string.lanche_hamburguer));
        } else if (v.getId() == R.id.buttonPizza) {
            selecionarLanche(buttonPizza, getString(R.string.lanche_pizza));
        } else if (v.getId() == R.id.buttonHotDog) {
            selecionarLanche(buttonHotDog, getString(R.string.lanche_hotdog));
        } else if (v.getId() == R.id.buttonSalada) {
            selecionarLanche(buttonSalada, getString(R.string.lanche_salada));
        } else if (v.getId() == R.id.buttonConfirmarPedido) {
            confirmarPedido();
        }
    }

    private void selecionarLanche(Button botao, String lanche) {
        if (botaoAtual != null) {
            botaoAtual.setBackgroundResource(R.drawable.button_background);
        }

        botao.setBackgroundResource(R.drawable.button_selected);
        botaoAtual = botao;
        lancheSelecionado = lanche;
    }

    private void confirmarPedido() {
        String nome = editTextNome.getText().toString().trim();

        if (TextUtils.isEmpty(nome)) {
            textInputLayoutNome.setError("Por favor, insira seu nome");
            return;
        }

        if (TextUtils.isEmpty(lancheSelecionado)) {
            Toast.makeText(this, getString(R.string.selecione_lanche), Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(PedidoActivity.this, ResumoActivity.class);
        intent.putExtra("NOME_CLIENTE", nome);
        intent.putExtra("LANCHE_SELECIONADO", lancheSelecionado);
        startActivity(intent);
    }
}