package com.hello.helloworld

data class MyNumbers  (var multiplicand : Int = 1, var multiplier : Int = 1 ){


    var product : Int = 1
    init {
        this.product = multiplicand * multiplier
    }
        /*
        set (value) {
            var solution = multiplicand * multiplier
            if (value != solution)
                field = solution
            else
                field = value

        }*/
}