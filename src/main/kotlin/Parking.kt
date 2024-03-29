package ru.ermolnik

internal object Parking {
    fun start(manager: Manager) {
        val parking = create()

        println("Введите команду /start для запуска программы :")
        while (true) {
            val scanner = readln().substringBefore(" ")
            when (scanner) {
                CommandImpl.Start.command -> CommandImpl.Start.message()
                CommandImpl.Help.command -> CommandImpl.Help.message()
                CommandImpl.Park.command -> {
                    manager.parkAuto()
                }

                CommandImpl.ParkStatus.command -> break
                CommandImpl.GetCar.command -> break
                CommandImpl.ParkInfoByCar.command -> break
                CommandImpl.ParkInfoByPlace.command -> break
                CommandImpl.GetParkStats.command -> break
                CommandImpl.End.command -> break
                else -> CommandImpl.Help.message()
            }
        }

    }

    private fun create(): MutableMap<String, Car?> {
        val parking = mutableMapOf<String, Car?>()
        for (i in 1..Constants.parkingSize) {
            parking["P$i"] = null
        }
        return parking
    }
}