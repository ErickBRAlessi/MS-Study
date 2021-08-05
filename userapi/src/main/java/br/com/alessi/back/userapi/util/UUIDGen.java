package br.com.alessi.back.userapi.util;

import java.util.UUID;

public abstract class UUIDGen {

    public static String build() {
        return UUID.randomUUID().toString();
    }
}
