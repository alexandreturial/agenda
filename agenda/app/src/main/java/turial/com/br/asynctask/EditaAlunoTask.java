package turial.com.br.asynctask;

import java.util.List;

import turial.com.br.database.dao.AlunoDAO;
import turial.com.br.database.dao.TelefoneDAO;
import turial.com.br.model.Aluno;
import turial.com.br.model.Telefone;
import turial.com.br.model.TipoTelefone;

public class EditaAlunoTask extends BaseAlunoComTelefoneTask {

    private final AlunoDAO alunoDAO;
    private final Aluno aluno;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final TelefoneDAO telefoneDAO;
    private final List<Telefone> telefonesDoAluno;

    public EditaAlunoTask(AlunoDAO alunoDAO, Aluno aluno,
                          TelefoneDAO telefoneDAO,
                          Telefone telefoneFixo,
                          Telefone telefoneCelular, List<Telefone> telefonesDoAluno, FinalizadaListener listener) {
        super(listener);
        this.alunoDAO = alunoDAO;
        this.aluno = aluno;
        this.telefoneDAO = telefoneDAO;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.telefonesDoAluno = telefonesDoAluno;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDAO.edita(aluno);
        vinculaAlunoComTelefone(aluno.getId(), telefoneFixo, telefoneCelular);

        atualizaIdsDosTelefones(telefoneFixo, telefoneCelular);
        telefoneDAO.atualiza(telefoneFixo, telefoneCelular);

        return null;
    }


    private void atualizaIdsDosTelefones(Telefone telefoneFixo, Telefone telefoneCelular) {
        for (Telefone telefone : telefonesDoAluno) {
            if(telefone.getTipo() == TipoTelefone.FIXO){
                telefoneFixo.setId(telefone.getId());
            }else{
                telefoneCelular.setId(telefone.getId());
            }
        }
    }

}
