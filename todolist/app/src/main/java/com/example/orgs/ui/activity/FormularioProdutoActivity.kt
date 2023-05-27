package com.example.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.orgs.R
import com.example.orgs.dao.ProdutosDao
import com.example.orgs.models.Produtos
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cofiguraBotaoSalvar()

    }

    private fun cofiguraBotaoSalvar() {
        val botaoSalvar = findViewById<Button>(R.id.produto_item_botao)
        val dao = ProdutosDao()

        botaoSalvar.setOnClickListener {
            val produtos = criaProduto()
            dao.adiciona(produtos)
            finish()
        }
    }

    private fun criaProduto(): Produtos {
        val campoNome = findViewById<EditText>(R.id.produto_item_nome)
        val nome = campoNome.text.toString()

        val campoDescricao = findViewById<EditText>(R.id.produto_item_descricao)
        val descricao = campoDescricao.text.toString()

        val campoValor = findViewById<EditText>(R.id.produto_item_valor)
        val valorEmTexto = campoValor.text.toString()

        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produtos(
            nome = nome,
            descricao = descricao,
            valor = valor
        )
    }


}