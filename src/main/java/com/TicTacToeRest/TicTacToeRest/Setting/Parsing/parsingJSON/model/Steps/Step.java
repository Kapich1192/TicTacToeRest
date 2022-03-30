package com.TicTacToeRest.TicTacToeRest.Setting.Parsing.parsingJSON.model.Steps;

import com.TicTacToeRest.TicTacToeRest.Setting.Parsing.parsingJSON.model.Game;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Step {
    int stepId;
    int playerId;
    int x;
    int y;
    int k;
    String gameResult;
//constructors
    public Step(int stepId, int playerId, int x, int y) {
        this.stepId = stepId;
        this.playerId = playerId;
        this.x = x;
        this.y = y;
    }
    public Step(String gameResult){
        this.gameResult = gameResult;
    }

    public Step(int playerId) {
        this.playerId = playerId;
    }

    public Step(int stepId, int playerId, int x, int y, String gameResult) {
        this.stepId = stepId;
        this.playerId = playerId;
        this.x = x;
        this.y = y;
        this.gameResult = gameResult;
    }

    //getters
    public int getStepId() { return stepId; }
    public int getPlayerId() { return playerId; }
    public int getX() { return x; }
    public int getY() { return y; }
    public String getGameResult() { return gameResult; }
//setters
    public void setStepId(int stepId) { this.stepId = stepId; }
    public void setPlayerId(int playerId) { this.playerId = playerId; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setK(int k) { this.k = k; }


    public void setGameResult(String gameResult) {
        this.gameResult = gameResult;
    }

    @Override
    public String toString() {
        return "Step{" +
                "stepId=" + stepId +
                ", playerId=" + playerId +
                ", x=" + x +
                ", y=" + y +
                ", k=" + k +
                ", gameResult='" + gameResult + '\'' +
                '}';
    }
}
