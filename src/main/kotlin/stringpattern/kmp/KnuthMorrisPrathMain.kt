package stringpattern.kmp

import java.util.*

fun main() {
    val text = "Jalan Raya Margonda 25, Desa/Dusun/Kelurahan Pancoran Mas, Kecamatan Depok, Jawa Barat 16433 Pancoran Mas Depok Indonesia"
    val pattern = "Depok"
    println(kmpSearch(pattern, text))
}

fun kmpSearch(pattern: String, text: String): List<Int>{
    val result = mutableListOf<Int>()
    val textLength = text.length
    val patternLength = pattern.length
    val lps = IntArray(patternLength)

    // membuat longest prefix suffix
    lpsArray(pattern, patternLength, lps)

    var i = 0
    var j = 0

    while(i < textLength){
        if(text[i] == pattern[j]){
            i += 1
            j += 1
        }else{
            if(j != 0){
                j = lps[j - 1]
            }else{
                i += 1
            }
        }

        if(j == patternLength){
            println("Found pattern at i($i) and j($j) index ${(i - j)}");
            val indexFound = i - j
            result.add(indexFound)
            j = lps[j - 1];
        }
    }

    return result
}

fun lpsArray(pattern: String, patternLength: Int, lps: IntArray) {
    var len = 0
    var i = 1

    while (i < patternLength){
        if(pattern[i] == pattern[len]){
            lps[i] = len + 1
            len += 1
            i += 1
        }else{
            if(len != 0){
                len = lps[len - 1]
            }else{
                lps[i] = len
                i++
            }
        }
    }
}
