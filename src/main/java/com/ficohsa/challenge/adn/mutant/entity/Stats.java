package com.ficohsa.challenge.adn.mutant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Stats")
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "dna")
    private String adnMutantRequest;

    @Column
    private Integer mutant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdnMutantRequest() {
        return adnMutantRequest;
    }

    public void setAdnMutantRequest(String adnMutantRequest) {
        this.adnMutantRequest = adnMutantRequest;
    }

    public Integer getMutant() {
        return mutant;
    }

    public void setMutant(Integer mutant) {
        this.mutant = mutant;
    }
}
