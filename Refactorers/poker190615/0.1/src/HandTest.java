package k73i55no5.refactorers.poker190615;

import static k73i55no5.refactorers.poker190615.HandConstants.*;
import static k73i55no5.refactorers.poker190615.RankConstants.*;
import static k73i55no5.refactorers.poker190615.SuitConstants.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class HandTest {

	@Test void testMatches() {
		List<Card> cards = new ArrayList<>() {{
			add(new Card(CLUB, TEN));
			add(new Card(CLUB, JACK));
			add(new Card(CLUB, QUEEN));
			add(new Card(CLUB, KING));
			add(new Card(CLUB, ACE));
		}};

		Hand hand = ROYAL_STRAIGHT_FLUSH.hand();
		System.out.println(hand);
		System.out.println(cards);
		assertTrue(hand.matches(cards));
		cards.set(0, new Card(SPADE, TEN));
		System.out.println(cards);
		assertFalse(hand.matches(cards));
		cards.set(0, new Card(CLUB, NINE));
		System.out.println(cards);
		assertFalse(hand.matches(cards));

		hand = STRAIGHT_FLUSH.hand();
		System.out.println(hand);
		cards.set(1, new Card(CLUB, TEN));
		cards.set(2, new Card(CLUB, JACK));
		cards.set(3, new Card(CLUB, QUEEN));
		cards.set(4, new Card(CLUB, KING));
		System.out.println(cards);
		assertTrue(hand.matches(cards));
		cards.set(1, new Card(SPADE, TEN));
		System.out.println(cards);
		assertFalse(hand.matches(cards));

		hand = FOUR_OF_A_KIND.hand();
		System.out.println(hand);
		cards.set(0, new Card(CLUB, NINE));
		cards.set(1, new Card(SPADE, NINE));
		cards.set(2, new Card(DIAMOND, NINE));
		cards.set(3, new Card(HEART, NINE));
		System.out.println(cards);
		assertTrue(hand.matches(cards));
		cards.set(3, new Card(HEART, EIGHT));
		System.out.println(cards);
		assertFalse(hand.matches(cards));

		hand = FULL_HOUSE.hand();
		System.out.println(hand);
		cards.set(4, new Card(CLUB, EIGHT));
		System.out.println(cards);
		assertTrue(hand.matches(cards));
		cards.set(0, new Card(CLUB, SEVEN));
		System.out.println(cards);
		assertFalse(hand.matches(cards));

		hand = FLUSH.hand();
		System.out.println(hand);
		cards.set(1, new Card(CLUB, ACE));
		cards.set(2, new Card(CLUB, TWO));
		cards.set(3, new Card(CLUB, THREE));
		System.out.println(cards);
		assertTrue(hand.matches(cards));
		cards.set(0, new Card(HEART, SEVEN));
		System.out.println(cards);
		assertFalse(hand.matches(cards));

		hand = STRAIGHT.hand();
		System.out.println(hand);
		cards.set(0, new Card(HEART, NINE));
		cards.set(1, new Card(SPADE, TEN));
		cards.set(2, new Card(DIAMOND, JACK));
		cards.set(3, new Card(HEART, QUEEN));
		cards.set(4, new Card(CLUB, KING));
		System.out.println(cards);
		assertTrue(hand.matches(cards));
		cards.set(0, new Card(HEART, ACE));
		System.out.println(cards);
		assertTrue(hand.matches(cards));
		cards.set(0, new Card(HEART, TWO));
		System.out.println(cards);
		assertFalse(hand.matches(cards));

		hand = THREE_OF_A_KIND.hand();
		System.out.println(hand);
		cards.set(1, new Card(SPADE, TWO));
		cards.set(2, new Card(DIAMOND, TWO));
		System.out.println(cards);
		assertTrue(hand.matches(cards));
		cards.set(3, new Card(HEART, TWO));
		System.out.println(cards);
		assertFalse(hand.matches(cards));

		hand = TWO_PAIR.hand();
		System.out.println(hand);
		cards.set(1, new Card(SPADE, TWO));
		cards.set(2, new Card(DIAMOND, THREE));
		cards.set(3, new Card(HEART, THREE));
		System.out.println(cards);
		assertTrue(hand.matches(cards));
		cards.set(2, new Card(DIAMOND, TWO));
		System.out.println(cards);
		assertFalse(hand.matches(cards));

		hand = ONE_PAIR.hand();
		System.out.println(hand);
		cards.set(2, new Card(DIAMOND, THREE));
		cards.set(3, new Card(HEART, FOUR));
		System.out.println(cards);
		assertTrue(hand.matches(cards));
		cards.set(0, new Card(HEART, ACE));
		System.out.println(cards);
		assertFalse(hand.matches(cards));
	}
}
