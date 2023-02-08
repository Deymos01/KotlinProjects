class CoffeeMachine {
    var Mwater: Int = 400
    var Mmilk: Int = 540
    var McoffeeBeans: Int = 120
    var cups: Int = 9
    var cash: Int = 550
    val status: String
        get() {
            return """
                The coffee machine has:
                $Mwater ml of water
                $Mmilk ml of milk
                $McoffeeBeans g of coffee beans
                $cups disposable cups
                ${'$'}$cash of money
            """.trimIndent()
        }

    enum class Coffee(val water: Int, val milk: Int, val coffeeBeans: Int, val price: Int) {
        espresso(250, 0, 16, 4),
        latte(350, 75, 20, 7),
        cappuccino(200, 100, 12, 6)
    }

    fun buy() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")

        when (readln()) {
            "1" -> sellCoffee(Coffee.espresso)
            "2" -> sellCoffee(Coffee.latte)
            "3" -> sellCoffee(Coffee.cappuccino)
            "back" -> return
        }
    }

    private fun sellCoffee(coffee: Coffee) {
        if (cups <= 0) {
            println("Sorry, not enough disposable cups!\n")
            return
        } else if (coffee.water > Mwater) {
            println("Sorry, not enough water!\n")
            return
        } else if (coffee.milk > Mmilk) {
            println("Sorry, not enough milk!\n")
        } else if (coffee.coffeeBeans > McoffeeBeans){
            println("Sorry, not enough coffee beans!\n")
        } else {
            println("I have enough resources, making you a coffee!\n")
            Mwater -= coffee.water
            Mmilk -= coffee.milk
            McoffeeBeans -= coffee.coffeeBeans
            cups--
            cash += coffee.price
        }
    }

    fun fill() {
        println("\nWrite how many ml of water you want to add:")
        Mwater += readln().toInt()
        println("Write how many ml of milk you want to add:")
        Mmilk += readln().toInt()
        println("Write how many grams of coffee beans you want to add:")
        McoffeeBeans += readln().toInt()
        println("Write how many disposable cups you want to add:")
        cups += readln().toInt()
        println()
    }

    fun take() {
        println("I gave you ${'$'}$cash")
        println()
        cash = 0
    }

    fun remaining() {
        println("\n${this.status}\n")
    }
}

fun startCoffeeMachine() {
    val coffeeMachine = CoffeeMachine()

    do {
        println("Write action (buy, fill, take, remaining, exit):")
        val userChoice: String = readln()

        when (userChoice) {
            "buy" -> coffeeMachine.buy()
            "fill" -> coffeeMachine.fill()
            "take" -> coffeeMachine.take()
            "remaining" -> coffeeMachine.remaining()
        }

    } while (userChoice != "exit")
}

fun main() {
    startCoffeeMachine()
}
