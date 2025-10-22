package org.example.Chapter7;

public record Magic(String wand, int length, boolean isFire) {
    public Magic{
        if(length < 0)
            throw new IllegalArgumentException();
    }
    public Magic(){
        this("Wand",-1,true);
    }
}
