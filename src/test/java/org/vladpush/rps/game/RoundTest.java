package org.vladpush.rps.game;

import org.junit.jupiter.api.Test;
import org.vladpush.rps.models.Move;
import org.vladpush.rps.models.Result;
import org.vladpush.rps.models.RoundInfo;
import org.vladpush.rps.output.TextPrinter;
import org.vladpush.rps.player.FakeMoveProvider;
import org.vladpush.rps.player.RealPlayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoundTest {

    @Test
    void returnsInfoWithPlayer1WinResult() {
        RockPaperScissorsEngine engine = new RockPaperScissorsEngine();
        Move player1Move = Move.ROCK;
        Move player2Move = Move.SCISSORS;
        var moveProviderForPlayer1 = new FakeMoveProvider(player1Move);
        var moveProviderForPlayer2 = new FakeMoveProvider(player2Move);
        var player1 = new RealPlayer(moveProviderForPlayer1);
        var player2 = new RealPlayer(moveProviderForPlayer2);
        Round round = new Round(engine, new TextPrinter(), player1, player2);

        RoundInfo info = round.play(1);

        assertEquals(player1Move, info.player1Move());
        assertEquals(player2Move, info.player2Move());
        assertEquals(Result.PLAYER_1_WINS, info.result());
    }

    @Test
    void returnsInfoWithPlayer2WinResult() {
        RockPaperScissorsEngine engine = new RockPaperScissorsEngine();
        Move player1Move = Move.ROCK;
        Move player2Move = Move.PAPER;
        var moveProviderForPlayer1 = new FakeMoveProvider(player1Move);
        var moveProviderForPlayer2 = new FakeMoveProvider(player2Move);
        var player1 = new RealPlayer(moveProviderForPlayer1);
        var player2 = new RealPlayer(moveProviderForPlayer2);
        Round round = new Round(engine, new TextPrinter(), player1, player2);

        RoundInfo info = round.play(1);

        assertEquals(player1Move, info.player1Move());
        assertEquals(player2Move, info.player2Move());
        assertEquals(Result.PLAYER_2_WINS, info.result());
    }

    @Test
    void returnsInfoWithDrawResult() {
        RockPaperScissorsEngine engine = new RockPaperScissorsEngine();
        Move player1Move = Move.PAPER;
        Move player2Move = Move.PAPER;
        var moveProviderForPlayer1 = new FakeMoveProvider(player1Move);
        var moveProviderForPlayer2 = new FakeMoveProvider(player2Move);
        var player1 = new RealPlayer(moveProviderForPlayer1);
        var player2 = new RealPlayer(moveProviderForPlayer2);
        Round round = new Round(engine, new TextPrinter(), player1, player2);

        RoundInfo info = round.play(1);

        assertEquals(player1Move, info.player1Move());
        assertEquals(player2Move, info.player2Move());
        assertEquals(Result.DRAW, info.result());
    }
}
