package com.efrei.emergency.care.entities;

public class Paperwork {
    private boolean checked;
    private boolean filled;

    public Paperwork() {
        this.checked = false;
        this.filled = false;
    }

    public Paperwork(boolean checked, boolean filled) {
        this.checked = checked;
        this.filled = filled;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
