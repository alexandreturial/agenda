package turial.com.br.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import turial.com.br.R;
import turial.com.br.asynctask.BuscaPrimeiroTelefoneDoAlunoTask;
import turial.com.br.database.AgendaDatabase;
import turial.com.br.database.dao.TelefoneDAO;
import turial.com.br.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;
    private final TelefoneDAO telefoneDAO;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
        telefoneDAO = AgendaDatabase.getInstance(context).getTefoneDAO();

    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int posicao) {
        return alunos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return alunos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        final View viewCriada = criaView(viewGroup);
        final Aluno aluno = alunos.get(posicao);

        vinculaAluno(viewCriada, aluno);
        return viewCriada;
    }

    private void vinculaAluno(View viewCriada, Aluno aluno) {
        TextView nome = viewCriada.findViewById(R.id.item_aluno_nome);
        nome.setText(aluno.getNome() + " " + aluno.dataFormatada());
        TextView telefone = viewCriada.findViewById(R.id.item_aluno_telefone);

        new BuscaPrimeiroTelefoneDoAlunoTask(
                telefoneDAO,
                aluno.getId(), (telefoneEncontrado) -> telefone.setText(telefoneEncontrado.getNumero())
        ).execute();

    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_aluno, viewGroup, false);
    }


    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }


    public void atualiza(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }
}
