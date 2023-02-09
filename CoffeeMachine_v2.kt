class CoffeeMachine {
    init {
        Resources.WATER.remaining = 400
        Resources.MILK.remaining = 540
        Resources.COFFEE_BEANS.remaining = 120
        Resources.CUPS.remaining = 9
        Resources.CASH.remaining = 550
    }
    private val status: String
        get() {
            return """
                The coffee machine has:
                ${Resources.WATER.remaining} ml of water
                ${Resources.MILK.remaining} ml of milk
                ${Resources.COFFEE_BEANS.remaining} g of coffee beans
                ${Resources.CUPS.remaining} disposable cups
                ${'$'}${Resources.CASH.remaining} of money
            """.trimIndent()
        }

    enum class Coffee(val water: Int, val milk: Int, val coffeeBeans: Int, val cup: Int, val price: Int) {
        ESPRESSO(250, 0, 16, 1, -4),
        LATTE(350, 75, 20, 1, -7),
        CAPPUCCINO(200, 100, 12, 1, -6);

        val requiredResources = mutableListOf(water, milk, coffeeBeans, cup, price)
    }

    private enum class Resources(val nameOfResource: String, var remaining: Int, val unit: String){
        WATER("water", 0, "ml"),
        MILK("milk", 0, "ml"),
        COFFEE_BEANS("coffee beans", 0, "grams"),
        CUPS("disposable cups", 0, ""),
        CASH("money", 0, "${'$'}")
    }

    fun buy() {
        print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")

        when (readln()) {
            "1" -> sellCoffee(Coffee.ESPRESSO)
            "2" -> sellCoffee(Coffee.LATTE)
            "3" -> sellCoffee(Coffee.CAPPUCCINO)
            "back" -> return
            else -> println("Invalid input")
        }
    }

    private fun sellCoffee(coffee: Coffee) {
        for (resource in Resources.values()){
            if (resource.remaining < coffee.requiredResources[resource.ordinal]){
                println("Sorry, not enough ${resource.nameOfResource}!\n")
                return
            }
        }
        println("I have enough resources, making you a coffee!\n")

        for (resource in Resources.values()){
            resource.remaining -= coffee.requiredResources[resource.ordinal]
        }
    }

    fun fill() {
        for (resource in Resources.values()){
            if (resource.nameOfResource == "disposable cups" || resource.nameOfResource == "money") continue
            print("Write how many ${resource.unit} of ${resource.nameOfResource} you want to add: ")
            resource.remaining += readln().toInt()
        }
        print("Write how many ${Resources.CUPS.nameOfResource} you want to add: ")
        Resources.CUPS.remaining += readln().toInt()
        println()
    }

    fun take() {
        println("I gave you ${'$'}${Resources.CASH.remaining}")
        println()
        Resources.CASH.remaining = 0
    }

    fun remaining() {
        println("\n${this.status}\n")
    }
}

fun startCoffeeMachine() {
    val coffeeMachine = CoffeeMachine()

    do {
        print("Write action (buy, fill, take, remaining, exit): ")
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
