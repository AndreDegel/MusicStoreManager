package Andre;

public class Main {
    static DatabaseController db = new DatabaseController();

    public static void main(String[] args) {
        try {
            db.connect();
            mainMenu main = new mainMenu();
        }
        catch (Exception ex){
            System.out.println("Could not get a connection to the database. Resolve before continue");
        }
    }
}
