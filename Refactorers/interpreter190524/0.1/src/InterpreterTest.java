package k73i55no5.refactorers.interpreter190524;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

final class InterpreterTest {

	@Test void testRun() {
		Interpreter interpreter = new Interpreter();
		interpreter.run("STA 10");
		assertEquals(interpreter.getRegisterValues(), "10 0 0");
		interpreter.run("STB -5");
		assertEquals(interpreter.getRegisterValues(), "10 -5 0");
		interpreter.run("STC 3");
		assertEquals(interpreter.getRegisterValues(), "10 -5 3");
		interpreter.run("PAL");
		assertEquals(interpreter.getRegisterValues(), "10 -5 3");
		interpreter.run("ADA 5");
		assertEquals(interpreter.getRegisterValues(), "15 -5 3");
		interpreter.run("SBB 10");
		assertEquals(interpreter.getRegisterValues(), "15 -15 3");
		interpreter.run("SBC -30");
		assertEquals(interpreter.getRegisterValues(), "15 -15 33");
		interpreter.run("PAL");
		assertEquals(interpreter.getRegisterValues(), "15 -15 33");
		interpreter.run("PRA");
		assertEquals(interpreter.getRegisterValues(), "15 -15 33");
		interpreter.run("PRC");
		assertEquals(interpreter.getRegisterValues(), "15 -15 33");
		interpreter.run("LDC B");
		assertEquals(interpreter.getRegisterValues(), "15 -15 -15");
		interpreter.run("LDC -10,b");
		assertEquals(interpreter.getRegisterValues(), "15 -15 -25");
		interpreter.run("ITA");
		assertEquals(interpreter.getRegisterValues(), "0 -15 -25");
		interpreter.run("PAL");
		assertEquals(interpreter.getRegisterValues(), "0 -15 -25");
	}
}
