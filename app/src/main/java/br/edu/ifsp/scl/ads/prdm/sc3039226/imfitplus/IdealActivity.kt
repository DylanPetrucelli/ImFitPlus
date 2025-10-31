package br.edu.ifsp.scl.ads.prdm.sc3039226.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039226.imfitplus.databinding.ActivityIdealBinding
import kotlin.math.abs

class IdealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIdealBinding

    private var altura: Double = 0.0
    private var pesoAtual: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        altura = intent.getDoubleExtra("ALTURA", 0.0)
        pesoAtual = intent.getDoubleExtra("PESO", 0.0)

        val pesoIdeal = 22 * (altura * altura)
        binding.tvPesoIdealValor.text = getString(R.string.formato_peso_kg, pesoIdeal)
        val diferenca = pesoAtual - pesoIdeal
        binding.tvDiferencaPesoValor.text = formatarMensagemDiferenca(diferenca)

        binding.btnFinalizar.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
        }

        binding.btnPesoIdealVoltar.setOnClickListener {
            finish()
        }
    }

    private fun formatarMensagemDiferenca(diferenca: Double): String {
        val diffAbs = abs(diferenca)
        val diffFormatada = String.format(java.util.Locale.getDefault(), "%.1f", diffAbs)

        return when {
            diferenca > 0.5 -> "Você precisa perder $diffFormatada kg"
            diferenca < -0.5 -> "Você precisa ganhar $diffFormatada kg"
            else -> "Você está no seu peso ideal!"
        }
    }


}

