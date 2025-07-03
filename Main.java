import java.io.*;
import java.util.*;

public class Exercise12_20 {
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
}