package dev.marcosfarias.pokedex.data.exception

import java.io.IOException

class ServerErrorException(val code: Int, val error: String? = null): IOException()