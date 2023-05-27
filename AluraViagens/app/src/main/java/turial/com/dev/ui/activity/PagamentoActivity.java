package turial.com.dev.ui.activity;

import static turial.com.dev.ui.activity.PacoteActivityConstantes.CHAVE_PACOTE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import turial.com.dev.R;
import turial.com.dev.model.Pacote;
import turial.com.dev.util.MoedaUtil;

public class PagamentoActivity extends AppCompatActivity {

    public static final String TITULO = "Pagamento";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        setTitle(TITULO);

        carregaPacote();


    }

    private void carregaPacote() {
        final Intent intent = getIntent();
        if(intent.hasExtra(CHAVE_PACOTE)){
            final Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);
            mostraPreco(pacote);
            configuraBotao(pacote);
        }
    }

    private void configuraBotao(Pacote pacote) {
        final Button pagamentoBtn = findViewById(R.id.pagamento_btn_finalizar);
        pagamentoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaiParaResumoPagamento(pacote);
            }
        });
    }

    private void vaiParaResumoPagamento(Pacote pacote) {
        final Intent intent = new Intent(PagamentoActivity.this, ResumoCompraActivity.class);
        intent.putExtra(CHAVE_PACOTE, pacote);
        startActivity(intent);
    }

    private void mostraPreco(Pacote pacote) {
        final TextView pagamentoPreco = findViewById(R.id.pagamento_preco);
        final String moedaFormato = MoedaUtil.formataMoedaParaBr(pacote.getPreco());
        pagamentoPreco.setText(moedaFormato);
    }
}