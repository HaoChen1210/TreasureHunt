import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Astar {
	char[][] view;
	Vertex[][] globalMap;
	public Astar(char[][] view) {
		this.view = view;
	
	}
	public Astar(Vertex[][] globalMap) {
		this.globalMap = globalMap;
	
	}


	public static final int STEP = 10;

	public Vertex AstarSearch(Vertex start, Vertex goal) {

		int hCost = 0;
		int gCost = 0;
		ArrayList<Vertex> path = new ArrayList<Vertex>();
		ArrayList<Vertex> openList = new ArrayList<Vertex>();
		ArrayList<Vertex> closed = new ArrayList<Vertex>();
		openList.add(start);
		while (openList.size() > 0) {
			Vertex curr = findMinFNodeInOpneList(openList);
			openList.remove(curr);			
			closed.add(curr);			
			ArrayList<Vertex> neighborNodes = findNeighborNodes(curr, closed, openList);		
			for (Vertex v : neighborNodes) {
				if (exists(openList, v)) {		
					foundPoint(curr, v);
				} else {			
					notFoundPoint(curr, goal, v, openList);					
				}
			}
			if (find(openList, goal) != null) {
				return find(openList, goal);
			}
		}
		return find(openList, goal);
	}

	public Vertex findMinFNodeInOpneList(ArrayList<Vertex> openList) {
		Vertex tempVertex = openList.get(0);
		for (Vertex v : openList) {
			if (v.getfValue() < tempVertex.getfValue()) {
				tempVertex = v;
			}
		}
		return tempVertex;
	}

	public static Vertex find(List<Vertex> openList, Vertex point) {
		for (Vertex v : openList) {
			if ((v.getX() == point.getX()) && (v.getY() == point.getY())) {
				return v;
			}
		}
		return null;
	}

	private int calcG(Vertex start, Vertex v) {
		int G = STEP;
		int parentG = v.getParent() != null ? v.getParent().getgValue() : 0;
		return G + parentG;
	}

	private int calcH(Vertex end, Vertex v) {
		int step = Math.abs(v.getX() - end.getX()) + Math.abs(v.getY() - end.getY());
		return step * STEP;
	}

	private void foundPoint(Vertex tempStart, Vertex v) {
		int G = calcG(tempStart, v);
		if (G < v.getgValue()) {
			v.setParent(tempStart);
			v.setgValue(G);
			v.calcF();
		}
	}

	private void notFoundPoint(Vertex tempStart, Vertex end, Vertex v, ArrayList<Vertex> openList) {
		// System.out.println("ttt");
		v.setParent(tempStart);
		v.setgValue(calcG(tempStart, v));
		v.sethValue(calcH(end, v));
		v.calcF();
		openList.add(v);
	}

	public int exists1(List<Vertex> vertexList, int x, int y) {
		// System.out.println("ttttttttttt");
		// System.out.println(x + "," + y);
		for (Vertex v : vertexList) {
			if ((v.getX() == x) && (v.getY() == y)) {
				return 1;
			}
		}
		return 0;
	}

	public static boolean exists(ArrayList<Vertex> openList, Vertex vertex) {
		for (Vertex v : openList) {	
			if ((v.getX() == vertex.getX()) && (v.getY() == vertex.getY())) {
				return true;
			}
		}
		return false;
	}

	public boolean canReach(int x, int y) {
		//System.out.println(globalMap[0][0]);
		if(globalMap[x][y]==null){
			Vertex v =new Vertex();
			globalMap[x][y] =v;
		}
		if (x >= 0 && x < globalMap.length && y >= 0 && y < globalMap[0].length) {
			return globalMap[x][y].getObj() == ' ' || globalMap[x][y].getObj() == '$';
		}
		return false;
	}

	public ArrayList<Vertex> findNeighborNodes(Vertex curr, ArrayList<Vertex> closed, ArrayList<Vertex> openList) {
		ArrayList<Vertex> arrayList = new ArrayList<Vertex>();
		// top bottom left right
		int topX = curr.getX();
		int topY = curr.getY() - 1;
		// System.out.println(canReach(topX, topY));
		// System.out.println("x:"+topX+"y:"+topY);
		// System.out.println(">>>>>>>>");
		if (canReach(topX, topY)) {
			if (exists1(closed, topX, topY) == 0) {
				// System.out.println(">>>>>>>>");
				Vertex v = new Vertex();
				v.setX(topX);
				v.setY(topY);
				arrayList.add(v);
			}
		}
		int bottomX = curr.getX();
		int bottomY = curr.getY() + 1;
		if (canReach(bottomX, bottomY) && exists1(closed, bottomX, bottomY) == 0) {
			// System.out.println(">>>>>>>>");
			Vertex v = new Vertex();
			v.setX(bottomX);
			v.setY(bottomY);
			arrayList.add(v);
		}
		int leftX = curr.getX() - 1;
		int leftY = curr.getY();
		if (canReach(leftX, leftY) && exists1(closed, leftX, leftY) == 0) {
			// System.out.println(">>>>>>>>");
			Vertex v = new Vertex();
			v.setX(leftX);
			v.setY(leftY);
			arrayList.add(v);
		}
		int rightX = curr.getX() + 1;
		int rightY = curr.getY();
		if (canReach(rightX, rightY) && exists1(closed, rightX, rightY) == 0) {
			// System.out.println(">>>>>>>>");
			Vertex v = new Vertex();
			v.setX(rightX);
			v.setY(rightY);
			arrayList.add(v);
		}
		return arrayList;
	}

	class FValueComparator implements Comparator<Vertex> {
		public int compare(Vertex o1, Vertex o2) {
			if (o1.getfValue() >= o2.getfValue()) {
				return 1;
			} else {
				return 0;
			}
		}
	}

}
