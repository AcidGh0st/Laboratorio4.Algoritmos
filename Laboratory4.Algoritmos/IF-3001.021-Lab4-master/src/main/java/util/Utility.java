package util;

import java.text.DecimalFormat;
import java.util.Random;
import domain.Customer;
import domain.DoublyLinkedList;
import domain.Product;
import domain.SinglyLinkedList;

public class Utility {
    private static SinglyLinkedList customerList;

    //static init
    static {
        customerList = new SinglyLinkedList();
    }

    public static SinglyLinkedList getCustomerList() {
        return customerList;
    }

    public static void setCustomerList(SinglyLinkedList customerList) {
        Utility.customerList = customerList;
    }


    private static DoublyLinkedList productList;

    //static init
    static {
        productList = new DoublyLinkedList();
    }

    public static DoublyLinkedList getProductList() {
        return productList;
    }

    public static void setProductList(DoublyLinkedList productList) {
        Utility.productList = productList;
    }

    public static String format(double value){
        return new DecimalFormat("###,###,###.##").format(value);
    }
    public static String $format(double value){
        return new DecimalFormat("$###,###,###.##").format(value);
    }
    public static String show(int[] a, int size) {
        String result="";
        for (int i = 0; i < size; i++) {
            result+= STR."\{a[i]} ";
        }
        return result;
    }

    public static void fill(int[] a, int bound) {
        for (int i = 0; i < a.length; i++) {
            a[i] = new Random().nextInt(bound);
        }
    }

    public static int getRandom(int bound) {
        return new Random().nextInt(bound)+1;
    }

    public static int compare(Object a, Object b) {
        switch (instanceOf(a, b)){
            case "Integer":
                Integer int1 = (Integer)a; Integer int2 = (Integer)b;
                return int1 < int2 ? -1 : int1 > int2 ? 1 : 0; //0 == equal
            case "String":
                String st1 = (String)a; String st2 = (String)b;
                return st1.compareTo(st2)<0 ? -1 : st1.compareTo(st2) > 0 ? 1 : 0;
            case "Character":
                Character c1 = (Character)a; Character c2 = (Character)b;
                return c1.compareTo(c2)<0 ? -1 : c1.compareTo(c2)>0 ? 1 : 0;
            case "Customer":
                Customer cu1 = (Customer)a; Customer cu2 = (Customer)b;
                //return compare(cu1.getId(), cu2.getId()); //forma 1 correcta
                int idComparisonC = Integer.compare(cu1.getId(), cu2.getId());
                if (idComparisonC != 0) {
                    return idComparisonC; // Si los IDs son diferentes, devolver la comparación de IDs
                } else {
                    // Si los IDs son iguales, comparar los nombres
                    return cu1.getName().compareTo(cu2.getName());
                }
            case "Product":
                Product pr1 = (Product) a;
                Product pr2 = (Product) b;
                int idComparisonP = Integer.compare(pr1.getId(), pr2.getId());
                if (idComparisonP != 0) {
                    return idComparisonP; // Si los IDs son diferentes, devolver la comparación de IDs
                } else {
                    // Si los IDs son iguales, comparar los nombres
                    return pr1.getName().compareTo(pr2.getName());
                }


        }
        return 2; //Unknown
    }

    private static String instanceOf(Object a, Object b) {
        if(a instanceof Integer && b instanceof Integer) return "Integer";
        if(a instanceof String && b instanceof String) return "String";
        if(a instanceof Character && b instanceof Character) return "Character";
        if(a instanceof Customer && b instanceof Customer) return "Customer";
        if(a instanceof Product && b instanceof Product) return "Product";
        return "Unknown";
    }
}
