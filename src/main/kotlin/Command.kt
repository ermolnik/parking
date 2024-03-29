package ru.ermolnik

internal sealed interface Command {
    fun message()
}

internal sealed class CommandImpl(val command: String) : Command {
    data object Start : CommandImpl("/start") {
        override fun message() {
            println("Добро пожаловать в парковку.")
        }
    }

    data object End : CommandImpl("/end") {
        override fun message() {
            println("Спасибо, что воспользовались парковкой")
        }
    }

    data object Help : CommandImpl("/help") {
        override fun message() {
            println("Доступные комманды:")
            println("1. Запуск программы - /start;")
            println("2. Запарковать автомобиль - /park;")
            println("3. Возвратить автомобиль - /return;")
            println("4. Поиск машины по номеру - /park_info_by_car;")
            println("5. Справка по месту парковки - /park_info_by_place;")
            println("6. Завершение программы - /end.")
        }
    }

    data object Park : CommandImpl("/park") {
        override fun message() {
            println("Следуйте инструкциям чтобы припарковать автомобиль")
        }

        fun getOwner(): Owner {
            println("Введите имя и фамилию владельца")
            val name = readln()
            return if (name.split(" ").size == 2) {
                Owner(name.split(" ")[0], name.split(" ")[1])
            } else getOwner()
        }

        fun getCarModel(): Model {
            println("Введите произвотель и модель машины")
            val model = readln()
            return if (model.split(" ").size == 2) {
                Model(model.split(" ")[0], model.split(" ")[1])
            } else getCarModel()
        }

        fun getCarColor(): Color {
            println("Выберите цвет машины из списка:")
            Color.entries.forEach { println(it.colorName) }
            val color = readln()
            return if (color.split(" ").size == 1) {
                Color.from(color) ?: getCarColor()
            } else getCarColor()
        }

        fun getCarNumber(): Number {
            println("Введите номер машины")
            val number = readln()
            println("Введите серию номера")
            val serial = readln()
            println("Введите регион номера")
            val region = readln()

            return if (Number.isValidNumber(number, serial, region)) {
                Number(number, serial, region)
            } else getCarNumber()

        }
    }

    data object ParkStatus : CommandImpl("/park_stats") {
        override fun message() {
            TODO("Not yet implemented")
        }
    }

    data object GetCar : CommandImpl("/return") {
        override fun message() {
            TODO("Not yet implemented")
        }
    }

    data object ParkInfoByCar : CommandImpl("/park_info_by_car") {
        override fun message() {
            TODO("Not yet implemented")
        }
    }

    data object ParkInfoByPlace : CommandImpl("/park_info_by_place") {
        override fun message() {
            TODO("Not yet implemented")
        }
    }

    data object GetParkStats : CommandImpl("/park_all_stats") {
        override fun message() {
            TODO("Not yet implemented")
        }
    }
}