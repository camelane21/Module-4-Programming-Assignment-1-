import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java Exercise12_20 srcRootDirectory");
            System.exit(1);
        }

        File rootDir = new File(args[0]);
        if (!rootDir.exists() || !rootDir.isDirectory()) {
            System.out.println("Invalid directory: " + args[0]);
            System.exit(2);
        }

        for (int i = 1; i <= 34; i++) {
            File chapterDir = new File(rootDir, "chapter" + i);
            if (chapterDir.exists() && chapterDir.isDirectory()) {
                processDirectory(chapterDir, "chapter" + i);
            }
        }
    }
 public static void processDirectory(File dir, String packageName) throws IOException {
        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                processDirectory(file, packageName);
            } else if (file.getName().endsWith(".java")) {
                removePackageStatement(file, packageName);
            }
        }
    }
     public static void removePackageStatement(File file, String packageName) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            // Skip package statement if it matches exactly
            if (line != null && line.trim().equals("package " + packageName + ";")) {
                line = reader.readLine(); // skip the package line
            }
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        }

        // Write back the updated lines
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (String updatedLine : lines) {
                writer.println(updatedLine);
            }
        }
    }
}