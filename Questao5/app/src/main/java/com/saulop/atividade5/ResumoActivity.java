package com.saulop.atividade5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class ResumoActivity extends AppCompatActivity {

    private TextView textViewSabores, textViewTamanho, textViewPagamento, textViewValorTotal;
    private MaterialButton buttonNovoPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);

        inicializarComponentes();

        exibirDetalhesPedido();

        configurarListeners();
    }

    private void inicializarComponentes() {
        textViewSabores = findViewById(R.id.textViewSabores);
        textViewTamanho = findViewById(R.id.textViewTamanho);
        textViewPagamento = findViewById(R.id.textViewPagamento);
        textViewValorTotal = findViewById(R.id.textViewValorTotal);
        buttonNovoPedido = findViewById(R.id.buttonNovoPedido);
    }

    private void exibirDetalhesPedido() {
        ArrayList<String> saboresSelecionados = getIntent().getStringArrayListExtra("SABORES");
        String tamanhoSelecionado = getIntent().getStringExtra("TAMANHO");
        String metodoPagamento = getIntent().getStringExtra("PAGAMENTO");
        double valorTotal = getIntent().getDoubleExtra("VALOR_TOTAL", 0.0);

        StringBuilder saboresStr = new StringBuilder();
        for (int i = 0; i < saboresSelecionados.size(); i++) {
            saboresStr.append(saboresSelecionados.get(i));
            if (i < saboresSelecionados.size() - 1) {
                saboresStr.append(", ");
            }
        }

        textViewSabores.setText(saboresStr.toString());
        textViewTamanho.setText(tamanhoSelecionado);
        textViewPagamento.setText(metodoPagamento);
        textViewValorTotal.setText(String.format(getString(R.string.formatar_preco), valorTotal));
    }

    private void configurarListeners() {
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