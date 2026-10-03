import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

// Hands-on 2: Testing Exception
// Tugas: Lengkapi test untuk BankAccount, termasuk kasus withdraw() yang
// melempar InsufficientFundsException saat saldo tidak cukup.

class BankAccountTest {

    @Test
    @DisplayName("Deposit menambah saldo dengan benar")
    fun testDeposit() {
        // Arrange: buat BankAccount dengan saldo awal 100.0
        val account = BankAccount(100.0)
        
        // Act: panggil deposit(50.0)
        account.deposit(50.0)
        
        // Assert: assertEquals(150.0, account.balance)
        assertEquals(150.0, account.balance, "Saldo setelah deposit 50.0 harus 150.0")
    }

    @Test
    @DisplayName("Withdraw mengurangi saldo saat saldo cukup")
    fun testWithdrawSufficientBalance() {
        // Arrange: saldo awal 100.0
        val account = BankAccount(100.0)
        
        // Act: withdraw 40.0
        account.withdraw(40.0)
        
        // Assert: hasil akhir harus 60.0
        assertEquals(60.0, account.balance, "Saldo setelah penarikan 40.0 harus 60.0")
    }

    @Test
    @DisplayName("Withdraw melempar InsufficientFundsException saat saldo tidak cukup")
    fun testWithdrawInsufficientBalance() {
        // Arrange: buat BankAccount dengan saldo awal 50.0
        val account = BankAccount(50.0)
        
        // Act + Assert: gunakan assertThrows untuk memastikan exception yang tepat dilempar
        assertThrows<InsufficientFundsException> {
            account.withdraw(100.0)
        }
    }
}