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

	String getRegisterValues() {
		String registers = Stream.of(Register.values())
			.map(r -> String.valueOf(r.value()))
			.collect(Collectors.joining(" "));
		return registers;
	}
}

enum Command {
	SET("ST([ABC]) (-?\\d{1,3})", m -> {
		String register = m.group(1);
		int value = Integer.parseInt(m.group(2));
		Register.fromString(register).set(value);
	}),
	CALCULATE("(AD|SB)([ABC]) (-?\\d{1,3})", m -> {
		String operation = m.group(1);
		String register = m.group(2);
		int operand = Integer.parseInt(m.group(3));
		Register.fromString(register).calculate(operation, operand);
	}),
	PRINT("PR([ABC])", m -> {
		String register = m.group(1);
		Register.fromString(register).print();
	}),
	LOAD("LD([ABC]) ([ABC])", m -> {
		String target = m.group(1);
		Register source = Register.fromString(m.group(2));
		Register.fromString(target).load(source);
	}),
	CALCULATE_AND_LOAD("LD([ABC]) (-?\\d{1,3}),([abc])", m -> {
		String target = m.group(1);
		int operand = Integer.parseInt(m.group(2));
		Register source = Register.fromString(m.group(3).toUpperCase());
		Register.fromString(target).calculate(source, operand);
	}),
	INITIALIZE("IT([ABC])", m -> {
		String register= m.group(1);
		Register.fromString(register).reset();
	}),
	PRINT_ALL("PAL", m -> {
		String registers = Stream.of(Register.values())
			.map(r -> String.valueOf(r.value()))
			.collect(Collectors.joining(" "));
		System.out.println(registers);
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

	void set(int value) {
		this.value = value;
	}
	void calculate(String operation, int operand) {
		value = OPERATORS.get(operation).applyAsInt(this.value, operand);
	}
	void print() {
		System.out.println(value);
	}
	void load(Register source) {
		value = source.value();
	}
	void calculate(Register source, int operand) {
		value = source.value() + operand;
	}
	void reset() { value = 0; }
	int value() { return value; }
}