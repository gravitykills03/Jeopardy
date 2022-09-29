@echo off

javac -d classes -classpath "lib\*" src/com/jeopardy/client/*.java src/com/jeopardy/controller/*.java src/com/jeopardy/*.java

jar --create --file jeopardy-1.0.jar -C classes .