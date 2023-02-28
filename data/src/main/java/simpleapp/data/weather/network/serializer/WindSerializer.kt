package simpleapp.data.weather.network.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeStructure
import nl.simpleapp.domain.weather.model.Wind

object WindSerializer : KSerializer<Wind> {

    override fun serialize(encoder: Encoder, value: Wind) {
        encoder.encodeStructure(descriptor) {
            encoder.encodeDouble(value.speed)
            encoder.encodeInt(value.deg)
        }
    }

    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("Wind") {
            element("speed", Double.serializer().descriptor)
            element("deg", Int.serializer().descriptor)
        }

    override fun deserialize(decoder: Decoder): Wind {
        val speed = decoder.decodeDouble()
        val deg = decoder.decodeInt()
        return Wind(speed, deg)
    }
}