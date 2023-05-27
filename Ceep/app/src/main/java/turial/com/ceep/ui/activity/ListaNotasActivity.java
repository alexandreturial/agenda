package turial.com.ceep.ui.activity;


import static turial.com.ceep.ui.activity.NotaActivityConstants.CHAVE_NOTA;
import static turial.com.ceep.ui.activity.NotaActivityConstants.CHAVE_POSICAO;
import static turial.com.ceep.ui.activity.NotaActivityConstants.CODIGO_REQUEST_EDIT_NOTA;
import static turial.com.ceep.ui.activity.NotaActivityConstants.CODIGO_REQUEST_INSERE_NOTA;
import static turial.com.ceep.ui.activity.NotaActivityConstants.POSICAO_INVALIDA;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import turial.com.ceep.R;
import turial.com.ceep.dao.NotaDAO;
import turial.com.ceep.model.Nota;
import turial.com.ceep.ui.activity.recycler.helper.callback.NotaItemTouchHelperCallback;
import turial.com.ceep.ui.recycler.adapter.ListaNotasAdapter;
import turial.com.ceep.ui.recycler.adapter.listener.OnItemClickListener;

public class ListaNotasActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Nota";
    private ListaNotasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TITULO_APPBAR);
        setContentView(R.layout.activity_lista_notas);
        final List<Nota> todasNotas = pegaTodasNotas();

        configuraRecyclerView(todasNotas);

        configuraBotaoInsereNota();
    }

    private List<Nota> pegaTodasNotas() {
        final NotaDAO notaDAO = new NotaDAO();
        return notaDAO.todos();
    }

    private void configuraBotaoInsereNota() {
        final TextView btnInsereNota = findViewById(R.id.lista_notas_insere);

        btnInsereNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaiParaFormNotaActivityInsere();
            }
        });
    }

    private void vaiParaFormNotaActivityInsere() {
        final Intent intent = new Intent(ListaNotasActivity.this, FormularioNotaActivity.class);
        startActivityForResult(intent, CODIGO_REQUEST_INSERE_NOTA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(eUmResultadoInsereNota(requestCode, data)){
            if(resultadoOk(resultCode)){
                final Nota nota = (Nota) data.getSerializableExtra(CHAVE_NOTA);
                adicionaNota(nota);
            }else if(resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(this, "Ocorreu um problema na alteração da nota", Toast.LENGTH_SHORT).show();
            }

        }

        if(eResultadoParaAlterarNota(data, requestCode)){
            if(resultadoOk(resultCode)){
                final Nota nota = (Nota)data.getSerializableExtra(CHAVE_NOTA);
                final int posicao = data.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);
                if(ePosicaoValida(posicao)){
                    alteraNota(nota, posicao);
                }
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void alteraNota(Nota nota, int posicao) {
        new NotaDAO().altera(posicao, nota);
        adapter.altera(posicao, nota);
    }

    private boolean ePosicaoValida(int posicao) {
        return posicao > POSICAO_INVALIDA;
    }

    private boolean eResultadoParaAlterarNota( @Nullable Intent data, int requestCode) {
        return eCodigoRequestEditNota(requestCode) && temNota(data);
    }

    private boolean eCodigoRequestEditNota(int requestCode) {
        return requestCode == CODIGO_REQUEST_EDIT_NOTA;
    }

    private void adicionaNota(Nota nota) {
        new NotaDAO().insere(nota);

        adapter.adiciona(nota);
    }

    private boolean eUmResultadoInsereNota(int requestCode, @Nullable Intent data) {
        return eCodigoRquestInsereNota(requestCode) && temNota(data);
    }

    private boolean temNota(@Nullable Intent data) {
        return data!= null && data.hasExtra(CHAVE_NOTA);
    }

    private boolean resultadoOk(int resultCode) {
        return resultCode == Activity.RESULT_OK;
    }

    private boolean eCodigoRquestInsereNota(int requestCode) {
        return requestCode == CODIGO_REQUEST_INSERE_NOTA;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void configuraRecyclerView(List<Nota> todasNotas) {
        final RecyclerView listaNotas = findViewById(R.id.lista_nota_recyclerView);
        configuraAdapter(todasNotas, listaNotas);

        configuraItemTouchHelper(listaNotas);
    }

    private void configuraItemTouchHelper(RecyclerView listaNotas) {
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new NotaItemTouchHelperCallback(adapter));

        itemTouchHelper.attachToRecyclerView(listaNotas);
    }

    private void configuraAdapter(List<Nota> todasNotas, RecyclerView listaNotas) {
        adapter = new ListaNotasAdapter(todasNotas, this);
        listaNotas.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Nota nota, int posicao) {
                vaiParaFormNotaActivityEdit(nota, posicao);
            }
        });
    }

    private void vaiParaFormNotaActivityEdit(Nota nota, int posicao) {
        final Intent abreFormComNota = new Intent(ListaNotasActivity.this, FormularioNotaActivity.class);
        abreFormComNota.putExtra(CHAVE_NOTA, nota);
        abreFormComNota.putExtra(CHAVE_POSICAO, posicao);
        startActivityForResult(abreFormComNota, CODIGO_REQUEST_EDIT_NOTA);
    }
}