import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * TODO write documentation
 */
public class AncestorsDescendants {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println(new AncestorsDescendants().solve(in));
	}

	public String solve(Scanner in) {
		in.nextLine();

		List<Person> path = new ArrayList<>();
		List<Person> roots = new ArrayList<>();

		while (in.hasNextLine()) {
			String line = in.nextLine();
			int currDepth = depth(line);

			final Person person = new Person(line.substring(currDepth));

			if (roots.isEmpty()) {
				path.add(person);
				roots.add(person);
			} else {
				if (currDepth == 0) {
					roots.add(person);
					path.clear();
				} else {
					path = path.subList(0, currDepth);
					path.get(path.size() - 1).addChild(person);
				}
				path.add(person);
			}
		}

		List<String> result = new ArrayList<>();

		roots.forEach(p -> p.print(result));

		return result.stream().collect(Collectors.joining("\n"));
	}

	private int depth(String line) {
		int currDepth = 0;
		for (int i = 0 ; i < line.length() ; i++) {
			if (line.charAt(i) == '.') {
				currDepth++;
			} else {
				break;
			}
		}

		return currDepth;
	}

	public static class Person {
		final UUID id;
		final String name;
		final List<Person> children;

		public Person(String name) {
			this.name = name;
			id = UUID.randomUUID();
			children = new ArrayList<>();
		}

		public void addChild(Person child) {
			children.add(child);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Person person = (Person) o;

			return id.equals(person.id);
		}

		@Override
		public int hashCode() {
			return id.hashCode();
		}

		public String getName() {
			return name;
		}

		private void print(List<Person> path, List<String> result) {
			final Person currentPerson = path.get(path.size() - 1);
			if (currentPerson.children.isEmpty()) {
				result.add(path.stream().map(Person::getName).collect(Collectors.joining(" > ")));
			} else {
				currentPerson.children.forEach(child -> {
					final List<Person> newList = new ArrayList<>(path);
					newList.add(child);

					child.print(newList, result);
				});
			}
		}

		public void print(List<String> result) {
			if (children.isEmpty()) {
				result.add(name);
			} else {
				// stream instead, add name here instead of at the bottom.
				children.forEach(child -> child.print(Collections.singletonList(this), result));
			}
		}

		public String toString() {
			return name;
		}
	}
}
