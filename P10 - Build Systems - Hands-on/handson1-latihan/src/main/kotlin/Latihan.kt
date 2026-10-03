import java.util.Properties

// Hands-on 1: Resource & Packaging
// Tugas: Baca file app.properties yang ada di src/main/resources melalui
// classpath (bukan lewat File API biasa), lalu tampilkan isinya.
//
// Ini menunjukkan salah satu tugas utama build system: mem-package resource
// non-kode (properties, gambar, config) ke classpath yang sama dengan
// hasil kompilasi .class, sehingga aplikasi bisa membacanya di runtime
// tanpa peduli lokasi file aslinya di disk.

fun main() {
    // TODO 1: Ambil InputStream untuk "app.properties" lewat classloader.
    //         Gunakan: object {}.javaClass.classLoader.getResourceAsStream("app.properties")
    val inputStream = object {}.javaClass.classLoader.getResourceAsStream("app.properties")

    // Validasi apakah file berhasil ditemukan di classpath
    if (inputStream == null) {
        println("Error: File app.properties tidak ditemukan di classpath!")
        return
    }

    // TODO 2: Load InputStream tersebut ke dalam java.util.Properties
    val properties = Properties()
    
    // Menggunakan fungsi .use { ... } agar InputStream otomatis ditutup (closed)
    // setelah selesai dibaca, mencegah kebocoran memori (memory leak).
    inputStream.use { 
        properties.load(it) 
    }

    // TODO 3: Ambil dan tampilkan nilai "app.name" dan "app.version"
    println("Nama aplikasi: ${properties.getProperty("app.name")}")
    println("Versi: ${properties.getProperty("app.version")}")
}