all: package run

package:
	@mvn clean package

run:
	@java -jar target/aoc-java-1.0-SNAPSHOT.jar 
