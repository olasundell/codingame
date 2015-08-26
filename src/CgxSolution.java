import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.Scanner;

class CgxSolution2 {
	public static void main(String[] args) throws IOException {
		System.out.println(CgxSolution.solve(new Scanner(System.in)));
	}
}

class CgxSolution {

	public static String solve(Scanner in) {
		int N = in.nextInt();
		State currentState = State.BEFORE_BEGIN;
		in.nextLine();

		String CGXLine = "";

		for (int i = 0; i < N; i++) {
			CGXLine += in.nextLine() + '\n';
		}

		StringBuffer output = new StringBuffer();
		int indentLevel = 0;
		char c = ' ';

		for (byte b: CGXLine.getBytes()) {
			c = (char)b;

			if (currentState != State.QUOTE_START && Character.isWhitespace(b)) {
				continue;
			}

			if (currentState == State.EQUALS && b == '(') {
				output.append('\n');
			}

			if (currentState == State.BLOCK_START && b != ')' ||
					currentState == State.EQUALS && b == '(' ||
					currentState == State.SEMICOLON) {
				for (int i = 0 ; i < indentLevel; i++) {
					output.append("    ");
				}
			}
			if (currentState == State.QUOTE_START && b != '\'') {
				output.append((char) b);
			} else {
				switch (b) {
				case ';':
					currentState = State.SEMICOLON;
					output.append(';');
					output.append('\n');
					break;
				case '=':
					output.append('=');
					currentState = State.EQUALS;
					break;
				case '\'':
					output.append('\'');
					if (currentState == State.QUOTE_START) {
						currentState = State.QUOTE_END;
					} else {
						currentState = State.QUOTE_START;
					}
					break;
				case '(':
					output.append("(\n");
					indentLevel++;
					currentState = State.BLOCK_START;
					break;
				case ')':
					indentLevel--;
					if (currentState != State.BLOCK_START) {
						output.append('\n');
					}
					for (int i = 0; i < indentLevel; i++) {
						output.append("    ");
					}
					output.append(')');
					currentState = State.BLOCK_END;
					break;
				default:
					if (Character.isDigit(b)) {
						output.append((char) b);
						currentState = State.NUMBER;
					} else if (Character.isAlphabetic(b)) {
						output.append((char) b);
						currentState = State.TEXT;
					}
					break;
				}
			}
		}

		return output.toString();
	}

	enum State {
		BEFORE_BEGIN,
		BLOCK_START,
		BLOCK_END,
		QUOTE_START,
		QUOTE_END,
		SEMICOLON,
		EQUALS,
		NUMBER,
		TEXT,
		LINEBREAK
	}

	public static String solve2(Scanner in) throws IOException {
		int N = in.nextInt();
		in.nextLine();

		String CGXLine = "";

		for (int i = 0; i < N; i++) {
			CGXLine += in.nextLine() + '\n';
		}

		StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(CGXLine));

		tokenizer.eolIsSignificant(false);
		tokenizer.quoteChar('\'');
		tokenizer.ordinaryChar('(');
		tokenizer.ordinaryChar(')');
		tokenizer.ordinaryChar(';');
		tokenizer.ordinaryChar('=');

		String output = "";
		int nestLevel = 0;
		int prevToken = 0;
		boolean addNest = false;

		for (;;) {
			tokenizer.nextToken();
			if (tokenizer.ttype == StreamTokenizer.TT_EOF) {
				break;
			}

			switch (tokenizer.ttype) {
			case StreamTokenizer.TT_NUMBER:
				if (addNest) {
					for (int i = 0 ; i < nestLevel ; i++) {
						output += "    ";
					}
					addNest = false;
				}
				output += (int)tokenizer.nval;
				break;
			case StreamTokenizer.TT_WORD:
				if (addNest) {
					for (int i = 0 ; i < nestLevel ; i++) {
						output += "    ";
					}
					addNest = false;
				}
				output += tokenizer.sval;
				break;
			case '\'':
				if (addNest) {
					for (int i = 0 ; i < nestLevel ; i++) {
						output += "    ";
					}
					addNest = false;
				}
				output += "'" + tokenizer.sval + "'";
				break;
			case '=':
				output += '=';
				break;
			case ';':
				output += ";\n";
				addNest = true;
				break;
			case '(':
				if (prevToken == '=') {
					output += '\n';
				}
				for (int i = 0 ; i < nestLevel ; i++) {
					output += "    ";
				}
				nestLevel++;
				output += "(\n";
				addNest = true;
				break;
			case ')':
				nestLevel--;
				if (prevToken != '(') {
					output += '\n';
				}
				for (int i = 0 ; i < nestLevel ; i++) {
					output += "    ";
				}
				output += ")";
				addNest = true;
			}

			if (tokenizer.ttype != StreamTokenizer.TT_EOL) {
				prevToken = tokenizer.ttype;
			}
		}

		return output;
	}
}
