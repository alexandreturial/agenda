package turial.com.br.ui.activity;

import static turial.com.br.ui.activity.ContantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import turial.com.br.R;
import turial.com.br.asynctask.BuscaTodosOsTelfonesAluno;
import turial.com.br.asynctask.EditaAlunoTask;
import turial.com.br.asynctask.SalvaAlunoTask;
import turial.com.br.database.AgendaDatabase;
import turial.com.br.database.dao.AlunoDAO;
import turial.com.br.database.dao.TelefoneDAO;
import turial.com.br.model.Aluno;
import turial.com.br.model.Telefone;
import turial.com.br.model.TipoTelefone;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_NOVO_ALUNO = "Novo Aluno";
    private static final String TITULO_APPBAR_EDITAR_ALUNO = "Editar Aluno";
    private EditText nomeCampo;
    private EditText telefoneFixoCampo;
    private EditText telefoneCelularCampo;
    private EditText emailCampo;
    private Aluno aluno;
    private AlunoDAO alunoDAO;
    private TelefoneDAO telefoneDAO;
    private List<Telefone> telefonesDoAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        final AgendaDatabase agendaDatabase = AgendaDatabase.getInstance(this);
        alunoDAO = agendaDatabase.getRoomAlunoDao();
        telefoneDAO = agendaDatabase.getTefoneDAO();
        inicializacaoDosCampos();

        carregaAluno();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_aluno_menu_salvar) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_APPBAR_EDITAR_ALUNO);
            this.aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCamposAluno();

        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            this.aluno = new Aluno();
        }
    }

    private void preencheCamposAluno() {
        nomeCampo.setText(this.aluno.getNome());
        emailCampo.setText(this.aluno.getEmail());
        preencherCamposTelefone();
    }

    private void preencherCamposTelefone() {
        new BuscaTodosOsTelfonesAluno(telefoneDAO, aluno, (telefones)->{
            this.telefonesDoAluno = telefones;
            for (Telefone telefone : telefonesDoAluno) {
                if(telefone.getTipo() == TipoTelefone.FIXO){
                    telefoneFixoCampo.setText(telefone.getNumero());
                }else{
                    telefoneCelularCampo.setText(telefone.getNumero());
                }
            }
        }).execute();

    }

    private void finalizaFormulario() {
        preencheAluno();

        final Telefone telefoneFixo = criaTelefone(telefoneFixoCampo, TipoTelefone.FIXO);
        final Telefone telefoneCelular = criaTelefone(telefoneCelularCampo, TipoTelefone.CELULAR);

        if (aluno.temIdValido()) {
            editaAluno(telefoneFixo, telefoneCelular);
        } else {
            salvaAluno(telefoneFixo, telefoneCelular);

        }
    }

    @NonNull
    private Telefone criaTelefone(EditText telefoneFixoCampo, TipoTelefone fixo) {
        final String numeroFixo = telefoneFixoCampo.getText().toString();
        return new Telefone(numeroFixo, fixo);
    }

    private void salvaAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
        new SalvaAlunoTask(alunoDAO, aluno, telefoneFixo, telefoneCelular, telefoneDAO, this::finish).execute();

    }

    private void editaAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
        new EditaAlunoTask(
                alunoDAO, aluno,
                telefoneDAO, telefoneFixo, telefoneCelular,
                telefonesDoAluno, this::finish).execute();
    }



    private void inicializacaoDosCampos() {
        nomeCampo = findViewById(R.id.activity_formulario_aluno_nome);
        telefoneFixoCampo = findViewById(R.id.activity_formulario_aluno_telefone_fixo);
        telefoneCelularCampo = findViewById(R.id.activity_formulario_aluno_telefone);

        emailCampo = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void preencheAluno() {
        String nome = nomeCampo.getText().toString();
        String email = emailCampo.getText().toString();

        aluno.setEmail(email);
        aluno.setNome(nome);
    }
}