package ru.ermolnik

internal class Manager {

    fun parkAuto(): Car {
        val owner = CommandImpl.Park.getOwner()
        val model = CommandImpl.Park.getCarModel()
        val color = CommandImpl.Park.getCarColor()
        val number = CommandImpl.Park.getCarNumber()
        val car = Car(model, color, number, owner)

        println("Ищу куда припарковать $car")
        return car
    }

    fun returnAuto(parking: Map<String, Car?>): Car? {
        return CommandImpl.GetCar.execute(parking)
    }

    fun getParkingLoad(parking: Map<String, Car?>): Int {
        return parking.filter { it.value != null }.size
    }

    fun getInfoByCar(model: Model, parking: Map<String, Car?>): String? {
        return parking.entries.find { it.value?.model == model }?.key
    }

    fun getInfoByPlace(place: String, parking: Map<String, Car?>): Car? {
        return parking.entries.find { it.key == place }?.value
    }
}