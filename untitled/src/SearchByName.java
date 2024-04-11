import java.util.List;

public class SearchByName {
    public static void searchByName(List<Product> products, String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                System.out.println(product);
                return;
            }
        }
        System.out.println("Товар з назвою \"" + name + "\" не знайдено.");
    }
}