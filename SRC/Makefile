CXX = g++
CXXFLAGS = -g -Wall -lGLU -lGL -lglut 

SRC_EXT := cpp
HDR_EXT := hpp
HEADERS := $(wildcard *.$(HDR_EXT))
SOURCES := $(wildcard *.$(SRC_EXT))
OBJECTS := $(patsubst %.$(SRC_EXT),%.o,$(SOURCES))

MAIN = Simulator

OBJS := $(SOURCES:.cpp=.o)

all: $(OBJS)
	$(CXX) -o $(MAIN) $(OBJS)

.$(SRC_EXT).o:
	$(CXX) -c $(CXXFLAGS) $(INCLUDES) -o $@ $<

clean:
	$(RM) $(OBJS)
	$(RM) $(MAIN)

rebuild: clean all
