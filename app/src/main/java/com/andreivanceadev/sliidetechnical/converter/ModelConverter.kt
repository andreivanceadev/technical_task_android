package com.andreivanceadev.sliidetechnical.converter

interface ModelConverter<INPUT, OUTPUT> {

    fun convert(inData: INPUT): OUTPUT

}
