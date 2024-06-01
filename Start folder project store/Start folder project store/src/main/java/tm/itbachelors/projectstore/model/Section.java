package tm.itbachelors.projectstore.model;

public class Section {
    private String name;
    private String picture;
    private boolean cooled;

    public Section() {
    }

    public Section(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isCooled() {
        return cooled;
    }

    public void setCooled(boolean cooled) {
        this.cooled = cooled;
    }
}
