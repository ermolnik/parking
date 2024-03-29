package ru.ermolnik

internal class Manager {

    fun parkAuto(): Car {
        val owner = CommandImpl.Park.getOwner()
        val model = CommandImpl.Park.getCarModel()
        val color = CommandImpl.Park.getCarColor()
        val number = CommandImpl.Park.getCarNumber()
        val car = Car(model, color, number, owner)

        println("$car припаркована")
        return car
    }

    fun returnAuto() {

    }

    fun getParkingLoad() {

    }

    fun getLoadStatistic() {

    }
}