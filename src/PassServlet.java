
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PassServlet
 * Gets a request and starts running code to see whether the person is
 * looking left or right
 * @author shilpamurthy
 */
@WebServlet("/PassServlet")
public class PassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PassServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String check = request.getParameter("param1");
		if (check != null && check.equalsIgnoreCase("true"))
		{
			result yay = new result();
			String path1 = "/Users/shilpamurthy/Desktop/Projects/haarcascade_eye.xml";
			String path2 = "/Users/shilpamurthy/Desktop/Projects/haarcascade_frontalface_alt_tree.xml";
			//System.out.println(path1);
			//System.out.println(path2);			
			int resIndex = GrabberShow.getEmPasswords( path2, path1);
			response.addHeader("Access-Control-Allow-Origin", "*");
			String passRes = yay.getPassword(resIndex);
			//System.out.println("WHY BUDDY?");
			response.getWriter().write(passRes);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
