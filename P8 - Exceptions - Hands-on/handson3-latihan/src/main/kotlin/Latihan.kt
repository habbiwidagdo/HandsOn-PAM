// Hands-on 3: Kotlin Sugar - try sebagai expression, require(), error(), multi-catch
// Tugas: Buat parser tiket sederhana dari format teks "nama/umur",
// misalnya "Budi/25", menggunakan gaya penanganan error khas Kotlin.

data class Tiket(val nama: String, val umur: Int)

fun parseTiket(input: String): Tiket {
    val bagian = input.split("/")

    // TODO 1: Gunakan require() untuk memastikan bagian.size == 2
    require(bagian.size == 2) { "Format harus 'nama/umur', diterima: $input" }

    val nama = bagian[0]

    // TODO 2: Gunakan try-catch sebagai expression
    val umur: Int = try {
        bagian[1].toInt()
    } catch (e: NumberFormatException) {
        error("Umur tidak valid: ${bagian[1]}")
    }

    // TODO 3: Gunakan require() lagi untuk memastikan umur >= 0 dan nama tidak kosong
    require(nama.isNotEmpty()) { "Nama tidak boleh kosong" }
    require(umur >= 0) { "Umur tidak boleh negatif" }

    return Tiket(nama, umur)
}

fun main() {
    val inputs = listOf("Budi/25", "Siti/17", "format-salah", "Andi/bukan-angka", "/30")

    for (input in inputs) {
        // TODO 4: Panggil parseTiket(input) di dalam try-catch dengan multi-catch
        try {
            val tiket = parseTiket(input)
            println("Berhasil parsing: $tiket")
        } catch (e: IllegalArgumentException) {
            // Ditangkap dari exception yang dilempar oleh require()
            println("Error Format/Validasi: ${e.message}")
        } catch (e: IllegalStateException) {
            // Ditangkap dari exception yang dilempar oleh error()
            println("Error Konversi Tipe Data: ${e.message}")
        }
    }
}