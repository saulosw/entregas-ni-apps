package com.saulop.atividade5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.HashMap;

public class TamanhoPagamentoActivity extends AppCompatActivity {

    private RadioGroup radioGroupTamanho, radioGroupPagamento;
    private RadioButton radioButtonPequena, radioButtonMedia, radioButtonGrande;
    private RadioButton radioButtonDinheiro, radioButtonCartao;
    private MaterialButton buttonFinalizarPedido;

    private ArrayList<String> saboresSelecionados;
    private double precoBase;

    // HashMap para armazenar os multiplicadores de tamanho
    private HashMap<String, Double> multiplicadoresTamanho = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tamanho_pagamento);

        // Recebe os dados da Activity anterior
        saboresSelecionados = getIntent().getStringArrayListExtra("SABORES");
        precoBase = getIntent().getDoubleExtra("PRECO_BASE", 0.0);

        // Inicializa os multiplicadores de tamanho
        inicializarMultiplicadores();

        // Inicializa os componentes da UI
        inicializarComponentes();

        // Configura os listeners
        configurarListeners();
    }

    private void inicializarMultiplicadores() {
        multiplicadoresTamanho.put("Pequena", 1.0);
        multiplicadoresTamanho.put("Média", 1.5);
        multiplicadoresTamanho.put("Grande", 2.0);
    }

    private void inicializarComponentes() {
        radioGroupTamanho = findViewById(R.id.radioGroupTamanho);
        radioGroupPagamento = findViewById(R.id.radioGroupPagamento);

        radioButtonPequena = findViewById(R.id.radioButtonPequena);
        radioButtonMedia = findViewById(R.id.radioButtonMedia);
        radioButtonGrande = findViewById(R.id.radioButtonGrande);

        radioButtonDinheiro = findViewById(R.id.radioButtonDinheiro);
        radioButtonCartao = findViewById(R.id.radioButtonCartao);

        buttonFinalizarPedido = findViewById(R.id.buttonFinalizarPedido);
    }

    private void configurarListeners() {
        buttonFinalizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalizarPedido();
            }
        });
    }

    private void finalizarPedido() {
        // Verifica se um tamanho foi selecionado
        if (radioGroupTamanho.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, getString(R.string.erro_tamanho), Toast.LENGTH_SHORT).show();
            return;
        }

        if (radioGroupPagamento.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, getString(R.string.erro_pagamento), Toast.LENGTH_SHORT).show();
            return;
        }

        String tamanhoSelecionado;
        double multiplicador;

        if (radioButtonPequena.isChecked()) {
            tamanhoSelecionado = "Pequena (25cm)";
            multiplicador = multiplicadoresTamanho.get("Pequena");
        } else if (radioButtonMedia.isChecked()) {
            tamanhoSelecionado = "Média (35cm)";
            multiplicador = multiplicadoresTamanho.get("Média");
        } else {
            tamanhoSelecionado = "Grande (45cm)";
            multiplicador = multiplicadoresTamanho.get("Grande");
        }

        String metodoPagamento;
        boolean temDesconto = false;

        if (radioButtonDinheiro.isChecked()) {
            metodoPagamento = "Dinheiro (5% de desconto)";
            temDesconto = true;
        } else {
            metodoPagamento = "Cartão";
            temDesconto = false;
        }

        double valorFinal = precoBase * multiplicador;

        if (temDesconto) {
            valorFinal *= 0.95; // 5% de desconto
        }

        Intent intent = new Intent(TamanhoPagamentoActivity.this, ResumoActivity.class);
        intent.putStringArrayListExtra("SABORES", saboresSelecionados);
        intent.putExtra("TAMANHO", tamanhoSelecionado);
        intent.putExtra("PAGAMENTO", metodoPagamento);
        intent.putExtra("VALOR_TOTAL", valorFinal);
        startActivity(intent);
    }
}