import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.*;


public class TransactionAnalyzer {
    private List<Transaction> transactions;
    private DateTimeFormatter dateFormatter;

    public TransactionAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
        this.dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public double calculateTotalBalance() {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    public int countTransactionsByMonth(String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }
    public List<Transaction> findTopExpenses() {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }
    public void findMaxMinExpenses(LocalDate startDate, LocalDate endDate) {
        double maxExpense = Double.MIN_VALUE;
        double minExpense = Double.MAX_VALUE;
        Transaction maxTransaction = null;
        Transaction minTransaction = null;

        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
            if (date.isAfter(startDate.minusDays(1)) && date.isBefore(endDate.plusDays(1))) {
                double amount = transaction.getAmount();
                if (amount < 0) {
                    if (amount < minExpense) {
                        minExpense = amount;
                        minTransaction = transaction;
                    }
                    if (amount < maxExpense) {
                        maxExpense = amount;
                        maxTransaction = transaction;
                    }
                }
            }
        }

        System.out.println("Найбільша витрата: " + maxTransaction);
        System.out.println("Найменша витрата: " + minTransaction);
    }

    public void createExpenseReport() {
        Map<String, Double> monthlyExpenses = new HashMap<>();
        Map<String, Double> categoryExpenses = new HashMap<>();

        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
            String monthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));

            monthlyExpenses.merge(monthYear, transaction.getAmount(), Double::sum);

            String category = transaction.getCategory();
            categoryExpenses.merge(category, transaction.getAmount(), Double::sum);
        }

        System.out.println("Витрати по місяцях:");
        for (Map.Entry<String, Double> entry : monthlyExpenses.entrySet()) {
            String month = entry.getKey();
            double amount = entry.getValue();
            int barLength = (int) Math.abs(amount / 1000);
            String bar = new String(new char[barLength]).replace("\0", "*");
            System.out.println(month + ": " + bar);
        }

        System.out.println("\nВитрати по категоріях:");
        for (Map.Entry<String, Double> entry : categoryExpenses.entrySet()) {
            String category = entry.getKey();
            double amount = entry.getValue();
            int barLength = (int) Math.abs(amount / 1000);
            String bar = new String(new char[barLength]).replace("\0", "*");
            System.out.println(category + ": " + bar);
        }
    }

}
