CXX = g++
CXXFLAGS = -Wall -lGLU -lGL -lglut 

SRC_EXT := cpp
HDR_EXT := hpp
HEADERS := $(wildcard *.$(HDR_EXT))
SOURCES := $(wildcard *.$(SRC_EXT))
OBJECTS := $(patsubst %.$(SRC_EXT),%.o,$(SOURCES))

MAIN = executavel

OBJS := $(SOURCES:.cpp=.o)

all: $(OBJS)
	$(CXX) $(CXXFLAGS) $(OBJS) -o $(MAIN)

.$(SRC_EXT).o:
	$(CXX) -c -o $@ $<

clean:
	$(RM) $(OBJS)
	$(RM) $(MAIN)

rebuild: clean all
