import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * TODO write documentation
 */
public class MicroAssembly {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println(new MicroAssembly().solve(in));
	}

	public String solve(Scanner in) {
		Registers registers = new Registers();
		registers.a.value = in.nextInt();
		registers.b.value = in.nextInt();
		registers.c.value = in.nextInt();
		registers.d.value = in.nextInt();

		int numOfInstructions = in.nextInt();
		in.nextLine();

		List<Instruction> lines = new ArrayList<>();

		for (int i = 0 ; i < numOfInstructions ; i++) {
			String[] parts = in.nextLine().split(" ");

			lines.add(createInstruction(parts, registers));
		}

		while (registers.pointer.getValue() < lines.size()) {
			final Instruction instruction = lines.get(registers.pointer.getValue());
			instruction.execute();
			if (!(instruction instanceof Jne)) {
				registers.pointer.value++;
			}
		}

		return registers.toString();
	}

	private Instruction createInstruction(String[] parts, Registers registers) {
		switch (parts[0]) {
			case "MOV":
				return new Mov(registers.register(parts[1]), createValue(parts[2], registers));
			case "ADD":
				return new Add(registers.register(parts[1]),
						createValue(parts[2], registers),
						createValue(parts[3], registers));
			case "SUB":
				return new Sub(registers.register(parts[1]),
						createValue(parts[2], registers),
						createValue(parts[3], registers));
			case "JNE":
				return new Jne(Integer.valueOf(parts[1]),
						registers.register(parts[2]),
						createValue(parts[3], registers),
						registers.pointer);
		}

		return null;
	}

	private Value createValue(String part, Registers registers) {
		Value value;
		if (isNumeric(part)) {
			value = new Numeric(Integer.valueOf(part));
		} else {
			value = registers.register(part);
		}
		return value;
	}


	private boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}

	public interface Instruction {
		void execute();
	}

	public static class Mov implements Instruction {
		Register to;
		Value from;

		public Mov(Register to, Value from) {
			this.to = to;
			this.from = from;
		}

		@Override
		public void execute() {
			to.value = from.getValue();
		}
	}

	public static class Add implements Instruction {
		Register to;
		Value one;
		Value two;

		public Add(Register to, Value one, Value two) {
			this.to = to;
			this.one = one;
			this.two = two;
		}

		@Override
		public void execute() {
			to.value = one.getValue() + two.getValue();
		}
	}

	public static class Sub implements Instruction {
		Register to;
		Value one;
		Value two;

		public Sub(Register to, Value one, Value two) {
			this.to = to;
			this.one = one;
			this.two = two;
		}

		@Override
		public void execute() {
			to.value = one.getValue() - two.getValue();
		}
	}

	public static class Jne implements Instruction {
		int dest;
		Register one;
		Value two;
		Register pointer;

		public Jne(int dest, Register one, Value two, Register pointer) {
			this.dest = dest;
			this.one = one;
			this.two = two;
			this.pointer = pointer;
		}

		@Override
		public void execute() {
			if (one.getValue() != two.getValue()) {
				pointer.value = dest;
			} else {
				pointer.value++;
			}
		}
	}

	public interface Value {
		int getValue();
	}
	
	public static class Numeric implements Value {
		int value;

		public Numeric(int value) {
			this.value = value;
		}

		@Override
		public int getValue() {
			return value;
		}
	}

	public static class Register implements Value {
		int value;

		public Register(int value) {
			this.value = value;
		}

		@Override
		public int getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}

	public static class Registers {
		Register a = new Register(0);
		Register b = new Register(0);
		Register c = new Register(0);
		Register d = new Register(0);
		Register pointer = new Register(0);

		public String toString() {
			return String.format("%s %s %s %s", a, b, c, d);
		}

		public Register register(String r) {
			switch (r) {
				case "a":
					return a;
				case "b":
					return b;
				case "c":
					return c;
				case "d":
					return d;
			}

			return null;
		}
	}
}
