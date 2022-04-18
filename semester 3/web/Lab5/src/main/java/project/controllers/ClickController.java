package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.entities.Click;
import project.repositories.ClickRepository;

import java.time.LocalTime;
import java.util.List;


@CrossOrigin()
@RestController()
@RequestMapping("/clicks")
public class ClickController {

    @Autowired
    ClickRepository clickRepository;

    @GetMapping
    public List<Click> getClicks(){
        System.out.println("GET request");
        return clickRepository.findAll();
    }

    @PostMapping
    public Click addClick(@RequestBody Click click){
        System.out.println("POST request");
        click.setTime(LocalTime.now());
        String result = isHit(click.getX(), click.getY(), click.getR()) ? "попадание" : "мимо";
        click.setResult(result);
        clickRepository.save(click);
        return click;
    }

    @DeleteMapping
    public void deleteClicks(){
        System.out.println("DELETE request");
        clickRepository.deleteAll();
    }

    private boolean isHit(float x, float y, float r){
        if (x < 0 && y > 0){
            return Math.sqrt(x*x + y * y) < r/2;
        } else if (x < 0  && y == 0){
            return x < r/2;
        } else if (x < 0 && y < 0){
            return -y < r - 2 * -x;
        } else if (x == 0 && y < 0){
            return y < r/2;
        } else if (x > 0 && y < 0){
            return (x < r && -y < r / 2);
        }
        return false;
    }


}
