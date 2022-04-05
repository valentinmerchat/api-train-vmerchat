package org.miage.apitrain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationInput {

   @NotNull
   @NotBlank
   private String idUtil;

   @NotNull
   @NotBlank
   private String idTrajet;


   @NotNull
   private boolean siegeFenetre;


}
