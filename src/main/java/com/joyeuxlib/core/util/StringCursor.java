package com.joyeuxlib.core.util;

public class StringCursor {
    private final String str;
    private int cursor;
    private final int step;

    public StringCursor(String str, int start, int step) {
        this.str = str;
        this.cursor = start;
        this.step = step;
    }

    public void next() {
        this.cursor += this.step;
    }

    public char peek() {
        return this.str.charAt(this.cursor);
    }

    public char peekNext() {
        return this.str.charAt(this.cursor + this.step);
    }

    public String substring() {
        if (this.step > 0) {
            return this.cursor == 0 ? null : this.str.substring(this.cursor);
        } else if (this.step < 0) {
            return this.cursor + 1 == this.str.length() ? null : this.str.substring(0, this.cursor + 1);
        } else {
            return null;
        }
    }
}
