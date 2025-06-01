JAVAC=javac
JAVA=java

OBJ_DIR = obj
SRC= src/Main.java \
	src/FileUtils.java \
	src/Indexer.java \
	src/DatabaseManager.java

CLASS=Main

all: compile

$(OBJ_DIR):
	mkdir -p $(OBJ_DIR)

compile: $(OBJ_DIR)
	$(JAVAC) -d $(OBJ_DIR) $(SRC)

run: compile
	$(JAVA) -cp $(OBJ_DIR) $(CLASS)

clean:
	rm -r $(OBJ_DIR)