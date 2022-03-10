import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        try {
            Menu.checkForContacts();
        } catch (IOException e) {
            System.out.println("no file" + e.getMessage());
        }


        Menu.menu();
//    show all your contacts
//    Add a new contact
//    Search a contact by her name
//    Delete an existing contact
// files.get to creat a new txt file if none exists
        // access file with advanced for loop to read each contact name and number

        // Load all of the contacts by calling a method that returns a List of Contact objects.
        //Call a method that shows the user the main menu and returns their choice of action.
        //Using the user's choice from the previous step, call a method that performs that action (modifying the contents of the List of Contact objects if applicable).
        //Keep calling the method from step two until the user chooses to exit.
        //Once the user chooses to exit, re-write the contents of the contacts.txt file using the List of Contact objects.

    }
}
