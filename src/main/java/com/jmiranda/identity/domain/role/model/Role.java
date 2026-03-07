package com.jmiranda.identity.domain.role.model;

import com.jmiranda.identity.domain.shared.valueobject.StateId;

import java.time.Clock;
import java.time.Instant;

public class Role {

     private final RoleId id;
     private final Code code;
     private final String name;
     private final StateId stateId;
     private final Instant createdAt;

     public Role(RoleId id, Code code, String name , StateId stateId, Instant createdAt) {
          this.id = id;
          this.code = code;
          this.name = name;
          this.stateId = stateId;
          this.createdAt = createdAt;
     }

     public RoleId getId() {
          return id;
     }

     public String getName() {
          return name;
     }
   public Instant getCreatedAt() {
       return createdAt;
   }

   public Code getCode() {
       return code;
   }

   public StateId getStateId() {
       return stateId;
   }

     public static Role restore(RoleId id, Code code, String name, StateId stateId, Instant createdAt) {
          return new Role(id, code, name, stateId, createdAt);
     }

     public static Role create(String name, Code code, StateId stateId, Clock systemClock) {
          return new Role(RoleId.generate(), code, name, stateId, systemClock.instant());
     }
}
