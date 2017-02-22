import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inertia {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println(new Inertia().solve(in));
	}

	protected String solve(Scanner in) {
		int inertia = in.nextInt();
		int W = in.nextInt();
		int H = in.nextInt();
		State state = new State(W, inertia);
		in.nextLine();
		for (int i = 0; i < H; i++) {
			char[] chars = in.nextLine().toCharArray();

			for (int j = 0 ; j < W ; j++) {
				switch (chars[j]) {
					case '_':
						state.track.set(j, Track.HORIZONTAL);
						break;
					case '/':
						state.track.set(j, Track.ASCENT);
						break;
					case '\\':
						state.track.set(j, Track.DESCENT);
						break;
				}
			}
		}

//		for (int i = 0; i < 10_000; i++) {
		for (int i = 0; i < 10_000_000; i++) {
			int delta = applyInertia(state);
//			System.err.println(String.format("% 5d: %02d % 4d % 3d %s",
//					i,
//					state.currentPos,
//					state.currentInertia,
//					delta,
//					state.getCurrentTrackType()));

			if (stop(state)) {
				return String.valueOf(state.currentPos);
			}

			move(state);
		}

		return "-1";
	}

	private boolean stop(State state) {
		if (state.currentInertia == 0 && state.getCurrentTrackType() == Track.HORIZONTAL) {
			return true;
		}

		if (state.currentPos == 0 && state.currentInertia < 0) {
			return true;
		}

		if (state.currentPos == state.width - 1 && state.currentInertia > 0) {
			return true;
		}

		return false;
	}

	private int applyInertia(State state) {
		Track trackType = state.getCurrentTrackType();
		int delta = trackType.getDelta();
		delta -= friction(state);

		state.currentInertia = state.currentInertia + delta;
		return delta;
	}

	private void move(State state) {
		if (state.currentInertia > 0) {
			state.currentPos++;
		} else if (state.currentInertia < 0){
			state.currentPos--;
		}
	}

	private int friction(State state) {
		if (state.currentInertia > 0  && state.getCurrentTrackType() != Track.ASCENT) {
			return 1;
		} else if (state.currentInertia < 0 && state.getCurrentTrackType() != Track.DESCENT) {
			return -1;
		}

		return 0;
	}

	class State {
		final List<Track> track;
		final int width;
		int currentInertia;
		int currentPos;

		State(int width, int initialInertia) {
			this.width = width;
			track = new ArrayList<>(width);

			for (int i = 0 ; i < width ; i++) {
				track.add(Track.HORIZONTAL);
			}

			currentInertia = initialInertia;
			currentPos = 0;
		}

		Track getCurrentTrackType() {
			return this.track.get(this.currentPos);
		}
	}

	enum Track {
		HORIZONTAL(0),
		DESCENT(10),
		ASCENT(-10);

		int delta;

		Track(int delta) {
			this.delta = delta;
		}

		public int getDelta() {
			return delta;
		}
	}
}
