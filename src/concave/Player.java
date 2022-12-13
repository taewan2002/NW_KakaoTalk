package concave;

public class Player {
    private String playerName;
    private String playerInfo;
    private int playerColor;

    public Player(String playerName, String playerInfo) {
        this.playerName = playerName;
        this.playerInfo = playerInfo;
    }

    // 플레이어의 돌 색깔을 반환
    int getPlayerColor()
    {
        return playerColor;
    }

    void setPlayerColor(int playerColor)
    {
        this.playerColor = playerColor;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(String playerInfo) {
        this.playerInfo = playerInfo;
    }
}
