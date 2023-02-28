package simpleapp.data.weather.network.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeStructure
import nl.simpleapp.domain.weather.model.Weather

object WeatherSerializer : KSerializer<Weather> {

    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("weather") {
            element("id", Int.serializer().descriptor)
            element("description", String.serializer().descriptor)
            element("icon", String.serializer().descriptor)
        }

    override fun deserialize(decoder: Decoder): Weather {
        return Weather(
            decoder.decodeLong(),
            decoder.decodeString(),
            decoder.decodeString(),
            decoder.decodeString()  
        )
    }

    override fun serialize(encoder: Encoder, value: Weather) {
        encoder.encodeStructure(descriptor) {
            encoder.encodeLong(value.id)
            encoder.encodeString(value.description)
            encoder.encodeString(value.icon)
        }
    }
}
