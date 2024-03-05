package if4030.soap;

public class RandomService {
    private int min = 0;
    private int max = 10;
        
    public void setBounds( int min, int max ) {
        this.min = min;
        this.max = max;
    }
    
    public int nextRandom() {
        return (int)Math.floor(Math.random() * (max - min + 1)) + min;
    }
}
