import java.util.List;

public class SearchByCategory {
    public static void searchByCategory(List<Product> products, String category) {
        for (Product product : products) {
            if (product.getCategory().getName().equalsIgnoreCase(category)) {
                System.out.println(product);
                return;
            }
        }
        System.out.println("Товар у категорії \"" + category + "\" не знайдено.");
    }
}