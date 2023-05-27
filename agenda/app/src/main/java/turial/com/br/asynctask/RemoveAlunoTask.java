package turial.com.br.asynctask;

import android.os.AsyncTask;

import turial.com.br.database.dao.AlunoDAO;
import turial.com.br.model.Aluno;
import turial.com.br.ui.adapter.ListaAlunosAdapter;

public class RemoveAlunoTask extends AsyncTask<Void, Void, Void> {
    private final AlunoDAO alunoDAO;
    private final ListaAlunosAdapter adapter;
    private final Aluno aluno;

    public RemoveAlunoTask(Aluno aluno, AlunoDAO alunoDAO, ListaAlunosAdapter adapter) {
        this.alunoDAO = alunoDAO;
        this.aluno = aluno;
        this.adapter = adapter;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDAO.remove(aluno);
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        adapter.remove(aluno);

    }
}
