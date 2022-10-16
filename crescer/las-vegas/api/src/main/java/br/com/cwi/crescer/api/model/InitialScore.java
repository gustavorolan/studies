package br.com.cwi.crescer.api.model;

import lombok.Getter;

@Getter
public enum InitialScore {
    SCORE(0);
    private int score;
    InitialScore (int score){
        this.score=score;
    }
}
