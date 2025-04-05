package com.saulop.atividade1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkArroz, checkLeite, checkCarne, checkFeijao, checkCoca;
    private TextView txtTotal, txtResumoProdutos;
    private Button btnCalcular;

    private final double PRECO_ARROZ = 2.69;
    private final double PRECO_LEITE = 2.70;
    private final double PRECO_CARNE = 16.70;
    private final double PRECO_FEIJAO = 3.38;
    private final double PRECO_COCA = 3.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarElementos();


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularTotal();
            }
        });
    }

    private void inicializarElementos() {
        checkArroz = findViewById(R.id.checkArroz);
        checkLeite = findViewById(R.id.checkLeite);
        checkCarne = findViewById(R.id.checkCarne);
        checkFeijao = findViewById(R.id.checkFeijao);
        checkCoca = findViewById(R.id.checkCoca);

        txtTotal = findViewById(R.id.txtTotal);
        txtResumoProdutos = findViewById(R.id.txtResumoProdutos);

        btnCalcular = findViewById(R.id.btnCalcular);

        txtTotal.setText("Total: R$ 0,00");
        txtResumoProdutos.setText("Nenhum produto selecionado");
    }


    private void calcularTotal() {
        double totalCompra = 0.0;
        List<String> produtosSelecionados = new ArrayList<>();

        if (checkArroz.isChecked()) {
            totalCompra += PRECO_ARROZ;
            produtosSelecionados.add("• Arroz 1 Kg - R$ " + formatarPreco(PRECO_ARROZ));
        }

        if (checkLeite.isChecked()) {
            totalCompra += PRECO_LEITE;
            produtosSelecionados.add("• Leite longa vida - R$ " + formatarPreco(PRECO_LEITE));
        }

        if (checkCarne.isChecked()) {
            totalCompra += PRECO_CARNE;
            produtosSelecionados.add("• Carne Friboi - R$ " + formatarPreco(PRECO_CARNE));
        }

        if (checkFeijao.isChecked()) {
            totalCompra += PRECO_FEIJAO;
            produtosSelecionados.add("• Feijão carioquinha 1 Kg - R$ " + formatarPreco(PRECO_FEIJAO));
        }

        if (checkCoca.isChecked()) {
            totalCompra += PRECO_COCA;
            produtosSelecionados.add("• Refrigerante coca-cola 2 litros - R$ " + formatarPreco(PRECO_COCA));
        }

        atualizarInterface(produtosSelecionados, totalCompra);
    }

    private void atualizarInterface(List<String> produtosSelecionados, double totalCompra) {
        String totalFormatado = formatarPreco(totalCompra);

        txtTotal.setText("Total: R$ " + totalFormatado);

        if (produtosSelecionados.isEmpty()) {
            txtResumoProdutos.setText("Nenhum produto selecionado");
        } else {
            StringBuilder resumo = new StringBuilder();
            for (String produto : produtosSelecionados) {
                resumo.append(produto).append("\n");
            }
            txtResumoProdutos.setText(resumo.toString());
        }
    }

    private String formatarPreco(double preco) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(preco);
    }
}