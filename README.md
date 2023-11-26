# War and Peace analyzer
## Build instructions

To build make sure that the JDK version >=17 is installed, then just run the following command

```bash
./gradlew build -x test
```

or if gradle is installed

```bash
gradle build -x test
```

## Run instructions

Make sure to make the file executable like this
```bash
chmod +x run.sh
```

Then just execute the run.sh like this with the default files
```bash
./run.sh
```
or like this if you want to specify your own files
```bash
./run.sh [path to text] [path to war terms] [path to peace terms]
```

## Instructions
For the problem:

Please create a program, that reads a large text file (e.g. "war and peace from Tolstoy") and another 2 text files with a word list, one with "war-terms" and one with "peace-terms". Now your program has to try to categorize the chapters of the book to be war-related or peace-related by the help of these 2 word lists. The occurrences of the words in the chapters and their relative distance to the next word of the same category can give the density of war- and peace-terms in the text. The chapter is characterized as war-chapter if the density of war terms is higher than the pease-density." . Chapters are announced by the word "chapter" and a number.

Create your solution in C++, Haskell, F#, Java, Python, Groovy, Scala, or Lisp (in a functional philosophy) with the following steps:

1.  Include necessary headers and set up the main function: Include headers for file I/O, strings, vectors, maps, and other required data structures. Then, set up the main function where the program will run.

2.    Read files: Create a function that reads a file and returns its content as a vector of strings. The function should be implemented using functional programming, immutability, and lambdas where possible.

3.   Tokenize the text: Create a function to tokenize a string into words. This function should use functional programming techniques and lambdas for string manipulation and splitting.

4.   Filter words: Create a function to filter words from a list based on another list. This function should use functional programming techniques, such as higher-order functions and lambdas, to perform filtering.

5.   Count occurrences: Create a function to count the occurrences of words in a list. This function should use the map-reduce philosophy and functional programming techniques to count word occurrences in a parallelizable and efficient manner.

6.  Calculate term density: Create a function to calculate the density of terms in a text, based on the occurrences of words and their relative distances to the next word of the same category. This function should use functional programming techniques and the map-reduce philosophy for parallelization and efficiency.

7.   Read input files and tokenize: Read the input files (book, war terms, and peace terms) and tokenize their contents into words using the functions created in steps 2 and 3.

8.   Process chapters: Process each chapter in the book by calculating the density of war and peace terms using the functions created in steps 4, 5, and 6. Store the densities in separate vectors for further processing.

9.  Categorize chapters: Iterate through the chapters, and for each chapter, compare the war density to the peace density to determine if it's war-related or peace-related. Store the results in a vector.

10.    Print results: Iterate through the results vector and print each chapter's categorization as war-related or peace-related.
 
Please implement all the steps immutable, pure using lambdas and it should be persistent where possible. It should follow the map reduce philosophy to be fast and for easy parallelization. 
