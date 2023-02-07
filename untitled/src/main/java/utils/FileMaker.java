package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileMaker {
    String PATH = "";
    public FileMaker(String PATH){
        this.PATH = PATH;
    }

    public String saveFile(String filename){
        String file = filename + ".txt";
        try{
            FileWriter writer = new FileWriter(PATH + file);
            writer.write("Books:");
        } catch (IOException e){
            e.printStackTrace();
        }
        return file;
    }

    public void addElementToFile(String filename,
                                 String name,
                                 String author,
                                 String rating,
                                 String imgUrl) throws IOException {

        try{
            Path p = Paths.get(PATH + filename);
            List<String> lines = Files.readAllLines(p, StandardCharsets.UTF_8);
            lines.add(0, "Title: %s\nAuthor: %s\nRating: %s\n ".formatted(name, author, rating));
            Files.write(p, lines, StandardCharsets.UTF_8);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
