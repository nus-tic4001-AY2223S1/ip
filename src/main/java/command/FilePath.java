package command;

import task.DukeException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This class allows the user to modify both the directory and filename for a flexible data source.
 */
public class FilePath {
    public static String Directory = "./data/";
    public static String Filename = "duke.txt";
    public static String FileLocation = Directory + Filename;

    /**
     * This method takes in a user input and understands if it is a yes or no.
     * @return Return yes or no command.
     * @throws DukeException Throw error if command is not recognized.
     */

    public static boolean command() throws DukeException {
        boolean response;
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        if (line.trim().toLowerCase().contains("yes"))     { response = true;}
        else if (line.trim().toLowerCase().contains("no")) { response = false;}
        else {
            throw new DukeException("Error, please enter either 'Yes' or 'No'");
        }
        return response;
    }

    /**
     * This method checks if there is any file within the directory and creates it if it isn't. If files already exist, it asks the user-input to choose which file to initialize.
     * @throws DukeException Throws error if user-input doesn't contain any file names that match within that directory.
     * @throws IOException Error if file cannot be found or created.
     */

    public static void InputFile() throws DukeException, IOException {
        String[] input;
        File f = new File(Directory);
        input = f.list();

        boolean found = false; // Whether user-input matches files existing inside Directory

        if (input == null) {
            System.out.println("Directory exists but no files inside currently. Shall create a file called 'duke.txt");
            Path path = Paths.get(Directory + Filename);
            Files.createFile(path);
        }
        else {
            System.out.println("Please view the list of the files in the current directory\n");
            for (String l : input) {
                System.out.println(l);
            }

            System.out.println("\nPlease type the name of the file including the file extension. E.g. duke.txt\n");

            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            for (String l : input) {
                if (l.equals(line)) {
                    Filename = line;
                    found = true;
                    System.out.println("Thank you for your response");
                }
            }
            if (!(found)) {
                throw new DukeException("Error, please entire a filename that exists within this directory");
            }
        }
    }

    /**
     * This method creates a directory if it previously doesn't exist yet.
     * @return Status of whether directory already exists or has been newly created.
     * @throws IOException Error if directory cannot be found or created.
     */

    public static boolean InputDirectory() throws IOException {
        boolean existing = true;

        System.out.println("Please type the name of the directory. E.g. './data/'\n");

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        File f = new File(line);

        if (!(f.isDirectory())) {
            System.out.println("No existing directory. Creating directory and file named 'duke.txt' inside it.");

            Files.createDirectories(Paths.get(line));
            Path path = Paths.get(line + Filename);
            Files.createFile(path);

            existing = false;
        }

        Directory = line;
        return existing;
    }

    /**
     * This main method initiates discussion with the user on the choice of directory and filename. It then selects that filepath and passes it into the main program for execution.
     * @throws DukeException Error if the user enters incompatible data.
     * @throws IOException Error if file / directory cannot be found or created.
     */

    public static void main() throws DukeException, IOException {
        boolean response;

        //Create existing directory and file if it doesn't exist
        Files.createDirectories(Paths.get(Directory));
        new FileOutputStream(FileLocation, true).close();

        System.out.println("Let me load the existing data for you (if any)\n");
        System.out.println("Please confirm if this current directory is correct.");
        System.out.println(Directory + "\n");
        System.out.println("If it is, please confirm by typing 'Yes'");
        System.out.println("If you want to select a different directory, please confirm by typing 'No'\n");

        response = command();

        if (response) {
            System.out.println("Please confirm if this current file is correct.");
            System.out.println(Filename + "\n");
            System.out.println("If it is, please confirm by typing 'Yes'");
            System.out.println("If you want to select a different file, please confirm by typing 'No'\n");

            response = command();

            if (response) { System.out.println("Thank you for your response");}
            else { InputFile();}
        }
        else {
            if (InputDirectory()) { InputFile();}
        }

        FileLocation = Directory + Filename;
        Storage.main();    //Load existing file data into initial TaskList
    }
}
