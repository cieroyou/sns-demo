package com.sera.snsdemo.util;

public record CursorRequest(Long key, int size) {
    public static final Long NONE_KEY = -1L;

    public boolean hasKey() {
        return key != null;
    }

    public CursorRequest next(Long key) {
        return new CursorRequest(key, size);
    }
}