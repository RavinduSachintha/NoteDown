package com.rs.notedown.models;

import java.util.Date;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@MappedSuperclass
public class Base {
  @CreationTimestamp @Temporal(TemporalType.TIMESTAMP) private Date createdAt;
  @UpdateTimestamp @Temporal(TemporalType.TIMESTAMP) private Date updatedAt;
}
