import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicTools {

    public static void click() {
        try {
            File fileBgm1 = new File("resource/UI按钮点击音效_爱给网_aigei_com (online-audio-converter.com).wav");
            Clip acBgm1 = AudioSystem.getClip();
            AudioInputStream audioInput1 = AudioSystem.getAudioInputStream(fileBgm1);
            acBgm1.open(audioInput1);
            acBgm1.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void error() {
        try {
            File fileBgm2 = new File("resource/用户界面错误音效.wav");
            Clip acBgm2 = AudioSystem.getClip();
            AudioInputStream audioInput2 = AudioSystem.getAudioInputStream(fileBgm2);
            acBgm2.open(audioInput2);
            acBgm2.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void addSuccess() {
        try {
            File fileBgm3 = new File("resource/HIT点击成功_03(HIT_Click_Positive__爱给网_aigei_com.wav");
            Clip acBgm3 = AudioSystem.getClip();
            AudioInputStream audioInput3 = AudioSystem.getAudioInputStream(fileBgm3);
            acBgm3.open(audioInput3);
            acBgm3.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteSuccess() {
        try {
            File fileBgm4 = new File("resource/技能升级成功_爱给网_aigei_com (online-audio-converter.com).wav");
            Clip acBgm4 = AudioSystem.getClip();
            AudioInputStream audioInput4 = AudioSystem.getAudioInputStream(fileBgm4);
            acBgm4.open(audioInput4);
            acBgm4.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
