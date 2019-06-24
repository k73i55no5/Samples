package k73i55no5.refactorers.interpreter190524;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class Main {

	public static void main(String[] args) {
		Interpreter interpreter = new Interpreter();
		try (Scanner sc = new Scanner(System.in)) {
			while (true) interpreter.run(sc.nextLine());
		}
	}
}

class Interpreter {
	void run(String str) {
		for (Command command : Command.values()) {
			Matcher m = command.matcher(str);
			if (m.matches()) { command.consumer().accept(m); break; }
		}
	}
	// テスト用
	String getRegisterValues() { return Register.getRegisterValues(); }
}

enum Command {
	SET("ST([ABC]) (-?\\d{1,3})", m -> {
		Register.fromString(m.group(1)).set(m.group(2));
	}),
	CALCULATE("(AD|SB)([ABC]) (-?\\d{1,3})", m -> {
		Register.fromString(m.group(2)).calculate(m.group(1), m.group(3));
	}),
	PRINT("PR([ABC])", m -> {
		Register.fromString(m.group(1)).print();
	}),
	LOAD("LD([ABC]) ([ABC])", m -> {
		Register.fromString(m.group(1)).load(m.group(2));
	}),
	CALCULATE_AND_LOAD("LD([ABC]) (-?\\d{1,3}),([abc])", m -> {
		Register.fromString(m.group(1)).calculateAndLoad(m.group(3).toUpperCase(), m.group(2));
	}),
	INITIALIZE("IT([ABC])", m -> {
		Register.fromString(m.group(1)).reset();
	}),
	PRINT_ALL("PAL", m -> {
		System.out.println(Register.getRegisterValues());
	}),
	END("(end|END)", m -> {
		System.exit(0);
	}),;

	private final Pattern p;
	private final Consumer<Matcher> consumer;

	private Command(String regex, Consumer<Matcher> consumer) {
		p = Pattern.compile(regex);
		this.consumer = consumer;
	}

	Matcher matcher(String input) { return p.matcher(input); }
	Consumer<Matcher> consumer() { return consumer; }
}

enum Register {
	A,
	B,
	C,;

	private static final Map<String, IntBinaryOperator> OPERATORS = new HashMap<>() {{
		put("AD", (l, r) -> l + r);
		put("SB", (l, r) -> l - r);
	}};

	private int value;

	static Register fromString(String register) { return valueOf(register); }

	static String getRegisterValues() {
		String registers = Stream.of(values())
			.map(r -> String.valueOf(r.value()))
			.collect(Collectors.joining(" "));
		return registers;
	}

	void set(String value) {
		this.value = Integer.parseInt(value);
	}
	void calculate(String operation, String operand) {
		value = OPERATORS.get(operation).applyAsInt(value, Integer.parseInt(operand));
	}
	void print() {
		System.out.println(value);
	}
	void load(String source) {
		value = fromString(source).value();
	}
	void calculateAndLoad(String source, String operand) {
		value = fromString(source).value() + Integer.parseInt(operand);
	}
	void reset() { value = 0; }

	int value() { return value; }
}