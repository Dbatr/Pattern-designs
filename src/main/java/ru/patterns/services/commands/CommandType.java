package ru.patterns.services.commands;

/**
 * Паттерн Command
 */
public class CommandType {
    public static final int GET_ALL_NOTES = 1;
    public static final int ADD_NOTE = 2;
    public static final int GET_NOTE_BY_ID = 3;
    public static final int DELETE_NOTE_BY_ID = 4;
    public static final int UPDATE_NOTE = 5;
    public static final int DUPLICATE_NOTE = 6;
}
