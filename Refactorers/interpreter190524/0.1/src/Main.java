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

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Interpreter interpreter = new Interpreter();
		try {
			while (true) interpreter.run(sc.nextLine());
		} catch (Exception e) {
			sc.close();
		}
	}
}

class Interpreter {
	void run(String str) {
		for (CommandConstants cst : CommandConstants.values()) {
			Matcher m = cst.command().matcher(str);
			if (m.matches()) { cst.command().consumer().accept(m); break; }
		}
	}
}

class Command {
	private Pattern p;
	private Consumer<Matcher> consumer;

	Command(String regex, Consumer<Matcher> consumer) {
		p = Pattern.compile(regex);
		this.consumer = consumer;
	}

	Matcher matcher(String input) { return p.matcher(input); }
	Consumer<Matcher> consumer() { return consumer; }
}

class Register {
	private static final Map<String, IntBinaryOperator> OPERATORS = new HashMap<>() {{
		put("AD", (l, r) -> l + r);
		put("SB", (l, r) -> l - r);
	}};

	private int value;

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

enum RegisterConstants {
	A(new Register()),
	B(new Register()),
	C(new Register()),;

	private Register register;

	private RegisterConstants(Register register) { this.register = register; }
	int value() { return register.value(); }
	static Register get(String register) { return valueOf(register).register; }
}

enum CommandConstants {
	SETTER(new Command("ST([ABC]) (-?\\d{1,3})",
		m -> {
			String register = m.group(1);
			int value = Integer.parseInt(m.group(2));
			RegisterConstants.get(register).set(value);
		})),
	CALCULATOR(new Command("(AD|SB)([ABC]) (-?\\d{1,3})",
		m -> {
			String operation = m.group(1);
			String register = m.group(2);
			int operand = Integer.parseInt(m.group(3));
			RegisterConstants.get(register).calculate(operation, operand);
		})),
	PRINTER(new Command("PR([ABC])",
		m -> {
			String register = m.group(1);
			RegisterConstants.get(register).print();
		})),
	LOADER(new Command("LD([ABC]) ([ABC])",
		m -> {
			String target = m.group(1);
			Register source = RegisterConstants.get(m.group(2));
			RegisterConstants.get(target).load(source);
		})),
	CALCULATABLE_LOADER(new Command("LD([ABC]) (-?\\d{1,3}),([abc])",
		m -> {
			String target = m.group(1);
			int operand = Integer.parseInt(m.group(2));
			Register source = RegisterConstants.get(m.group(3).toUpperCase());
			RegisterConstants.get(target).calculate(source, operand);
		})),
	INITIALIZER(new Command("IT([ABC])",
		m -> {
			String register= m.group(1);
			RegisterConstants.get(register).reset();
		})),
	ALL_PRINTER(new Command("APR",
		m -> {
			String registers = Stream.of(RegisterConstants.values())
				.map(r -> String.valueOf(r.value()))
				.collect(Collectors.joining(" "));
			System.out.println(registers);
		})),;

	private Command command;

	private CommandConstants(Command command) { this.command = command; }
	Command command() { return command; }
}

