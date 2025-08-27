package org.vladpush.rps.game;

import org.junit.jupiter.api.Test;
import org.vladpush.rps.models.Move;
import org.vladpush.rps.models.Result;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RockPaperScissorsEngineTest {

    private final RockPaperScissorsEngine engine = new RockPaperScissorsEngine();

    @Test
    void returnsPlayer1WinsWhenRockBeatsScissors() {
        assertEquals(Result.PLAYER_1_WINS, engine.getRoundResult(Move.ROCK, Move.SCISSORS));
    }

    @Test
    void returnsPlayer1WinsWhenPaperBeatsRock() {
        assertEquals(Result.PLAYER_1_WINS, engine.getRoundResult(Move.PAPER, Move.ROCK));
    }

    @Test
    void returnsPlayer1WinsWhenScissorsBeatPaper() {
        assertEquals(Result.PLAYER_1_WINS, engine.getRoundResult(Move.SCISSORS, Move.PAPER));
    }

    @Test
    void returnsPlayer2WinsWhenRockLosesToPaper() {
        assertEquals(Result.PLAYER_2_WINS, engine.getRoundResult(Move.ROCK, Move.PAPER));
    }

    @Test
    void returnsPlayer2WinsWhenPaperLosesToScissors() {
        assertEquals(Result.PLAYER_2_WINS, engine.getRoundResult(Move.PAPER, Move.SCISSORS));
    }

    @Test
    void returnsPlayer2WinsWhenScissorsLosesToRock() {
        assertEquals(Result.PLAYER_2_WINS, engine.getRoundResult(Move.SCISSORS, Move.ROCK));
    }

    @Test
    void returnsDrawWhenMovesAreEqual() {
        assertEquals(Result.DRAW, engine.getRoundResult(Move.ROCK, Move.ROCK));
        assertEquals(Result.DRAW, engine.getRoundResult(Move.PAPER, Move.PAPER));
        assertEquals(Result.DRAW, engine.getRoundResult(Move.SCISSORS, Move.SCISSORS));
    }
}
