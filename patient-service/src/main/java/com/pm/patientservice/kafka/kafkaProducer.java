package com.pm.patientservice.kafka;

import com.pm.patientservice.model.Patient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;
import patient.events.PatientEvents;

//this class is responsible for sending events to kafka topics
@Service
public class kafkaProducer {

    private final KafkaTemplate<String, byte[]> kafkaTemplate; //telling kafka we are going to send key value (string,byte array)
    public kafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(Patient patient) {

        PatientEvents event=PatientEvents.newBuilder()
                .setPatientId(patient.getId().toString())
                .setName(patient.getName())
                .setEmail(patient.getEmail())
                .setEventType("PATIENT_CREATED")
                .build();

    }
}
