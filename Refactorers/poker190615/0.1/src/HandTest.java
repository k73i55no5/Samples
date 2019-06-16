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
		// ロイヤルストレートフラッシュ
		assertTrue(ROYAL_STRAIGHT_FLUSH.hand().matches(cards));
		cards.set(0, new Card(SPADE, TEN));
		assertFalse(ROYAL_STRAIGHT_FLUSH.hand().matches(cards));
		cards.set(0, new Card(CLUB, NINE));
		assertFalse(ROYAL_STRAIGHT_FLUSH.hand().matches(cards));
		// ストレートフラッシュ
		cards.set(1, new Card(CLUB, TEN));
		cards.set(2, new Card(CLUB, JACK));
		cards.set(3, new Card(CLUB, QUEEN));
		cards.set(4, new Card(CLUB, KING));
		assertTrue(STRAIGHT_FLUSH.hand().matches(cards));
		cards.set(1, new Card(SPADE, TEN));
		assertFalse(STRAIGHT_FLUSH.hand().matches(cards));
		// フォーカード
		cards.set(0, new Card(CLUB, NINE));
		cards.set(1, new Card(SPADE, NINE));
		cards.set(2, new Card(DIAMOND, NINE));
		cards.set(3, new Card(HEART, NINE));
		assertTrue(FOUR_OF_A_KIND.hand().matches(cards));
		cards.set(3, new Card(HEART, EIGHT));
		assertFalse(FOUR_OF_A_KIND.hand().matches(cards));
		// フルハウス
		cards.set(4, new Card(CLUB, EIGHT));
		assertTrue(FULL_HOUSE.hand().matches(cards));
		cards.set(0, new Card(CLUB, SEVEN));
		assertFalse(FULL_HOUSE.hand().matches(cards));
		// フラッシュ
		cards.set(1, new Card(CLUB, ACE));
		cards.set(2, new Card(CLUB, TWO));
		cards.set(3, new Card(CLUB, THREE));
		assertTrue(FLUSH.hand().matches(cards));
		cards.set(0, new Card(HEART, SEVEN));
		assertFalse(FLUSH.hand().matches(cards));
		// ストレート
		cards.set(0, new Card(HEART, NINE));
		cards.set(1, new Card(SPADE, TEN));
		cards.set(2, new Card(DIAMOND, JACK));
		cards.set(3, new Card(HEART, QUEEN));
		cards.set(4, new Card(CLUB, KING));
		assertTrue(STRAIGHT.hand().matches(cards));
		cards.set(0, new Card(HEART, ACE));
		assertTrue(STRAIGHT.hand().matches(cards));
		cards.set(0, new Card(HEART, TWO));
		assertFalse(STRAIGHT.hand().matches(cards));
		// スリーカード
		cards.set(1, new Card(SPADE, TWO));
		cards.set(2, new Card(DIAMOND, TWO));
		assertTrue(THREE_OF_A_KIND.hand().matches(cards));
		cards.set(3, new Card(HEART, TWO));
		assertFalse(THREE_OF_A_KIND.hand().matches(cards));
		// ツーペア
		cards.set(1, new Card(SPADE, TWO));
		cards.set(2, new Card(DIAMOND, THREE));
		cards.set(3, new Card(HEART, THREE));
		assertTrue(TWO_PAIR.hand().matches(cards));
		cards.set(2, new Card(DIAMOND, TWO));
		assertFalse(TWO_PAIR.hand().matches(cards));
		// ワンペア
		cards.set(2, new Card(DIAMOND, THREE));
		cards.set(3, new Card(HEART, FOUR));
		assertTrue(ONE_PAIR.hand().matches(cards));
		cards.set(0, new Card(HEART, ACE));
		assertFalse(ONE_PAIR.hand().matches(cards));
	}
}
