package pl.camp.it.magazyn.gui;

import pl.camp.it.magazyn.database.ProductRepository;
import pl.camp.it.magazyn.model.Product;

import java.util.List;
import java.util.Scanner;

public class GUI {

    private static Scanner scanner = new Scanner(System.in);

    public static void showMainMenu() {
        System.out.println("1. Dodaj produkt");
        System.out.println("2. Wydaj produkt");
        System.out.println("3. Wyswietl produkty");
        System.out.println("4. Wyjscie");

        String choose = scanner.nextLine();

        switch (choose) {
            case "1":
                break;
            case "2":
                deliverProduct();
                showMainMenu();
                break;
            case "3":
                showAllProducts();
                showMainMenu();
                break;
            case "4":
                System.exit(0);
                default:
                    System.out.println("Nieprawidlowy wybor !!");
                    showMainMenu();
                    break;
        }
    }

    private static void showAllProducts() {
        ProductRepository baza = ProductRepository.getInstance();
        List<Product> products = baza.getAllProducts();
        for(Product currentProduct : products) {
            System.out.println(currentProduct);
        }
    }

    private static void deliverProduct() {
        System.out.println("Podaj produkt:");
        String productToDeliver = scanner.nextLine();
        System.out.println("Podaj ilos sztuk:");
        int piecesToDeliver = Integer.parseInt(scanner.nextLine());
        boolean success = ProductRepository.getInstance().deliverProduct(productToDeliver, piecesToDeliver);
        if(success) {
            System.out.println("Wydano produkt !!");
        } else {
            System.out.println("Nie udalo nie wydac produktu !!");
        }
    }
}
