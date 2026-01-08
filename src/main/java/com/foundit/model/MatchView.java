package com.foundit.model;

public class MatchView {
    private FoundItem item;
    private int score;

    public MatchView(FoundItem item, int score) {
        this.item = item;
        this.score = score;
    }
    public FoundItem getItem() { return item; }
    public int getScore() { return score; }
}
