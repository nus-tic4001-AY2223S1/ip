package command;
import task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class provides the ability to load existing task data into the 'TaskList' and save the latest modified 'TaskList' into a file.
 * <br> It also provides some basic function to detect if the existing data is in an incompatible format and allows to throw an exception.
 */
public class Storage {

    protected static int count = 0;
    protected static String Directory = "./data/";
    protected static String FileLocation = "data/duke.txt";
    protected static ArrayList<Task> list = new ArrayList<>();

    /**
     * This method allows to read any existing file contents within the specified filepath and store its contents into 'TaskList'.
     * @param filePath The filepath for the file that the method is trying to read the data from.
     * @throws DukeException Throws an error exception if the data is in an incompatible format.
     * @throws IOException Signals that an I/O exception to some sort has occurred.
     */
    private static void printFileContents(String filePath) throws DukeException , IOException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String current = s.nextLine();
            if (current.contains("[T]")) {
                String description = current.substring(7);
                list.add(new Todo(description));
                if (current.contains("X")) {
                    list.get(count).setStatus(true);
                }
            }
            else if (current.contains("[D]") || current.contains("[E]")) {
                int m = current.indexOf("(");
                int n = current.indexOf(")");
                String description = current.substring(7,m-1);
                String by = current.substring(m+5,n);
                if (current.contains("[D]")) {
                    list.add(new Deadline(description,by));
                }
                else {
                    list.add(new Event(description,by));
                }
                if (current.contains("X")) {
                    list.get(count).setStatus(true);
                }
            }
            else {
                throw new DukeException("Error: Task in existing data is incompatible\n");
            }
            count++;
        }
    }

    /**
     * This method allows to store the latest modified 'TaskList' contents into the specified filepath. If the file or directory isn't available, it creates it automatically.
     * @throws IOException Signals that an I/O exception to some sort has occurred.
     */
    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(FileLocation);
        list = TaskList.UpdatedList();
        for (Task l : list) {
            fw.write(l + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * This main method is to initialize the Storage class to begin the process of reading existing data and loading it into the 'TaskList'.
     */
    public static void main() {
        try {
            Files.createDirectories(Paths.get(Directory));
            printFileContents(FileLocation);
            new TaskList(list, count); //Initialize data with existing file
        } catch (DukeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}