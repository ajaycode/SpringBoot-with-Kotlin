package com.hello.helloworld

data class MyNumbers  (var multiplicand : Int = 1, var multiplier : Int = 1 ){


    var product : Int = 1
    init {
        this.product = multiplicand * multiplier
    }

}