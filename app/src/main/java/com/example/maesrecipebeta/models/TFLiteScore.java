package com.example.maesrecipebeta.models;

public class TFLiteScore implements Comparable<TFLiteScore> {
    private int index;
    private Double score;

    public TFLiteScore(int index,Double score){
        this.index = index;
        this.score = score;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public int compareTo(TFLiteScore other){
        return this.getScore().compareTo(other.getScore()) * -1;
    }
}
