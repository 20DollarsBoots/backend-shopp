package br.com.brn.shopp.model;

import java.io.Serializable;

public interface AbstractEntity extends Serializable {

    public Long getId();

    public void setId(Long id);
}
