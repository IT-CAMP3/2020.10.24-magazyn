package pl.camp.it.magazyn.exception;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws DivideByZeroException {
        //metoda(null);

        double result = divide(3.0, 0.0);


        System.out.println("dziala dalej !!");
    }

    public static void metoda(Object o) {
        try {
            /*List a = new LinkedList();
            a.get(0);*/
            o.toString();
        } catch(NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Zepsulo sie !!");
        }

    }

    public static double divide(double a, double b) throws DivideByZeroException {
        if(b == 0.0) {
            throw new DivideByZeroException();
        }
        return a/b;
    }
}
