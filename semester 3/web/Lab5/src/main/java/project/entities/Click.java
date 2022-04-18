package project.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity(name = "clicks")
public class Click {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id; 
    private float x;
    private float y;
    private float r;
    private LocalTime time;
    private String result;
    private int userId;


    @Override
    public String toString() {
        return "Click{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", time=" + time +
                ", result='" + result + '\'' +
                ", clickedBy='" + userId + '\'' +
                '}';
    }

    public Click(float x, float y, float r, LocalTime time, String result, int clickedBy) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.time = time;
        this.result = result;
        this.userId = clickedBy;
    }

    public Click(){}

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
