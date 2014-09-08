/**
 * 
 * @author shilpamurthy
 *
 */
public class Tuple{
	private int x, y;
	public Tuple(int x, int y){
		this.x = 0;
		this.y = 0;
	}
	public Tuple(){
		this.x = 0;
		this.y = 0;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public void incrX(){
		this.x +=1;
	}
	
	public void incrY(){
		this.y+=1;
	}
}