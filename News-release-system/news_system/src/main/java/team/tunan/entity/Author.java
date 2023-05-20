package team.tunan.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Tunan
 * @since 2023-05-20
 */
@Getter
@Setter
@ApiModel(value = "Author对象", description = "")
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String responsibility;

    private String phone;

    private String email;

}
