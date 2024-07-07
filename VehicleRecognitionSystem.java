import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

public class VehicleRecognitionSystem {

    public static void main(String[] args) {
        // Load OpenCV native library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Path to input image file
        String imagePath = "path_to_your_input_image.jpg";

        // Load image from file
        Mat image = Imgcodecs.imread(imagePath);

        // Initialize Cascade Classifier for vehicle detection
        CascadeClassifier carClassifier = new CascadeClassifier();
        carClassifier.load("path_to_opencv_xml_files/haarcascade_car.xml"); // Replace with actual path

        // Detect vehicles in the image
        MatOfRect cars = new MatOfRect();
        carClassifier.detectMultiScale(image, cars);

        // Draw bounding boxes around detected vehicles
        for (Rect rect : cars.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0), 2);
        }

        // Display the output image
        showImage("Detected Vehicles", image);
    }

    // Helper method to display image in a window
    private static void showImage(String title, Mat img) {
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpg", img, matOfByte);
        byte[] byteArray = matOfByte.toArray();

        ImageIcon icon = new ImageIcon(byteArray);
        JFrame frame = new JFrame(title);
        frame.setLayout(new FlowLayout());
        frame.setSize(img.width() + 50, img.height() + 50);

        JLabel lbl = new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
