package at.fhtw.fprog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
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
        long readBookStart = System.nanoTime();
        List<List<String>> book = readBook(bookPath);
        long readBookTime = System.nanoTime() - readBookStart;

        long readWarTermsStart = System.nanoTime();
        Set<String> warTerms = readTerms(warTermsPath);
        long readWarTermsTime = System.nanoTime() - readWarTermsStart;

        long readPeaceTermsStart = System.nanoTime();
        Set<String> peaceTerms = readTerms(peaceTermsPath);
        long readPeaceTermsTime = System.nanoTime() - readPeaceTermsStart;

        if (book.isEmpty() || warTerms.isEmpty() || peaceTerms.isEmpty()) {
            System.err.println("Could not read files, or files are empty");
            return;
        }

        long analyzeBookStart = System.nanoTime();
        List<Chapter> analyzedBook = analyzeBook(book, warTerms, peaceTerms);
        long analyzeBookTime = System.nanoTime() - analyzeBookStart;

        long outputStart = System.nanoTime();
        IntStream.range(0, analyzedBook.size())
                .forEach(i -> System.out.println("Chapter " + (i + 1) + ": " + analyzedBook.get(i)));
        long outputTime = System.nanoTime() - outputStart;

        System.out.println("Opening and parsing the book took " + readBookTime * 1e-9 + "s");
        System.out.println("Opening and parsing the war terms took " + readWarTermsTime * 1e-9 + "s");
        System.out.println("Opening and parsing the peace terms took " + readPeaceTermsTime * 1e-9 + "s");
        System.out.println("Analyzing the book took " + analyzeBookTime * 1e-9 + "s");
    }

    static Optional<String> readFile(final String fileName) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
            return Optional.of(contentBuilder.toString());
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    static Set<String> readTerms(final String fileName) {
        Optional<String> termsOptional = readFile(fileName);
        return Set.of(termsOptional
                        .map(s -> s.split("\\s+"))
                        .orElseGet(() -> new String[]{})
        );
    }

    static List<List<String>> readBook(final String fileName) {
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
                .filter(string -> !string.isEmpty())
                .map(string -> Stream.of(string
                                .replaceAll("\\p{Punct}+", "")
                                .toLowerCase()
                                .split("\\s+"))
                        .filter(s -> !"".equals(s))
                        .toList())
                .toList();
    }

    static List<Chapter> analyzeBook(final List<List<String>> book, final Set<String> warTerms, final Set<String> peaceTerms) {
        return book.parallelStream()
                .map(chapter ->
                        new Chapter(
                                calculateDensity(chapter, peaceTerms),
                                calculateDensity(chapter, warTerms)
                        )
                )
                .toList();

    }

    static List<String> filterListByList(final List<String> toFilter, final List<String> filter) {
        return toFilter.parallelStream()
                .filter(filter::contains)
                .toList();
    }

    static long countOccurrences(final List<String> list, final Set<String> of) {
        return list.parallelStream()
                .filter(of::contains)
                .count();
    }

    static int[] filterAndCreateCoordinateArray(final List<String> list, final List<String> of) {
        return IntStream.range(0, list.size())
                .mapToObj(i -> new Word(list.get(i), i))
                .filter(x -> of.contains(x.string()))
                .mapToInt(Word::coordinate)
                .toArray();
    }

    static double calculateDensity(final List<String> list, final Set<String> of) {
        long termCount = countOccurrences(list, of);
        long totalDistance = IntStream.range(0, list.size())
                .filter(i -> of.contains(list.get(i)))
                .reduce(0, (a, b) -> a + (b - a));
        return termCount == 0 ? Double.POSITIVE_INFINITY : (double) totalDistance / termCount;
    }

    static double calculateDensityTrivial(final List<String> list, final List<String> of) {
        long count = list.parallelStream()
                .filter(of::contains)
                .count();
        return list.size() / (double) count;
    }

    static double calculateDensityAll(final List<String> list, final List<String> of) {
        var distancesArray = filterAndCreateCoordinateArray(list, of);
        int length = distancesArray.length;
        return IntStream.range(0, length).parallel()
                .flatMap(i -> IntStream.range(0, length).parallel()
                        .map(j -> Math.abs(distancesArray[i] - distancesArray[j])))
                .average()
                .orElse(0);
    }

    static double calculateDensityFirst(final List<String> list, final List<String> of) {
        var distancesArray = filterAndCreateCoordinateArray(list, of);
        return IntStream.range(0, distancesArray.length).parallel()
                .mapToDouble(i -> Math.abs(distancesArray[i] - distancesArray[0]))
                .average()
                .orElse(0);
    }

    static double calculateDensityNeighbor(final List<String> list, final List<String> of) {
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
        return (peaceDensity <= warDensity ? "peace" : "war") + "-related";
    }
}
