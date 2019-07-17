
/*********************************************
 *  Agent.java 
 *  Sample Agent for Text-Based Adventure Game
 *  COMP3411/9414/9814 Artificial Intelligence
 *  UNSW Session 1, 2018
*/

import java.util.*;
import java.io.*;
import java.net.*;

public class Agent {

	int pointList[][] = { { -2, 2 }, { -1, 2 }, { 0, 2 }, { 1, 2 }, { 2, 2 }, { -2, 1 }, { -1, 1 }, { 0, 1 }, { 1, 1 },
			{ 2, 1 }, { -2, 0 }, { -1, 0 }, { 0, 0 }, { 1, 0 }, { 2, 0 }, { -2, -1 }, { -1, -1 }, { 0, -1 }, { 1, -1 },
			{ 2, -1 }, { -2, -2 }, { -1, -2 }, { 0, -2 }, { 1, -2 }, { 2, -2 } };

	// private Map<Coordinate, Vertex> map = new HashMap<Coordinate, Vertex>();
	private Queue<Character> pendingMoves = new LinkedList<Character>();
	private Vertex[][] globalMap = new Vertex[160][160];;
	Man m = new Man();
	Queue<Character> temp = new LinkedList<Character>();
	private char lastAction = 'Z';
	private Queue<Character> move = new LinkedList<Character>();
	public Agent(){
		Vertex first = new Vertex();
		globalMap[80][80] = first;
		globalMap[80][80].setVisited(true);
		globalMap[80][80].setObj(' ');
		globalMap[80][80].setX(80);
		globalMap[80][80].setY(80);
	}
	public Queue<Character> makeMove(Vertex start, Vertex goal) {
		Queue<Character> moveQueue = new LinkedList<Character>();
		// move up
		if (goal.getY() - start.getY() > 0 && goal.getX() - start.getX() == 0) {
			if (m.getDirection() == 0) {
				moveQueue.add('f');
				return moveQueue;
			} else if (m.getDirection() == 1) {
				moveQueue.add('r');
				moveQueue.add('f');
				return moveQueue;
			} else if (m.getDirection() == 2) {
				moveQueue.add('r');
				moveQueue.add('r');
				moveQueue.add('f');
				return moveQueue;
			} else if (m.getDirection() == 3) {
				moveQueue.add('l');
				moveQueue.add('f');
				return moveQueue;
			}
		} else if (goal.getY() - start.getY() < 0 && goal.getX() - start.getX() == 0) {
			if (m.getDirection() == 0) {
				moveQueue.add('l');
				moveQueue.add('l');
				moveQueue.add('f');
				return moveQueue;
			} else if (m.getDirection() == 1) {
				moveQueue.add('l');
				moveQueue.add('f');
				return moveQueue;
			} else if (m.getDirection() == 2) {
				moveQueue.add('f');
				return moveQueue;
			} else if (m.getDirection() == 3) {
				moveQueue.add('r');
				moveQueue.add('f');
				return moveQueue;
			}
		}
		if (goal.getX() - start.getX() > 0 && goal.getY() - start.getY() == 0) {
			if (m.getDirection() == 0) {
				moveQueue.add('r');
				moveQueue.add('f');
				return moveQueue;
			} else if (m.getDirection() == 1) {
				moveQueue.add('r');
				moveQueue.add('r');
				moveQueue.add('f');
				return moveQueue;
			} else if (m.getDirection() == 2) {
				moveQueue.add('r');
				moveQueue.add('f');
				return moveQueue;
			} else if (m.getDirection() == 3) {
				moveQueue.add('f');
				return moveQueue;
			}
		} else if (goal.getX() - start.getX() < 0 && goal.getY() - start.getY() == 0) {
			if (m.getDirection() == 0) {
				moveQueue.add('l');
				moveQueue.add('f');
				return moveQueue;
			} else if (m.getDirection() == 1) {
				moveQueue.add('f');
				return moveQueue;
			} else if (m.getDirection() == 2) {
				moveQueue.add('l');
				moveQueue.add('f');
				return moveQueue;
			} else if (m.getDirection() == 3) {
				moveQueue.add('l');
				moveQueue.add('l');
				moveQueue.add('f');
				return moveQueue;
			}
		}
		return null;
	}

	public void moveAlgorithm(Vertex goal) {
		
		Astar a = new Astar(globalMap);
		Vertex mv = new Vertex();
		mv.setX(m.getX());
		mv.setY(m.getY());
		Vertex parent = a.AstarSearch(mv, goal);
		ArrayList<Vertex> pathList = new ArrayList<Vertex>();
		while (parent != null) {
			Vertex v = new Vertex();
			v.setX(parent.getX());
			v.setY(parent.getY());
			pathList.add(v);
			parent = parent.getParent();
			
			//globalMap[parent.getY()][parent.getX()].setVisited(true);//set visited
		}
		//System.out.println("<<<<<<<");
		Collections.reverse(pathList);
		for(int i =0;i<pathList.size();i++){
			System.out.println("coo"+pathList.get(i).getX()+","+pathList.get(i).getY());
		}
		for (int i = 0; i < pathList.size()-1; i++) {
			//if(!makeMove(pathList.get(i), pathList.get(i + 1)).isEmpty()){
				//System.out.println("tttt");
				move.add(makeMove(pathList.get(i), pathList.get(i + 1)).poll());
				//System.out.println(move);
			//}
			//System.out.println("<<<<");
			//if (i + 1 == pathList.size()) {
			//	break;
			//}
		}
	}

	public Vertex getVision() {
		int k = 1;
		int findFlag = 0;
		int finished = 0;
		Vertex findVertex;
		// Queue<Vertex> pointQueue = new LinkedList<Vertex>();
		while (true) {

			System.out.println("1."+globalMap[80][81].isVisited());
			Queue<Vertex> pointQueue = new LinkedList<Vertex>();
			for (int n = 0; n <= k/2; n++) {
				int tempY = (m.getY() + (k - n));// 1
				int tempX = (m.getX() + n);
				Vertex a = globalMap[tempY][tempX];
			
				if(a != null&& a.getObj()!='~'){
					pointQueue.add(a);
				}
				
				tempY = (m.getY() + (k - n));// 1
				tempX = (m.getX() - n);
				Vertex b = globalMap[tempY][tempX];
				if(b != null &&b.getObj()!='~'){
					pointQueue.add(b);
				}
				tempY = (m.getY() - (k - n));// 1
				tempX = (m.getX() + n);
				Vertex c = globalMap[tempY][tempX];
				if(c != null&&c.getObj()!='~'){
					pointQueue.add(c);
				}
				tempY = (m.getY() - (k - n));// 1
				tempX = (m.getX() - n);
				//Vertex d = globalMap[tempY][tempX];
				if(globalMap[tempY][tempX] != null && globalMap[tempY][tempX].getObj()!='~'){
					pointQueue.add(globalMap[tempY][tempX]);
					System.out.println("d."+globalMap[tempY][tempX].isVisited()+tempX+","+tempY);
					System.out.println("2."+globalMap[81][80].isVisited());
				}
				//System.out.println("2."+globalMap[80][81].isVisited());
				//System.out.println("d."+d.isVisited());
			}
			if (pointQueue.isEmpty()) {
				//System.out.println("tttt");
				finished = 1;
				break;
			}

			while (!pointQueue.isEmpty()) {
				
				Vertex tempV = pointQueue.poll();
				System.out.println("temp"+tempV.getX()+","+tempV.getY()+""+tempV.isVisited());
				if (tempV.isVisited() == false) {
					
					findFlag = 1;
					findVertex = tempV;
		
					System.out.println("ve"+findVertex.getX()+","+findVertex.getY());
					return findVertex;
					// break;
				}
			}
			k += 1;
		}
		return null;

	}

	public void addToGobalMap(char view[][]) {
		// up
		// System.out.println(m.getDirection());
		if (m.getDirection() == 0) {
			for (int i = 0; i < 25; i++) {
				int x = pointList[i][0];
				int y = pointList[i][1];

				int globalx = x;
				int globaly = y;

				globalx = globalx + m.getX();
				globaly = globaly + m.getY();

				if (globalMap[globaly][globalx] == null) {
					Vertex v = new Vertex();

					globalMap[globaly][globalx] = v;
					globalMap[globaly][globalx].setObj(view[4 - (y + 2)][x + 2]);
					globalMap[globaly][globalx].setX(globalx);
					globalMap[globaly][globalx].setY(globaly);
				}

			}
		}
		// left

		if (m.getDirection() == 1) {
			for (int i = 0; i < 25; i++) {
				//System.out.println("left");
				int x = pointList[i][0];
				int y = pointList[i][1];
				int globalx = -y;
				int globaly = x;

				globalx = globalx + m.getX();
				globaly = globaly + m.getY();

				if (globalMap[globaly][globalx] == null) {
					Vertex v = new Vertex();
					globalMap[globaly][globalx] = v;
					globalMap[globaly][globalx].setObj(view[4 - (y + 2)][(x + 2)]);
					globalMap[globaly][globalx].setX(globalx);
					globalMap[globaly][globalx].setY(globaly);

					//System.out.println(view[4 - (y + 2)][x + 2]);
					int a = 4 - (y + 2);
					int b = x + 2;

				}
			}
		}
		// down
		if (m.getDirection() == 2) {
			for (int i = 0; i < 25; i++) {

				int x = pointList[i][0];
				int y = pointList[i][1];
				int globalx = -x;
				int globaly = -y;
				// System.out.println("down");

				globalx = globalx + m.getX();
				globaly = globaly + m.getY();
				// System.out.println("man"+m.getX()+","+ m.getY());
				System.out.println("global" + globalx + "," + globaly);

				if (globalMap[globaly][globalx] == null) {
					Vertex v = new Vertex();
					globalMap[globaly][globalx] = v;
					globalMap[globaly][globalx].setObj(view[4 - (y + 2)][(x + 2)]);
					globalMap[globaly][globalx].setX(globalx);
					globalMap[globaly][globalx].setY(globaly);
					//System.out.println(view[4 - (y + 2)][x + 2]);
					int a = 4 - (y + 2);
					int b = x + 2;
					//System.out.println("view" + a + "," + b);
				}
			}
		}
		// right
		if (m.getDirection() == 3) {
			for (int i = 0; i < 25; i++) {

				int x = pointList[i][0];
				int y = pointList[i][1];
				int globalx = y;
				int globaly = -x;

				globalx = globalx + m.getX();
				globaly = globaly + m.getY();

				if (globalMap[globaly][globalx] == null) {
					Vertex v = new Vertex();
					globalMap[globaly][globalx] = v;
					globalMap[globaly][globalx].setObj(view[4 - (y + 2)][(x + 2)]);
					globalMap[globaly][globalx].setX(globalx);
					globalMap[globaly][globalx].setY(globaly);

				}
			}
		}

		for (int i = 90; i > 70; i--) {
			for (int j = 70; j < 90; j++) {
				if (globalMap[i][j] != null) {
					System.out.print(globalMap[i][j].getObj());
				} else {
					System.out.print("n");
				}
			}
			System.out.println("");
		}
	}

	public void map(char view[][]) {
		Vertex v2 = new Vertex();// goal
		Vertex v1 = new Vertex();// start

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				// System.out.print(view[i][j]);
				if (view[i][j] == '$') {
					v2.setX(i);
					v2.setY(j);
				}
				if (view[i][j] == '^') {
					v1.setX(i);
					v1.setY(j);
				}
			}
		}
		v1.setX(2);
		v1.setY(2);

		Astar a = new Astar(view);
		Vertex parent = a.AstarSearch(v1, v2);
		
		ArrayList<Vertex> arrayList = new ArrayList<Vertex>();
		while (parent != null) {
			
			Vertex v = new Vertex();
			v.setX(parent.getX());
			v.setY(parent.getY());
			arrayList.add(v);
			parent = parent.getParent();

			// pendingMoves =m.addToPendingMoves(start, goal, 0);
		} 
		Collections.reverse(arrayList);

		for (int i = 0; i < arrayList.size() - 1; i++) {
			Move m = new Move();
			m.addToPendingMoves(arrayList.get(i), arrayList.get(i + 1), 0, pendingMoves);
			// pendingMoves.add(e)m.addToPendingMoves(arrayList.get(i),
			// arrayList.get(i+1), 0);
			// System.out.println("x" + arrayList.get(i).getX() + "y" +
			// arrayList.get(i).getY());
			// System.out.println(pendingMoves);
			// System.out.println("y"+arrayList.get(i).getY());
		}

	}

	public char get_action(char view[][]) {
		view[2][2] = ' ';
		//moveAlgorithm(getVision());
		 //System.out.println("vertex"+getVision());
		// map(view);
		
		// addToGobalMap(view);
		System.out.println(lastAction);
		if (lastAction == 'l') {
			m.setDirection(m.getDirection() + 1);
		} else if (lastAction == 'r') {
			m.setDirection(m.getDirection() - 1);
		} else if (lastAction == 'f') {
			m.setDirection(m.getDirection());
			if (m.getDirection() == 0) {
				m.setY(m.getY() + 1);
			} else if (m.getDirection() == 1) {
				m.setX(m.getX() - 1);
			} else if (m.getDirection() == 2) {
				System.out.println("ttt");
				m.setY(m.getY() - 1);
			} else if (m.getDirection() == 3) {
				m.setX(m.getX() + 1);
			}
		}
		

		
		globalMap[m.getY()][m.getX()].setVisited(true);
		//System.out.println("ttttt");
		System.out.println("m"+m.getX()+","+m.getY() +globalMap[m.getY()][m.getX()].isVisited());
		addToGobalMap(view);
		//System.out.println("m"+m.getX()+","+m.getY());
		
		//globalMap[m.getX()][m.getY()].setVisited(true);
		
		Vertex t = getVision();

		moveAlgorithm(t);
		//System.out.println(globalMap[m.getX()][m.getY()].getVisited());
		char action = 0;
		//System.out.println(m.getDirection());
		System.out.println(move);
		while(!move.isEmpty()){
			action = move.poll();
		}
		/*
		if (temp.isEmpty()) {
			temp.add('l');
			// temp.add('l');
			temp.add('f');
			temp.add('f');
			temp.add('l');
			temp.add('f');

			temp.add('l');
			temp.add('l');
			temp.add('f');
			temp.add('r');
			temp.add('f');
			temp.add('f');

		} else {
			action = temp.poll();
		}
		*/
		// System.out.println(m.getX() + "," + m.getY());

		// Man m = new Man();
		// char firstStep = 'f';
		/*
		 * while (!pendingMoves.isEmpty()) { //addToGobalMap(view);
		 * System.out.println("ttt"); return pendingMoves.poll(); }
		 */
		// return 'r';
		lastAction = action;
		return action;

		// return 'f';
		// return pendingMoves.poll();
		// System.out.println("ttt");
		// System.out.println(char view[][]);
		/*
		 * // REPLACE THIS CODE WITH AI TO CHOOSE ACTION
		 * 
		 * int ch = 0;
		 * 
		 * System.out.print("Enter Action(s): ");
		 * 
		 * try { while (ch != -1) { // read character from keyboard ch =
		 * System.in.read();
		 * 
		 * switch (ch) { // if character is a valid action, return it case 'F':
		 * case 'L': case 'R': case 'C': case 'U': case 'f': case 'l': case 'r':
		 * case 'c': case 'u': return ((char) ch); } } } catch (IOException e) {
		 * System.out.println("IO error:" + e); }
		 * 
		 * return 0;
		 */
		// return 0;
	}

	void print_view(char view[][]) {
		int i, j;

		System.out.println("\n+-----+");
		for (i = 0; i < 5; i++) {
			System.out.print("|");
			for (j = 0; j < 5; j++) {
				if ((i == 2) && (j == 2)) {
					System.out.print('^');
				} else {
					System.out.print(view[i][j]);
				}
			}
			System.out.println("|");
		}
		System.out.println("+-----+");
	}

	public static void main(String[] args) {
		InputStream in = null;
		OutputStream out = null;
		Socket socket = null;
		Agent agent = new Agent();
		char view[][] = new char[5][5];
		char action = 'F';
		int port;
		int ch;
		int i, j;
		//globalMap = new Vertex[160][160];
		

		if (args.length < 2) {
			System.out.println("Usage: java Agent -p <port>\n");
			System.exit(-1);
		}

		port = Integer.parseInt(args[1]);

		try { // open socket to Game Engine
			socket = new Socket("localhost", port);
			in = socket.getInputStream();
			out = socket.getOutputStream();
		} catch (IOException e) {
			System.out.println("Could not bind to port: " + port);
			System.exit(-1);
		}

		try { // scan 5-by-5 wintow around current location
			while (true) {
				for (i = 0; i < 5; i++) {
					for (j = 0; j < 5; j++) {
						if (!((i == 2) && (j == 2))) {
							ch = in.read();
							if (ch == -1) {
								System.exit(-1);
							}
							view[i][j] = (char) ch;
							// System.out.println("ttttttttttttt");
						}
					}
				}
				agent.print_view(view); // COMMENT THIS OUT BEFORE SUBMISSION
				action = agent.get_action(view);
				// System.out.println("ttttttttttttt");
				out.write(action);
			}
		} catch (IOException e) {
			System.out.println("Lost connection to port: " + port);
			System.exit(-1);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}
}
