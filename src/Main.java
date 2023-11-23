import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        var book = readFile("in/war_and_peace.txt");
        var warTerms = readFile("in/war_terms.txt");
        var peaceTerms = readFile("in/peace_terms.txt");

        if (book.isEmpty() || warTerms.isEmpty() || peaceTerms.isEmpty())
            System.out.println("Could not read files, or files are empty");

        var tokenizedBook = tokenizeBook(book);
        var splitBook = splitIntoChapters(tokenizedBook);

        var peaceDensity = calculateDensity(tokenizedBook, peaceTerms);
        var warDensity = calculateDensity(tokenizedBook, warTerms);

        var chapter = new Chapter(1, peaceDensity, warDensity);
        System.out.println(chapter);

        // tokenizedBook.forEach(System.out::println);
    }

    private static List<String> readFile(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.toList();
        } catch (IOException e) {
            return List.of();
        }
    }

    private static List<List<String>> splitIntoChapters(List<String> words) {
        int[] chapterCoordinates = filterAndCreateCoordinateArray(words, List.of("chapter"));
        return IntStream.range(0, chapterCoordinates.length - 1)
                .mapToObj(i -> words.subList(chapterCoordinates[i], chapterCoordinates[i + 1]))
                .toList();
    }

    private static List<String> tokenizeBook(List<String> text) {
        String bookStart = "CHAPTER 1";
        String bookEnd = "*** END OF THE PROJECT GUTENBERG EBOOK, WAR AND PEACE ***";


        return text.parallelStream()
                .dropWhile(line -> !bookStart.equals(line))
                .takeWhile(line -> !bookEnd.equals(line))
                .map(line -> line
                        .toLowerCase()
                        .replaceAll("\\p{Punct}+", "")
                        .split("\\s+"))
                .flatMap(Arrays::stream)
                .filter(string -> !string.isEmpty())
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

    private static double calculateDensity(List<String> list, List<String> of) {
        var distancesArray = filterAndCreateCoordinateArray(list, of);
        return IntStream.range(0, distancesArray.length - 1)
                .mapToDouble(i -> distancesArray[i + 1] - distancesArray[i])
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
