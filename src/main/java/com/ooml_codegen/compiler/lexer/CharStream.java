package com.ooml_codegen.compiler.lexer;

import java.io.*;

public class CharStream {

    private final BufferedReader reader;
    private char currentChar = 0;
    private boolean reachedEOF = false;

    /**
     * CharStream Constructor, opens file and loads a character in internal buffer if file is not empty.
     * Updates EOF status if file is empty.
     * @param file File to open
     * @throws FileNotFoundException File not found
     */
    public CharStream(File file) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(file));
        next();
    }

    /**
     * Loads next character in internal storage,
     * Updates internal EOF status,
     * If EOF or error, close reader.
     * @return true if a new character has been read, false if error or EOF
     */
    public boolean next() {
        if (isEOF()) {
            return false;
        }

        int readChar = -1;
        try {
            readChar = reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (readChar == -1) {
            this.reachedEOF = true;
            this.closeReaderQuietly();

            return false;
        }

        this.currentChar = (char) readChar;

        return true;
    }

    /**
     * @return true if EOF has been reached, false otherwise.
     */
    public boolean isEOF(){
        return this.reachedEOF;
    }

    /**
     * @return current character. Undefined when EOF has been reached.
     */
    public char getChar(){
        return this.currentChar;
    }

    /**
     * Manual reader close
     */
    public void close() {
        if (!isEOF()){
            this.reachedEOF = true;
            try {
                this.reader.close();
            } catch (IOException ignored) {}
        }
    }

    private void closeReaderQuietly() {
        try {
            reader.close();
        } catch (IOException ignored) {
        }
    }

}
