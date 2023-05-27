package turial.com.br.ui.activity;

import static turial.com.br.ui.activity.ContantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import turial.com.br.R;
import turial.com.br.model.Aluno;
import turial.com.br.ui.ListaAlunoView;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";
    private ListaAlunoView listaAlunoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        listaAlunoView = new ListaAlunoView(ListaAlunosActivity.this);
        configuraFabNovoAluno();
        configuraLista();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_alunos_menu_remover) {
            listaAlunoView.confirmaRemocao(item);
        }

        return super.onContextItemSelected(item);
    }



    private void configuraFabNovoAluno() {
        FloatingActionButton fabNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        fabNovoAluno.setOnClickListener(view -> abrirFormularioInsereNovoALuno());
    }

    private void abrirFormularioInsereNovoALuno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        listaAlunoView.atualizaAlunos();
    }



    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        listaAlunoView.configuraAdpter(listaDeAlunos);
        configuraListenerDeClique(listaDeAlunos);

        registerForContextMenu(listaDeAlunos);
    }



    private void configuraListenerDeClique(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener((adapterView, view, position, id) -> {
            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);
            AbreFormularioModoEditarAluno(alunoEscolhido);
        });
    }

    private void AbreFormularioModoEditarAluno(Aluno alunoEscolhido) {
        final Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, alunoEscolhido);
        startActivity(vaiParaFormularioActivity);
    }
}

