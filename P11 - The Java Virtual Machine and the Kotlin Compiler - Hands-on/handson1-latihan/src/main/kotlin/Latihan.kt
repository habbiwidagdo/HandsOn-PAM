// Hands-on 1: Inline Function & Reified Generics
//
// Konteks: JVM menerapkan TYPE ERASURE pada generics — informasi tipe generik
// (mis. List<String> vs List<Int>) DIHAPUS saat dikompilasi menjadi bytecode
// .class, sehingga pada saat runtime JVM hanya melihat "List" biasa. Karena
// itu, fungsi generik biasa TIDAK BISA melakukan pengecekan `value is T`.
//
// Kotlin punya solusi: kombinasi `inline` + `reified`. Fungsi inline "disalin"
// (di-inline) langsung ke tempat pemanggilannya oleh compiler backend Kotlin
// SEBELUM menjadi bytecode, sehingga tipe T yang konkret (mis. String) ikut
// tersalin dan tidak pernah "dihapus" oleh JVM.
//
// Tugas: Lengkapi fungsi isType() di bawah agar bisa mengecek tipe value pada
// runtime.

// TODO 1 & 2: Tambahkan modifier `inline` dan ubah <T> menjadi <reified T>
inline fun <reified T> isType(value: Any): Boolean {
    // TODO 3: Ganti dengan return value is T
    return value is T
}

fun main() {
    println(isType<String>("Hello"))   // harus mencetak: true
    println(isType<Int>("Hello"))      // harus mencetak: false
    println(isType<String>(123))       // harus mencetak: false

    // Pertanyaan diskusi (jawab di komentar):
    // Kenapa `fun <T> isType(value: Any): Boolean = value is T` TIDAK BISA
    // dikompilasi tanpa `inline` + `reified`? Jelaskan hubungannya dengan
    // type erasure pada JVM bytecode.
    //
    // JAWABAN:
    // Karena JVM menerapkan "Type Erasure" saat proses kompilasi ke bytecode 
    // demi kompatibilitas mundur (backward compatibility) dengan versi Java lama. 
    // Ini berarti semua informasi tentang parameter tipe generik `<T>` akan 
    // dihilangkan. Pada saat runtime, JVM tidak tahu apa itu `T`, sehingga 
    // operasi `is T` tidak mungkin dievaluasi.
    // 
    // Dengan kombinasi `inline` dan `reified`, Kotlin tidak memanggil fungsi 
    // secara normal, melainkan menyalin (copy-paste) isi fungsi ke setiap 
    // tempat pemanggilannya pada tahap kompilasi. Karena kode disalin ke 
    // tempat pemanggil, compiler Kotlin bisa menukar tipe generik `T` dengan 
    // tipe konkretnya (misal, mengubah `value is T` langsung menjadi 
    // `value is String`). Akibatnya, JVM di akhir bisa mengeksekusi instruksinya.
}