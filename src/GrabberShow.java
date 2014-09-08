import java.io.IOException;

import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

//import com.googlecode.javacv.FrameGrabber;

/***
 * 
 * @author shilpamurthy
 * Opens Web Camera and captures images every two seconds 
 * 
 */
public class GrabberShow implements Runnable {
	final int INTERVAL = 3000;// /you may use interval
	IplImage image;
	private static int y = 0;
	private static String p1 = "" ,p2 = "";

	// CanvasFrame canvas = new CanvasFrame("Web Cam");
	public GrabberShow() {
		// canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void run() {
		OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
		int i = 0;
		try {
			grabber.start();
			IplImage img;
			while (true) {
				img = grabber.grab();
				int islr = 0;
				if (img != null) {
					try {
						// retrieve image
						//System.out.println("Don't die");
						//System.out.println("From gs = " + p1+" and "+ p2);
						DetectEye eye = new DetectEye(p1,p2);
						islr = eye.isLeftOrRight(img);
						System.out.println("THE VALUE OF BUDDY IS " + islr);
						y = (y << 1 | islr);
						System.out.println("The value of y = " +y);
						i++;
					} catch (IOException e) {
						System.out.println("Image not rendered");
					}
					// show image on window
					// canvas.showImage(finIm);
				}

				Thread.sleep(INTERVAL);
				if (i == 2)
					break;
			}
		} catch (Exception e) {
		}
			try {
				grabber.stop();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		// System.out.println("DOES iT CLOSE? BUDDY");
	}

	public static int getEmPasswords(String path1, String path2) {
		GrabberShow startVid = new GrabberShow();
		p1 = path1;
		p2 = path2;
		startVid.run();
		return y;
	}
	/*
	 * public static void main(String[] args) { GrabberShow test = new
	 * GrabberShow(); test.run(); }
	 */
}
