// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.springsource.petclinic.domain;

import java.lang.String;

privileged aspect Vet_Roo_ToString {
    
    public String Vet.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("FirstName: ").append(getFirstName()).append(", ");
        sb.append("LastName: ").append(getLastName()).append(", ");
        sb.append("Address: ").append(getAddress()).append(", ");
        sb.append("City: ").append(getCity()).append(", ");
        sb.append("Telephone: ").append(getTelephone()).append(", ");
        sb.append("HomePage: ").append(getHomePage()).append(", ");
        sb.append("Email: ").append(getEmail()).append(", ");
        sb.append("BirthDay: ").append(getBirthDay()).append(", ");
        sb.append("EmployedSince: ").append(getEmployedSince()).append(", ");
        sb.append("Specialty: ").append(getSpecialty());
        return sb.toString();
    }
    
}
