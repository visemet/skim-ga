#!/bin/bash

CLASSPATH=.
CLASSPATH=$CLASSPATH:lib/commons-cli-1.2.jar
CLASSPATH=$CLASSPATH:build/classes

java -cp $CLASSPATH edu.caltech.visemet.skelgen.examples.onemax.OneMaxExample "$@"
