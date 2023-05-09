package br.com.webcko.academia.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "personais", schema = "public")
public class Personal extends Usuario {

    @Getter @Setter
    @Column(name = "admin", nullable = false)
    private boolean admin;
  
    @PrePersist
      private void prePersiste(){
          this.admin = false;
      }
}
