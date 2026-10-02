// Hands-on 3: Variance — declaration-site "out"
// Tugas: interface Container<T> di bawah HANYA memproduksi T (fun get(): T),
// tidak pernah mengonsumsinya. Tandai T dengan modifier variance yang tepat
// agar Container<Cat> bisa dianggap sebagai subtipe dari Container<Animal>.

open class Animal(val name: String)
class Cat(name: String) : Animal(name)

// TODO 1: Tambahkan modifier 'out' pada type parameter T di sini
// Karena interface ini HANYA menghasilkan T (covariant / producer)
interface Container<out T> {
    fun get(): T
}

class CatContainer(private val cat: Cat) : Container<Cat> {
    override fun get(): Cat = cat
}

// Baris ini butuh Container<Cat> dianggap sebagai Container<Animal>.
fun printAnimalName(container: Container<Animal>) {
    println("Nama hewan: ${container.get().name}")
}

fun main() {
    val catContainer: Container<Cat> = CatContainer(Cat("Whiskers"))

    // TODO 2: Setelah 'out' ditambahkan, dipanggil tanpa error:
    printAnimalName(catContainer)
}