package turial.com.br.asynctask;

import android.os.AsyncTask;

import java.util.List;

import turial.com.br.database.dao.AlunoDAO;
import turial.com.br.model.Aluno;
import turial.com.br.ui.adapter.ListaAlunosAdapter;

public class BuscaAlunosTask extends AsyncTask<Void, Void, List<Aluno>> {
    private final AlunoDAO alunoDAO;
    private final ListaAlunosAdapter adapter;

    public  BuscaAlunosTask(AlunoDAO alunoDAO, ListaAlunosAdapter adapter){

        this.alunoDAO = alunoDAO;
        this.adapter = adapter;
    }

    @Override
    protected List<Aluno> doInBackground(Void[] objects) {
        final List<Aluno> todosAlunos = alunoDAO.getAlunos();
        return todosAlunos;
    }

    @Override
    protected void onPostExecute(List<Aluno> todosAlunos) {
        super.onPostExecute(todosAlunos);
        adapter.atualiza(todosAlunos);

    }
}
