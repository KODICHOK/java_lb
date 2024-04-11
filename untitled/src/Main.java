import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static Cart cart = new Cart();
    static List<Order> orders = new ArrayList<>();
    static List<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        products.add(new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics));
        products.add(new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones));
        products.add(new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories));

        while (true) {
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Переглянути кошик");
            System.out.println("4 - Зробити замовлення");
            System.out.println("5 - Видалення з кошику");
            System.out.println("6 - Пошук товару");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;
                case 2:
                    System.out.println("Введіть ID товару для додавання до кошика:");
                    int id = scanner.nextInt();
                    for (Product product : products) {
                        if (product.getId() == id) {
                            cart.addProduct(product);
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.println(cart);
                    break;
                case 4:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        System.out.println("Замовлення оформлено:");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;
                case 5:
                    System.out.println("Введіть ID товару для видалення з кошика:");
                    int removeId = scanner.nextInt();
                    for (Product product : cart.getProducts()) {
                        if (product.getId() == removeId) {
                            cart.removeProduct(product);
                            System.out.println("Товар видалено з кошика.");
                            break;
                        }
                    }
                    break;
                case 6:
                    System.out.println("Оберіть тип пошуку:");
                    System.out.println("1 - Пошук за назвою");
                    System.out.println("2 - Пошук за категорією");
                    int searchChoice = scanner.nextInt();
                    switch (searchChoice) {
                        case 1:
                            System.out.println("Введіть назву товару для пошуку:");
                            String productName = scanner.next();
                            SearchByName.searchByName(products, productName);
                            break;
                        case 2:
                            System.out.println("Введіть категорію для пошуку:");
                            String category = scanner.next();
                            SearchByCategory.searchByCategory(products, category);
                            break;
                        default:
                            System.out.println("Невідомий тип пошуку. Спробуйте ще раз.");
                            break;
                    }
                    break;
                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;
                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }

        }

    }
}

