package VoiceControl;

import com.sun.speech.freetts.*;
import java.util.Random;

public class VoiceControlOfGame {

    private static final String[] s = {"Kevin16","Kevin8","Alan"};
//    static Random rand = new Random();
//    private static int n = rand.nextInt(2)+ 0;
    private static final String VoiceName = "Kevin16";

    public void Text2Voice(String stringToRead) {

        System.out.println("String to Read "+stringToRead);
        try {
            Voice voice;
            VoiceManager voiceManager = VoiceManager.getInstance();
            voice = voiceManager.getVoice(VoiceName);

            try{
                voice.allocate();
                voice.speak(stringToRead);
            }catch (Exception ve){
                ve.printStackTrace();
            }
            voice.deallocate();

        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }
    }
}
