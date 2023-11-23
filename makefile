all: main

.outputFolder:
	mkdir -p out

main: .outputFolder
	javac -d ./out/ ./src/Main.java
	
clean:
	rm -rf ./out/
