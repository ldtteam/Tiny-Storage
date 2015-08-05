package com.timthebrick.tinystorage.client.gui.widgets;

import java.util.ArrayList;
import java.util.List;

public class GuiAnimationCollector {

    private int lifeCycle;
    private int frequency;
    public List<IGuiAnimation> animationList = new ArrayList<IGuiAnimation>();

    public GuiAnimationCollector(int tickOffset){
        this.frequency = tickOffset;
    }

    public void addAnimationComponent(IGuiAnimation animation){
        animationList.add(animation);
    }

    public void updateCollector(){
        lifeCycle++;
        if(lifeCycle % frequency == 0){
            animationList.get(0).startAnimation();
            animationList.remove(0);
        }
    }
}
