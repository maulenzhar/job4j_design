package ru.job4j.ood.lsp.hw;

/*Постусловия (Postconditions) не могут быть ослаблены в подклассе.*/
public class Credit {
    public int give(int amount) throws Exception {
        if (amount > 100000) {
            throw new Exception("Сумма велика");
        }
        return amount;
    }
}

class Microcredit extends Credit {
    public int give(int amount) throws Exception {
        return amount;
    }
}
