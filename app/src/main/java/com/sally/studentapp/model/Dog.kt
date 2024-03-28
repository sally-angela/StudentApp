package com.sally.studentapp.model

data class Dog(
    val id:Int?,
    val breed:String?,
    val size:String?,
    val colors:List<String>?,
    val characteristics:DogCharacteristics?,
    val images:String?
)

data class DogCharacteristics(
    val friendly:Boolean?,
    val intelligent:Boolean?,
    val loyal:Boolean?
)
