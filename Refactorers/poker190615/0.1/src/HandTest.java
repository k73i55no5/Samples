package k73i55no5.refactorers.poker190615;

import static k73i55no5.refactorers.poker190615.Hand.*;
import static k73i55no5.refactorers.poker190615.Rank.*;
import static k73i55no5.refactorers.poker190615.Suit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

final class HandTest {

	private final List<Card> cards = new ArrayList<>() {{
		add(new Card(CLUB, TEN));
		add(new Card(CLUB, JACK));
		add(new Card(CLUB, QUEEN));
		add(new Card(CLUB, KING));
		add(new Card(CLUB, ACE));
	}};

	private Hand hand = get(ROYAL_STRAIGHT_FLUSH);

	@Test void testMatches() {
		assertTrue(matches());
		cards.set(0, new Card(SPADE, TEN));
		assertFalse(matches());
		cards.set(0, new Card(CLUB, NINE));
		assertFalse(matches());

		hand = get(STRAIGHT_FLUSH);
		cards.set(1, new Card(CLUB, TEN));
		cards.set(2, new Card(CLUB, JACK));
		cards.set(3, new Card(CLUB, QUEEN));
		cards.set(4, new Card(CLUB, KING));
		assertTrue(matches());
		cards.set(1, new Card(SPADE, TEN));
		assertFalse(matches());

		hand = get(FOUR_OF_A_KIND);
		cards.set(0, new Card(CLUB, NINE));
		cards.set(1, new Card(SPADE, NINE));
		cards.set(2, new Card(DIAMOND, NINE));
		cards.set(3, new Card(HEART, NINE));
		assertTrue(matches());
		cards.set(3, new Card(HEART, EIGHT));
		assertFalse(matches());

		hand = get(FULL_HOUSE);
		cards.set(4, new Card(CLUB, EIGHT));
		assertTrue(matches());
		cards.set(0, new Card(CLUB, SEVEN));
		assertFalse(matches());

		hand = get(FLUSH);
		System.out.println(hand);
		cards.set(1, new Card(CLUB, ACE));
		cards.set(2, new Card(CLUB, DEUCE));
		cards.set(3, new Card(CLUB, THREE));
		assertTrue(matches());
		cards.set(0, new Card(HEART, SEVEN));
		assertFalse(matches());

		hand = get(STRAIGHT);
		cards.set(0, new Card(HEART, NINE));
		cards.set(1, new Card(SPADE, TEN));
		cards.set(2, new Card(DIAMOND, JACK));
		cards.set(3, new Card(HEART, QUEEN));
		cards.set(4, new Card(CLUB, KING));
		assertTrue(matches());
		cards.set(0, new Card(HEART, ACE));
		assertTrue(matches());
		cards.set(0, new Card(HEART, DEUCE));
		assertFalse(matches());

		hand = get(THREE_OF_A_KIND);
		cards.set(1, new Card(SPADE, DEUCE));
		cards.set(2, new Card(DIAMOND, DEUCE));
		assertTrue(matches());
		cards.set(3, new Card(HEART, DEUCE));
		assertFalse(matches());

		hand = get(TWO_PAIR);
		cards.set(1, new Card(SPADE, DEUCE));
		cards.set(2, new Card(DIAMOND, THREE));
		cards.set(3, new Card(HEART, THREE));
		assertTrue(matches());
		cards.set(2, new Card(DIAMOND, DEUCE));
		assertFalse(matches());

		hand = get(ONE_PAIR);
		cards.set(2, new Card(DIAMOND, THREE));
		cards.set(3, new Card(HEART, FOUR));
		assertTrue(matches());
		cards.set(0, new Card(HEART, ACE));
		assertFalse(matches());
	}

	private Hand get(Hand hand) {
		System.out.println(hand);
		return hand;
	}

	private boolean matches() {
		System.out.println(cards);
		return hand.matches(cards);
	}
}
