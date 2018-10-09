package com.company;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean isDone){
        this.isDone=isDone;
    }
    @Override
    public String toString() {
        return "description: " + description;
    }

    public String save(){
        return description;
    }

}