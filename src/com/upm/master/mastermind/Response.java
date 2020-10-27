package com.upm.master.mastermind;

// See https://mesadejuegos.top/mastermind/
public final class Response {
    private static final int NONE = 0;
    private static final int WHITE = 1;
    private static final int BLACK = 2;

    int values[];

    public Response(int size) {
        values = new int[size];
        for (int ii = 0; ii < size; ii ++)
            values[ii] = NONE;
    }
    public void putBlack(int ii) {
        values[ii] = BLACK;
    }
    public void putWhite(int ii) {
        values[ii] = WHITE;
    }
    public boolean codeWasBroken() {
        for (int value : values) {
            if (value != BLACK)
                return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Response{" );
        for (int value : values) {
            sb.append((value == NONE) ? ' ': (value == WHITE) ? 'o': '*');
        }
        sb.append('}');
        return sb.toString();
    }
};
