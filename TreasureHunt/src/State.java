/**
 * gvalue is the cost of the Coordinate
 * fvalue is the cost of the distance
 * @author chenhao
 *
 */
public class State {
	private boolean status;
	private int gValue;
	private int fValue;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getgValue() {
		return gValue;
	}
	public void setgValue(int gValue) {
		this.gValue = gValue;
	}
	public int getfValue() {
		return fValue;
	}
	public void setfValue(int fValue) {
		this.fValue = fValue;
	}
}
