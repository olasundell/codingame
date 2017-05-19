import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO write documentation
 */
public class HiddenWord {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println(new HiddenWord().solve(in));
	}

	public String solve(Scanner in) {
		int n = in.nextInt();
		in.nextLine();
		System.err.println("New run");

		Tree tree = createTree(in, n);
		Matrix matrix = createMatrix(in);

		crossAndDot(matrix, tree);

		return matrix.stream()
				.filter(MatrixCell::isEnabled)
				.map(MatrixCell::getValue)
				.map(String::valueOf)
				.collect(Collectors.joining());
	}

	private void crossAndDot(Matrix matrix, Tree tree) {
		matrix.downLeft.forEach(l -> checkCollection(tree, l));
		matrix.downRight.forEach(l -> checkCollection(tree, l));
		matrix.leftRight.forEach(l -> checkCollection(tree, l));
		matrix.upDown.forEach(l -> checkCollection(tree, l));
	}

	private void checkCollection(Tree tree, List<MatrixCell> col) {
		StringBuilder builder = new StringBuilder();
		List<MatrixCell> path = new ArrayList<>();

		for (int i = 0 ; i < col.size(); i++) {
			final MatrixCell cell = col.get(i);
			builder.append(cell.value);
			Result result = findWord(tree, builder.toString());
			if (result.isPartial()) {
				path.add(cell);
			} else {
				i -= path.size();
				if (result.isWhole()){
					path.add(cell);
					path.forEach(c -> c.setEnabled(false));
				}

				path.clear();
				builder.setLength(0);
			}
		}
	}

	private Result findWord(Tree tree, String s) {
		Result r = findWord(tree.root, s);
		Result r2 = findWord(tree.reverse, s);

		return new Result(r, r2);
	}

	private Result findWord(TreeNode node, String s) {
		if (s.isEmpty()) {
			return new Result(true, node.getChildren().isEmpty());
		}

		if (node.getChildren().containsKey(s.charAt(0))) {
			return findWord(node.getChildren().get(s.charAt(0)), s.substring(1));
		}

		return Result.NOPE;
	}

	protected static class Result {
		private final boolean match;
		private final boolean whole;

		static Result NOPE = new Result(false, false);

		public Result(boolean match, boolean whole) {
			this.match = match;
			this.whole = whole;
		}

		public Result(Result r, Result r2) {
			this.match = r.match || r2.match;
			this.whole = r.whole || r2.whole;
		}

		public boolean isPartial() {
			return match && !whole;
		}

		public boolean isWhole() {
			return match && whole;
		}

		@Override
		public String toString() {
			return "Result{" +
					"match=" + match +
					", whole=" + whole +
					'}';
		}
	}

	private Matrix createMatrix(Scanner in) {
		int xSize = in.nextInt();
		int ySize = in.nextInt();
		in.nextLine();
		Matrix matrix = new Matrix(xSize, ySize);

		for (int i = 0 ; i < ySize ; i++) {
			final char[] chars = in.nextLine().toCharArray();
			for (int j = 0 ; j < chars.length ; j++) {
				final MatrixCell cell = new MatrixCell(chars[j]);

				matrix.leftRight.get(i).add(cell);
				matrix.upDown.get(j).add(cell);
				matrix.downRight.get(j + (ySize - i)).add(cell);
				matrix.downLeft.get(j + i).add(cell);
			}
		}

		return matrix;
	}

	static class Matrix {
		private final List<List<MatrixCell>> upDown = new ArrayList<>();
		private final List<List<MatrixCell>> leftRight = new ArrayList<>();
		private final List<List<MatrixCell>> downRight = new ArrayList<>();
		private final List<List<MatrixCell>> downLeft = new ArrayList<>();

		Matrix(int xSize, int ySize) {
			for (int i = 0 ; i < ySize ; i++) {
				leftRight.add(new ArrayList<>());
				upDown.add(new ArrayList<>());
				downRight.add(new ArrayList<>());
				downRight.add(new ArrayList<>());
				downLeft.add(new ArrayList<>());
				downLeft.add(new ArrayList<>());
			}
		}

		@Override
		public String toString() {
			return leftRight.stream()
					.map(list -> list.stream()
							.map(matrixCell -> {
								if (matrixCell.isEnabled()) {
									return matrixCell.getValue();
								} else {
									return Character.toLowerCase(matrixCell.getValue());
								}
							})
							.map(String::valueOf)
							.collect(Collectors.joining()))
					.collect(Collectors.joining("\n"));
		}

		public Stream<MatrixCell> stream() {
			return leftRight.stream()
					.flatMap(List::stream);
		}
	}

	private Tree createTree(Scanner in, int n) {
		TreeNode root = new TreeNode(Character.MIN_VALUE);
		TreeNode reverse = new TreeNode(Character.MIN_VALUE);

		for (int i = 0 ; i < n ; i++) {
			final String line = in.nextLine();
			putWord(root, line);
			putWord(reverse, new StringBuilder(line).reverse().toString());
		}

		return new Tree(root, reverse);
	}

	static class Tree {
		final TreeNode root;
		final TreeNode reverse;

		public Tree(TreeNode root, TreeNode reverse) {
			this.root = root;
			this.reverse = reverse;
		}
	}

	void putWord(TreeNode node, String s) {
		if (s.isEmpty()) {
			return;
		}

		final char key = s.charAt(0);

		if (node.getChildren().containsKey(key)) {
			putWord(node.getChildren().get(key), s.substring(1));
		} else {
			final TreeNode child = new TreeNode(key);
			node.getChildren().put(key, child);
			putWord(child, s.substring(1));
		}
	}

	protected static class TreeNode {
		private final Map<Character, TreeNode> children = new HashMap<>();
		private final Character value;
		private int passes;

		public TreeNode(Character value) {
			this.value = value;
		}

		public Character getValue() {
			return value;
		}

		public Map<Character, TreeNode> getChildren() {
			return children;
		}

		@Override
		public String toString() {
			return "TreeNode{" +
					"children=" + children +
					", value=" + value +
					", passes=" + passes +
					'}';
		}
	}

	protected static class MatrixCell {
		private final Character value;
		private Boolean enabled;

		public MatrixCell(Character value) {
			this.value = value;
			this.enabled = true;
		}

		@Override
		public String toString() {
			return "MatrixCell{" +
					"value=" + value +
					", enabled=" + enabled +
					'}';
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

		public boolean isEnabled() {
			return enabled;
		}

		public Character getValue() {
			return value;
		}
	}
}
