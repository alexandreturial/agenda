package turial.com.dev.ui.activity;

import static turial.com.dev.ui.activity.PacoteActivityConstantes.CHAVE_PACOTE;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import turial.com.dev.R;
import turial.com.dev.model.Pacote;
import turial.com.dev.util.ActionBarStyle;
import turial.com.dev.util.DataUtil;
import turial.com.dev.util.DiasUtil;
import turial.com.dev.util.MoedaUtil;
import turial.com.dev.util.ResourcesUtil;

public class ResumoPacoteActivity extends AppCompatActivity {


    public static final String TITULO_APPBAR = "Resumo do pacote";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_pacote);
        setTitle(TITULO_APPBAR);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ActionBarStyle.setActionBarStyle(actionBar);

        carregaPacote();
    }

    private void carregaPacote() {
        final Intent intent = getIntent();
        if(intent.hasExtra(CHAVE_PACOTE)){
            final Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);

            inicializaCampos(pacote);

            configuraBotao(pacote);
        }
    }

    private void inicializaCampos(Pacote pacote) {
        mostrarLocal(pacote);

        mostarImage(pacote);

        mostrarDias(pacote);

        mostrarPreco(pacote);

        mostarData(pacote);
    }

    private void configuraBotao(Pacote pacote) {
        final Button pagamentoBtn = findViewById(R.id.resumo_pacote_btn_pagamento);
        pagamentoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaiParaPagamento(pacote);
            }
        });
    }

    private void vaiParaPagamento(Pacote pacote) {
        final Intent intent = new Intent(ResumoPacoteActivity.this, PagamentoActivity.class);
        intent.putExtra(CHAVE_PACOTE, pacote);
        startActivity(intent);
    }

    private void mostarData(Pacote pacote) {
        final TextView resumoData = findViewById(R.id.resumo_pacote_data);
        final String dataPacote = DataUtil.periodoTexto(pacote.getDias());
        resumoData.setText(dataPacote);
    }

    private void mostrarPreco(Pacote pacote) {
        final TextView resumoPreco = findViewById(R.id.resumo_pacote_preco);
        final String precoFormated = MoedaUtil.formataMoedaParaBr(pacote.getPreco());
        resumoPreco.setText(precoFormated);
    }

    private void mostrarDias(Pacote pacote) {
        final TextView resumoDias = findViewById(R.id.resumo_pacote_dias);
        final String diasFormated = DiasUtil.formataEmTexto(pacote.getDias());
        resumoDias.setText(diasFormated);
    }

    private void mostarImage(Pacote pacote) {
        final ImageView resumoImagem = findViewById(R.id.resumo_pacote_imagem);
        final Drawable drawableIamge = ResourcesUtil.devolverDrawable(pacote.getImagem(), this);
        resumoImagem.setImageDrawable(drawableIamge);
    }

    private void mostrarLocal(Pacote pacoteSalvador) {
        final TextView resumoLocal = findViewById(R.id.resumo_pacote_local);
        resumoLocal.setText(pacoteSalvador.getLocal());
    }
}