package ru.ermolnik

internal object Parking {
    fun start(manager: Manager) {
        val parking = create()
        var parkingLoad = 0

        println("Введите команду /start для запуска программы :")
        while (true) {
            val scanner = readln().substringBefore(" ")
            when (scanner) {
                CommandImpl.Start.command -> CommandImpl.Start.message()
                CommandImpl.Help.command -> CommandImpl.Help.message()
                CommandImpl.Park.command -> {
                    val car = manager.parkAuto()
                    val freePlace = findFreePlace(parking)
                    if (freePlace != null) {
                        parking[freePlace] = car
                        println("Припарковал $car на $freePlace")
                        parkingLoad += 1
                    } else {
                        println("Нет свободных мест")
                    }

                    println("Что-то еще?")
                }

                CommandImpl.ParkStatus.command -> {
                    println("Всего мест: ${Constants.parkingSize}")
                    println("Загрузка: ${manager.getParkingLoad(parking)}/${Constants.parkingSize}")
                    println("Что-то еще?")
                }

                CommandImpl.GetCar.command -> {
                    CommandImpl.GetCar.message()
                    val car = manager.returnAuto(parking)
                    if (car != null) {
                        println("Вот ваш автомобиль")
                        parking.entries.find { it.value == car }?.key?.let {
                            parking[it] = null
                        }
                    } else {
                        println("Такого автомобиля нет или вы не являетесь владельцем")
                    }
                    println("Что-то еще?")
                }

                CommandImpl.ParkInfoByCar.command -> {
                    CommandImpl.ParkInfoByCar.message()
                    manager.getInfoByCar(CommandImpl.ParkInfoByCar.execute(), parking)?.let {
                        println("Эта машина припаркована на $it")

                    } ?: println("Такой машины нет на парковке")
                    println("Что-то еще?")
                }

                CommandImpl.ParkInfoByPlace.command -> {
                    CommandImpl.ParkInfoByPlace.message()
                    manager.getInfoByPlace(CommandImpl.ParkInfoByPlace.execute(), parking)?.let {
                        println("На этом месте припаркована $it")

                    } ?: println("На этом месте ничего не припарковано")
                    println("Что-то еще?")
                }

                CommandImpl.ParkAllStats.command -> {
                    println("Сегодня было припарковано: $parkingLoad")
                    println("Что-то еще?")
                }

                CommandImpl.End.command -> break
                else -> CommandImpl.Help.message()
            }
        }
    }

    private fun findFreePlace(parking: Map<String, Car?>): String? {
        parking.forEach {
            if (it.value == null) return it.key
        }

        return null
    }

    private fun create(): MutableMap<String, Car?> {
        val parking = mutableMapOf<String, Car?>()
        for (i in 1..Constants.parkingSize) {
            parking["P$i"] = null
        }
        return parking
    }
}