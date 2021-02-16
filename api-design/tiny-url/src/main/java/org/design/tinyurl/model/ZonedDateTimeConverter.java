package org.design.tinyurl.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Java 8's ZonedDateTime implementation isn't directly compatible with JPA 2.1
 * due to the former being released first, and so Hibernate will attempt to stick
 * ZonedDateTime instances into the database as blobs. This class will convert
 * the new classes so that Postgres can store them as type timestamp.
 *
 * Created by arodman on 4/25/16.
 */
@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime attribute) {
        return attribute == null ? null : Timestamp.from(attribute.toInstant());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp dbData) {
        return dbData == null ? null : ZonedDateTime.ofInstant(dbData.toInstant(), ZoneId.systemDefault());
    }
}
