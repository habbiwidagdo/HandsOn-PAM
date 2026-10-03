import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.findAnnotation

// Hands-on 3: Custom Annotation + Reflection Validator
// Tugas: Buat annotation class @Required, lalu fungsi validate(obj) yang
// mengecek semua property beranotasi @Required tidak boleh null atau
// String kosong/blank. Kembalikan daftar NAMA FIELD yang gagal validasi.

// TODO 1: Buat annotation class bernama Required.
//         Jangan lupa @Target dan @Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Required

data class RegistrasiForm(
    @Required val nama: String,
    @Required val email: String,
    val alamat: String? // opsional, tidak wajib diisi
)

fun validate(obj: Any): List<String> {
    val gagal = mutableListOf<String>()

    // TODO 2: Iterasi memberProperties dari obj::class
    for (prop in obj::class.memberProperties) {
        
        // TODO 3: Cek apakah punya annotation @Required
        //         Menggunakan findAnnotation<Required>() dari kotlin-reflect
        if (prop.findAnnotation<Required>() != null) {
            
            // TODO 4: Ambil nilainya dengan property.getter.call(obj)
            val value = prop.getter.call(obj)
            
            // TODO 5: Jika nilai null ATAU (String dan blank), tambahkan property.name ke `gagal`
            if (value == null || (value is String && value.isBlank())) {
                gagal.add(prop.name)
            }
        }
    }

    return gagal
}

fun main() {
    val formValid = RegistrasiForm("Siti", "siti@mail.com", null)
    println("Form valid -> gagal: ${validate(formValid)}") // gagal: []

    val formInvalid = RegistrasiForm("", "budi@mail.com", "Jl. Merdeka")
    println("Form invalid -> gagal: ${validate(formInvalid)}") // gagal: [nama]
}