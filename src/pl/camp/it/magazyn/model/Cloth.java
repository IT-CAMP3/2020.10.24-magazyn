package pl.camp.it.magazyn.model;

public class Cloth extends Product {
    private String length;

    public Cloth(int size, String name, String color, int pieces, String length) {
        super(size, name, color, pieces);
        this.length = length;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Cloth{" +
                super.toString() +
                ", length='" + length + '\'' +
                '}';
    }
}
