# makefile Lab_4

.SUFFIXES: .class .java

PACK= company
TARG=Main
JC=javac -g
JM=java -ea -jar
JR=jar -cfe

OBJ=$(PACK)\Polynom.class \
    $(PACK)\Main.class

.java.class:
	$(JC) $<

run: build
	$(JM) $(TARG).jar

build: 	$(OBJ)
	$(JR) $(TARG).jar $(PACK).Main $(OBJ)

clean:
	for %%f in ($(OBJ)) do del %%f
	del $(TARG).jar

$(PACK)\Polynom.class: $(PACK)\Polynom.java makefile

$(PACK)\Main.class: $(PACK)\Main.java makefile

# eof makefile Lab_4