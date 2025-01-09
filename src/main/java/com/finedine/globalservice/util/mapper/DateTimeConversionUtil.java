package com.finedine.globalservice.util.mapper;

import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class DateTimeConversionUtil {

    private DateTimeConversionUtil() {
        // Prevent instantiation
    }

    @Named("offsetToLocal")
    public static LocalDateTime offsetToLocal(OffsetDateTime offsetDateTime) {
        return offsetDateTime == null ? null : offsetDateTime.toLocalDateTime();
    }

    @Named("localToOffset")
    public static OffsetDateTime localToOffset(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.atOffset(OffsetDateTime.now().getOffset());
    }
}
