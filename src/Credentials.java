/**
 * 
 * @author shilpamurthy
 *
 */
public class Credentials {
	
	private String uName;
	private String password;
	private int ID;
	
	public Credentials (String u, String p, int id)
	{
		setuName(u);
		setPassword(p);
		setID(id);
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public boolean isSameUserName(String check)
	{
		return (check.equalsIgnoreCase(getuName()));
	}
	
	public boolean isIDEqual(int a)
	{
		return (a==getID());
	}
}
