// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.springsource.petclinic.domain;

import java.lang.String;

privileged aspect Visit_Roo_ToString {
    
    public String Visit.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PetName: ").append(getPetName()).append(", ");
        sb.append("PetId: ").append(getPetId()).append(", ");
        sb.append("VetName: ").append(getVetName()).append(", ");
        sb.append("VetId: ").append(getVetId()).append(", ");
        sb.append("Description: ").append(getDescription()).append(", ");
        sb.append("VisitDate: ").append(getVisitDate()).append(", ");
        sb.append("Pet: ").append(getPet()).append(", ");
        sb.append("Vet: ").append(getVet());
        return sb.toString();
    }
    
}
