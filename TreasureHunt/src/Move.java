import java.util.LinkedList;
import java.util.Queue;

public class Move {
	//private Queue<Character> pendingMoves = new LinkedList<Character>();

	// direction up:0 left:1 down:2 right :3
	public void makeMove(){
		
	}
	public void addToPendingMoves(Vertex start, Vertex goal, int direction,Queue<Character> pendingMoves) {
		// move left
		if (start.getY() - goal.getY() > 0) {
			if (direction == 0) {
				pendingMoves.add('l');
				pendingMoves.add('f');
				pendingMoves.add('f');
				//return pendingMoves;
			}
		}
		if (start.getX() - goal.getX() < 0) {
			if (direction == 1) {
				pendingMoves.add('l');
				pendingMoves.add('f');
				//return pendingMoves;
			}
		}
		//return pendingMoves;
	}
	public void addToPendingMoves2(Vertex start, Vertex goal, int direction,Queue<Character> pendingMoves) {
		// move left
		if (start.getY() - goal.getY() > 0) {
			if (direction == 0) {
				pendingMoves.add('l');
				int steps= start.getY() - goal.getY();
				for(int i=0;i<steps;i++){
					pendingMoves.add('f');
				}
				//return pendingMoves;
			}
		}
		if (start.getX() - goal.getX() < 0) {
			if (direction == 1) {
				pendingMoves.add('l');
				pendingMoves.add('f');
				//return pendingMoves;
			}
		}
		//return pendingMoves;
	}
}
