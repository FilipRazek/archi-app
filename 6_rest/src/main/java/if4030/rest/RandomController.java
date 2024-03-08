package if4030.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomController {
	private int min = 0;
    private int max = 10;

	@GetMapping("/setBounds")
    public void setBounds(@RequestParam("min") int min, @RequestParam("max") int max ) {
        this.min = min;
        this.max = max;
    }

    @PostMapping("/set")
    public void setBounds(@RequestBody SetBounds bounds) {
        this.min = bounds.min;
        this.max = bounds.max;
    }
    
	@GetMapping("/nextRandom")
    public int nextRandom() {
        return (int)Math.floor(Math.random() * (max - min + 1)) + min;
    }
}
