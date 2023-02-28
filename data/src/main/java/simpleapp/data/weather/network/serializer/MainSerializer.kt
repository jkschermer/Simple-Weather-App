package simpleapp.data.weather.network.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import nl.simpleapp.domain.weather.model.Main

object MainSerializer : KSerializer<Main> {

    override fun serialize(encoder: Encoder, value: Main) {
        encoder.encodeDouble(value.temp)
        encoder.encodeDouble(value.feels_like)
        encoder.encodeDouble(value.temp_min)
        encoder.encodeDouble(value.temp_max)
        encoder.encodeInt(value.pressure)
        encoder.encodeInt(value.humidity)
    }

    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("main") {
            element("temp", Double.serializer().descriptor)
            element("feels_like", Double.serializer().descriptor)
            element("temp_min", Double.serializer().descriptor)
            element("temp_max", Double.serializer().descriptor)
            element("pressure", Int.serializer().descriptor)
            element("humidity", Int.serializer().descriptor)
        }

    override fun deserialize(decoder: Decoder): Main {
        return Main(
            decoder.decodeDouble(),
            decoder.decodeDouble(),
            decoder.decodeDouble(),
            decoder.decodeDouble(),
            decoder.decodeInt(),
            decoder.decodeInt()
        )
    }
}