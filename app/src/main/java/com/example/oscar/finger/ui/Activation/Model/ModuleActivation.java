package com.example.oscar.finger.ui.Activation.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Oscar on 29/12/2017.
 */

@ToString
public class ModuleActivation {
  @Getter @Setter
  private String name;

  public ModuleActivation(String name) {
    this.name = name;
  }
}
