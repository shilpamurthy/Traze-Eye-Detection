
import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSeqElem;
import static com.googlecode.javacv.cpp.opencv_core.cvLoad;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2GRAY;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_objdetect.cvHaarDetectObjects;

import java.awt.image.BufferedImage;

import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.CvRect;
import com.googlecode.javacv.cpp.opencv_core.CvSeq;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_objdetect.CvHaarClassifierCascade;
/**
 * 
 * @author shilpamurthy
 * Algorithm to detect whether eye is looking left or right
 */
public class DetectEye {

	private static String CASCADE_FILE = "";
	private static String CASCADE_FILE2 = "";
	
	public DetectEye(String p1, String p2)
	{
		CASCADE_FILE = p1;
		CASCADE_FILE2 = p2;
	}

	public int isLeftOrRight(IplImage originalImage) throws Exception {

		IplImage grayImage = IplImage.create(originalImage.width(),
				originalImage.height(), IPL_DEPTH_8U, 1);

		// We convert the original image to grayscale.
		cvCvtColor(originalImage, grayImage, CV_BGR2GRAY);

		CvMemStorage storage = CvMemStorage.create();

		// We instantiate a classifier cascade to be used for detection,
		// using
		// the cascade definition.
		//System.out.println("HULLO");
		//System.out.println(" NOW " + CASCADE_FILE);
		CvHaarClassifierCascade cascade = new CvHaarClassifierCascade(
				cvLoad(CASCADE_FILE));
		//System.out.println("HULLO1");

		// We detect the faces.
		CvSeq faces = cvHaarDetectObjects(grayImage, cascade, storage, 1.1, 1,
				0);
		//System.out.println("HULLO12");

		CvRect r = new CvRect(cvGetSeqElem(faces, 0));
		BufferedImage im = (originalImage.getBufferedImage()).getSubimage(
				r.x(), r.y(), r.width(), r.height());
		//System.out.println("HULLO13");
		return getEyeSide(im);

	}

	public int getEyeSide(BufferedImage bufferedImg) throws Exception {

		IplImage originalImage = IplImage.createFrom(bufferedImg);
		CvMemStorage storage = CvMemStorage.create();

		// We instantiate a classifier cascade to be used for detection,
		// using
		// the cascade definition.
		//System.out.println("HULLO2");
		CvHaarClassifierCascade cascade = new CvHaarClassifierCascade(
				cvLoad(CASCADE_FILE2));
		//System.out.println("HULLO3");

		// We detect the faces.
		CvSeq faces = cvHaarDetectObjects(originalImage, cascade, storage, 1.1,
				1, 0);
		CvRect max1 = new CvRect(1, 1, 1, 1);
		CvRect max2 = new CvRect(1, 1, 1, 1);
		for (int i = 0; i < faces.total(); i++) {
			CvRect r = new CvRect(cvGetSeqElem(faces, i));
			if ((r.width() * r.height() > max1.width() * max1.height())
					|| (r.width() * r.height() > max2.width() * max2.height())) {
				if (max1.width() * max1.height() > max2.width() * max2.height()) {
					max2 = r;
				} else {
					max1 = r;
				}
			}

		}
		Tuple leftRight = new Tuple();
		mask(bufferedImg, max1, leftRight);
		mask(bufferedImg, max2, leftRight);
		if (leftRight.getX() > leftRight.getY()) {
			return 0;
		}
		return 1;

	}

	public static void mask(BufferedImage bufferedImg, CvRect r, Tuple leftRight) {
		for (int j = r.x(); j < r.x() + r.width() - 1; j++) {
			for (int k = r.y(); k < r.y() + r.height() - 1; k++) {
				if (bufferedImg.getRGB(j, k) <= 0xFF333333) {
					if (j < (r.x() + r.width() / 2)) {
						leftRight.incrX();
					} else {
						leftRight.incrY();
					}
				}
			}
		}
	}
}