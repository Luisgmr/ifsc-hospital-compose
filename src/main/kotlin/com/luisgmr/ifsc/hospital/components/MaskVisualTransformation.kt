package com.luisgmr.ifsc.hospital.components

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.absoluteValue

class MaskVisualTransformation(
    private val mask: String,
    private val maxDigits: Int? = null
) : VisualTransformation {

    private val specialSymbolsIndices = mask.indices.filter { mask[it] != '#' }

    // Conta quantos dígitos a máscara comporta
    private val maskDigitCount = mask.count { it == '#' }

    // Define o limite efetivo de dígitos, considerando o maxDigits informado
    private val effectiveMaxDigits = maxDigits?.let { minOf(it, maskDigitCount) } ?: maskDigitCount

    override fun filter(text: AnnotatedString): TransformedText {
        // Limita o texto aos dígitos permitidos
        val digits = text.text.filter { it.isDigit() }.take(effectiveMaxDigits)

        var out = ""
        var maskIndex = 0
        for (char in digits) {
            while (specialSymbolsIndices.contains(maskIndex)) {
                out += mask[maskIndex]
                maskIndex++
            }
            out += char
            maskIndex++
        }
        return TransformedText(AnnotatedString(out), offsetTranslator())
    }

    private fun offsetTranslator() = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            // Garante que não ultrapasse o número máximo de dígitos
            val safeOffset = offset.coerceIn(0, effectiveMaxDigits)

            var numberOfHashtags = 0
            // Máscara parcial correspondente ao número de dígitos solicitados (safeOffset)
            val masked = mask.takeWhile {
                if (it == '#') numberOfHashtags++
                numberOfHashtags < safeOffset
            }

            val transformedOffset = if (safeOffset > 0) masked.length + 1 else 0

            // Garante que não ultrapasse o tamanho real do texto transformado
            // O tamanho máximo do texto transformado é o tamanho da máscara com os dígitos inseridos
            // Mas aqui, 'masked.length + 1' é sempre válido pois 'masked' é parte da máscara.
            // Ainda assim, caso algo saia do previsto, fazemos um clamp com base no comprimento da máscara.
            return transformedOffset.coerceAtMost(mask.length)
        }

        override fun transformedToOriginal(offset: Int): Int {
            // Garante que não ultrapasse o tamanho da máscara
            val safeOffset = offset.coerceIn(0, mask.length)

            val count = mask.take(safeOffset).count { it == '#' }
            // Também garante que não retorne um valor maior que o número máximo de dígitos permitidos
            return count.coerceAtMost(effectiveMaxDigits)
        }
    }
}
