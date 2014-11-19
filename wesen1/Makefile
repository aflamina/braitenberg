# Braitenberg Project
# fb
# v0.1

JC=nxjc
JCFLAGS=
LD=nxjlink
LDFLAGS=
UPLOAD=nxjupload
UPLOADFLAGS=-u -r
SOURCES=$(shell ls *.java)
OBJECTS=$(SOURCES:.java=.class)
EXECUTABLE=CreatureOne.nxj

all: $(SOURCES) $(EXECUTABLE)
	
$(EXECUTABLE): $(OBJECTS)
	$(LD) $(LDFLAGS) -o $@ $(OBJECTS:.class=)

%.class: %.java
	$(JC) $(JCFLAGS) $<

upload: $(EXECUTABLE)
	$(UPLOAD) $(UPLOADFLAGS) $<

.PHONY: clean
clean:
	$(RM) $(OBJECTS) $(EXECUTABLE)
