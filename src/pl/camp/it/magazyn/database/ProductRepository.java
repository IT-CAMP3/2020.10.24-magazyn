package pl.camp.it.magazyn.database;

import pl.camp.it.magazyn.model.Cloth;
import pl.camp.it.magazyn.model.Jewellery;
import pl.camp.it.magazyn.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products = new ArrayList<>();
    private static final ProductRepository productRepository = new ProductRepository();

    private ProductRepository() {
        this.products.add(new Cloth(40, "Sukienka", "Zielony", 10, "Medium"));
        this.products.add(new Cloth(38, "Spodnica", "Czerwony", 5, "Medium"));
        this.products.add(new Jewellery(18, "Pierscien z diamentem", "zloty", 2, "Pierscien"));
        this.products.add(new Jewellery(20, "Naszyjnik z rubinem", "srebrny", 3, "Naszyjnik"));
    }

    public static ProductRepository getInstance() {
        return productRepository;
    }

    public List<Product> getAllProducts() {
        return this.products;
    }

    public boolean deliverProduct(String productName, int pieces) {
        for(Product currentProduct : this.products) {
            if(currentProduct.getName().equals(productName) && currentProduct.getPieces() >= pieces) {
                currentProduct.setPieces(currentProduct.getPieces() - pieces);
                return true;
            }
        }
        return false;
    }

    public Product findProduct(String productName) {
        for(Product currentProduct : this.products) {
            if(currentProduct.getName().equals(productName)) {
                return currentProduct;
            }
        }
        return null;
    }

    public void addProductToDatabase(Product product) {
        this.products.add(product);
    }
}
