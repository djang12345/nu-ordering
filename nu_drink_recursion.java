import java.util.Scanner;

public class NuDrinkOrderingSystem {
    static Scanner scanner = new Scanner(System.in);
    
    // Recursive function to get valid order
    public static char getOrder() {
        System.out.print("Enter Order[A/a]: ");
        String input = scanner.nextLine().trim();
        
        // Validate input - must be single character
        if (input.length() != 1) {
            System.out.println("Invalid Input");
            return getOrder(); // Recursion
        }
        
        char order = input.toUpperCase().charAt(0);
        
        // Validate if it's A, B, or C
        if (order != 'A' && order != 'B' && order != 'C') {
            System.out.println("Invalid Input");
            return getOrder(); // Recursion
        }
        
        return order;
    }
    
    // Recursive function to get valid quantity
    public static int getQuantity() {
        System.out.print("Enter Quantity: ");
        String input = scanner.nextLine().trim();
        
        try {
            int quantity = Integer.parseInt(input);
            
            // Validate quantity (must be greater than 0)
            if (quantity <= 0) {
                System.out.println("Enter Valid Quantity");
                return getQuantity(); // Recursion
            }
            
            return quantity;
        } catch (NumberFormatException e) {
            // Input is not a valid number
            System.out.println("Enter Valid Quantity");
            return getQuantity(); // Recursion
        }
    }
    
    public static void main(String[] args) {
        String drinkName = "";
        int price = 0;
        
        // Display menu
        System.out.println("NU-DRINK MENU");
        System.out.println("[A] - Milktea");
        System.out.println("[B] - Coffee");
        System.out.println("[C] - Fruit Juice");
        
        // Get valid order using recursion
        char order = getOrder();
        
        // Get valid quantity using recursion
        int quantity = getQuantity();
        
        // Determine drink name and price based on order
        switch(order) {
            case 'A':
                drinkName = "Milktea";
                price = 100;
                break;
            case 'B':
                drinkName = "Coffee";
                price = 105;
                break;
            case 'C':
                drinkName = "Fruit Juice";
                price = 56;
                break;
        }
        
        // Calculate total
        int total = price * quantity;
        
        // Display receipt
        System.out.println();
        System.out.println("RECEIPT");
        System.out.println(quantity + "pc/pcs " + drinkName + " " + price + "php each.");
        System.out.println("Total: " + total);
    }
}