package com.kosta.kostapay;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Pay {

    @Id
    private String userId;
    private float payBalance;

}
