package org.vladpush.rps;

import org.vladpush.rps.handler.RockPaperScissorsGameHandler;
import org.vladpush.rps.output.TextPrinter;
import org.vladpush.rps.parser.InputParser;
import org.vladpush.rps.player.ComputerPlayer;
import org.vladpush.rps.player.RealPlayer;
import org.vladpush.rps.provider.InputProvider;
import org.vladpush.rps.provider.RandomProvider;

import java.util.Random;
import java.util.Scanner;

/**
 * Entry point for the Rock-Paper-Scissors console application.
 * <p>
 * Wires the dependencies (printer, parser, players) and starts the interactive game handler.
 */
public class Application {

    /**
     * Launches the application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        var printer = new TextPrinter();
        var inputParser = new InputParser(new Scanner(System.in), printer);
        var realPlayer = new RealPlayer(new InputProvider(inputParser));
        var computerPlayer = new ComputerPlayer(new RandomProvider(new Random()));
        new RockPaperScissorsGameHandler(printer, inputParser, realPlayer, computerPlayer).handle();
    }
}
