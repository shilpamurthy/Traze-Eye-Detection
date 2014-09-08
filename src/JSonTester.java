import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSonTester {

	public static void main(String[] args) {
		FileReader reader;
		try {
			reader = new FileReader("haarcascade_eye.xml");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
