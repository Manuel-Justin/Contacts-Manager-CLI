import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu<dir> {
    static Scanner scanner = new Scanner(System.in);
    static String contacts = "contacts.txt";
    static String directory = "./src/";
    static Path cont = Paths.get(directory, contacts);
    static List<String> contats;
    static {
        try {
            contats = Files.readAllLines(cont);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void menu() throws IOException {

        boolean loop = true;
        while(loop){
        System.out.println("what would you like to do?");
        System.out.print("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5): ");
        int input = scanner.nextInt();



                switch (input) {
                    case 1:
                        allContacts();
                        break;
                    case 2:
                        addContact();
                        break;
                    case 3:
                        search();
                        break;
                    case 4:
                        delete();
                        break;
                    case 5:
                        exit();
                        loop = false;
                        break;
                    default:
                        System.out.println("bad input try again");
                        menu();
            }
        }
    }
    public static void checkForContacts() throws IOException {
        Path dir = Paths.get(directory);
        Path cont = Paths.get(directory, contacts);
        if (Files.notExists(dir)) {
            Files.createDirectories(dir);
        }
        if(Files.notExists(cont)){
            Files.createFile(cont);
        }
    }
    public static void allContacts() {

        if(contats.size()==0){
            System.out.println("Your contacts list is empty");
        } else{
        for (int i = 0; i < contats.size(); i += 1) {
            System.out.println((i + 1) + ": " + contats.get(i));
        }}

    }

    public static void addContact() {
        System.out.println("Enter contact's name:");
        String name= scanner.next();
        System.out.println("Enter contact's number:");
        String number= scanner.next();
        contats.add(name + " " + number);

    }

    public static void search() {
        System.out.println("Name of contact:");
        String input = scanner.next();
        for (int i =0; i< contats.size(); i++) {

            if (contats.get(i).contains(input)) {
                System.out.println(contats.get(i));
            }
        }
    }

    public static void delete() {
        System.out.println("Name of contact removing:");
        String input = scanner.next();
        int remove = 0;
        for (int i =0; i< contats.size(); i++) {

            if (contats.get(i).equalsIgnoreCase(input)) {
                remove = i;
            }
        }
        contats.remove(remove);
        
        
    }

    public static void exit() throws IOException {
        Files.write(cont, contats);
    }

}
