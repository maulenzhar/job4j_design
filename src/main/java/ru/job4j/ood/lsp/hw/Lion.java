package ru.job4j.ood.lsp.hw;

/*Предусловия (Preconditions) не могут быть усилены в подклассе*/
public class Lion {
    public int gramm;

    public Lion(int meat) {
        this.gramm = meat;
    }

    public int eatMeat(int gramm) {
        if (gramm > 5000) {
            throw new IllegalArgumentException("too much!");
        }
        return gramm;
    }
}

class Tiger extends Lion {

    public Tiger(int meat) {
        super(meat);
    }

    public int eatMeat(int gramm) {
        if (gramm > 4000) {
            throw new IllegalArgumentException("too much!");
        }
        return gramm;
    }

}
