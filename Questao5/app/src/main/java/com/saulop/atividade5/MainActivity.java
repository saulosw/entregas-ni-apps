package com.saulop.atividade5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBoxCalabresa, checkBoxMarguerita, checkBoxPortuguesa,
            checkBoxQuatroQueijos, checkBoxFrangoCatupiry;
    private MaterialButton buttonProximo;

    private HashMap<String, Double> precosSabores = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarPrecos();

        inicializarComponentes();

        configurarListeners();
    }

    private void inicializarPrecos() {
        precosSabores.put("Calabresa", 5.0);
        precosSabores.put("Marguerita", 5.0);
        precosSabores.put("Portuguesa", 6.0);
        precosSabores.put("Quatro Queijos", 7.0);
        precosSabores.put("Frango c/ Catupiry", 6.5);
    }

    private void inicializarComponentes() {
        checkBoxCalabresa = findViewById(R.id.checkBoxCalabresa);
        checkBoxMarguerita = findViewById(R.id.checkBoxMarguerita);
        checkBoxPortuguesa = findViewById(R.id.checkBoxPortuguesa);
        checkBoxQuatroQueijos = findViewById(R.id.checkBoxQuatroQueijos);
        checkBoxFrangoCatupiry = findViewById(R.id.checkBoxFrangoCatupiry);
        buttonProximo = findViewById(R.id.buttonProximo);
    }

    private void configurarListeners() {
        buttonProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosseguirParaTamanho();
            }
        });
    }

    private void prosseguirParaTamanho() {
        ArrayList<String> saboresSelecionados = new ArrayList<>();
        double precoBase = 0.0;

        if (checkBoxCalabresa.isChecked()) {
            saboresSelecionados.add("Calabresa");
            precoBase += precosSabores.get("Calabresa");
        }

        if (checkBoxMarguerita.isChecked()) {
            saboresSelecionados.add("Marguerita");
            precoBase += precosSabores.get("Marguerita");
        }

        if (checkBoxPortuguesa.isChecked()) {
            saboresSelecionados.add("Portuguesa");
            precoBase += precosSabores.get("Portuguesa");
        }

        if (checkBoxQuatroQueijos.isChecked()) {
            saboresSelecionados.add("Quatro Queijos");
            precoBase += precosSabores.get("Quatro Queijos");
        }

        if (checkBoxFrangoCatupiry.isChecked()) {
            saboresSelecionados.add("Frango c/ Catupiry");
            precoBase += precosSabores.get("Frango c/ Catupiry");
        }

        if (saboresSelecionados.isEmpty()) {
            Toast.makeText(this, getString(R.string.erro_selecao), Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(MainActivity.this, TamanhoPagamentoActivity.class);
        intent.putStringArrayListExtra("SABORES", saboresSelecionados);
        intent.putExtra("PRECO_BASE", precoBase);
        startActivity(intent);
    }
}