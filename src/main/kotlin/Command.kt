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
            println("1. Запуск программы - ${Start.command};")
            println("2. Запарковать автомобиль - ${Park.command};")
            println("3. Возвратить автомобиль - ${GetCar.command};")
            println("4. Поиск машины по номеру - ${ParkInfoByCar.command};")
            println("5. Справка по месту парковки - ${ParkInfoByPlace.command};")
            println("6. Загрузка парковки - ${ParkStatus.command};")
            println("7. Cколько всего машин было припарковано - ${ParkAllStats.command};")
            println("8. Завершение программы - ${End.command};")
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
            println("Введите произвдителя и модель машины")
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
            println("Текущий статус парковки:")
        }
    }

    data object GetCar : CommandImpl("/return") {
        override fun message() {
            println("Следуйте инструкциям чтобы получить автомобиль")
        }

        fun execute(parking: Map<String, Car?>): Car? {
            println("Введите имя и фамилию владельца")
            val name = readln()
            val owner = if (name.split(" ").size == 2) {
                Owner(name.split(" ")[0], name.split(" ")[1])
            } else Park.getOwner()

            println("Введите производителя и модель машины")
            val model = readln()
            val car = if (model.split(" ").size == 2) {
                Model(model.split(" ")[0], model.split(" ")[1])
            } else Park.getCarModel()

            parking.values.forEach {
                if (it?.owner == owner && it.model == car) {
                    return it
                }
            }
            return null
        }
    }

    data object ParkInfoByCar : CommandImpl("/park_info_by_car") {
        override fun message() {
            println("Напишите марку и модель автомобиля")
        }

        fun execute(): Model {
            val input = readln()
            val car = if (input.split(" ").size == 2) {
                Model(input.split(" ")[0], input.split(" ")[1])
            } else Park.getCarModel()
            return car
        }
    }

    data object ParkInfoByPlace : CommandImpl("/park_info_by_place") {
        override fun message() {
            println("Напишите место парковки")
        }

        fun execute(): String {
            return readln()
        }
    }

    data object ParkAllStats : CommandImpl("/park_all_stats") {
        override fun message() {
            println("Статус парковки за все время:")
        }
    }
}