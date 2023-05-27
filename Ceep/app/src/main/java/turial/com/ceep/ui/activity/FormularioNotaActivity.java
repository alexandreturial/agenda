package turial.com.ceep.ui.activity;

import static turial.com.ceep.ui.activity.NotaActivityConstants.CHAVE_NOTA;
import static turial.com.ceep.ui.activity.NotaActivityConstants.CHAVE_POSICAO;
import static turial.com.ceep.ui.activity.NotaActivityConstants.POSICAO_INVALIDA;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import turial.com.ceep.R;
import turial.com.ceep.model.Nota;

public class FormularioNotaActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_ALTERAR_NOTA = "Alterar nota";
    public static final String TITULO_APPBAR_INSERE_NOTA = "Inserir Nota";
    private int posicaoNota = POSICAO_INVALIDA;
    private TextView titulo;
    private TextView descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TITULO_APPBAR_INSERE_NOTA);
        setContentView(R.layout.activity_formulario_nota);
        final Intent intent = getIntent();
        inicializaCampos();

        if(intent.hasExtra(CHAVE_NOTA)){
            setTitle(TITULO_APPBAR_ALTERAR_NOTA);
            final Nota notaRecebida = (Nota)intent.getSerializableExtra(CHAVE_NOTA);
            posicaoNota = intent.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);

            preencheCampos(notaRecebida);
        }
    }

    private void preencheCampos(Nota notaRecebida) {
        titulo.setText(notaRecebida.getTitulo());
        descricao.setText(notaRecebida.getDescricao());
    }

    private void inicializaCampos() {
        titulo = findViewById(R.id.formulario_nota_titulo);
        descricao = findViewById(R.id.formulario_nota_descricao);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_nota_salva, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (eMenuSalvaNota(item)){
            final Nota nota = criarNota();

            retornaNota(nota);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }

    private void retornaNota(Nota nota) {
        final Intent resultadoInsercao = new Intent();
        resultadoInsercao.putExtra(CHAVE_NOTA, nota);
        resultadoInsercao.putExtra(CHAVE_POSICAO, posicaoNota);
        setResult(Activity.RESULT_OK, resultadoInsercao);
    }

    @NonNull
    private Nota criarNota() {
        return new Nota(titulo.getText().toString(), descricao.getText().toString());
    }


    private boolean eMenuSalvaNota(@NonNull MenuItem item) {
        return item.getItemId() == R.id.menu_formulario_nota_ic_salva;
    }
}