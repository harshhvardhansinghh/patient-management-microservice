package com.pm.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvents;
import patient.events.PatientEvents;


@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "patient", groupId = "analytics-server")
   //any events that are sent to -- patient topic -- are going to be consumed (read) by this method
    public void consumeEvent(byte[] event) {

        //convert byte array to patient event

        try {
            PatientEvents patientEvent=PatientEvents.parseFrom(event);
            //perform any business related to analytics here

            log.info("Received Patient Event:  [PatientId={}, PatientName={},"
                    + "PatientEmail={}]", patientEvent.getPatientId(), patientEvent.getName(), patientEvent.getEmail());
        } catch (InvalidProtocolBufferException e) {
            log.error("Error deserializing event {}" , e.getMessage());
        }

    }
}
