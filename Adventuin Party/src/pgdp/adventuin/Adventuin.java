package pgdp.adventuin;

import pgdp.color.RgbColor;

public class Adventuin {
    private  String name;
    private int height;
    private RgbColor color;
    private HatType hatType;
    private Language language;

    public Adventuin(String name, int height, RgbColor color, HatType hatType, Language language) {
        this.name = name;
        this.height = height;
        this.color = color;
        this.hatType = hatType;
        this.language = language;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public RgbColor getColor() {
        return color;
    }

    public void setColor(RgbColor color) {
        this.color = color;
    }

    public HatType getHatType() {
        return hatType;
    }

    public void setHatType(HatType hatType) {
        this.hatType = hatType;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
