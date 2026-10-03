import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

// Hands-on 1: Race Condition
// Tugas: Dua thread meng-increment sebuah shared counter (var c) sebanyak
// 100_000x masing-masing secara BERSAMAAN. Karena c++ bukan operasi atomik
// (baca-ubah-tulis, bisa saling menyela/interleave antar thread), hasil akhirnya
// SERING SALAH (bukan 200_000) — itulah race condition.

class Counter {
    // Mengganti tipe data menjadi AtomicInteger
    private val c = AtomicInteger(0)

    fun increment() {
        // TODO 1: Bungkus baris di bawah supaya thread-safe
        // incrementAndGet() adalah operasi atomik (baca-ubah-tulis dalam satu langkah aman)
        c.incrementAndGet()
    }

    fun value(): Int {
        // TODO 2: Jika kamu memakai AtomicInteger, sesuaikan cara membaca nilainya di sini
        return c.get()
    }
}

fun main() {
    val counter = Counter()
    val iterasi = 100_000

    val t1 = thread {
        repeat(iterasi) { counter.increment() }
    }
    val t2 = thread {
        repeat(iterasi) { counter.increment() }
    }

    t1.join()
    t2.join()

    println("Hasil akhir: ${counter.value()} (seharusnya ${iterasi * 2})")
}