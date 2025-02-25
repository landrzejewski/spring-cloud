package pl.training.payments.adapters.time;

import org.mapstruct.Mapper;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface RemoteTimeRestMapper {

    default Instant toDomain(TimestampDto timestampDto) {
        return Instant.parse(timestampDto.getDateTime().split("\\.")[0] + "Z" );
    }

}
