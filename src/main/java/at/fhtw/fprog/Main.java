package at.fhtw.fprog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        String bookPath = "in/war_and_peace.txt";
        String warTermsPath = "in/war_terms.txt";
        String peaceTermsPath = "in/peace_terms.txt";
        if (args.length == 3) {
            bookPath = args[0];
            warTermsPath = args[1];
            peaceTermsPath = args[2];

        }
        List<List<String>> book = readBook(bookPath);
        List<String> warTerms = readTerms(warTermsPath);
        List<String> peaceTerms = readTerms(peaceTermsPath);

        if (book.isEmpty() || warTerms.isEmpty() || peaceTerms.isEmpty())
            System.out.println("Could not read files, or files are empty");

        // List<String> tokenizedBook = tokenizeBook(book);
        // List<List<String>> splitBook = splitIntoChapters(tokenizedBook);

        // double peaceDensity = calculateDensity(tokenizedBook, peaceTerms);
        // double warDensity = calculateDensity(tokenizedBook, warTerms);

        // Chapter chapter = new Chapter(1, peaceDensity, warDensity);
        // System.out.println(chapter);

        // tokenizedBook.forEach(System.out::println);
    }

    static Optional<String> readFile(String fileName) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
            return Optional.of(contentBuilder.toString());
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    static List<String> readTerms(String fileName) {
        Optional<String> termsOptional = readFile(fileName);
        return List.of(
                termsOptional
                        .map(s -> s.split("\\s+"))
                        .orElseGet(() -> new String[]{})
        );
    }

    static List<List<String>> readBook(String fileName) {
        Optional<String> bookOptional = readFile(fileName);
        String book = bookOptional.orElse("");
        int indexChapter1 = book.indexOf("CHAPTER 1");
        String bookWithoutPreamble = book.substring(indexChapter1);
        int indexEnd = bookWithoutPreamble.indexOf("*** END OF THE PROJECT GUTENBERG EBOOK, WAR AND PEACE ***");
        String bookWithoutSuffix = bookWithoutPreamble.substring(0, indexEnd);

        String[] chapters = bookWithoutSuffix.split("CHAPTER \\d+");
        return Arrays.stream(chapters)
                .filter(string -> !"".equals(string))
                .map(string -> Stream.of(string
                    .toLowerCase()
                    .replaceAll("\\p{Punct}+", "")
                    .split("\\s+"))
                        .filter(s -> !"".equals(s))
                        .toList())
                .toList();
    }

    private static List<String> filterListByList(List<String> toFilter, List<String> filter) {
        return toFilter.parallelStream()
                .filter(filter::contains)
                .toList();
    }

    private static long countOccurrences(List<String> list, List<String> of) {
        return list.parallelStream()
                .filter(of::contains)
                .count();
    }

    private static int[] filterAndCreateCoordinateArray(List<String> list, List<String> of) {
        return IntStream.range(0, list.size())
                .mapToObj(i -> new Word(list.get(i), i))
                .filter(x -> of.contains(x.string()))
                .mapToInt(Word::coordinate)
                .toArray();
    }


    static double calculateDensity(List<String> list, List<String> of) {
        var distancesArray = filterAndCreateCoordinateArray(list, of);
        return IntStream.range(0, distancesArray.length - 1)
                .mapToDouble(i -> distancesArray[i] - distancesArray[0])
                .average()
                .orElse(0);
    }
}

record Word(String string, int coordinate) {
}

record Chapter(int number, double peaceDensity, double warDensity) {
    @Override
    public String toString() {
        return "Chapter " + number + ": " + (peaceDensity > warDensity ? "peace" : "war") + "-related";
    }
}
