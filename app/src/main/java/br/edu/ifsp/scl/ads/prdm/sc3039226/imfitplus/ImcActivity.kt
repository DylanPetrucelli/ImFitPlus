package br.edu.ifsp.scl.ads.prdm.sc3039226.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039226.imfitplus.databinding.ActivityImcBinding

class ImcActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImcBinding

    private var nome: String = ""
    private var idade: Int = 0
    private var sexo: String = ""
    private var altura: Double = 0.0
    private var peso: Double = 0.0
    private var nivelAtividadeIndex: Int = 0
    private var imc: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        receberDados()

        binding.tvImcNome.text = getString(R.string.label_ola_nome, nome)
        binding.tvImcValor.text = String.format(java.util.Locale.getDefault(), "%.2f", imc)

        val categoria = getCategoriaImc(imc)
        binding.tvImcCategoria.text = categoria

        binding.btnGastoCalorico.setOnClickListener {
            val intent = Intent(this, GastoActivity::class.java).apply {
                putExtra("IDADE", idade)
                putExtra("SEXO", sexo)
                putExtra("ALTURA", altura)
                putExtra("PESO", peso)
                putExtra("NIVEL_ATIVIDADE_INDEX", nivelAtividadeIndex)
            }
            startActivity(intent)
        }

        binding.btnImcVoltar.setOnClickListener {
            finish()
        }
    }

    private fun receberDados() {
        nome = intent.getStringExtra("NOME") ?: ""
        idade = intent.getIntExtra("IDADE", 0)
        sexo = intent.getStringExtra("SEXO") ?: ""
        altura = intent.getDoubleExtra("ALTURA", 0.0)
        peso = intent.getDoubleExtra("PESO", 0.0)
        nivelAtividadeIndex = intent.getIntExtra("NIVEL_ATIVIDADE_INDEX", 0)
        imc = intent.getDoubleExtra("IMC", 0.0)
    }

    private fun getCategoriaImc(imc: Double): String {
        return when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25.0 -> "Normal"
            imc < 30.0 -> "Acima do peso"
            else -> "Obesidade"
        }
    }
}


