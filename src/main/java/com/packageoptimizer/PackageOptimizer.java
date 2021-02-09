package com.packageoptimizer;
/**
 * The class reads an input text file where each row contains a package specification and
 * computes for each row the best package in terms of maximum total price and minimum total weight in case of the same total price.
 * See the README.md for the complete requirements.<br>
 * The main processing steps are: <ul>
 * Step 1 it reads the file line by line
 * Step 2 for each line <ul>
 * Step 3 it parses and validates the package specification resulting a set of products with a max total weight
 * Step 4 it finds the best package
 * Step 5 it prints the resulting package on one line
 */
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
public class PackageOptimizer {

	  /**
     * Processes the input file line by line.
     *
     * @param args only one argument: the input file path
     */
    public static void main(String[] args) {
        if (args != null && args.length == 1) {
            try (Stream<String> linesStream = Files.lines(Path.of(args[0]), StandardCharsets.UTF_8)) {
                io.vavr.collection.Stream.ofAll(linesStream)
                    .zipWithIndex()
                    .filter(lineWithNumber -> StringUtils.isNotBlank(lineWithNumber._1()))
                    .forEach(lineWithNumber -> processLine(lineWithNumber._1(), lineWithNumber._2()));
            } catch (IOException e) {
                System.err.println(e.toString());
            }
        } else {
            System.err.println("Invalid command arguments. One argument is required: the input file path.");
        }
    }
  
    /**
     * Processes one text line containing the package specifications and prints the best package.
     *
     * @param line       the package specifications
     * @param lineNumber the number of the line in the original file
     */
    private static void processLine(String line, int lineNumber) {
        try {
            PackageSpecification packageSpecification = new PackageSpecification(line, lineNumber);
            packageSpecification.setFindBestPackageStrategy(PackageSpecification.FindBestPackageStrategy.COMBIOPTIMIZE);
            System.out.println(new OutputLine(packageSpecification.findBestPackage()));
        } catch (PackageSpecificationBaseException e) {
            System.out.println("-"); //no package could be found because of errors
            System.err.println("Line " + lineNumber + " cannot be processed because :" + System.lineSeparator() + e.getMessage());
        }
    }
}
