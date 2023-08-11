package com.jr.example.api.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Message {
    /** Message Text */
    private String text;

    /** Timestamp from Server  */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mmZ")
    private ZonedDateTime serverTime;
}
