import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class TransactionAnalyzerTest {
    @Test
    public void testCalculateTotalBalance() {
        TransactionCSVReader reader = new TransactionCSVReader();

        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = reader.readTransactions(filePath);

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        double result = analyzer.calculateTotalBalance();

        // Перевірка результату
        // Замініть очікуваний баланс відповідно до ваших тестових даних в CSV-файлі
        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCountTransactionsByMonth() {
        TransactionCSVReader reader = new TransactionCSVReader();

        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = reader.readTransactions(filePath);

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        int countFeb = analyzer.countTransactionsByMonth("02-2023");
        int countMar = analyzer.countTransactionsByMonth("03-2023");


        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }
    public void testGetTop10Expenses() {
        TransactionCSVReader reader = new TransactionCSVReader();
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = reader.readTransactions(filePath);

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        List<Transaction> top10Expenses = analyzer.findTopExpenses();


        Assertions.assertEquals(10, top10Expenses.size(), "Кількість транзакцій у списку має бути 10");


        List<Double> expectedExpenses = Arrays.asList(
                -9100.0, -8800.0, -7850.0, -7500.0, -6000.0, -5500.0, -4500.0, -3300.0, -3200.0, -3000.0
        );

        // Перевірка значень витрат у списку
        for (int i = 0; i < top10Expenses.size(); i++) {
            Assertions.assertEquals(expectedExpenses.get(i), top10Expenses.get(i).getAmount(),
                    "Витрата в списку не відповідає очікуваному значенню");
        }
    }
}
