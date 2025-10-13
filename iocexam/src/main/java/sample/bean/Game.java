package sample.bean;

import java.util.List;

public class Game {
    private List<Player> players;

    public Game(){}

    //생성자를 통한 주입
    public Game(List<Player> PlayList) {
        this.players = PlayList;
    }

    public void play(){
        for(Player player : players){
            player.play();
        }
    }
}
