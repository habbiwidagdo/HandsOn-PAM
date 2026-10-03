import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress

// Hands-on 2: Routing & JSON Sederhana
// Tugas: Tambahkan route GET /users yang mengembalikan daftar user
// dalam format JSON (dibangun manual dari List<Pair<String, String>>,
// tanpa library JSON), lengkap dengan header Content-Type yang benar.

data class User(val id: Int, val name: String)

val users = listOf(
    User(1, "Andi"),
    User(2, "Budi"),
    User(3, "Citra")
)

// TODO 1: Lengkapi fungsi ini supaya mengubah List<User> menjadi string JSON array
fun usersToJson(users: List<User>): String {
    // Memanfaatkan fungsi joinToString untuk memformat list menjadi struktur JSON
    return users.joinToString(prefix = "[", postfix = "]") { user ->
        "{\"id\":${user.id},\"name\":\"${user.name}\"}"
    }
}

fun main() {
    val server = HttpServer.create(InetSocketAddress("0.0.0.0", 8080), 0)

    server.createContext("/users") { exchange ->
        // TODO 2: Panggil usersToJson(users), kirim sebagai response body
        val jsonResponse = usersToJson(users)
        val responseBody = jsonResponse.toByteArray()

        // TODO 3: Set header "Content-Type" ke "application/json" SEBELUM sendResponseHeaders
        exchange.responseHeaders.set("Content-Type", "application/json")
        
        // TODO 4: sendResponseHeaders(200, ...) lalu tulis body & tutup stream
        exchange.sendResponseHeaders(200, responseBody.size.toLong())
        exchange.responseBody.use { os ->
            os.write(responseBody)
        }
    }

    server.start()
    println("Server berjalan di http://localhost:8080 — coba: curl http://localhost:8080/users")

    Thread.sleep(10_000)
    server.stop(0)
    println("Server dihentikan")
}