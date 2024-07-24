
interface VendingMachine {
    HotDrink getProduct(int name, int volume, int temperature);
}


class HotDrink {
    protected String name;
    protected int volume; 

    public HotDrink(String name, int volume) {
        this.name = name;
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }

  
    public String toString() {
        return String.format("%s (%d ml)", name, volume);
    }
}


class HotDrinkWithTemperature extends HotDrink {
    private int temperature; 

    public HotDrinkWithTemperature(String name, int volume, int temperature) {
        super(name, volume);
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }


    public String toString() {
        return String.format("%s (%d ml, %d°C)", name, volume, temperature);
    }
}


class HotDrinkVendingMachine implements VendingMachine {
    private HotDrinkWithTemperature[] drinks;

    public HotDrinkVendingMachine(HotDrinkWithTemperature[] drinks) {
        this.drinks = drinks;
    }

   
    public HotDrink getProduct(int name, int volume, int temperature) {
        for (HotDrinkWithTemperature drink : drinks) {
            if (drink.getName().hashCode() == name && drink.getVolume() == volume && drink.getTemperature() == temperature) {
                return drink;
            }
        }
        return null; 
    }
}


public class Main {
    public static void main(String[] args) {
        // Инициализация горячих напитков
        HotDrinkWithTemperature tea = new HotDrinkWithTemperature("Tea", 250, 80);
        HotDrinkWithTemperature coffee = new HotDrinkWithTemperature("Coffee", 200, 90);
        HotDrinkWithTemperature cocoa = new HotDrinkWithTemperature("Cocoa", 300, 70);

        // Инициализация автомата
        HotDrinkWithTemperature[] drinks = {tea, coffee, cocoa};
        HotDrinkVendingMachine vendingMachine = new HotDrinkVendingMachine(drinks);

        // Запрос напитков
        HotDrink requestedDrink = vendingMachine.getProduct(tea.getName().hashCode(), 250, 80);
        if (requestedDrink != null) {
            System.out.println("You received: " + requestedDrink);
        } else {
            System.out.println("Drink not found.");
        }

        requestedDrink = vendingMachine.getProduct(coffee.getName().hashCode(), 200, 85); 
        if (requestedDrink != null) {
            System.out.println("You received: " + requestedDrink);
        } else {
            System.out.println("Drink not found.");
        }
    }
}
