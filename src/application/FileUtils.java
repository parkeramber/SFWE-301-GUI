package application;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileUtils {
    public static String readFileContent(String filePath) throws IOException {
        // Open the file and read its content
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        // Concatenate the lines to form the file content
        StringBuilder contentBuilder = new StringBuilder();
        for (String line : lines) {
            contentBuilder.append(line).append("\n");
        }

        return contentBuilder.toString();
    }

    public static void writeFileContent(String filePath, String content) throws IOException {
        // Write the content to the file, creating the file if it doesn't exist
        Files.write(Paths.get(filePath), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
