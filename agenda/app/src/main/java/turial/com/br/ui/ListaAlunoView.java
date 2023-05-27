package turial.com.br.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import turial.com.br.asynctask.BuscaAlunosTask;
import turial.com.br.asynctask.RemoveAlunoTask;
import turial.com.br.database.AgendaDatabase;
import turial.com.br.database.dao.AlunoDAO;
import turial.com.br.model.Aluno;
import turial.com.br.ui.adapter.ListaAlunosAdapter;

public class ListaAlunoView {
    private final ListaAlunosAdapter adapter;
    private final AlunoDAO alunoDAO;
    private final Context context;

    public ListaAlunoView(Context context) {
        this.context = context;
        adapter = new ListaAlunosAdapter(this.context);
        alunoDAO = AgendaDatabase.getInstance(context).getRoomAlunoDao();

    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    final Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                    removerALuno(alunoEscolhido);
                })
                .setNegativeButton("Não",null)
                .show();
    }

    public void atualizaAlunos() {
        new BuscaAlunosTask(alunoDAO, adapter).execute();

    }

    public void configuraAdpter(ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(adapter);
    }

    private void removerALuno(Aluno aluno) {
        new RemoveAlunoTask (aluno, alunoDAO, adapter).execute();

    }
}
