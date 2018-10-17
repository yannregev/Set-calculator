JFLAGS = -g -Xlint
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Main.java \
	Set.java \
	SetInterface.java \
	ListInterface.java\
	List.java \
	APException.java


default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
