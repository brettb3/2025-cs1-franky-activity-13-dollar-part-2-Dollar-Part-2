public class MoneyProcessor{
  public static void main(String[] args){
    Dollar[] wallet = null;
        int count = 0;
        
        // First pass to count lines and determine array size
        try (Scanner fileScanner = new Scanner(new File("money.txt"))) {
            while (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: money.txt");
            return;
        }
        
        // Initialize array
        wallet = new Dollar[count];
        
        // Second pass to populate array
        try (Scanner fileScanner = new Scanner(new File("money.txt"))) {
            int index = 0;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] tokens = line.split(",");
                int denomination = Integer.parseInt(tokens[0].trim());
                String face = tokens[1].trim();
                String serialNumber = tokens[2].trim();
                int year = Integer.parseInt(tokens[3].trim());
                
                wallet[index++] = new Dollar(denomination, face, serialNumber, year);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: money.txt");
            return;
        }
        
        // Call the required methods
        System.out.println("***** printBills() *****");
        printBills(wallet);
        
        System.out.println("***** printOnlyDenominations() *****");
        printOnlyDenominations(wallet);
        
        System.out.println("***** getTotalAmountInWallet() *****");
        System.out.println(getTotalAmountInWallet(wallet));
    }
    
    public static void printBills(Dollar[] wallet) {
        for (Dollar bill : wallet) {
            bill.showBill();
        }
    }
    
    public static void printOnlyDenominations(Dollar[] wallet) {
        for (Dollar bill : wallet) {
            System.out.print(bill.getDenomination() + " ");
        }
        System.out.println();
    }
    
    public static int getTotalAmountInWallet(Dollar[] wallet) {
        int total = 0;
        for (Dollar bill : wallet) {
            total += bill.getDenomination();
        }
        return total;
  }
}
