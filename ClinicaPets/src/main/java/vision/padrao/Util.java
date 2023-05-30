package vision.padrao;

public class Util {

    public static String getCaminhoIMG(String nameIMG) {
        String imagePath = Util.class.getClassLoader().getResource(nameIMG).getPath();
        if (imagePath == null) {
        	return "";
        }
        return imagePath;
    }
}
