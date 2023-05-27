package turial.com.dev.ui.activity;

import static turial.com.dev.ui.activity.PacoteActivityConstantes.CHAVE_PACOTE;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import turial.com.dev.R;
import turial.com.dev.model.Pacote;
import turial.com.dev.ui.adapter.ListaPacotesAdapter;
import turial.com.dev.dao.PacoteDAO;
import turial.com.dev.util.ActionBarStyle;

public class ListaPacotesActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Pacotes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacotes);
        setTitle(TITULO_APPBAR);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ActionBarStyle.setActionBarStyle(actionBar);

        configuraListaPacotes();

    }


    private void configuraListaPacotes() {
        ListView listaPacotes = findViewById(R.id.lista_pacotes_list_view);

        final List<Pacote> pacotes = new PacoteDAO().lista();
        listaPacotes.setAdapter(new ListaPacotesAdapter(pacotes, this));
        listaPacotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final Pacote pacoteSelecionado = pacotes.get(position);
                vaiParaResumoPacote(pacoteSelecionado);
            }
        });
    }

    private void vaiParaResumoPacote(Pacote pacoteSelecionado) {
        final Intent intent = new Intent(ListaPacotesActivity.this, ResumoPacoteActivity.class);
        intent.putExtra(CHAVE_PACOTE, pacoteSelecionado);
        startActivity(intent);
    }
}