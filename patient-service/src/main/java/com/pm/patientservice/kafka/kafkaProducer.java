package com.pm.patientservice.kafka;

import com.pm.patientservice.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;
import patient.events.PatientEvents;

//this class is responsible for sending events to kafka topics
@Service
public class kafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(kafkaProducer.class);
    private final KafkaTemplate<String, byte[]> kafkaTemplate; //telling kafka we are going to send key value (string,byte array)
    public kafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //send this event when patient is created
    public void sendEvent(Patient patient) {

        PatientEvents event=PatientEvents.newBuilder()
                .setPatientId(patient.getId().toString())
                .setName(patient.getName())
                .setEmail(patient.getEmail())
                .setEventType("PATIENT_CREATED")
                .build();

        try{
            kafkaTemplate.send("patient", event.toByteArray()); //this byte array is to be consumed to kafka consumer
        }
        catch (Exception e){
            log.error("Error while sending PatientCreated event: {}",event);
        }
    }
}
