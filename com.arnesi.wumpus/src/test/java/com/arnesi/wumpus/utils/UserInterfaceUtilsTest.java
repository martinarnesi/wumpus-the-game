package com.arnesi.wumpus.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserInterfaceUtilsTest {

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@Test
	@DisplayName("Test drawBanner() method.")
	void drawBannerTest() {
		// Expected Strings
		List<String> expectedStrings = Arrays.asList("by", "Martin", "Arnesi");

		// Draw on output
		UserInterfaceUtils.drawBanner();
		List<String> capturedStrings = Arrays.asList(outputStreamCaptor.toString().split(" "));

		assertTrue(checkExpectedActions(expectedStrings, capturedStrings));
	}

	@Test
	@DisplayName("Test drawCommands() method.")
	void drawCommandsTest() {
		// Expected Strings
		List<String> expectedStrings = Arrays.asList("TYPE", "GF", "TL", "TR", "EXIT", "ENDGAME", "Hunter", "Game");

		// Draw on output
		UserInterfaceUtils.drawCommands();
		List<String> capturedStrings = Arrays.asList(outputStreamCaptor.toString().split(" "));

		assertTrue(checkExpectedActions(expectedStrings, capturedStrings));
	}

	private static boolean checkExpectedActions(List<String> expectedStrings, List<String> capturedStrings) {
		return CollectionUtils.containsAny(expectedStrings, capturedStrings);
	}
}