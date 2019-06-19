package k73i55no5.refactorers.poker190615;

import static k73i55no5.refactorers.poker190615.JQKMark.*;
import static k73i55no5.refactorers.poker190615.RankConstants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

final class Main {

	public static void main(String[] args) {
		PokerController poker = new PokerController();
		poker.run();
	}
}

class PokerController {
	private final HandRepository handRepository = new HandRepository();

	void run() {
		// コンソールにプレーヤーの手札を表示
		Printer printer = new Printer();
		Player player = handRepository.getPlayer();
		printer.printHandsOf(player);
		// プレーヤーに手札交換の有無を確認
		printer.printExchange();
		// プレーヤーが手札を交換するならば、リポジトリに交換を命令する。
		handRepository.exchange();
		// 手札を判定し、コンソールに結果を表示する。
		Judger judger = new Judger();
		Computer computer = handRepository.getComputer();
		printer.printHandsOf(computer);
		printer.printHand(judger.judgeHand(computer));
		printer.printHandsOf(player);
		printer.printHand(judger.judgeHand(player));
		printer.printJudgement(judger.judgeScore(player, computer));
	}
}

class HandRepository {
	private final Computer computer;
	private final Player player;

	HandRepository() {
		// コンピューター、プレーヤーの順にカードをドロー
		computer = new Computer();
		player = new Player();
	}

	void exchange() {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < Card.QUANTITY; i++) {
			String str = sc.nextLine();
			if (str.equals("end")) break;
			int index = Integer.parseInt(str);
			player.exchangeAt(index);
		}
		sc.close();
	}

	Computer getComputer() { return computer; }
	Player getPlayer() { return player; }
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
	void printHandsOf(Human human) {
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
		for (HandConstants cst : HandConstants.values()) {
			Hand hand = cst.hand();
			if (hand.matches(cards)) human.setHand(hand);
		}
		return human.getHand().toString();
	}

	String judgeScore(Player player, Computer computer) {
		int player_score = player.getHand().score();
		int computer_score = computer.getHand().score();
		return (player_score > computer_score) ? "あなたの勝ちです" :
			(player_score < computer_score) ? "あなたの負けです" : "引き分けです";
	}
}

class Card implements Comparable<Card> {
	private SuitConstants suit;
	private RankConstants rank;
	// ジョーカーを除く52枚すべてのカードを示すオブジェクトを格納するイテレータ（以下「カード・イテレータ」という。）の初期化
	static final Iterator<Card> CARDS;
	// カードを引く枚数
	static final int QUANTITY = 5;

	// カード・イテレータの初期化
	static {
		// まずリストの生成
		List<Card> hands = new ArrayList<>() {{
			for (SuitConstants suit : SuitConstants.values()) {
				for (RankConstants rank : RankConstants.values())
					add(new Card(suit, rank));
			}
		}};
		// 予めシャッフルしておく。
		Collections.shuffle(hands);
		// イテレータに変換
		CARDS = hands.iterator();
	}

	// カードの初期化
	Card(SuitConstants suit, RankConstants rank) {
		this.suit = suit;
		this.rank = rank;
	}

	@Override public int compareTo(Card card) {
		return rank.compareTo(card.getRank());
	}

	@Override public String toString() {
		return suit.toString().trim() + rank.toString();
	}

	List<String> getLinesOfMark() { return rank.lines(); }
	RankConstants getRank() { return rank; }
	SuitConstants getSuit() { return suit; }
}

class Hand {
	private String name;
	private int score;
	private BiPredicate<Map<SuitConstants, Integer>, Map<RankConstants, Integer>> judger;

	Hand(
		String name,
		int score,
		BiPredicate<Map<SuitConstants, Integer>, Map<RankConstants, Integer>> judger)
	{
		this.name = name;
		this.score = score;
		this.judger = judger;
	}

	@Override public String toString() { return name; }

	boolean matches(List<Card> cards) {
		List<SuitConstants> suits = cards.stream().map(Card::getSuit).collect(Collectors.toList());
		Map<SuitConstants, Integer> suitMap = suits.stream()
			.collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(t -> 1)));
		List<RankConstants> ranks = cards.stream().map(Card::getRank).collect(Collectors.toList());
		Map<RankConstants, Integer> rankMap = ranks.stream()
			.collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(t -> 1)));
		return judger.test(suitMap, rankMap);
	}

	int score() { return score; }
}

abstract class Human {
	private final List<Card> cards;
	private Hand hand = new Hand("ノーペア", 0, null); // 手役判定用オブジェクトを必要としないためnull

	Human() {
		List<Card> cards = new ArrayList<>();
		// カード・リストの先頭から順に5枚ドロー
		for (int i = 0; i < Card.QUANTITY; i++) cards.add(Card.CARDS.next());
		// カードをセット
		this.cards = cards;
	}

	// 引数で指定した番目のカードを交換
	void exchangeAt(int index) {
		cards.set(index - 1, Card.CARDS.next());
	}

	List<Card> getCards() { return cards; }

	List<List<String>> getMarks() {
		return cards.stream().map(Card::getLinesOfMark).collect(Collectors.toList());
	}

	Hand getHand() { return hand; }
	void setHand(Hand hand) { this.hand = hand; }
}

class Computer extends Human {
	@Override public String toString() { return "コンピューター"; }
}

class Player extends Human {
	@Override public String toString() { return "プレーヤー"; }
}

enum HandConstants {
	ROYAL_STRAIGHT_FLUSH(new Hand("ロイヤルストレートフラッシュ", 9,
		(suitMap, rankMap) -> {
			return suitMap.size() == 1 &&
				rankMap.containsKey(TEN) &&
				rankMap.containsKey(JACK) &&
				rankMap.containsKey(QUEEN) &&
				rankMap.containsKey(KING) &&
				rankMap.containsKey(ACE);
		})),
	STRAIGHT_FLUSH(new Hand("ストレートフラッシュ", 8,
		(suitMap, rankMap) -> {
			RankConstants[] ranks = rankMap.keySet().toArray(RankConstants[]::new);
			Arrays.sort(ranks);
			return suitMap.size() == 1 &&
				ranks[1].number() == ranks[0].number() + 1 &&
				ranks[2].number() == ranks[1].number() + 1 &&
				ranks[3].number() == ranks[2].number() + 1 &&
				ranks[4].number() == ranks[3].number() + 1;
		})),
	FOUR_OF_A_KIND(new Hand("フォーカード", 7,
		(suitMap, rankMap) -> {
			return rankMap.containsValue(4);
		})),
	FULL_HOUSE(new Hand("フルハウス", 6,
		(suitMap, rankMap) -> {
			return rankMap.containsValue(3) && rankMap.containsValue(2);
		})),
	FLUSH(new Hand("フラッシュ", 5,
		(suitMap, rankMap) -> {
			return suitMap.size() == 1;
		})),
	STRAIGHT(new Hand("ストレート", 4,
		(suitMap, rankMap) -> {
			RankConstants[] ranks = rankMap.keySet().toArray(RankConstants[]::new);
			Arrays.sort(ranks);
			return (rankMap.containsKey(TEN) &&
				rankMap.containsKey(JACK) &&
				rankMap.containsKey(QUEEN) &&
				rankMap.containsKey(KING) &&
				rankMap.containsKey(ACE)) ||
				(ranks[1].number() == ranks[0].number() + 1 &&
				ranks[2].number() == ranks[1].number() + 1 &&
				ranks[3].number() == ranks[2].number() + 1 &&
				ranks[4].number() == ranks[3].number() + 1);
		})),
	THREE_OF_A_KIND(new Hand("スリーカード", 3,
		(suitMap, rankMap) -> {
			return rankMap.containsValue(3);
		})),
	TWO_PAIR(new Hand("ツーペア", 2,
		(suitMap, rankMap) -> {
			return rankMap.size() == 3 && rankMap.containsValue(2);
		})),
	ONE_PAIR(new Hand("ワンペア", 1,
		(suitMap, rankMap) -> {
			return rankMap.size() == 4 && rankMap.containsValue(2);
		})),;

	private Hand hand;

	private HandConstants(Hand hand) { this.hand = hand; }

	Hand hand() { return hand; }
}

enum SuitConstants {
	SPADE("スペード "),
	HEART(" ハート   "),
	DIAMOND(" ダイヤ  "),
	CLUB("クローバー "),;

	private String suit;

	private SuitConstants(String suit) { this.suit = suit; }
	@Override public String toString() { return suit; }
}

enum RankConstants {
	ACE(1, "|A      | ", "|       | ", "|   *   | ", "|       | ", "|       | "),
	TWO(2, "|2      | ", "|   *   | ", "|       | ", "|   *   | ", "|       | "),
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

	private RankConstants(int number, String... mark) {
		this.number = number;
		lines = new ArrayList<>() {{
			add(SIDES);
			for (String str : mark) add(str);
			add(SIDES);
		}};
	}

	@Override public String toString() { return String.valueOf(number); }
	int number() { return number; }
	List<String> lines() { return lines; }
}

class JQKMark {
	static final String HEAD = "| ( '-')| ";
	static final String BODY = "| /==$=|| ";
	static final String BODY_REVERSE = "||=$==/ | ";
	static final String HEAD_REVERSE = "|(,-,)  | ";
}