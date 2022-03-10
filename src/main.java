import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        try {
            Menu.checkForContacts();
        } catch (IOException e) {
            System.out.println("no file" + e.getMessage());
        }
        Menu.menu();
    }
}
