package org.vladpush.rps.models;

/**
 * Wrapper for values parsed from user input.
 *
 * @param <T> type of the parsed value
 * @param value parsed value
 */
public record InputParserResult<T>(T value) {
}
