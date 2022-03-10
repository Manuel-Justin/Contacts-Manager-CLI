import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
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
        while (loop) {
            System.out.println("what would you like to do?");
            System.out.print("1. View contacts.\n" +
                    "2. Add a new contact.\n" +
                    "3. Search a contact by name.\n" +
                    "4. Delete an existing contact.\n" +
                    "5. Exit.\n" +
                    "Enter an option (1, 2, 3, 4 or 5): ");
            int input = scanner.nextInt();

            switch (input) {
                case 1: {
                    allContacts();
                    break;
                }
                case 2: {
                    addContact();
                    break;
                }
                case 3: {
                    search();
                    break;
                }
                case 4: {
                    delete();
                    break;
                }
                case 5: {
                    System.out.println("Exiting. Saving contacts.txt");
                    exit();
                    loop = false;
                    break;
                }
                default: {
                    System.out.println("bad input try again");
                    menu();
                }
            }
        }
    }

    public static void checkForContacts() throws IOException {
        Path dir = Paths.get(directory);
        Path cont = Paths.get(directory, contacts);
        if (Files.notExists(dir)) {
            Files.createDirectories(dir);
        }
        if (Files.notExists(cont)) {
            Files.createFile(cont);
        }
    }

    public static void allContacts() {
        if (contats.size() == 0) {
            System.out.println("Your contacts list is empty");
        } else {
            System.out.printf("%-14s | %s\n", "Name", "Phone number");
            System.out.println("--------------------------------");
            for (int i = 0; i < contats.size(); i += 1) {
                String[] contactLine = contats.get(i).split(" ");
                String name = contactLine[0];
                name = name.replace("_"," ");
                String phoneNumber = contactLine[1];
                StringBuilder sb = new StringBuilder(phoneNumber);

                int j = 0;
                for (int o = sb.length() - 1; o >= 0; o--) {
                    j++;
                    if (j == 4) {
                        sb.insert(o, '-');
                    }
                    if (phoneNumber.length() >= 10) {
                        if (j == 7) {
                            sb.insert(o, ")-");
                        }
                        if (j == 10) {
                            sb.insert(o, '(');
                        }
                    }
                    if (phoneNumber.length() >= 11) {
                        if (j == 11) {
                            sb.insert(o+1, "-");
                        }
                    }
                }

                phoneNumber = sb.toString();
                System.out.printf("%s.  %-10s |  %s\n", i + 1, name, phoneNumber);
            }
        }
    }

    public static void addContact() {
        System.out.println("Enter contact's name:");
        scanner.nextLine();
        String name = scanner.nextLine();
        String fullName= name.replace(" ","_");

        boolean done = false;
        for(String contact: contats){
            if(contact.toLowerCase().contains(name.toLowerCase())){
                System.out.printf("%s already exists, would you like to overwrite? Y/N", name);
                String yn= scanner.next();
                if(yn.equalsIgnoreCase("y")){
                    System.out.println("Enter contact's number:");
                    String number = scanner.next();
                    contats.set(contats.indexOf(contact), fullName + " " + number);
                    done=true;
                } else{
                    addContact();
                }
            }
        }
        if(!done){
            System.out.println("Enter contact's number: ");
            String number = scanner.next();
            contats.add(fullName + " " + number);
        }
    }

    public static void search() {
        System.out.println("Name of contact:");
        String input = scanner.next();
        System.out.printf("%-14s | %s\n", "Name", "Phone number");
        System.out.println("--------------------------------");
        for (int i = 0; i < contats.size(); i += 1) {
            if (contats.get(i).contains(input)) {
                String[] contactLine = contats.get(i).split(" ");
                String name = contactLine[0];
                String phoneNumber = contactLine[1];
                System.out.printf("%s.  %-10s |  %s\n", i + 1, name, phoneNumber);
            }
        }
    }

    public static void delete() {
        System.out.println("Name of contact removing:");
        String input = scanner.next();
        int remove = 0;
        for (int i = 0; i < contats.size(); i++) {
            if (contats.get(i).contains(input)) {
                remove = i;
            }
        }
        contats.remove(remove);
    }

    public static void exit() throws IOException {
        Files.write(cont, contats);
    }
}