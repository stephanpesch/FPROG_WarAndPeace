all: main

.outputFolder:
	mkdir -p out

main: .outputFolder
	cp src/manifest.txt out/manifest.txt
	javac -d ./out/ ./src/at/fhtw/fprog/Main.java
	cd out; jar -cvfm ../out.jar manifest.txt at/fhtw/fprog/*.class
	
clean:
	rm -rf ./out/ out.jar
