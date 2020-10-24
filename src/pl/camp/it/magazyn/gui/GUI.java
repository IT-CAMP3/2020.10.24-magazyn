package pl.camp.it.magazyn.gui;

import pl.camp.it.magazyn.database.ProductRepository;
import pl.camp.it.magazyn.model.Cloth;
import pl.camp.it.magazyn.model.Jewellery;
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

        switch (scanner.nextLine()) {
            case "1":
                addProduct();
                showMainMenu();
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
        List<Product> products = ProductRepository.getInstance().getAllProducts();
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

    private static void addProduct() {
        System.out.println("Podaj nazwe produktu:");
        String productName = scanner.nextLine();
        Product productFromDatabase = ProductRepository.getInstance().findProduct(productName);
        if(productFromDatabase != null) {
            try {
                System.out.println("Podaj liosc:");
                int productPieces = Integer.parseInt(scanner.nextLine());
                productFromDatabase.setPieces(productPieces + productFromDatabase.getPieces());
                System.out.println("Dodano produkt !!");
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidlowa liczba !!");
                addProduct();
            }
        } else {
            addNewProduct(productName);
        }
    }

    private static void addNewProduct(String productName) {
        System.out.println("1. Cloth");
        System.out.println("2. Jewellery");
        DataWrapper dataWrapper;
        switch (scanner.nextLine()) {
            case "1":
                dataWrapper = readCommonData();
                System.out.println("Podaj dlugosc:");
                String length = scanner.nextLine();
                Cloth cloth = new Cloth(dataWrapper.size, productName, dataWrapper.color, dataWrapper.pieces, length);
                ProductRepository.getInstance().addProductToDatabase(cloth);
                System.out.println("Produkt dodany !!");
                break;
            case "2":
                dataWrapper = readCommonData();
                System.out.println("Podaj typ:");
                String type = scanner.nextLine();
                Jewellery jewellery = new Jewellery(dataWrapper.size, productName, dataWrapper.color, dataWrapper.pieces, type);
                ProductRepository.getInstance().addProductToDatabase(jewellery);
                System.out.println("Produkt dodany !!");
                break;
            default:
                System.out.println("Nieprawidlowy wybor !!");
                addNewProduct(productName);
                break;
        }
    }

    private static DataWrapper readCommonData() {
        try {
            System.out.println("Podaj wielkosc:");
            int size = Integer.parseInt(scanner.nextLine());
            System.out.println("Podaj kolor:");
            String color = scanner.nextLine();
            System.out.println("Podaj ilosc sztuk:");
            int pieces = Integer.parseInt(scanner.nextLine());

            return new DataWrapper(size, color, pieces);
        } catch (NumberFormatException e) {
            System.out.println("Niepoeprawna liczba !");
            return readCommonData();
        }
    }

    static class DataWrapper {
        int size;
        String color;
        int pieces;

        DataWrapper(int size, String color, int pieces) {
            this.size = size;
            this.color = color;
            this.pieces = pieces;
        }
    }
}
