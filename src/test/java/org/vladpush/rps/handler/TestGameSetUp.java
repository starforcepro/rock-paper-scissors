package org.vladpush.rps.handler;

import org.vladpush.rps.models.Move;

import java.util.List;

public class TestGameSetUp {
    private final StringBuilder setupString = new StringBuilder();

    public TestGameSetUp withRounds(int roundsCount) {
        setupString.append(roundsCount);
        setupString.append("\n");
        return this;
    }

    public TestGameSetUp withPlayerMoves(List<Move> moves) {
        for (var move : moves) {
            setupString.append(move.name().charAt(0));
            setupString.append("\n");
        }
        return this;
    }

    public String build() {
        return setupString.toString();
    }
}
