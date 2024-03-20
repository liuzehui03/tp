package seedu.bookbuddy;

public class Ui {
    public static void printWelcome() {
        String logo =
                " ____    ____  \n"
                        + "|    \\  |    \\ \n"
                        + "| |_) / | |_) / \n"
                        + "| |_) \\ | |_) \\ \n"
                        + "|____/  |____/ \n";
        printLine();
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! We are BookBuddy!");
        System.out.println("How can I help you today?");
        printShortLine();
    }
    public static void printLine() {
        System.out.println("________________________________________");
    }
    public static void printShortLine() {
        System.out.println("_____________");
    }
    public static void printExitMessage() {
        System.out.println("Thank you for using BookBuddy! Hope to see you again keke :)");
    }

    /*public static String printInvalidCommand() {
        String message = "The add command requires a book title.";
        System.out.println(message);
        return message;
    } */
    public static void addBookMessage(String title) {
        System.out.println("okii added [" + title + "] to the list.");
        System.out.println("remember to read it soon....");
    }
    public static void removeBookMessage(int index) {
        System.out.println("alright.. i've removed " + BookList.books.get(index).getTitle() + " from the list.");
    }


}