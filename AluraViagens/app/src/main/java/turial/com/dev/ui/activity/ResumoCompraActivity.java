package turial.com.dev.ui.activity;

import static turial.com.dev.ui.activity.PacoteActivityConstantes.CHAVE_PACOTE;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import turial.com.dev.R;
import turial.com.dev.model.Pacote;
import turial.com.dev.util.ActionBarStyle;
import turial.com.dev.util.DataUtil;
import turial.com.dev.util.MoedaUtil;
import turial.com.dev.util.ResourcesUtil;

public class ResumoCompraActivity extends AppCompatActivity {

    public static final String TITULO = "Resumo da compra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_compra);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ActionBarStyle.setActionBarStyle(actionBar);
        setTitle(TITULO);

        carregaPacote();
    }

    private void carregaPacote() {
        final Intent intent = getIntent();
        if(intent.hasExtra(CHAVE_PACOTE)){
            final Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);
            CarregaDadosDoPacote(pacote);
        }
    }

    private void CarregaDadosDoPacote(Pacote pacote) {
        mostraImagem(pacote);

        mostraLocal(pacote);

        mostraData(pacote);

        mostraPreco(pacote);
    }

    private void mostraPreco(Pacote pacote) {
        final TextView resumoPreco = findViewById(R.id.resumo_compra_preco);
        final String precoFormated = MoedaUtil.formataMoedaParaBr(pacote.getPreco());
        resumoPreco.setText(precoFormated);
    }

    private void mostraData(Pacote pacote) {
        final TextView resumoData = findViewById(R.id.resumo_compra_data);
        final String dataFormated = DataUtil.periodoTexto(pacote.getDias());
        resumoData.setText(dataFormated);
    }

    private void mostraImagem(Pacote pacote) {
        final ImageView resumoImagem = findViewById(R.id.resumo_compra_imagem);
        final Drawable drawableBanner = ResourcesUtil.devolverDrawable(pacote.getImagem(), this);
        resumoImagem.setImageDrawable(drawableBanner);
    }

    private void mostraLocal(Pacote pacote) {
        final TextView resumoLocal = findViewById(R.id.resumo_compra_local);
        resumoLocal.setText(pacote.getLocal());
    }
}