package Andre;

public class Main {
    static DatabaseController db = new DatabaseController();

    public static void main(String[] args) {
        mainMenu main = new mainMenu();

        try {
            db.connect();

        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
}
