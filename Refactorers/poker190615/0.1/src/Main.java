package k73i55no5.refactorers.poker190615;

import static k73i55no5.refactorers.poker190615.Human.*;
import static k73i55no5.refactorers.poker190615.JQKMark.*;
import static k73i55no5.refactorers.poker190615.Rank.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class Main {

	public static void main(String[] args) {
		PokerController poker = new PokerController();
		poker.run();
	}
}

class PokerController {
	private final HandRepository handRepository = HandRepository.INSTANCE;

	void run() {
		// コンソールにプレーヤーの手札を表示
		Printer printer = new Printer();
		printer.printHands(PLAYER);
		// プレーヤーに手札交換の有無を確認
		printer.printExchange();
		// プレーヤーが手札を交換するならば、リポジトリに交換を命令する。
		handRepository.exchange();
		// 手札を判定し、コンソールに結果を表示する。
		Judger judger = new Judger();
		printer.printHands(COMPUTER);
		printer.printHand(judger.judgeHand(COMPUTER));
		printer.printHands(PLAYER);
		printer.printHand(judger.judgeHand(PLAYER));
		printer.printJudgement(judger.judgeScore());
	}
}

enum HandRepository {
	INSTANCE;

	void exchange() {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < Card.QUANTITY; i++) {
			String str = sc.nextLine();
			if (str.equals("end")) break;
			int index = Integer.parseInt(str);
			PLAYER.exchangeAt(index);
		}
		sc.close();
	}
}

class Printer {
	Printer() { System.out.println("    1         2         3         4         5      "); }

	void printExchange() {
		System.out.println("交換するカードの番号を入力してください");
	}

	// 役を出力
	void printHand(String hand) {
		System.out.println(hand);
		System.out.println();
	}

	// トランプの絵柄を出力
	void printHands(Human human) {
		System.out.println(human.toString());
		human.getCards().stream().map(Card::getSuit).forEach(System.out::print);
		System.out.println();
		int count = human.getMarks().get(0).size();
		for (int i = 0; i < count; i++) {
			for (List<String> lines : human.getMarks())
				System.out.print(lines.get(i));
			System.out.println();
		}
	}

	// 勝敗の結果を出力
	void printJudgement(String judgement) {
		System.out.println(judgement);
	}
}

class Judger {
	String judgeHand(Human human) {
		List<Card> cards = human.getCards();
		Collections.sort(cards);
		for (Hand hand : Hand.valuesExceptNoPair()) {
			if (hand.matches(cards)) human.setHand(hand);
		}
		return human.getHand().toString();
	}

	String judgeScore() {
		int player_score = PLAYER.getHand().score();
		int computer_score = COMPUTER.getHand().score();
		return (player_score > computer_score) ? "あなたの勝ちです" :
			(player_score < computer_score) ? "あなたの負けです" : "引き分けです";
	}
}

class Card implements Comparable<Card> {
	private Suit suit;
	private Rank rank;
	// ジョーカーを除く52枚すべてのカードを格納するイテレータ（以下「デッキ・イテレータ」という。）の初期化
	static final Iterator<Card> DECK;
	// カードを引く枚数
	static final int QUANTITY = 5;

	// デッキ・イテレータの初期化
	static {
		// まずリストの生成
		List<Card> deck = new ArrayList<>() {{
			for (Suit suit : Suit.values()) {
				for (Rank rank : Rank.values())
					add(new Card(suit, rank));
			}
		}};
		// 予めシャッフルしておく。
		Collections.shuffle(deck);
		// イテレータに変換
		DECK = deck.iterator();
	}

	// カードの初期化
	Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	@Override public int compareTo(Card card) {
		return rank.compareTo(card.getRank());
	}

	// テスト用
	@Override public String toString() {
		return suit.toString().trim() + rank.toString();
	}

	List<String> getLinesOfMark() { return rank.lines(); }
	Rank getRank() { return rank; }
	Suit getSuit() { return suit; }
}

enum Human {
	// カードをドローする順番で宣言
	COMPUTER("コンピューター"),
	PLAYER("プレーヤー"),;

	private final String name;
	private final List<Card> cards;
	private Hand hand = Hand.NO_PAIR;

	private Human(String name) {
		this.name = name;
		List<Card> cards = new ArrayList<>();
		// デッキから5枚ドロー
		for (int i = 0; i < Card.QUANTITY; i++) cards.add(Card.DECK.next());
		// カードをセット
		this.cards = cards;
	}

	@Override public String toString() { return name; }

	// 引数で指定した番目のカードを交換
	void exchangeAt(int index) {
		cards.set(index - 1, Card.DECK.next());
	}

	List<Card> getCards() { return cards; }

	List<List<String>> getMarks() {
		return cards.stream().map(Card::getLinesOfMark).collect(Collectors.toList());
	}

	Hand getHand() { return hand; }
	void setHand(Hand hand) { this.hand = hand; }
}

enum Suit {
	SPADE("スペード "),
	HEART(" ハート   "),
	DIAMOND(" ダイヤ  "),
	CLUB("クローバー "),;

	private String suit;

	private Suit(String suit) { this.suit = suit; }
	@Override public String toString() { return suit; }
}

enum Rank {
	ACE(1, "|A      | ", "|       | ", "|   *   | ", "|       | ", "|       | "),
	DEUCE(2, "|2      | ", "|   *   | ", "|       | ", "|   *   | ", "|       | "),
	THREE(3, "|3      | ", "|   *   | ", "|   *   | ", "|   *   | ", "|       | "),
	FOUR(4, "|4      | ", "| *   * | ", "|       | ", "| *   * | ", "|       | "),
	FIVE(5, "|5      | ", "| *   * | ", "|   *   | ", "| *   * | ", "|       | "),
	SIX(6, "|6      | ", "| *   * | ", "| *   * | ", "| *   * | ", "|       | "),
	SEVEN(7, "|7      | ", "| *   * | ", "| * * * | ", "| *   * | ", "|       | "),
	EIGHT(8, "|8      | ", "| * * * | ", "| *   * | ", "|   *   | ", "| *   * | "),
	NINE(9, "|9      | ", "| *   * | ", "| * * * | ", "| *   * | ", "| *   * | "),
	TEN(10 ,"|10     | ", "| *   * | ", "| * * * | ", "| * * * | ", "| *   * | "),
	JACK(11, "|J      | ", HEAD, BODY, BODY_REVERSE, HEAD_REVERSE),
	QUEEN(12 ,"|Q      | ", HEAD, BODY, BODY_REVERSE, HEAD_REVERSE),
	KING(13, "|K      | ", HEAD, BODY, BODY_REVERSE, HEAD_REVERSE),;

	private static final String SIDES = " -------  ";
	private int number;
	private List<String> lines;

	private Rank(int number, Object... marks) {
		this.number = number;
		lines = new ArrayList<>() {{
			add(SIDES);
			for (Object mark : marks) add(mark.toString());
			add(SIDES);
		}};
	}

	@Override public String toString() { return String.valueOf(number); }
	int number() { return number; }
	List<String> lines() { return lines; }
}

enum Hand {
	ROYAL_STRAIGHT_FLUSH("ロイヤルストレートフラッシュ", 9, cards -> {
		return Type.FLUSH.matches(cards) && Type.BROADWAY.matches(cards);
	}),
	STRAIGHT_FLUSH("ストレートフラッシュ", 8, cards -> {
		return Type.FLUSH.matches(cards) && Type.STRAIGHT.matches(cards);
	}),
	FOUR_OF_A_KIND("フォーカード", 7, cards -> {
		return Type.FOUR_OF_A_KIND.matches(cards);
	}),
	FULL_HOUSE("フルハウス", 6, cards -> {
		return Type.THREE_OF_A_KIND.matches(cards) && Type.TWO_OF_A_KIND.matches(cards);
	}),
	FLUSH("フラッシュ", 5, cards -> {
		return Type.FLUSH.matches(cards);
	}),
	STRAIGHT("ストレート", 4, cards -> {
		return Type.BROADWAY.matches(cards) || Type.STRAIGHT.matches(cards);
	}),
	THREE_OF_A_KIND("スリーカード", 3, cards -> {
		return Type.THREE_OF_A_KIND.matches(cards);
	}),
	TWO_PAIR("ツーペア", 2, cards -> {
		return Type.THREE_RANKS.matches(cards) && Type.TWO_OF_A_KIND.matches(cards);
	}),
	ONE_PAIR("ワンペア", 1, cards -> {
		return Type.FOUR_RANKS.matches(cards) && Type.TWO_OF_A_KIND.matches(cards);
	}),
	NO_PAIR("ノーペア", 0, null),; // 手役判定用オブジェクトを必要としないためnull

	private static final Hand[] VALUES_EXCEPT_NO_PAIR =
		Stream.of(values()).filter(h -> h != NO_PAIR).toArray(Hand[]::new);

	private final String name;
	private final int score;
	private final Predicate<List<Card>> matcher;

	private Hand(String name, int score, Predicate<List<Card>> matcher) {
		this.name = name;
		this.score = score;
		this.matcher = matcher;
	}

	@Override public String toString() { return name; }

	static Hand[] valuesExceptNoPair() { return VALUES_EXCEPT_NO_PAIR; }
	boolean matches(List<Card> cards) { return matcher.test(cards); }
	int score() { return score; }

	private enum Type {
		BROADWAY((suitMap, rankMap) -> {
			return rankMap.containsKey(TEN) &&
				rankMap.containsKey(JACK) &&
				rankMap.containsKey(QUEEN) &&
				rankMap.containsKey(KING) &&
				rankMap.containsKey(ACE);
		}),
		FLUSH((suitMap, rankMap) -> {
			return suitMap.size() == 1;
		}),
		STRAIGHT((suitMap, rankMap) -> {
			Rank[] ranks = rankMap.keySet().toArray(Rank[]::new);
			Arrays.sort(ranks);
			return ranks[1].number() == ranks[0].number() + 1 &&
				ranks[2].number() == ranks[1].number() + 1 &&
				ranks[3].number() == ranks[2].number() + 1 &&
				ranks[4].number() == ranks[3].number() + 1;
		}),
		FOUR_OF_A_KIND((suitMap, rankMap) -> {
			return rankMap.containsValue(4);
		}),
		THREE_OF_A_KIND((suitMap, rankMap) -> {
			return rankMap.containsValue(3);
		}),
		TWO_OF_A_KIND((suitMap, rankMap) -> {
			return rankMap.containsValue(2);
		}),
		THREE_RANKS((suitMap, rankMap) -> {
			return rankMap.size() == 3;
		}),
		FOUR_RANKS((suitMap, rankMap) -> {
			return rankMap.size() == 4;
		}),;

		private final BiPredicate<Map<Suit, Integer>, Map<Rank, Integer>> matcher;

		private Type(BiPredicate<Map<Suit, Integer>, Map<Rank, Integer>> matcher) {
			this.matcher = matcher;
		}

		boolean matches(List<Card> cards) {
			List<Suit> suits = cards.stream().map(Card::getSuit).collect(Collectors.toList());
			Map<Suit, Integer> suitMap = suits.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(t -> 1)));
			List<Rank> ranks = cards.stream().map(Card::getRank).collect(Collectors.toList());
			Map<Rank, Integer> rankMap = ranks.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(t -> 1)));
			return matcher.test(suitMap, rankMap);
		}
	}
}

enum JQKMark {
	HEAD("| ( '-')| "),
	BODY("| /==$=|| "),
	BODY_REVERSE("||=$==/ | "),
	HEAD_REVERSE("|(,-,)  | "),;

	private String mark;

	private JQKMark(String mark) { this.mark = mark; }
	@Override public String toString() { return mark; }
}