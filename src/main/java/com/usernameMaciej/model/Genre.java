package com.usernameMaciej.model;

import java.util.Arrays;

public enum Genre {
    COMEDY("Comedy"),
    DRAMA("Drama"),
    HORROR("Horror"),
    SCI_FI("Science Fiction"),
    ADVENTURE("Adventure"),
    FANTASY("Fantasy");

    public final String description;

    Genre(String description) {
        this.description = description;
    }

    public static String convert(String genre) {
        Genre[] values = Genre.values();
        for (Genre value : values) {
            if (genre.equalsIgnoreCase(value.description)) {
                return value.description;
            }
        }
        return null;
    }

    public static void printAllGenresToChoose() {
        Object[] genres = Arrays.stream(Genre.values()).map(genre -> genre.description).toArray();
        System.out.print(Arrays.toString(genres));
        System.out.println();
    }
}