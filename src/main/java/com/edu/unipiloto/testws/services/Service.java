/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.unipiloto.testws.services;

import com.edu.unipiloto.testws.person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author USER
 */
@Path("/service")
public class Service {

    private static Map<Integer, person> persons = new HashMap<>();

    static {
        int count = 0;
        for (int i = 0; i < 11; i++) {
            person pers = new person();
            int id = i + 1;
            count++;
            int edad = new Random().nextInt(20 + id);
            double salary = (edad * 1300000) / 3;
            pers.setId(id);
            pers.setFullName("mi persona " + id);
            pers.setAge(edad);
            pers.setSalary(salary);
            persons.put(id, pers);
        }
    }

    @GET
    @Path("/getPersonByIdXML/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public person getPersonByIdXML(@PathParam("id") int id) {
        return persons.get(id);
    }

    @GET
    @Path("/getPersonByIdJSON/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public person getPersonByIdJSON(@PathParam("id") int id) {
        return persons.get(id);
    }

    @GET
    @Path("/getAllPersonsInXML")
    @Produces(MediaType.APPLICATION_XML)
    public List<person> getAllPersonsInXML() {
        return new ArrayList<person>(persons.values());
    }

    @GET
    @Path("/getAllPersonsInJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public List<person> getAllPersonsInJSON() {
        return new ArrayList<person>(persons.values());
    }

    @POST
    @Path("/addPerson")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<person> addPerson(List<person> pr) {

        for (person pers : pr) {
            persons.put(pers.getId(), pers);
        }
        return new ArrayList<person>(persons.values());
    }

    @POST
    @Path("/addPersonInJSON")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public person addPersonInJSON(person pers) {
        System.out.println(pers.getId());
        persons.put(new Integer(pers.getId()), pers);
        return pers;
    }

    @GET
    @Path("/getAverageInXML")
    @Produces(MediaType.APPLICATION_XML)
    public double getAverageInXML() {
        double average = 0;
        int count = 0;
        ArrayList<person> p = new ArrayList<person>(persons.values());
        for (person pr : p) {
            average = average + pr.getSalary();
            count++;
        }
        average = average / count;
        return average;
    }

    @GET
    @Path("/getSumSalariesInJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public double getSumSalariesInJSON() {
        double suma = 0;
        ArrayList<person> p = new ArrayList<person>(persons.values());
        for (person pr : p) {
            suma = suma + pr.getSalary();
        }
        return suma;
    }

}
