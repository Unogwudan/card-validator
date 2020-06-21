package com.unogwudan.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class BaseModel<T> {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(hidden = true)
	private LocalDateTime createdAt = LocalDateTime.now();

    @ApiModelProperty(hidden = true)
    private LocalDateTime modifiedAt = LocalDateTime.now();

}
