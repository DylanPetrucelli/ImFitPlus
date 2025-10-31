package br.edu.ifsp.scl.ads.prdm.sc3039226.imfitplus


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039226.imfitplus.databinding.ActivityGastoBinding

class GastoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGastoBinding

    private var idade: Int = 0
    private var sexo: String = ""
    private var altura: Double = 0.0
    private var peso: Double = 0.0
    private var nivelAtividadeIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGastoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        receberDados()

        val alturaCm = altura * 100
        val tmb = calcularTMB(sexo, peso, alturaCm, idade)

        val fatorAtividade = getFatorAtividade(nivelAtividadeIndex)
        val gastoTotal = tmb * fatorAtividade

        binding.tvTmbValor.text = "${String.format("%.0f", tmb)} kcal"
        binding.tvGastoTotalValor.text = "${String.format("%.0f", gastoTotal)} kcal"

        val niveis = resources.getStringArray(R.array.niveis_atividade)
        binding.tvGastoAtividadeLabel.text = "(Nível de Atividade: ${niveis[nivelAtividadeIndex]})"

        binding.btnVerPesoIdeal.setOnClickListener {
            val intent = Intent(this, IdealActivity::class.java).apply {
                putExtra("ALTURA", altura)
                putExtra("PESO", peso)
                putExtra("NOME", intent.getStringExtra("NOME"))
                putExtra("IMC", intent.getDoubleExtra("IMC", 0.0))
                putExtra("CATEGORIA", intent.getStringExtra("CATEGORIA"))
                putExtra("GASTO", gastoTotal)
            }
            startActivity(intent)
        }

        binding.btnGastoVoltar.setOnClickListener {
            finish()
        }
    }

    private fun receberDados() {
        idade = intent.getIntExtra("IDADE", 0)
        sexo = intent.getStringExtra("SEXO") ?: ""
        altura = intent.getDoubleExtra("ALTURA", 0.0)
        peso = intent.getDoubleExtra("PESO", 0.0)
        nivelAtividadeIndex = intent.getIntExtra("NIVEL_ATIVIDADE_INDEX", 0)
    }

    private fun calcularTMB(sexo: String, peso: Double, alturaCm: Double, idade: Int): Double {
        return if (sexo == "M") {
            66 + (13.7 * peso) + (5 * alturaCm) - (6.8 * idade)
        } else {
            655 + (9.6 * peso) + (1.8 * alturaCm) - (4.7 * idade)
        }
    }

    private fun getFatorAtividade(index: Int): Double {
        return when (index) {
            0 -> 1.2
            1 -> 1.375
            2 -> 1.55
            3 -> 1.725
            else -> 1.2
        }
    }
}

