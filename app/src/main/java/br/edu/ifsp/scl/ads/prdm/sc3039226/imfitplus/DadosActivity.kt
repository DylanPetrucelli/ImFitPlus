package br.edu.ifsp.scl.ads.prdm.sc3039226.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039226.imfitplus.databinding.ActivityDadosBinding

class DadosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDadosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDadosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAvancarImc.setOnClickListener {
            if (validarCampos()) {
                navegarParaImc()
            }
        }
    }

    private fun validarCampos(): Boolean {
        if (binding.etNome.text.isBlank()) {
            Toast.makeText(this, R.string.validacao_erro_nome, Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.etIdade.text.isBlank() || (binding.etIdade.text.toString().toIntOrNull()
                ?: 0) <= 0
        ) {
            Toast.makeText(this, R.string.validacao_erro_idade, Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.rgSexo.checkedRadioButtonId == -1) {
            Toast.makeText(this, R.string.validacao_erro_sexo, Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.etAltura.text.isBlank() || (binding.etAltura.text.toString().toDoubleOrNull()
                ?: 0.0) <= 0
        ) {
            Toast.makeText(this, R.string.validacao_erro_altura, Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.etPeso.text.isBlank() || (binding.etPeso.text.toString().toDoubleOrNull()
                ?: 0.0) <= 0
        ) {
            Toast.makeText(this, R.string.validacao_erro_peso, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun navegarParaImc() {
        val nome = binding.etNome.text.toString()
        val idade = binding.etIdade.text.toString().toInt()
        val altura = binding.etAltura.text.toString().toDouble()
        val peso = binding.etPeso.text.toString().toDouble()

        val sexo = if (binding.rgSexo.checkedRadioButtonId == R.id.rb_masculino) "M" else "F"

        val nivelAtividadeIndex = binding.spinnerAtividade.selectedItemPosition

        val imc = peso / (altura * altura)

        val intent = Intent(this, ImcActivity::class.java).apply {
            putExtra("NOME", nome)
            putExtra("IDADE", idade)
            putExtra("SEXO", sexo)
            putExtra("ALTURA", altura)
            putExtra("PESO", peso)
            putExtra("NIVEL_ATIVIDADE_INDEX", nivelAtividadeIndex)
            putExtra("IMC", imc)
        }

        startActivity(intent)
    }
}

