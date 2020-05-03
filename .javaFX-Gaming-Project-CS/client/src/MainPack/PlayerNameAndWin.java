package MainPack;

public class PlayerNameAndWin {

    static String playerName;
    boolean isWin;
    int totalScore ;
    String totalTime ;

    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }
    public void setIsWon(){
        this.isWin = true;
    }
    public static String getPlayerName(){
        return playerName;
    }
    public boolean getIsWon(){
        return this.isWin;
    }
    public void setTotalScore(int totalScore){
        this.totalScore = totalScore;
    }
    public int getTotalScore(){
        return this.totalScore;
    }
    public void setTotalTime(String totalTime){
        this.totalTime = totalTime;
    }
    public String getTotalTime(){
        return this.totalTime;
    }
}
