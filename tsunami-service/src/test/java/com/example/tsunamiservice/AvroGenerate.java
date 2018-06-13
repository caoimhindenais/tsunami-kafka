package com.example.tsunamiservice;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.avro.AvroFactory;
import com.fasterxml.jackson.dataformat.avro.schema.AvroSchemaGenerator;
import org.junit.Test;


public class AvroGenerate {

    @Test
    public void test() throws JsonMappingException {
        ObjectMapper mapper = new ObjectMapper(new AvroFactory());
        AvroSchemaGenerator gen = new AvroSchemaGenerator();
        mapper.acceptJsonFormatVisitor(Tsnunami.class, gen);
        com.fasterxml.jackson.dataformat.avro.AvroSchema schemaWrapper = gen.getGeneratedSchema();
        org.apache.avro.Schema avroSchema = schemaWrapper.getAvroSchema();
        String asJson = avroSchema.toString(true);
        System.out.println("asJson = " + asJson);
    }
}
