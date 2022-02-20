package writeXMLfile;

public class Jober {
    int id;
    String name;
    String lang;
    int age;

    public Jober(int id, String name, String lang, int age) {
        this.id = id;
        this.name = name;
        this.lang = lang;
        this.age = age;
    }

    public Jober() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Jober{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lang='" + lang + '\'' +
                ", age=" + age +
                '}';
    }
}
