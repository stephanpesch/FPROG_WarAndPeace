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

        if (book.isEmpty() || warTerms.isEmpty() || peaceTerms.isEmpty()) {
            System.err.println("Could not read files, or files are empty");
            return;
        }

        List<Chapter> analyzedBook = analyzeBook(book, warTerms, peaceTerms);

        for (int i = 0; i < analyzedBook.size(); i++) {
            System.out.println("Chapter " + (i + 1) + ": " + analyzedBook.get(i));
        }
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
        String book = readFile(fileName).orElse("");
        String bookWithoutPreamble = book
                .substring(book
                        .indexOf("CHAPTER 1"));
        String bookWithoutSuffix = bookWithoutPreamble
                .substring(0,
                        bookWithoutPreamble
                                .indexOf("*** END OF THE PROJECT GUTENBERG EBOOK, WAR AND PEACE ***")
                );
        String[] chapters = bookWithoutSuffix.split("CHAPTER \\d+");
        return Arrays.stream(chapters).parallel()
                .filter(string -> !"".equals(string))
                .map(string -> Stream.of(string
                                .toLowerCase()
                                .replaceAll("\\p{Punct}+", "")
                                .split("\\s+"))
                        .filter(s -> !"".equals(s))
                        .toList())
                .toList();
    }

    private static List<Chapter> analyzeBook(List<List<String>> book, List<String> warTerms, List<String> peaceTerms) {
        return book.parallelStream()
                .map(chapter ->
                        new Chapter(
                                calculateDensity(chapter, peaceTerms),
                                calculateDensity(chapter, warTerms)
                        )
                )
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
        return IntStream.range(0, list.size()).parallel()
                .mapToObj(i -> new Word(list.get(i), i))
                .filter(x -> of.contains(x.string()))
                .mapToInt(Word::coordinate)
                .toArray();
    }

    static double calculateDensityTrivial(List<String> list, List<String> of) {
        long count = list.parallelStream()
                .filter(of::contains)
                .count();
        return list.size() / (double) count;
    }

    static double calculateDensity(List<String> list, List<String> of) {
        var distancesArray = filterAndCreateCoordinateArray(list, of);
        int length = distancesArray.length;
        return IntStream.range(0, length).parallel()
                .flatMap(i -> IntStream.range(0, length).parallel()
                        .map(j -> Math.abs(distancesArray[i] - distancesArray[j])))
                .average()
                .orElse(0);
    }

    static double calculateDensityFirst(List<String> list, List<String> of) {
        var distancesArray = filterAndCreateCoordinateArray(list, of);
        return IntStream.range(0, distancesArray.length).parallel()
                .mapToDouble(i -> Math.abs(distancesArray[i] - distancesArray[0]))
                .average()
                .orElse(0);
    }

    static double calculateDensityNeighbor(List<String> list, List<String> of) {
        var distancesArray = filterAndCreateCoordinateArray(list, of);
        return IntStream.range(0, distancesArray.length - 1).parallel()
                .mapToDouble(i -> distancesArray[i + 1] - distancesArray[i])
                .average()
                .orElse(0);
    }
}

record Word(String string, int coordinate) {
}

record Chapter(double peaceDensity, double warDensity) {
    @Override
    public String toString() {
        return (peaceDensity < warDensity ? "peace" : "war") + "-related";
    }
}
