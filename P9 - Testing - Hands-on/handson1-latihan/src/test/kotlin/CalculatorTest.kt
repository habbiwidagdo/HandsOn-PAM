import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

// Hands-on 1: Unit Test Dasar (pola Arrange-Act-Assert)
// Tugas: Lengkapi ketiga test di bawah mengikuti pola AAA:
//   Arrange -> siapkan data/objek yang dibutuhkan
//   Act     -> panggil method yang diuji
//   Assert  -> verifikasi hasilnya dengan assertEquals(expected, actual)

class CalculatorTest {

    // Asumsi: Class Calculator memiliki fungsi add(), subtract(), dan divide()
    private val calculator = Calculator()

    @Test
    @DisplayName("Penjumlahan dua bilangan positif")
    fun testAdd() {
        // Arrange
        val a = 2
        val b = 3
        
        // Act
        val hasil = calculator.add(a, b)
        
        // Assert
        assertEquals(5, hasil, "Hasil 2 + 3 seharusnya 5")
    }

    @Test
    @DisplayName("Pengurangan yang menghasilkan angka negatif")
    fun testSubtractNegativeResult() {
        // Arrange
        val a = 3
        val b = 5
        
        // Act
        val hasil = calculator.subtract(a, b)
        
        // Assert
        assertEquals(-2, hasil, "Hasil 3 - 5 seharusnya -2")
    }

    @Test
    @DisplayName("Pembagian dua bilangan bulat")
    fun testDivide() {
        // Arrange
        val a = 10
        val b = 2
        
        // Act
        val hasil = calculator.divide(a, b)
        
        // Assert
        assertEquals(5, hasil, "Hasil 10 / 2 seharusnya 5")
    }
}