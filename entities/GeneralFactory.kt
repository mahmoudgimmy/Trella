package com.cargood.factories

abstract class GeneralFactory<input,output> {

    abstract fun convert(data:input):output
}