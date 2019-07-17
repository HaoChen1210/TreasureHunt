
public class Man {
	// up 0 left 1 right 3 down 2
	private int direction = 0;
	private int x = 80;
	private int y = 80;

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
		if (this.direction == 4) {
			this.direction = 0;
		}
		if (this.direction == -1) {
			this.direction = 3;
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
