import java.util.HashSet;

/**
 * 
 * @author shilpamurthy
 *
 */
public class result {

	HashSet<Credentials> setOfPasswords = new HashSet<Credentials>();
	private String uName1 = "shilpamurthy93@gmail.com";
	private String uName2 = "shilpam@gandrew.cmu.edu";
	private String uName3 = "aramkuma@andrew.cmu.edu";
	private String uName4 = "srnaik@andrew.cmu.edu";
	private String p1 = "Ilovemhacks";
	private String p2 = "Mhacksrocks8";
	private String p3 = "yaymhacks";
	private String p4 = "redbull";
	private int id1 = 0;
	private int id2 = 1;
	private int id3 = 2;
	private int id4 = 3;
	public result()
	{
		setOfPasswords.add(new Credentials(uName1, p1, id1));
		setOfPasswords.add(new Credentials(uName2, p2, id2));
		setOfPasswords.add(new Credentials(uName3, p3, id3));
		setOfPasswords.add(new Credentials(uName4, p4, id4));
	}
	public boolean[] getResult()
	{
		boolean[] res = {true,false};
		return res;
	}
	
	public String getPassword(int id)
	{
		for (Credentials c : setOfPasswords)
		{
			if (c.isIDEqual(id))
			{
				return c.getPassword();
			}
		}
		return "";
	}
}
